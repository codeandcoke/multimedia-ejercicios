package com.sfaci.plataformas.managers;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.sfaci.plataformas.characters.Enemigo;

/**
 * Created by dam on 27/02/18.
 */
public class LevelManager {

    TiledMap mapa;
    private SpriteManager spriteManager;
    public int nivelActual;

    public LevelManager(SpriteManager spriteManager) {
        nivelActual = 1;
        this.spriteManager = spriteManager;
    }

    public void cargarNivel() {

        mapa = new TmxMapLoader().load("levels/level" +
                nivelActual + ".tmx");
        cargarEnemigos();
    }

    public void resetNivel() {
        spriteManager.enemigos.clear();
    }

    public void cambiarNivel() {

        resetNivel();
        nivelActual++;
        cargarNivel();
    }

    public void cambiarNivel(int nivel) {

        resetNivel();
        nivelActual = nivel;
        cargarNivel();
    }

    public void cargarEnemigos() {

        spriteManager.enemigos = new Array<Enemigo>();

        MapLayer capaObjetos = mapa.getLayers().get("objects");
        for (MapObject objetoMapa : capaObjetos.getObjects()) {
            if (objetoMapa.getProperties().containsKey("enemy")) {
                if (objetoMapa instanceof TiledMapTileMapObject) {
                    TiledMapTileMapObject objetoRect = (TiledMapTileMapObject) objetoMapa;
                    Enemigo enemigo = new Enemigo(objetoRect.getX(), objetoRect.getY(), 1, R.getTextura("enemy"));
                    spriteManager.enemigos.add(enemigo);
                }
            }
        }
    }
}
