package org.domiexpress.entrega;

public class RecogerEnTienda implements TipoEntrega{
    private final String nombreTienda;

    public RecogerEnTienda(String nombreTienda){
        this.nombreTienda = nombreTienda;
    }
//retorna 0 por que no tiene envio
    @Override
    public double calcularCostoEnvio() {
        return 0;
    }

    @Override
    public String describir() {
        return "";
    }
}
