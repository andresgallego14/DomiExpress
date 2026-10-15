package org.domiexpress.productos;

import java.util.List;

public class Combo extends Producto{

    private final List<Producto> items;
    private final double porcentajeDescuento;

    public Combo (String nombre, List<Producto> items, double porcentajeDescuento){
        super(nombre, 0);

        if (porcentajeDescuento < 0 || porcentajeDescuento > 1){
            throw new IllegalArgumentException("el descuento debe estar entre 0 y 1");
        }
        this.items = List.copyOf(items);
        this.porcentajeDescuento = porcentajeDescuento;

    }

    @Override
    public  double calcularPrecio(){
        double suma = 0;
        for (Producto item : items){
            suma += item.calcularPrecio();
        }
        return suma * (1 - porcentajeDescuento);
    }

}
