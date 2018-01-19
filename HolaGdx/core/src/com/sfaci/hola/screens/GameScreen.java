package com.sfaci.hola.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sfaci.hola.characters.Cubo;
import com.sfaci.hola.characters.Gota;

public class GameScreen implements Screen {

    Cubo cubo;
    SpriteBatch batch;
    Array<Gota> gotas;
    Texture texturaGota;
    long tiempoEntreGotas;
    long tiempoUltimaGota;

    public GameScreen() {

        batch = new SpriteBatch();
        cubo = new Cubo(new Texture("bucket.png"), 0, 0);
        gotas = new Array<Gota>();
        texturaGota = new Texture("droplet.png");
        tiempoEntreGotas = 10;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


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
                //gotas.removeValue(gota, true);
            }

            // Si la gota choca con el cubo (su rectángulo),
            // se elimina
            if (gota.rect.overlaps(cubo.rect)) {
                gotas.removeValue(gota, true);
                // TODO Reproducir sonido de gota
                // TODO Sumar puntos o lo que sea
            }
        }

        batch.begin();
        cubo.render(batch);
        for (Gota gota : gotas)
            gota.render(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cubo.mover(new Vector2(10, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cubo.mover(new Vector2(-10, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cubo.mover(new Vector2(0, 10));
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
