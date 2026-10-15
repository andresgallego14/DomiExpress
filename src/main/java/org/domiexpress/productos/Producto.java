package org.domiexpress.productos;

public abstract class Producto {
    private final String nombre;
    private final double precioBase;

    protected Producto(String nombre, double precioBase){
        if (precioBase < 0) {
            throw new IllegalArgumentException("el precio no puede ser negativo");

        }
        this.nombre = nombre;
        this.precioBase = precioBase;

    }

    public String getNombre(){
        return nombre;
    }
    protected double getPrecioBase(){
        return precioBase;
    }
    public abstract double calcularPrecio();
}
