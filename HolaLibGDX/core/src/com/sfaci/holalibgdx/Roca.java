package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam on 13/01/17.
 */
public class Roca extends Ovni {

    public final static int VELOCIDAD = 10;

    public Roca(Texture imagen, float x, float y) {
        super(imagen, x, y);
    }

    public void caer() {
        posicion.y -= VELOCIDAD;
    }
}
