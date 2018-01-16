package com.sfaci.hola;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sfaci.hola.screens.MainMenuScreen;

import java.util.Random;

public class HolaGdx extends Game {
	
	@Override
	public void create () {

	    setScreen(new MainMenuScreen());
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
	}
}
