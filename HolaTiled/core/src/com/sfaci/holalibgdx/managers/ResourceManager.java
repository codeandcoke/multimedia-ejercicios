package com.sfaci.holalibgdx.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by dam on 7/02/17.
 */
public class ResourceManager {

    private static AssetManager assetManager = new AssetManager();

    /**
     * Carga todos los assets del juego
     */
    public static void cargarRecursos() {

        assetManager.load("atlas.pack", TextureAtlas.class);
        assetManager.finishLoading();

        //assetManager.load("explosion.wav", Sound.class);
        //assetManager.load("musica_level_1.mp3", Music.class);
    }

    /**
     * Obtiene los frame de una animación
     * @param nombre
     * @return
     */
    public static Array<TextureAtlas.AtlasRegion> obtenerAnimacion(String nombre) {

        return assetManager.get("atlas.pack", TextureAtlas.class)
                .findRegions(nombre);
    }

    /**
     * Obtiene el primer frame de una animación
     * @param nombre
     * @return
     */
    public static TextureRegion obtenerFrame(String nombre) {

        return assetManager.get("atlas.pack", TextureAtlas.class)
                .findRegion(nombre);
    }

    public static Sound obtenerSonido(String nombre) {

        Sound sound = null;

        return sound;
    }
}
