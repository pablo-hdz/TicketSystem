package com.cenfotec.tickets;

/**
 * Logica de negocio: coordina la cola de pendientes y la lista de resueltos.
 * No imprime ni lee datos; eso le corresponde al menu.
 */
public class SistemaTickets {

    private final ColaPrioridad pendientes;
    private final ListaEnlazadaSimple resueltos;

    /** Crea el sistema con la cola y la lista vacias. */
    public SistemaTickets() {
        this.pendientes = new ColaPrioridad();
        this.resueltos = new ListaEnlazadaSimple();
    }

    /** Crea un ticket y lo inserta en la cola de pendientes. */
    public Ticket crearTicket(String descripcion, String nombreCompleto) {
        Ticket nuevoTicket = new Ticket(descripcion, nombreCompleto);
        pendientes.insertar(nuevoTicket);
        return nuevoTicket;
    }

    /** Busca un ticket en la lista de resueltos. Retorna null si no esta. */
    public Ticket buscarTicketResuelto(int id) {
        return resueltos.buscarPorId(id);
    }

    /** Indica si el ticket existe y todavia esta esperando en la cola. */
    public boolean estaPendiente(int id) {
        return pendientes.contiene(id);
    }

    /** Retorna el ticket al frente de la cola, o null si no hay pendientes. */
    public Ticket verTicketAlFrente() {
        return pendientes.verFrente();
    }

    /**
     * Saca el ticket del frente, le establece la fechaResolucion y lo pasa
     * a la lista de resueltos. Retorna null si no hay pendientes.
     */
    public Ticket resolverTicketAlFrente() {
        Ticket ticket = pendientes.remover();
        if (ticket != null) {
            ticket.resolver();
            resueltos.insertarFinal(ticket);
        }
        return ticket;
    }

    /** Retorna cuantos tickets siguen pendientes. */
    public int cantidadPendientes() {
        return pendientes.getTamanio();
    }
}
