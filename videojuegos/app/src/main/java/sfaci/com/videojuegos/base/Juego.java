package sfaci.com.videojuegos.base;

import java.util.Date;

/**
 * Created by Santi on 04/10/16.
 */
public class Juego {

    private String nombre;
    private float precio;
    private String genero;
    private Date fecha;

    public Juego() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
