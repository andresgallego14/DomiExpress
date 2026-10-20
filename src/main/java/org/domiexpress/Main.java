package org.domiexpress;

import org.domiexpress.entrega.*;
import org.domiexpress.facturacion.GeneradorFactura;
import org.domiexpress.notificaciones.*;
import org.domiexpress.pago.*;
import org.domiexpress.pedido.Pedido;
import org.domiexpress.persistencia.*;
import org.domiexpress.productos.*;
import org.domiexpress.promociones.*;
import org.domiexpress.servicio.ServicioPedidos;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Productos de tipos distintos
        Producto hamburguesa = new Comida("Hamburguesa", 10000);
        Producto papas = new Comida("Papas", 4000);
        Producto gaseosa = new Bebida("Gaseosa", 5000, TamanoBebida.GRANDE);
        Producto combo = new Combo("Combo Clasico", List.of(hamburguesa, gaseosa), 0.15);

        // 2. DIP: aqui se ELIGE la infraestructura; el servicio solo ve interfaces.
        //    Cambiar a SMS o a otra base de datos es cambiar UNA linea.
        RepositorioPedidos repositorio = new RepositorioEnMemoria();
        Notificador notificador = new NotificadorEmail();
        ServicioPedidos servicio = new ServicioPedidos(repositorio, notificador, new GeneradorFactura());

        // ===== Pedido A: Domicilio + Nequi + dos promociones =====
        Pedido pedidoA = new Pedido("P-001");
        pedidoA.agregarProducto(combo);
        pedidoA.agregarProducto(papas);

        System.out.println("\n##### PEDIDO A #####");
        servicio.procesar(pedidoA,
                new Domicilio("Calle 10 # 20-30"),
                new PagoNequi("3001234567"),
                List.of(new PromoPorcentaje(0.10), new PromoEnvioGratis()),
                "cliente@correo.com");

        // ===== Pedido B: Recoger en tienda + OTRO medio de pago + 2x1 =====
        // Mismo servicio, mismo codigo: solo cambian los objetos que le pasamos.
        Pedido pedidoB = new Pedido("P-002");
        pedidoB.agregarProducto(hamburguesa);
        pedidoB.agregarProducto(gaseosa);

        System.out.println("\n##### PEDIDO B #####");
        servicio.procesar(pedidoB,
                new RecogerEnTienda("Sede Centro"),
                new PagoDaviplata("3109876543"),   // medio nuevo, servicio intacto
                List.of(new PromoDosPorUno()),
                "3109876543");

        // ===== Persistencia =====
        System.out.println("\nPedidos guardados: " + repositorio.listarTodos().size());
    }
}