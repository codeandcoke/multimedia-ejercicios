package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam on 13/01/17.
 */
public abstract class Personaje {

    protected TextureRegion currentFrame;
    protected Animation animacionDerecha;
    protected Animation animacionIzquierda;
    public Vector2 posicion;
    public Rectangle rect;

    public Personaje(float x, float y) {

        posicion = new Vector2(x, y);
    }

    public void dispose() {
    }
}
