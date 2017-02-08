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
import com.sfaci.holalibgdx.managers.ResourceManager;
import com.sfaci.holalibgdx.util.Constantes;

/**
 * Created by dam on 20/01/17.
 */
public class PantallaMenuPrincipal implements Screen {

    Stage stage;

    @Override
    public void show() {

        ResourceManager.cargarRecursos();

        if (!VisUI.isLoaded())
            VisUI.load();

        stage = new Stage();
        VisTable tabla = new VisTable();
        tabla.setFillParent(true);
        stage.addActor(tabla);

        VisTextButton btJugar = new VisTextButton("JUGAR");
        btJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaJuego());
                dispose();
            }
        });

        VisTextButton btConfigurar = new VisTextButton("CONFIGURAR");
        btConfigurar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaConfiguracion());
                dispose();
            }
        });

        VisTextButton btSalir = new VisTextButton("SALIR");
        btSalir.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        tabla.row();
        tabla.add(btJugar).center().pad(5).width(200).height(50);
        tabla.row();
        tabla.add(btConfigurar).center().pad(5).width(200).height(50);
        tabla.row();
        tabla.add(btSalir).center().pad(5).width(200).height(50);

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
