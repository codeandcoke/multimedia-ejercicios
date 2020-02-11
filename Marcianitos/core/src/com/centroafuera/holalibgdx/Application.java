package com.centroafuera.holalibgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Application extends ApplicationAdapter {
	private SpriteBatch batch;
	private Nave nave;
	private Array<Marciano> marcianos;
	private Array<Roca> rocas;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		nave = new Nave(new Vector2(0, 0), new Texture("ship/f1.png"), 3, 10, Nave.TipoDisparo.RAFAGA,
				false);
		marcianos = new Array<>();
		rocas = new Array<>();
	}

	@Override
	public void render () {
		actualizar();
		pintar();
	}

	private void actualizar() {
		// Input de usuario (teclado)
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			nave.getPosicion().x = nave.getPosicion().y + nave.getVelocidad();
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			nave.getPosicion().y = nave.getPosicion().y - nave.getVelocidad();
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			nave.getPosicion().x = nave.getPosicion().x - nave.getVelocidad();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			nave.getPosicion().x = nave.getPosicion().x + nave.getVelocidad();

		// Mover enemigos

		// Generar enemigos

		// Comprobar colisiones

		// Comprobaciones varias . . .
	}

	private void pintar() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		nave.pintar(batch);
		for (Marciano marciano : marcianos)
			marciano.pintar(batch);
		for (Roca roca : rocas)
			roca.pintar(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
