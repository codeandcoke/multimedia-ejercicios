package com.sfaci.holalibgdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
    private VisCheckBox cbSonido, cbPantallaCompleta;
    private VisSlider slVolumen;
    private VisSelectBox sbResolucion;

    private void cargarPreferencias() {

        Preferences preferences = Gdx.app.getPreferences("holalibgdx");
        cbSonido.setChecked(preferences.getBoolean("sonido"));
        cbPantallaCompleta.setChecked(preferences.getBoolean("pantallaCompleta"));
        slVolumen.setValue(preferences.getFloat("volumen"));
        sbResolucion.setSelected(preferences.getString("resolucion"));
    }

    private void guardarPreferencias() {

        Preferences preferences = Gdx.app.getPreferences("holalibgdx");
        preferences.putBoolean("sonido", cbSonido.isChecked());
        preferences.putBoolean("pantallaCompleta", cbPantallaCompleta.isChecked());
        preferences.putFloat("volumen", slVolumen.getValue());
        preferences.putString("resolucion", (String) sbResolucion.getSelected());
        preferences.flush();
    }

    @Override
    public void show() {

        stage = new Stage();
        VisTable tabla = new VisTable();
        tabla.setFillParent(true);
        stage.addActor(tabla);

        cbSonido = new VisCheckBox("SONIDO");
        cbSonido.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        slVolumen = new VisSlider(0, 254, 1, false);
        slVolumen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        tabla.addActor(slVolumen);

        sbResolucion = new VisSelectBox();
        sbResolucion.setItems("1024x768", "800x600", "640x480");

        cbPantallaCompleta = new VisCheckBox("PANTALLA COMPLETA");
        cbPantallaCompleta.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        VisTextButton btHecho = new VisTextButton("HECHO");
        btHecho.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                guardarPreferencias();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrincipal());
                dispose();
            }
        });

        tabla.row();
        tabla.add(cbSonido).left().width(200).height(20).pad(10);
        tabla.row();
        tabla.add(slVolumen).center().width(200).height(20).pad(10);
        tabla.row();
        tabla.add(sbResolucion).center().width(200).height(20).pad(10);
        tabla.row();
        tabla.add(cbPantallaCompleta).left().width(200).height(20).pad(15);
        tabla.row();
        tabla.add(btHecho).center().width(200).height(50).pad(5);

        cargarPreferencias();
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
