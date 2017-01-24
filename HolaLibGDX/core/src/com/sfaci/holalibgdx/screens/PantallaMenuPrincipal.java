package com.sfaci.holalibgdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.sfaci.holalibgdx.Principal;
import com.sfaci.holalibgdx.util.Constantes;

/**
 * Created by dam on 20/01/17.
 */
public class PantallaMenuPrincipal implements Screen {

    private Principal juego;
    Stage stage;

    public PantallaMenuPrincipal(Principal juego) {
        this.juego = juego;
    }

    @Override
    public void show() {

        if (!VisUI.isLoaded())
            VisUI.load();

        stage = new Stage();
        VisTable tabla = new VisTable();
        tabla.setWidth(500);
        tabla.setHeight(600);
        tabla.setPosition(Constantes.ANCHURA / 2 - tabla.getWidth() / 2,
                Constantes.ALTURA / 2);
        tabla.setFillParent(true);
        stage.addActor(tabla);

        VisTextButton boton = new VisTextButton("Jugar");
        boton.setWidth(200);
        boton.setHeight(50);
        boton.setPosition(tabla.getWidth() / 2 - boton.getWidth() / 2, 0);
        boton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                juego.setScreen(new PantallaJuego(juego));
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        tabla.addActor(boton);
        VisTextButton botonSalir = new VisTextButton("Salir");
        botonSalir.setWidth(200);
        botonSalir.setHeight(50);
        botonSalir.setPosition(
                tabla.getWidth() / 2 - botonSalir.getWidth() / 2, -60);
        botonSalir.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                juego.setScreen(new PantallaJuego(juego));
            }
        });
        tabla.addActor(botonSalir);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float dt) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
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

    }
}
