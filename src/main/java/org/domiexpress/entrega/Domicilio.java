package org.domiexpress.entrega;


public class Domicilio implements  TipoEntrega {
    private static final double COSTO_ENVIO = 4000;
    private final String direccion;

    public Domicilio(String direccion) {
        if(direccion == null || direccion.isBlank()){
            throw new IllegalArgumentException("El domicilio requiere direccion");
        }
        this.direccion = direccion;

    }

    @Override
    public double calcularCostoEnvio() {
        return COSTO_ENVIO;
    }

    @Override
    public String describir() {
        return "Domicilio a " + direccion;
    }
}
