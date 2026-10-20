package org.domiexpress;

import org.domiexpress.entrega.*;
import org.domiexpress.facturacion.GeneradorFactura;
import org.domiexpress.pago.*;
import org.domiexpress.pedido.Pedido;
import org.domiexpress.productos.*;
import org.domiexpress.promociones.*;
import org.domiexpress.notificaciones.*;
import org.domiexpress.persistencia.*;
import org.domiexpress.servicio.ServicioPedidos;
import org.domiexpress.usuarios.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Producto hamburguesa = new Comida("Hamburguesa", 10000);
        Producto gaseosa = new Bebida("Gaseosa", 5000, TamanoBebida.GRANDE);
        Producto combo = new Combo("Combo Clásico", List.of(hamburguesa, gaseosa), 0.15);

        Pedido pedido = new Pedido("P-001");
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(combo);

        double total = pedido.calcularTotal();

        // Pagos
        procesarPago(new PagoTarjeta("4321"), total);
        procesarPago(new PagoNequi("3001234567"), total);

        // Entregas
        mostrarEntrega(new Domicilio("Calle 10 # 20-30"), total);
        mostrarEntrega(new RecogerEnTienda("Sede Centro"), total);

        // Promociones: todas se usan igual, a través de la interfaz
        TipoEntrega entrega = new Domicilio("Calle 10 # 20-30");
        double costoEnvio = entrega.calcularCostoEnvio();



        List<Promocion> promociones = List.of(
                new PromoDosPorUno(),
                new PromoPorcentaje(0.10),
                new PromoEnvioGratis()
        );

        for (Promocion promo : promociones) {
            System.out.println(promo.describir() + " -> descuento: "
                    + promo.calcularDescuento(pedido, costoEnvio));
        }
        // Persistencia: el Main solo conoce la interfaz
        RepositorioPedidos repositorio = new RepositorioEnMemoria();
        repositorio.guardar(pedido);
        System.out.println("Pedidos guardados: " + repositorio.listarTodos().size());

        // Notificación: cambiar de canal es cambiar una línea
        Notificador notificador = new NotificadorEmail();
        notificador.notificar("cliente@correo.com", "Tu pedido " + pedido.getId() + " fue creado");

        notificador = new NotificadorSMS();
        notificador.notificar("3001234567", "Tu pedido " + pedido.getId() + " va en camino");

        // Roles: cada uno se usa por su capacidad, no por su clase
        Pedido pedidoRol = new Pedido("P-002");
        PuedeOrdenar cliente = new Cliente("Ana", pedidoRol);
        cliente.agregarAlPedido(hamburguesa);

        PuedePrepararPedidos restaurante = new Restaurante("La Parrilla");
        restaurante.preparar(pedidoRol);

        PuedeEntregar repartidor = new Repartidor("Carlos");
        repartidor.entregar(pedidoRol);

        System.out.println("Estado final: " + pedidoRol.getEstado());
        // Servicio: todo conectado por interfaces
        ServicioPedidos servicio = new ServicioPedidos(
                new RepositorioEnMemoria(),
                new NotificadorEmail(),
                new GeneradorFactura());

        servicio.procesar(pedido,
                new Domicilio("Calle 10 # 20-30"),
                new PagoNequi("3001234567"),
                List.of(new PromoPorcentaje(0.10)),
                "cliente@correo.com");
    }

    private static void procesarPago(MetodoPago metodo, double monto) {
        metodo.pagar(monto);
    }

    private static void mostrarEntrega(TipoEntrega entrega, double subtotal) {
        System.out.println(entrega.describir()
                + " | Envío: " + entrega.calcularCostoEnvio()
                + " | Total: " + (subtotal + entrega.calcularCostoEnvio()));
    }

}