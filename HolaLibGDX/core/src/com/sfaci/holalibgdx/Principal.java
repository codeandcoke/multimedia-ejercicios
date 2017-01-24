package com.sfaci.holalibgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.kotcrab.vis.ui.VisUI;
import com.sfaci.holalibgdx.screens.PantallaMenuPrincipal;
import com.sfaci.holalibgdx.util.Constantes;

import java.sql.Time;

public class Principal extends Game {

	public SpriteBatch batch;
    public BitmapFont fuente;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        fuente = new BitmapFont(Gdx.files.internal("default.fnt"));

        setScreen(new PantallaMenuPrincipal(this));
	}

	@Override
	public void render () {
        super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
        fuente.dispose();
		VisUI.dispose();
	}
}
