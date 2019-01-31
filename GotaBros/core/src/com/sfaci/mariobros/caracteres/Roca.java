package com.sfaci.mariobros.caracteres;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Roca extends Caracter {

    public final int VELOCIDAD_INICIAL = 5;
    public enum Tipo {
        GRANDE, MEDIANA, PEQUENA
    }

    public Tipo tipo;
    public int vidas;

    public Roca(Texture imagen) {
        super(imagen);
        tipo = Tipo.PEQUENA;
        vidas = 1;
        velocidad = new Vector2(0, -VELOCIDAD_INICIAL);
    }

    public Roca(Texture imagen, Tipo tipo) {
        super(imagen);
        velocidad = new Vector2(0, -VELOCIDAD_INICIAL);
        this.tipo = tipo;
        switch (tipo) {
            case PEQUENA:
                vidas = 1;
                break;
            case MEDIANA:
                vidas = 2;
                break;
            case GRANDE:
                vidas = 3;
                break;
        }
    }
}
