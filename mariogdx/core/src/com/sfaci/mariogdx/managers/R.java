package com.sfaci.mariogdx.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class R {

    private static AssetManager assets = new AssetManager();
    private static String TEXTURE_ATLAS = "characters.pack";

    public static void cargarRecursos() {
        assets.load(TEXTURE_ATLAS, TextureAtlas.class);
        cargarSonidos();
        cargarMusicas();
    }

    public static void cargarSonidos() {
    }

    public static void cargarMusicas() {

    }

    public static boolean update() {
        return assets.update();
    }

    public static void finalizarCarga() {
        assets.finishLoading();
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

        return assets.get(nombre, Sound.class);
    }

    public static Music getMusica(String nombre) {

        return assets.get(nombre, Music.class);
    }
}
