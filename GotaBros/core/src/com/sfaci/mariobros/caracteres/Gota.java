    package com.sfaci.mariobros.caracteres;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

    public class Gota extends Caracter {

    public final int VELOCIDAD_INICIAL = 5;
    public static final int ANCHURA = 64;

    public enum Tipo {
        NORMAL, MUNICION
    }

    public Tipo tipo;

    public Gota(TextureRegion imagen, int posicionX) {
        super(imagen);
        posicion.y = Gdx.graphics.getHeight();
        posicion.x = posicionX;
        tipo = Tipo.NORMAL;
        velocidad = new Vector2(0, -VELOCIDAD_INICIAL);
    }

    public Gota(TextureRegion imagen, int posicionY, boolean municion) {
        super(imagen);
        posicion.y = posicionY;
        velocidad = new Vector2(0, -VELOCIDAD_INICIAL);
        if (municion)
            tipo = Tipo.MUNICION;
        else
            tipo = Tipo.NORMAL;
    }

    public void caer() {
        mover();
    }
}
