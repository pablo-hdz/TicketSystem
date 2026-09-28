package com.cenfotec.tickets;

/**
 * Punto de entrada del programa.
 * Solo crea el sistema y el menu; toda la logica esta en las otras clases.
 */
public class Main {

    /** Crea el sistema y el menu, y arranca la aplicacion. */
    public static void main(String[] args) {
        // 1. Crear el sistema (cola de pendientes y lista de resueltos vacias).
        SistemaTickets sistema = new SistemaTickets();

        // 2. Crear el menu de consola y arrancarlo.
        MenuConsola menu = new MenuConsola(sistema);
        menu.iniciar();
    }
}