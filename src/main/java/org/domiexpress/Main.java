package org.domiexpress;

import org.domiexpress.productos.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Producto hamburguesa = new Comida( "Hamburguesa", 10000);
        Producto gaseosa = new Bebida("Gaseosa", 5000, TamanoBebida.GRANDE);
        Producto combo = new Combo("Comba Clasico", List.of(hamburguesa, gaseosa), 0.15);

        System.out.println(hamburguesa.getNombre() + ":" + hamburguesa.calcularPrecio());
        System.out.println(gaseosa.getNombre() + ":" + gaseosa.calcularPrecio());
        System.out.println(combo.getNombre() + ":" + combo.calcularPrecio());
    }
}