package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.holalibgdx.util.Constantes;

/**
 * Created by dam on 13/01/17.
 */
public class Jugador extends Ovni{

    public Jugador(Texture imagen, float x, float y) {
        super(imagen, x, y);
    }

    public void mover(Vector2 movimiento) {
        posicion.add(movimiento);

        rect.setPosition(posicion);
    }
}
