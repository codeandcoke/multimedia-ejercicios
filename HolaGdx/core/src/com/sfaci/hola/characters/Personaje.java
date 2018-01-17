package com.sfaci.hola.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;

/**
 * Created by dam on 17/01/18.
 */
public abstract class Personaje {

    public Texture imagen;
    public Vector2 posicion;
    public Rectangle rect;

    public Personaje(Texture imagen, float x, float y) {

        this.imagen = imagen;
        posicion = new Vector2(x, y);
        rect = new Rectangle(x, y, imagen.getWidth(),
                imagen.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(imagen, posicion.x, posicion.y);
    }

    public void mover(Vector2 movimiento) {
        posicion.add(movimiento);
        rect.setPosition(posicion);
    }
}
