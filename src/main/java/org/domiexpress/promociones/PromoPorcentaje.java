package org.domiexpress.promociones;

import org.domiexpress.pedido.Pedido;

public class PromoPorcentaje implements Promocion {
    private final double porcentaje;

    public PromoPorcentaje(double porcentaje){
        if (porcentaje <= 0 || porcentaje > 1 ){
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 1");
        }
        this.porcentaje = porcentaje;
    }
    @Override
    public double calcularDescuento(Pedido pedido, double costoEnvio) {
        return pedido.calcularTotal() * porcentaje;
    }
    @Override
    public String describir(){
        return "Descuento del " + (int) (porcentaje * 100) + "%" ;
    }
}