package org.domiexpress.notificaciones;

public class NotificadorEmail implements Notificador {
    @Override
    public void notificar(String destinatario, String mensaje) {
        System.out.println("[EMAIL a " + destinatario + "] " + mensaje);
    }
}