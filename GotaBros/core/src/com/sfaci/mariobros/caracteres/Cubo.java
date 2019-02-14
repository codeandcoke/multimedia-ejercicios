package com.sfaci.mariobros.caracteres;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.mariobros.managers.R;

import static com.sfaci.mariobros.caracteres.Cubo.Estado.QUIETO;

public class Cubo extends Caracter {

    public enum Estado {
        DERECHA, IZQUIERDA, QUIETO
    }

    public final int VELOCIDAD_INICIAL = 10;

    public int vidas;
    public int municion;

    private Animation<TextureAtlas.AtlasRegion> animacionDerecha;
    private Animation<TextureAtlas.AtlasRegion> animacionIzquierda;
    public Estado estado;
    private float tiempo;

    public Cubo(TextureRegion imagen) {
        super(imagen);
        vidas = 3;
        municion = 0;
        velocidad = new Vector2(VELOCIDAD_INICIAL, 0);

        animacionDerecha = new Animation<>(0.15f, R.getAnimacion("player_run_right"));
        animacionIzquierda = new Animation<>(0.15f, R.getAnimacion("player_run_left"));
        estado = QUIETO;
        tiempo = 0;
    }

    public void actualizar(float dt) {
        tiempo += dt;

        switch (estado) {
            case DERECHA:
                imagen = animacionDerecha.getKeyFrame(tiempo, true);
                break;
            case IZQUIERDA:
                imagen = animacionIzquierda.getKeyFrame(tiempo, true);
                break;
            case QUIETO:
                imagen = R.getTextura("player_idle_down");
                break;
        }
    }

    public Nube disparar() {
        Nube nube = new Nube(R.getTextura("cloud"));
        nube.posicion.x = posicion.x + (tamano.x / 2) - nube.tamano.x / 2;
        nube.posicion.y = posicion.y + tamano.y;
        return nube;
    }
}
