package com.sfaci.holalibgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.holalibgdx.managers.ResourceManager;

/**
 * Created by dam on 10/02/17.
 */
public class Diamante {

    public TextureRegion imagen;
    public Rectangle rect;
    public int puntuacion;

    public Diamante(float x, float y, int puntuacion) {

        imagen = ResourceManager.obtenerFrame("diamante");
        rect = new Rectangle(x, y, imagen.getRegionWidth(), imagen.getRegionHeight());
        this.puntuacion = puntuacion;
    }

    public void render(Batch batch) {
        batch.draw(imagen, rect.x, rect.y);
    }
}
