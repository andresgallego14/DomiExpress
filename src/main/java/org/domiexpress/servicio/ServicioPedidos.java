package org.domiexpress.servicio;

import org.domiexpress.entrega.TipoEntrega;
import org.domiexpress.facturacion.GeneradorFactura;
import org.domiexpress.notificaciones.Notificador;
import org.domiexpress.pago.MetodoPago;
import org.domiexpress.pedido.EstadoPedido;
import org.domiexpress.pedido.Pedido;
import org.domiexpress.persistencia.RepositorioPedidos;
import org.domiexpress.promociones.Promocion;

import java.util.List;

public class ServicioPedidos {

    private final RepositorioPedidos repositorio;
    private final Notificador notificador;
    private final GeneradorFactura generadorFactura;

    public ServicioPedidos(RepositorioPedidos repositorio, Notificador notificador, GeneradorFactura generadorFactura) {
        this.repositorio = repositorio;
        this.notificador = notificador;
        this.generadorFactura = generadorFactura;
    }

    public void procesar(Pedido pedido, TipoEntrega entrega, MetodoPago metodoPago, List<Promocion> promociones,
                         String destinatario) {

        // 1. Calcular lo que se debe pagar (productos + envio - descuentos)
        double costoEnvio = entrega.calcularCostoEnvio();
        double descuentos = 0;
        for (Promocion promo : promociones) {
            descuentos += promo.calcularDescuento(pedido, costoEnvio);
        }
        double totalAPagar = pedido.calcularTotal() + costoEnvio - descuentos;

        // 2. Cobrar con el medio elegido (no sabemos cual es)
        metodoPago.pagar(totalAPagar);
        pedido.cambiarEstado(EstadoPedido.PAGADO);

        // 3. Guardar
        repositorio.guardar(pedido);

        // 4. Facturar y notificar
        System.out.println(generadorFactura.generar(pedido, entrega, promociones));
        notificador.notificar(destinatario,
                "Tu pedido " + pedido.getId() + " esta " + pedido.getEstado());
    }
}