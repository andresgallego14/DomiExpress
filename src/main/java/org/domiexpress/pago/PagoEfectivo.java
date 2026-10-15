package org.domiexpress.pago;

public class PagoEfectivo implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.println("pagando $" + monto + "en efectivo al recibir el pedido");
    }
}
