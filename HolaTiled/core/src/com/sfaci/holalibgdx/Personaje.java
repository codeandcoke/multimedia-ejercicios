package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.holalibgdx.managers.ResourceManager;

/**
 * Created by dam on 13/01/17.
 */
public abstract class Personaje {

    protected TextureRegion frameActual;
    protected Animation animacionDerecha;
    protected Animation animacionIzquierda;
    public Vector2 posicion;
    public Vector2 velocidad;
    public Rectangle rect;
    private float tiempo;
    public Estado estado;

    public enum Estado {
        DERECHA, IZQUIERDA, QUIETO;
    }

    public Personaje(float x, float y, String nombreAnimacionDerecha,
                     String nombreAnimacionIzquierda) {

        posicion = new Vector2(x, y);
        animacionDerecha = new Animation(0.25f,
                ResourceManager.obtenerAnimacion(nombreAnimacionDerecha));
        animacionIzquierda = new Animation(0.25f,
                ResourceManager.obtenerAnimacion(nombreAnimacionIzquierda));
        estado = Estado.QUIETO;
        tiempo = 0;

        frameActual = (TextureRegion) animacionDerecha.getKeyFrame(0);
        rect = new Rectangle(posicion.x, posicion.y,
                frameActual.getRegionWidth(), frameActual.getRegionHeight());
    }

    public void update(float dt) {

        tiempo += dt;

        switch (estado) {
            case DERECHA:
                frameActual = (TextureRegion) animacionDerecha.getKeyFrame(tiempo, true);
                break;
            case IZQUIERDA:
                frameActual = (TextureRegion) animacionIzquierda.getKeyFrame(tiempo, true);
                break;
            case QUIETO:
                frameActual = (TextureRegion) animacionDerecha.getKeyFrame(0);
                break;
            default:
        }
    }

    public void render(Batch batch) {
        batch.draw(frameActual, posicion.x, posicion.y);
    }

    public void dispose() {
    }
}
