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

    Stage stage;

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

        VisTextButton btJugar = new VisTextButton("JUGAR");
        btJugar.setWidth(200);
        btJugar.setHeight(50);
        btJugar.setPosition(
                tabla.getWidth() / 2 - btJugar.getWidth() / 2, 0);
        btJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaJuego());
                dispose();
            }
        });
        tabla.addActor(btJugar);

        VisTextButton btConfigurar = new VisTextButton("CONFIGURAR");
        btConfigurar.setWidth(200);
        btConfigurar.setHeight(50);
        btConfigurar.setPosition(
                tabla.getWidth() / 2 - btConfigurar.getWidth() / 2, -60);
        btConfigurar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaConfiguracion());
            }
        });
        tabla.addActor(btConfigurar);

        VisTextButton btSalir = new VisTextButton("SALIR");
        btSalir.setWidth(200);
        btSalir.setHeight(50);
        btSalir.setPosition(
                tabla.getWidth() / 2 - btSalir.getWidth() / 2, -120);
        btSalir.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        tabla.addActor(btSalir);

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
        stage.dispose();
    }
}
