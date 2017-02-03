package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dam on 18/01/17.
 */
public class Bala extends Ovni {

    public Bala(Texture imagen, float x, float y) {
        super(imagen, x, y);
    }

    public void mover() {
        posicion.y += 10;
    }
}
