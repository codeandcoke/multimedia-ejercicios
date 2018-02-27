package com.sfaci.plataformas.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.sfaci.plataformas.characters.Enemigo;
import com.sfaci.plataformas.characters.Mario;
import com.sfaci.plataformas.managers.*;

import static com.sfaci.plataformas.utils.Constantes.ALTURA_EN_CELDAS;
import static com.sfaci.plataformas.utils.Constantes.ANCHURA_CELDA;
import static com.sfaci.plataformas.utils.Constantes.ANCHURA_EN_CELDAS;


/**
 * Pantalla de juego
 */
public class GameScreen implements Screen {


    LevelManager levelManager;
    SpriteManager spriteManager;
    RenderManager renderManager;

    public GameScreen() {
        spriteManager = new SpriteManager();
        levelManager = new LevelManager(spriteManager);
        levelManager.cargarNivel();

        spriteManager.setLevelManager(levelManager);
        renderManager = new RenderManager(spriteManager, levelManager);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {

        renderManager.render();

        comprobarTeclado(dt);
        spriteManager.update(dt);
    }

    /**
     * Comprueba la entrada de teclado
     * @param dt
     */
    private void comprobarTeclado(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spriteManager.mario.desplazar(50 * dt);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            spriteManager.mario.desplazar(-50 * dt);
        }
        else
            spriteManager.mario.estado = Mario.Estado.QUIETO;

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (!spriteManager.mario.saltando) {
                spriteManager.mario.saltar();
            }
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
