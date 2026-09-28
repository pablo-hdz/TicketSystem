package com.cenfotec.tickets;

/**
 * Cola de prioridad de tickets pendientes, implementada con nodos enlazados.
 * Los nodos se mantienen ordenados por prioridad (compareTo del ticket),
 * asi que el ticket de mayor prioridad siempre esta al frente:
 * insertar recorre la cola para ubicar el nodo, y sacar o ver el frente es directo.
 */
public class ColaPrioridad {

    private NodoTicket frente;
    private int tamanio;

    /** Crea una cola vacia. */
    public ColaPrioridad() {
        this.frente = null;
        this.tamanio = 0;
    }

    /** Inserta el ticket en la posicion que le corresponde segun su prioridad. */
    public void insertar(Ticket ticket) {
        NodoTicket nuevoNodo = new NodoTicket(ticket);

        // Caso 1: cola vacia o el nuevo ticket tiene mayor prioridad que el frente.
        if (frente == null || ticket.compareTo(frente.getTicket()) < 0) {
            nuevoNodo.setSiguiente(frente);
            frente = nuevoNodo;
        } else {
            // Caso 2: avanzar hasta el ultimo nodo con prioridad menor o igual.
            NodoTicket nodoActual = frente;
            while (nodoActual.getSiguiente() != null
                    && nodoActual.getSiguiente().getTicket().compareTo(ticket) <= 0) {
                nodoActual = nodoActual.getSiguiente();
            }
            nuevoNodo.setSiguiente(nodoActual.getSiguiente());
            nodoActual.setSiguiente(nuevoNodo);
        }
        tamanio++;
    }

    /** Retorna el ticket al frente sin sacarlo. Retorna null si la cola esta vacia. */
    public Ticket verFrente() {
        return estaVacia() ? null : frente.getTicket();
    }

    /** Saca y retorna el ticket al frente. Retorna null si la cola esta vacia. */
    public Ticket remover() {
        if (estaVacia()) {
            return null;
        }
        Ticket ticket = frente.getTicket();
        frente = frente.getSiguiente();
        tamanio--;
        return ticket;
    }

    /** Indica si existe un ticket pendiente con ese id. */
    public boolean contiene(int id) {
        NodoTicket nodoActual = frente;
        while (nodoActual != null && nodoActual.getTicket().getId() != id) {
            nodoActual = nodoActual.getSiguiente();
        }
        return nodoActual != null;
    }

    /** Indica si la cola no tiene tickets. */
    public boolean estaVacia() {
        return frente == null;
    }

    /** Retorna la cantidad de tickets en la cola. */
    public int getTamanio() {
        return tamanio;
    }
}
