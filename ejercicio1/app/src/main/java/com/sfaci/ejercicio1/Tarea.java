package com.sfaci.ejercicio1;

/**
 * Created by dam on 18/10/16.
 */
public class Tarea {

    private String nombre;
    private boolean hecha;

    public Tarea(String nombre) {
        this.nombre = nombre;
        hecha = false;
    }

    public void hacer() {
        hecha = true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaHecha() {
        return hecha;
    }
}
