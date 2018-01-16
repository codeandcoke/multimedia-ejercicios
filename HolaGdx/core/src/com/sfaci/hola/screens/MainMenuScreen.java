package com.sfaci.hola.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * Created by dam on 16/01/18.
 */
public class MainMenuScreen implements Screen {

    Stage stage;

    @Override
    public void show() {

        if (!VisUI.isLoaded())
            VisUI.load();

        stage = new Stage();
        VisTable tabla = new VisTable();
        tabla.setFillParent(true);
        stage.addActor(tabla);

        VisTextButton botonPlay = new VisTextButton("JUGAR");
        botonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
                dispose();
            }
        });
        VisTextButton botonSalir = new VisTextButton("SALIR");
        botonSalir.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
                dispose();
            }
        });

        tabla.row();
        tabla.add(botonPlay).center().width(200).height(100).pad(5);
        tabla.row();
        tabla.add(botonSalir).center().width(200).height(100).pad(5);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float dt) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();
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
        stage.dispose();
    }
}
