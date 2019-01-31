package com.sfaci.mariobros.caracteres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Cubo extends Caracter {

    public final int VELOCIDAD_INICIAL = 10;

    public int vidas;
    public int municion;

    public Cubo(Texture imagen) {
        super(imagen);
        vidas = 3;
        municion = 0;
        velocidad = new Vector2(VELOCIDAD_INICIAL, 0);
    }
}
