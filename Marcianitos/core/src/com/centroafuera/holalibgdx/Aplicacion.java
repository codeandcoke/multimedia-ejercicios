package com.centroafuera.holalibgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.centroafuera.holalibgdx.model.Marciano;
import com.centroafuera.holalibgdx.model.Nave;
import com.centroafuera.holalibgdx.model.Roca;
import com.centroafuera.holalibgdx.screens.PantallaCarga;
import com.centroafuera.holalibgdx.screens.PantallaJuego;
import com.centroafuera.holalibgdx.screens.PantallaMenuPrincipal;
import com.centroafuera.holalibgdx.util.Constantes;

public class Aplicacion extends Game {

	@Override
	public void create () {
		((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrincipal());
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

	}
}
