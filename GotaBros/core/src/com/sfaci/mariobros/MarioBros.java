package com.sfaci.mariobros;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.sfaci.mariobros.caracteres.Cubo;
import com.sfaci.mariobros.caracteres.Gota;
import com.sfaci.mariobros.caracteres.Nube;
import com.sfaci.mariobros.managers.R;
import com.sfaci.mariobros.pantallas.PantallaJuego;
import com.sfaci.mariobros.pantallas.PantallaMenuPrincipal;
import com.sfaci.mariobros.pantallas.PantallaSplash;

public class MarioBros extends Game {

	@Override
	public void create () {
		setScreen(new PantallaSplash());
	}

	@Override
	public void render () {
		super.render();
	}


	
	@Override
	public void dispose () {
	}
}
