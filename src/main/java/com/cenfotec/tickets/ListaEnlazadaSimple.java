package com.cenfotec.tickets;

/**
 * Lista enlazada simple de tickets resueltos.
 * Su unico atributo es la referencia al primer nodo; el ultimo nodo
 * apunta a null.
 */
public class ListaEnlazadaSimple {

    private NodoTicket primero;

    /** Crea una lista vacia. */
    public ListaEnlazadaSimple() {
        this.primero = null;
    }

    /** Inserta un ticket al final de la lista (queda en orden de resolucion). */
    public void insertarFinal(Ticket ticket) {
        NodoTicket nuevoNodo = new NodoTicket(ticket);
        if (primero == null) {
            primero = nuevoNodo;
            return;
        }
        NodoTicket nodoActual = primero;
        while (nodoActual.getSiguiente() != null) {
            nodoActual = nodoActual.getSiguiente();
        }
        nodoActual.setSiguiente(nuevoNodo);
    }

    /** Busca un ticket por id. Retorna null si la lista esta vacia o no lo encuentra. */
    public Ticket buscarPorId(int id) {
        NodoTicket nodoActual = primero;
        while (nodoActual != null && nodoActual.getTicket().getId() != id) {
            nodoActual = nodoActual.getSiguiente();
        }
        return (nodoActual == null) ? null : nodoActual.getTicket();
    }
}
