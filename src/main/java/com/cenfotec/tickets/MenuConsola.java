package com.cenfotec.tickets;

import java.util.Scanner;

/**
 * Menus de usuario y administrador en linea de comandos.
 * Lee y valida la entrada y delega el trabajo a SistemaTickets.
 */
public class MenuConsola {

    // Colores ANSI. Si la consola no los soporta, poner "" en las cuatro.
    private static final String RESET = "\u001B[0m";
    private static final String TITULO = "\u001B[1;36m";
    private static final String EXITO = "\u001B[32m";
    private static final String ERROR = "\u001B[31m";

    /** Valor que devuelve leerEntero() cuando el texto no es un numero. */
    private static final int ENTRADA_INVALIDA = -1;

    private static final String MENSAJE_OPCION_INVALIDA = "Opcion invalida. Elija 1, 2 o 0.";

    private final Scanner scanner;
    private final SistemaTickets sistema;

    /** Crea el menu asociado al sistema de tickets. */
    public MenuConsola(SistemaTickets sistema) {
        this.scanner = new Scanner(System.in);
        this.sistema = sistema;
    }

    /** Menu principal. Termina cuando el usuario elige salir. */
    public void iniciar() {
        int opcion;
        do {
            imprimirTitulo("SISTEMA DE GESTION DE TICKETS");
            System.out.println("1. Menu de usuario");
            System.out.println("2. Menu de administrador");
            System.out.println("0. Salir");
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuAdministrador();
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    imprimirError(MENSAJE_OPCION_INVALIDA);
            }
        } while (opcion != 0);

        scanner.close();
    }

    /** Menu con las opciones del usuario: crear y buscar tickets. */
    private void menuUsuario() {
        int opcion;
        do {
            imprimirTitulo("MENU DE USUARIO");
            System.out.println("1. Crear un ticket");
            System.out.println("2. Buscar un ticket resuelto");
            System.out.println("0. Volver");
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    crearTicket();
                    break;
                case 2:
                    buscarTicket();
                    break;
                case 0:
                    break;
                default:
                    imprimirError(MENSAJE_OPCION_INVALIDA);
            }
        } while (opcion != 0);
    }

    /** Pide nombre y descripcion, crea el ticket y muestra su numero. */
    private void crearTicket() {
        String nombre = leerTextoNoVacio("Ingrese su nombre completo: ");
        String descripcion = leerTextoNoVacio("Describa el problema o solicitud: ");

        Ticket ticket = sistema.crearTicket(descripcion, nombre);
        imprimirExito("Ticket creado. Guarde su numero de ticket: " + ticket.getId());
        System.out.println(ticket);
    }

    /** Busca un ticket resuelto por id; si no esta, indica si esta pendiente o no existe. */
    private void buscarTicket() {
        System.out.print("Ingrese el numero (id) del ticket: ");
        int id = leerEntero();
        if (id < 1) {
            imprimirError("El id debe ser un numero entero positivo.");
            return;
        }

        Ticket ticket = sistema.buscarTicketResuelto(id);
        if (ticket != null) {
            imprimirExito("Ticket resuelto encontrado:");
            System.out.println(ticket);
        } else if (sistema.estaPendiente(id)) {
            System.out.println("El ticket #" + id + " esta pendiente de resolucion.");
        } else {
            imprimirError("No existe ningun ticket con el id " + id + ".");
        }
    }

    /** Menu con las opciones del administrador: ver y resolver el ticket al frente. */
    private void menuAdministrador() {
        int opcion;
        do {
            imprimirTitulo("MENU DE ADMINISTRADOR");
            System.out.println("1. Ver el ticket al frente de la cola");
            System.out.println("2. Resolver el ticket al frente de la cola");
            System.out.println("0. Volver");
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    verTicketAlFrente();
                    break;
                case 2:
                    resolverTicket();
                    break;
                case 0:
                    break;
                default:
                    imprimirError(MENSAJE_OPCION_INVALIDA);
            }
        } while (opcion != 0);
    }

    /** Muestra el ticket al frente de la cola sin sacarlo. */
    private void verTicketAlFrente() {
        Ticket ticket = sistema.verTicketAlFrente();
        if (ticket == null) {
            imprimirError("No hay tickets pendientes en la cola.");
            return;
        }
        System.out.println("Ticket al frente de la cola (pendientes: "
                + sistema.cantidadPendientes() + "):");
        System.out.println(ticket);
    }

    /** Resuelve el ticket al frente y muestra el resultado. */
    private void resolverTicket() {
        Ticket ticket = sistema.resolverTicketAlFrente();
        if (ticket == null) {
            imprimirError("No hay tickets pendientes por resolver.");
            return;
        }
        imprimirExito("Ticket resuelto:");
        System.out.println(ticket);
    }

    /** Muestra el prompt y lee la opcion del menu. */
    private int leerOpcion() {
        System.out.print("Seleccione una opcion: ");
        return leerEntero();
    }

    /** Lee un entero; devuelve ENTRADA_INVALIDA si el texto no es un numero. */
    private int leerEntero() {
        String linea = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return ENTRADA_INVALIDA;
        }
    }

    /** Pide un texto y lo repite mientras quede vacio. */
    private String leerTextoNoVacio(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                imprimirError("Este dato es obligatorio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    /** Imprime un titulo de menu con color. */
    private void imprimirTitulo(String texto) {
        System.out.println("\n" + TITULO + texto + RESET);
    }

    /** Imprime un mensaje de exito con color. */
    private void imprimirExito(String texto) {
        System.out.println("\n" + EXITO + texto + RESET);
    }

    /** Imprime un mensaje de error con color. */
    private void imprimirError(String texto) {
        System.out.println(ERROR + texto + RESET);
    }
}
