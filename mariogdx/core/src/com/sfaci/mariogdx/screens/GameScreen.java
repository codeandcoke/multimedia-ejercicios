package com.sfaci.mariogdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sfaci.mariogdx.caracteres.Mario;
import com.sfaci.mariogdx.caracteres.Personaje;
import com.sfaci.mariogdx.managers.R;

/**
 * Created by dam on 7/02/18.
 */
public class GameScreen implements Screen {

    Batch batch;
    OrthographicCamera camara;
    TiledMap mapa;
    OrthogonalTiledMapRenderer mapaRenderer;
    Mario mario;

    @Override
    public void show() {

        camara = new OrthographicCamera();
        camara.setToOrtho(false, 32 * 10, 32 * 10);
        camara.update();

        mapa = new TmxMapLoader().load("levels/level1.tmx");
        mapaRenderer = new OrthogonalTiledMapRenderer(mapa);
        batch = mapaRenderer.getBatch();

        mapaRenderer.setView(camara);

        mario = new Mario(0, 64, 3, R.getTextura("mario_idle_right"));
    }

    @Override
    public void render(float dt) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapaRenderer.render();

        manejarTeclado(dt);
        moverCamara();

        camara.update();
        mapaRenderer.setView(camara);

        mario.update(dt);

        batch.begin();
        mario.render(batch);
        batch.end();
    }

    private void moverCamara() {

        // La cámara sigue al jugador
        // Siempre deja al jugador en el centro de la pantalla
        camara.position.x = mario.posicion.x;
        // Si el jugador está en el borde izquierdo dejamos la
        // cámara fija
        if (mario.posicion.x < 160) {
            camara.position.x = 160;
        }
        camara.position.y = 160;
    }

    private void manejarTeclado(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mario.desplazar(100 * dt);
            mario.estado = Personaje.Estado.DERECHA;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mario.desplazar(-100 * dt);
            mario.estado = Personaje.Estado.IZQUIERDA;
        }
        else {
            mario.estado = Personaje.Estado.QUIETO;
        }

        // El jugador sólo puede saltar si no está saltando
        // Evita que salte en mitad de otro salto
        if (!mario.saltando) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                mario.saltar();
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
