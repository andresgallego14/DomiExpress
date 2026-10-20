package org.domiexpress.promociones;

import org.domiexpress.pedido.Pedido;

public interface Promocion {
    double calcularDescuento(Pedido pedido, double costoEnvio);
    String describir();


}
