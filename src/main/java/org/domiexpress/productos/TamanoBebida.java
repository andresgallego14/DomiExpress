package org.domiexpress.productos;

public enum TamanoBebida {

        PEQUENO(0.0),
        MEDIANO(0.10),
        GRANDE(0.20);

        private final double recargo;

        TamanoBebida(double recargo) {
                this.recargo = recargo;
        }

        public double aplicarRecargo(double precio) {
                return precio * (1 + recargo);
        }
}







