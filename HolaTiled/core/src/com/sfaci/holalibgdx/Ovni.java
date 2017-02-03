package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam on 13/01/17.
 */
public abstract class Ovni {

    public Texture imagen;
    public Vector2 posicion;
    public Rectangle rect;

    public Ovni(Texture imagen, float x, float y) {
        this.imagen = imagen;
        posicion = new Vector2(x, y);
        rect = new Rectangle(x, y, imagen.getWidth(), imagen.getHeight());
    }

    public void dispose() {
        imagen.dispose();
    }
}
