package com.cenfotec.tickets;

/**
 * Nodo autorreferenciado: guarda un ticket y una referencia al siguiente
 * nodo. Lo usan tanto la cola de prioridad como la lista enlazada simple.
 */
public class NodoTicket {

    private final Ticket ticket;
    private NodoTicket siguiente;

    /** Crea un nodo con el ticket dado; el siguiente empieza en null. */
    public NodoTicket(Ticket ticket) {
        this.ticket = ticket;
        this.siguiente = null;
    }

    /** Retorna el ticket almacenado en este nodo. */
    public Ticket getTicket() {
        return ticket;
    }

    /** Retorna el nodo siguiente, o null si es el ultimo. */
    public NodoTicket getSiguiente() {
        return siguiente;
    }

    /** Establece cual es el nodo siguiente. */
    public void setSiguiente(NodoTicket siguiente) {
        this.siguiente = siguiente;
    }
}
