package com.cenfotec.tickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un ticket del sistema.
 * Los datos se adquieren al crear el ticket; la fecha de resolucion
 * empieza en null y se establece cuando el administrador lo resuelve.
 * Implementa Comparable para que la cola de prioridad pueda ordenarlos.
 */
public class Ticket implements Comparable<Ticket> {

    /** Contador consecutivo compartido por todos los tickets (genera los ids). */
    private static int cantidad = 0;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final int id;
    private final String descripcion;
    private final String nombreCompleto;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    /** Crea un ticket nuevo con id unico y fechaResolucion en null. */
    public Ticket(String descripcion, String nombreCompleto) {
        cantidad++;
        this.id = cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaResolucion = null;
    }

    /** Retorna el id unico del ticket. */
    public int getId() {
        return id;
    }

    /** Retorna la descripcion del problema o solicitud. */
    public String getDescripcion() {
        return descripcion;
    }

    /** Retorna el nombre completo del usuario que creo el ticket. */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /** Retorna la fecha y hora en que se creo el ticket. */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /** Retorna la fecha de resolucion, o null si el ticket sigue pendiente. */
    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    /** Marca el ticket como resuelto estableciendo la fecha actual. */
    public void resolver() {
        this.fechaResolucion = LocalDateTime.now();
    }

    /**
     * Criterio de prioridad: el ticket mas antiguo (menor id) tiene
     * mayor prioridad y por eso queda al frente de la cola.
     */
    @Override
    public int compareTo(Ticket otro) {
        return Integer.compare(this.id, otro.id);
    }

    /** Devuelve los datos del ticket listos para mostrar en consola. */
    @Override
    public String toString() {
        String resolucion = (fechaResolucion == null)
                ? "Pendiente"
                : fechaResolucion.format(FORMATO);
        return "Ticket #" + id
                + "\n  Solicitante : " + nombreCompleto
                + "\n  Descripcion : " + descripcion
                + "\n  Creado      : " + fechaCreacion.format(FORMATO)
                + "\n  Resuelto    : " + resolucion;
    }
}
