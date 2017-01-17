package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.holalibgdx.util.Constantes;

/**
 * Created by dam on 13/01/17.
 */
public class Nave extends Ovni{

    public Nave(Texture imagen, float x, float y) {
        super(imagen, x, y);
    }

    public void mover(Vector2 movimiento) {
        posicion.add(movimiento);

        if (posicion.x + imagen.getWidth() >= Constantes.ANCHURA)
            posicion.x = Constantes.ANCHURA - imagen.getWidth();

        if (posicion.x < 0)
            posicion.x = 0;

        rect.setPosition(posicion);
    }
}
