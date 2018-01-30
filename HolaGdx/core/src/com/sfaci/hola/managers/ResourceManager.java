package com.sfaci.hola.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.io.File;

import static com.sfaci.hola.util.Constantes.MUSICA_LLUVIA;
import static com.sfaci.hola.util.Constantes.SONIDO_GOTA;

/**
 * Created by dam on 24/01/18.
 */
public class ResourceManager {

    private static AssetManager assets = new AssetManager();
    private static String TEXTURE_ATLAS = "atlas.atlas";

    public static void cargarRecursos() {
        assets.load(TEXTURE_ATLAS, TextureAtlas.class);
        cargarSonidos();
        cargarMusicas();
    }

    public static void cargarSonidos() {
        assets.load(SONIDO_GOTA, Sound.class);
    }

    public static void cargarMusicas() {
        assets.load(MUSICA_LLUVIA, Music.class);
    }

    public boolean update() {
        return assets.update();
    }

    public static TextureRegion getTextura(String nombre) {
        return assets.get(TEXTURE_ATLAS, TextureAtlas.class).
                findRegion(nombre);
    }

    public static TextureRegion getTextura(String nombre, int posicion) {
        return assets.get(TEXTURE_ATLAS, TextureAtlas.class).
                findRegion(nombre, posicion);
    }

    public static Array<TextureAtlas.AtlasRegion> getTexturas(String nombre) {
        return assets.get(TEXTURE_ATLAS, TextureAtlas.class).
                findRegions(nombre);
    }

    public static Sound getSonido(String nombre) {
        return assets.get(nombre + ".mp3", Sound.class);
    }

    public static Music getMusica(String nombre) {
        return assets.get(nombre + ".mp3", Music.class);
    }
}
