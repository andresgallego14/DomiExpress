package org.domiexpress.pago;

public class PagoNequi implements MetodoPago {
    private final String celular;

    public PagoNequi(String celular){
        this.celular = celular;
    }
    @Override
    public void pagar(double monto){
        System.out.println("Pagando $" + monto + " De nequi del celular "  + celular);
    }
}
