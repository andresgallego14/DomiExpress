package org.domiexpress.pago;


public class PagoDaviplata implements MetodoPago {

    private final String celular;

    public PagoDaviplata(String celular) {
        this.celular = celular;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " con Daviplata del celular " + celular);
    }
}