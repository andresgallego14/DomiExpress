package org.domiexpress.usuarios;

import org.domiexpress.pedido.EstadoPedido;
import org.domiexpress.pedido.Pedido;

// Restaurante SOLO implementa PuedePrepararPedidos.
public class Restaurante implements PuedePrepararPedidos {

    private final String nombre;

    public Restaurante(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void preparar(Pedido pedido) {
        pedido.cambiarEstado(EstadoPedido.EN_CAMINO);
        System.out.println("Restaurante " + nombre + " preparó el pedido " + pedido.getId());
    }
}