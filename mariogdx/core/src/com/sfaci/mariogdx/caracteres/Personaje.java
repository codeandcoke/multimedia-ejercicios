package com.sfaci.mariogdx.caracteres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.mariogdx.managers.R;

public abstract class Personaje {

    public enum Estado {
        DERECHA, IZQUIERDA, QUIETO, SALTO
    }

    public Vector2 posicion;
    public TextureRegion frameActual;
    public Rectangle rect;
    public Estado estado;
    public int vidas;

    public Personaje(float x, float y, int vidas,
                     TextureRegion frameInicial) {
        posicion = new Vector2(x, y);
        estado = Estado.QUIETO;
        this.vidas = vidas;

        this.frameActual = frameInicial;
        rect = new Rectangle(x, y, frameActual.getRegionWidth(),
                frameActual.getRegionHeight());
    }

    public void render(Batch batch) {
        batch.draw(frameActual, posicion.x, posicion.y);
    }

    public abstract void update(float dt);
}
