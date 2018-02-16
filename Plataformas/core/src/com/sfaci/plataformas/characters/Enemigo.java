package com.sfaci.plataformas.characters;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemigo extends Personaje {

    public Enemigo(float x, float y, int vidas, TextureRegion frameInicial) {
        super(x, y, vidas, frameInicial);
    }

    @Override
    public void update(float dt) {
        posicion.x -= 15 * dt;
        rect.x = posicion.x;

        super.update(dt);
    }
}
