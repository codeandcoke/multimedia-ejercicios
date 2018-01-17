package com.sfaci.hola.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sfaci.hola.characters.Cubo;

public class GameScreen implements Screen {

    Cubo cubo;
    SpriteBatch batch;

    public GameScreen() {

        batch = new SpriteBatch();
        cubo = new Cubo(new Texture("bucket.png"), 0, 0);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        cubo.render(batch);
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
