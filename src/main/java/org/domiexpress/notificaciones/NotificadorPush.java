package org.domiexpress.notificaciones;

public class NotificadorPush implements Notificador {
    @Override
    public void notificar(String destinatario, String mensaje) {
        System.out.println("[PUSH a " + destinatario + "] " + mensaje);
    }
}