package org.domiexpress.pago;

public class PagoTarjeta implements MetodoPago{

    private final String ultimos4Digitos;

    public PagoTarjeta(String ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " con tarjeta terminada en " + ultimos4Digitos);

    }
}
