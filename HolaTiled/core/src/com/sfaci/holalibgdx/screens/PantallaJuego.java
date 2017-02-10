package com.sfaci.holalibgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sfaci.holalibgdx.Diamante;
import com.sfaci.holalibgdx.Jugador;
import com.sfaci.holalibgdx.Personaje;
import com.sfaci.holalibgdx.managers.ResourceManager;

/**
 * Created by dam on 20/01/17.
 */
public class PantallaJuego implements Screen {

    Batch batch;
    BitmapFont fuente;
    TiledMap mapa;
    OrthogonalTiledMapRenderer mapRenderer;
    OrthographicCamera camara;
    Jugador jugador;
    Array<Diamante> diamantes;

    public PantallaJuego() {

        batch = new SpriteBatch();
        fuente = new BitmapFont(Gdx.files.internal("default.fnt"));
    }

    @Override
    public void show() {

        jugador = new Jugador(0, 600);
        diamantes = new Array<Diamante>();

        camara = new OrthographicCamera();
        camara.setToOrtho(false, 25 * 64, 18 * 64);
        camara.update();

        mapa = new TmxMapLoader().load("levels/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(mapa);
        batch = mapRenderer.getBatch();

        mapRenderer.setView(camara);
    }

    @Override
    public void render(float dt) {

        // Limpia la pantalla
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render(new int[]{0, 1});
        batch.begin();
        jugador.render(batch);
        batch.end();

        jugador.update(dt);

        comprobarTeclado(dt);
        fijarCamara();
        comprobarColisiones();
    }

    private void comprobarColisiones() {

        MapLayer capaColision = mapa.getLayers().get("colision");
        MapObjects mapObjects = capaColision.getObjects();
        for (MapObject mapObject : mapObjects) {

            Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
            if (rect.overlaps(jugador.rect)) {
                jugador.posicion.y = rect.y + rect.getHeight();
                jugador.saltando = false;
            }
        }
    }

    private void fijarCamara() {

        if (jugador.posicion.x < 25 * 64 / 2) {
            camara.position.set(25 * 64 / 2, 20 * 64 / 2 - 64, 0);
        }
        else {
            camara.position.set(jugador.posicion.x, 20 * 64 / 2 - 64, 0);
        }

        camara.update();
        mapRenderer.setView(camara);
    }

    private void comprobarTeclado(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            jugador.velocidad.x = 150 * dt;
            jugador.estado = Jugador.Estado.DERECHA;

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            jugador.velocidad.x = -150 * dt;
            jugador.estado = Jugador.Estado.IZQUIERDA;
        }
        else {
            jugador.estado = Jugador.Estado.QUIETO;
            jugador.velocidad.x = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!jugador.saltando) {
                jugador.velocidad.y = 8;
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
        batch.dispose();
        fuente.dispose();
    }
}
