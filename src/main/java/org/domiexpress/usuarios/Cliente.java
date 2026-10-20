package org.domiexpress.usuarios;

import org.domiexpress.pedido.Pedido;
import org.domiexpress.productos.Producto;

public class Cliente implements PuedeOrdenar {
    private final String nombre;
    private final Pedido pedidoActual;

    public Cliente(String nombre, Pedido pedidoActual) {
        this.nombre = nombre;
        this.pedidoActual = pedidoActual;
    }
    public String getNombre(){
        return nombre;
    }
    @Override
    public void agregarAlPedido(Producto producto){
        pedidoActual.agregarProducto(producto);
    }
}

