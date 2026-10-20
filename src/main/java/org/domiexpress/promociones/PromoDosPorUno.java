package org.domiexpress.promociones;

import org.domiexpress.pedido.Pedido;
import org.domiexpress.productos.Producto;

public class PromoDosPorUno implements Promocion {
    @Override
    public double calcularDescuento(Pedido pedido, double costoEnvio){
        if (pedido.getProductos().size() < 2){
            return 0;
        }
        double masBarato = Double.MAX_VALUE;
        for (Producto producto : pedido.getProductos()){
            masBarato = Math.min(masBarato, producto.calcularPrecio());
        }
        return masBarato;
    }
    @Override
    public String describir(){
        return "2x1: el producto mas barato es gratis";

    }
}
