package org.domiexpress;

import org.domiexpress.pago.*;
import org.domiexpress.pedido.Pedido;
import org.domiexpress.productos.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Producto hamburguesa = new Comida("Hamburguesa", 10000);
        Producto gaseosa = new Bebida("Gaseosa", 5000, TamanoBebida.GRANDE);
        Producto combo = new Combo("Combo Clásico", List.of(hamburguesa, gaseosa), 0.15);

        Pedido pedido = new Pedido("P-001");
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(combo);

        double total = pedido.calcularTotal();

        // Primer intento: pago con tarjeta
        procesarPago(new PagoTarjeta("4321"), total);

        // Cambiamos de medio de pago sin tocar Pedido ni procesarPago
        procesarPago(new PagoNequi("3001234567"), total);
    }

    // Recibe la INTERFAZ, no una clase concreta (polimorfismo + OCP).
    private static void procesarPago(MetodoPago metodo, double monto) {
        metodo.pagar(monto);
    }
}