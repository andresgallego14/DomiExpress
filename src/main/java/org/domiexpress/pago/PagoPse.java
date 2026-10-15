package org.domiexpress.pago;

public class PagoPse implements MetodoPago {
    private final String banco;

    public PagoPse (String banco){
        this.banco = banco;
    }
    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + "por PSE del banco" + banco);
    }
}
