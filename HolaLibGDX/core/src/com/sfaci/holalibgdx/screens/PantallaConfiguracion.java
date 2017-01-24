package com.sfaci.holalibgdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;
import com.sfaci.holalibgdx.util.Constantes;

/**
 * Created by dam on 20/01/17.
 */
public class PantallaConfiguracion implements Screen {

    private Stage stage;
    private VisCheckBox cbSonido;

    @Override
    public void show() {

        stage = new Stage();
        VisTable tabla = new VisTable();
        tabla.setWidth(500);
        tabla.setHeight(600);
        tabla.setPosition(Constantes.ANCHURA / 2 - tabla.getWidth() / 2,
                Constantes.ALTURA / 2);
        tabla.setFillParent(true);
        stage.addActor(tabla);

        cbSonido = new VisCheckBox("SONIDO");
        cbSonido.setWidth(200);
        cbSonido.setHeight(50);
        cbSonido.setPosition(tabla.getWidth() / 2 - cbSonido.getWidth() / 2, 0);
        cbSonido.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        tabla.addActor(cbSonido);

        VisSlider slVolumen = new VisSlider(0, 254, 1, false);
        slVolumen.setWidth(200);
        slVolumen.setHeight(50);
        slVolumen.setPosition(
                tabla.getWidth() / 2 - slVolumen.getWidth() / 3, -60);
        slVolumen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        tabla.addActor(slVolumen);

        VisSelectBox sbResolucion = new VisSelectBox();
        sbResolucion.setWidth(200);
        sbResolucion.setHeight(40);
        sbResolucion.setItems("1024x768", "800x600", "640x480");
        sbResolucion.setPosition(
                tabla.getWidth() / 2 - sbResolucion.getWidth() / 3, -120);
        tabla.addActor(sbResolucion);

        VisCheckBox cbPatallaCompleta = new VisCheckBox("PANTALLA COMPLETA");
        cbPatallaCompleta.setWidth(200);
        cbPatallaCompleta.setHeight(50);
        cbPatallaCompleta.setPosition(
                tabla.getWidth() / 2 - cbPatallaCompleta.getWidth() / 2, -170);
        cbPatallaCompleta.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        tabla.addActor(cbPatallaCompleta);

        VisTextButton btHecho = new VisTextButton("HECHO");
        btHecho.setWidth(200);
        btHecho.setHeight(50);
        btHecho.setPosition(tabla.getWidth() / 2, btHecho.getWidth() / 2, -230);
        btHecho.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrincipal());
            }
        });
        tabla.addActor(btHecho);

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
