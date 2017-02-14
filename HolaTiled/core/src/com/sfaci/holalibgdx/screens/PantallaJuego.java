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
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sfaci.holalibgdx.Diamante;
import com.sfaci.holalibgdx.Jugador;
import com.sfaci.holalibgdx.Personaje;
import com.sfaci.holalibgdx.managers.ResourceManager;

import static com.sfaci.holalibgdx.util.Constantes.ALTURA;
import static com.sfaci.holalibgdx.util.Constantes.ANCHURA;

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

        cargarDiamantes();
    }

    @Override
    public void render(float dt) {

        // Limpia la pantalla
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render(new int[]{0, 1});
        batch.begin();
        jugador.render(batch);
        for (Diamante diamante : diamantes)
            diamante.render(batch);
        fuente.getData().setScale(2);
        fuente.draw(batch, "Puntos: " + jugador.puntos,
                camara.position.x - ANCHURA + 50, ALTURA * 2 - 100);
        batch.end();

        jugador.update(dt);

        comprobarTeclado(dt);
        fijarCamara();
        comprobarColisiones();
    }

    private void cargarDiamantes() {

        MapLayer capaObjetos = mapa.getLayers().get("objetos");
        MapObjects mapObjects = capaObjetos.getObjects();
        for (MapObject mapObject : mapObjects) {
            String tipo = (String) mapObject.getProperties().get("tipo");
            if (tipo.equals("diamante")) {
                int puntuacion = Integer.parseInt(
                        (String) mapObject.getProperties().get("puntos"));
                TiledMapTileMapObject tileMapObject =
                        (TiledMapTileMapObject) mapObject;
                Diamante diamante = new Diamante(
                        tileMapObject.getX(), tileMapObject.getY(), puntuacion);
                diamantes.add(diamante);
            }
        }
    }

    private void comprobarColisiones() {

        /*
        Comprueba la colisión con el suelo
         */
        MapLayer capaColision = mapa.getLayers().get("colision");
        MapObjects mapObjects = capaColision.getObjects();
        for (MapObject mapObject : mapObjects) {

            Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
            if (rect.overlaps(jugador.rect)) {
                jugador.posicion.y = rect.y + rect.getHeight();
                jugador.saltando = false;
            }
        }

        /*
        Comprueba la colisión con los diamantes
         */
        for (Diamante diamante : diamantes) {
            if (diamante.rect.overlaps(jugador.rect)) {
                jugador.puntos += diamante.puntuacion;
                diamantes.removeValue(diamante, true);
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
