package org.domiexpress.pedido;

import org.domiexpress.productos.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final String id;
    private final List<Producto> productos = new ArrayList<>();
    private EstadoPedido estado = EstadoPedido.CREADO;

    public Pedido (String id){
        this.id = id;
    }
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    public double calcularTotal(){
        double total = 0;
        for (Producto producto : productos){
            total += producto.calcularPrecio();
        }
        return total;
    }

    public void cambiarEstado(EstadoPedido nuevoEstado){
        this.estado = nuevoEstado;
    }
    public String getId(){
        return id;
    }
    public EstadoPedido getEstado(){
        return estado;
    }
    public List<Producto> getProductos() {
        return List.copyOf(productos);
    }
}


