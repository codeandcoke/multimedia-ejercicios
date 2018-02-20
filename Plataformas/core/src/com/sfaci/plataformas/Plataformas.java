package com.sfaci.plataformas;

import com.badlogic.gdx.Game;
import com.sfaci.plataformas.managers.R;
import com.sfaci.plataformas.screens.GameScreen;
import com.sfaci.plataformas.screens.MainMenuScreen;

public class Plataformas extends Game {
	
	@Override
	public void create () {

		R.cargarTodo();
		R.finalizarCarga();
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
