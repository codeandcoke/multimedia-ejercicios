package com.sfaci.hola.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam on 16/01/18.
 */
public class Bucket {

    Texture imagen;
    Vector2 posicion;

    public Bucket(float x, float y) {
        imagen = new Texture("bucket.png");
        posicion = new Vector2(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(imagen, posicion.x, posicion.y);
    }

    public void mover(Vector2 movimiento) {
        posicion.add(movimiento);
    }

    public void update(float dt) {

    }
}
