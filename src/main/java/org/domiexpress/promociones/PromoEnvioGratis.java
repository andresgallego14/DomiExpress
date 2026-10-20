package org.domiexpress.promociones;

import org.domiexpress.pedido.Pedido;

public class PromoEnvioGratis implements Promocion{
    @Override
    public double calcularDescuento(Pedido pedido, double costoEnvio){
        return costoEnvio;
    }
    @Override
    public String describir(){
        return "Envio gratis";
    }
}
