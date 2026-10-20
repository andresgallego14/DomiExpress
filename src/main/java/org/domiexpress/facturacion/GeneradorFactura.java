package org.domiexpress.facturacion;

import org.domiexpress.entrega.TipoEntrega;
import org.domiexpress.pedido.Pedido;
import org.domiexpress.productos.Producto;
import org.domiexpress.promociones.Promocion;

import java.util.List;
public class GeneradorFactura {

    public String generar(Pedido pedido, TipoEntrega entrega, List<Promocion> promociones) {
        StringBuilder sb = new StringBuilder();
        sb.append("===== FACTURA ").append(pedido.getId()).append(" =====\n");

        for (Producto producto : pedido.getProductos()) {
            sb.append(producto.getNombre()).append(": $")
                    .append(producto.calcularPrecio()).append("\n");
        }

        double subtotal = pedido.calcularTotal();
        double costoEnvio = entrega.calcularCostoEnvio();
        sb.append("Subtotal: $").append(subtotal).append("\n");
        sb.append("Entrega: ").append(entrega.describir())
                .append(" ($").append(costoEnvio).append(")\n");

        double descuentos = 0;
        for (Promocion promo : promociones) {
            double d = promo.calcularDescuento(pedido, costoEnvio);
            descuentos += d;
            sb.append("Promo ").append(promo.describir())
                    .append(": -$").append(d).append("\n");
        }

        sb.append("TOTAL A PAGAR: $").append(subtotal + costoEnvio - descuentos);
        return sb.toString();
    }
}