package com.sfaci.hola.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.sfaci.hola.characters.Cubo;
import com.sfaci.hola.characters.Gota;

import java.io.File;

public class GameScreen implements Screen {

    Cubo cubo;
    SpriteBatch batch;
    Array<Gota> gotas;
    Texture texturaGota;
    long tiempoEntreGotas;
    long tiempoUltimaGota;
    Sound sonidoGota;
    Sound sonidoRoca;
    Music musica;
    BitmapFont fuente;
    int puntos;
    boolean pausado;
    boolean muerto;

    public GameScreen() {

        batch = new SpriteBatch();
        cubo = new Cubo(new Texture("bucket.png"), 0, 0);
        gotas = new Array<Gota>();
        texturaGota = new Texture("droplet.png");
        tiempoEntreGotas = 10;
        sonidoGota = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
        sonidoRoca = Gdx.audio.newSound(Gdx.files.internal("rock.mp3"));
        musica = Gdx.audio.newMusic(Gdx.files.internal("undertreeinrain.mp3"));
        fuente = new BitmapFont(Gdx.files.internal("default.fnt"));
        fuente.getData().setScale(2f);
        puntos = 0;
    }

    @Override
    public void show() {
        musica.setLooping(true);
        musica.setVolume(1f);
        musica.play();
    }

    @Override
    public void render(float dt) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!pausado) {
            if ((TimeUtils.millis() - tiempoUltimaGota) > tiempoEntreGotas) {
                Gota unaGota = new Gota(texturaGota,
                        MathUtils.random(0,
                                Gdx.graphics.getWidth() - texturaGota.getWidth()),
                        Gdx.graphics.getHeight());
                gotas.add(unaGota);

                tiempoUltimaGota = TimeUtils.millis();
            }

            for (Gota gota : gotas) {
                gota.mover(new Vector2(0, -10));

                // Si la gota toca el suelo, se elimina
                if (gota.posicion.y < 0) {
                    gotas.removeValue(gota, true);
                }

                // Si la gota choca con el cubo (su rectángulo),
                // se elimina
                if (gota.rect.overlaps(cubo.rect)) {
                    gotas.removeValue(gota, true);
                    sonidoGota.play();
                    puntos++;
                    muerto = true;
                    Timer.schedule(new Timer.Task() {
                        public void run() {
                            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverScreen());
                            dispose();
                        }
                    }, 3);
                }
            }
        }

        batch.begin();
        cubo.render(batch);
        for (Gota gota : gotas)
            gota.render(batch);
        fuente.getData().setScale(1);
        fuente.draw(batch, "Puntos: " + puntos, 5, Gdx.graphics.getHeight() - 5);
        if (muerto) {
            fuente.getData().setScale(3);
            fuente.draw(batch, "Game Over", 250, 250);
            pausado = true;
        }
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cubo.mover(new Vector2(10, 0));
            if (cubo.posicion.x > Gdx.graphics.getWidth() -
                    cubo.imagen.getWidth()) {
                cubo.posicion.x = Gdx.graphics.getWidth() - cubo.imagen.getWidth();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cubo.mover(new Vector2(-10, 0));
            if (cubo.posicion.x < 0) {
                cubo.posicion.x = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cubo.mover(new Vector2(0, 10));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            pausado = !pausado;
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

        musica.dispose();
        sonidoGota.dispose();
        texturaGota.dispose();
    }
}
