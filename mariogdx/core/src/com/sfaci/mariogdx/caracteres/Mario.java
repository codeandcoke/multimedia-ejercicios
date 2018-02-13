package com.sfaci.mariogdx.caracteres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.mariogdx.managers.R;

public class Mario extends Personaje {

    private Animation<TextureRegion> animacionDerecha, animacionIzquierda;
    public boolean invencible;
    private float tiempo;
    public int monedas;
    // Marca la velocidad del jugador en el eje y (caída / salto)
    public float velocidadY;
    public boolean saltando;

    public Mario(float x, float y, int vidas, TextureRegion frameInicial) {
        super(x, y, vidas, frameInicial);
        invencible = false;
        monedas = 0;

        animacionDerecha = new Animation<TextureRegion>(0.15f,
                R.getTexturas("mario_walk_right"));
        animacionIzquierda = new Animation<TextureRegion>(0.15f,
                R.getTexturas("mario_walk_left"));

        estado = Estado.QUIETO;
    }

    @Override
    public void update(float dt) {

        tiempo += dt;

        switch (estado) {
            case DERECHA:
                frameActual = animacionDerecha.getKeyFrame(tiempo, true);
                break;
            case IZQUIERDA:
                frameActual = animacionIzquierda.getKeyFrame(tiempo, true);
                break;
            case QUIETO:
                frameActual = R.getTextura("mario_idle_right");
                break;
            case SALTO:
                frameActual = R.getTextura("mario_jump_right");
                break;
            default:
        }

        // Marcamos una velocidad máxima
        if (velocidadY > -300 * dt)
            // Hace actuar la velocidad de la gravedad sobre
            // el desplazamiento en Y del jugador
            velocidadY -= 50 * dt;

        // Aplica el desplazamiento en Y sobre el jugador
        posicion.y += velocidadY;
        // Si toca el suelo (por ahora está fijo en 64 px)
        // hacemos que no siga cayendo
        if (posicion.y < 64) {
            posicion.y = 64;
            saltando = false;
        }
    }

    /**
     * Deplaza al personaje en el eje X
     * @param x
     */
    public void desplazar(float x) {

        posicion.x += x;
    }

    public void saltar() {

        saltando = true;
        velocidadY += 15;
    }
}
