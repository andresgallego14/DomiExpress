package org.domiexpress.productos;

public class Comida extends Producto{

    public Comida(String nombre, double precioBase){
        super(nombre, precioBase);

    }
    @Override
    public double calcularPrecio(){
        return getPrecioBase();
    }
}
