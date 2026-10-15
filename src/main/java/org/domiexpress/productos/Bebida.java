package org.domiexpress.productos;

public class Bebida extends Producto {
    private final TamanoBebida tamano;

    public Bebida (String nombre, double precioBase, TamanoBebida tamano){
        super(nombre, precioBase);
        this.tamano = tamano;
    }
    @Override
    public double calcularPrecio(){
        return tamano.aplicarRecargo(getPrecioBase());

    }
}
