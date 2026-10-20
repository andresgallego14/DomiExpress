package org.domiexpress.usuarios;

import org.domiexpress.pedido.EstadoPedido;
import org.domiexpress.pedido.Pedido;

// Repartidor SOLO implementa PuedeEntregar.
public class Repartidor implements PuedeEntregar {

    private final String nombre;

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void entregar(Pedido pedido) {
        pedido.cambiarEstado(EstadoPedido.ENTREGADO);
        System.out.println("Repartidor " + nombre + " entregó el pedido " + pedido.getId());
    }
}