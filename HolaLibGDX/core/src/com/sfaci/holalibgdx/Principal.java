package com.sfaci.holalibgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sfaci.holalibgdx.util.Constantes;

import java.sql.Time;

public class Principal extends ApplicationAdapter {
	SpriteBatch batch;
	Nave nave;
    Array<Roca> rocas;
    long ultimaRoca;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

        nave = new Nave(new Texture("ship.png"), 0, 0);
        rocas = new Array<>();
        ultimaRoca = TimeUtils.millis();
	}

	@Override
	public void render () {
		// Limpia la pantalla
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Pinta la imagen en pantalla
		batch.begin();
		batch.draw(nave.imagen, nave.posicion.x, nave.posicion.y);
        for (Roca roca : rocas)
            batch.draw(roca.imagen, roca.posicion.x, roca.posicion.y);
		batch.end();

        moverRocas();
        generarRoca();
		comprobarInput();
        comprobarLimites();
	}

	private void moverRocas() {

        for (Roca roca : rocas) {
            roca.caer();
            if (roca.posicion.y + roca.imagen.getHeight() <= 0)
                rocas.removeValue(roca, true);
        }
    }

	private void generarRoca() {

        if (TimeUtils.millis() - ultimaRoca > Constantes.TIEMPO_ENTRE_ROCAS) {
            Texture imagenRoca = new Texture("stone.png");
            Roca roca = new Roca(imagenRoca,
                    MathUtils.random(0, Constantes.ANCHURA - imagenRoca.getWidth()),
                    Constantes.ALTURA);
            rocas.add(roca);

            ultimaRoca = TimeUtils.millis();
        }

    }

	private void comprobarInput() {
        // El usuario pulsa la tecla DERECHA
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            nave.posicion.x += 10;

        // El usuario pulsa la tecla IZQUIERDA
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            nave.posicion.x -= 10;
    }

    private void comprobarLimites() {

        // Comprueba los limites con la pantalla a la derecha
        if (nave.posicion.x + nave.imagen.getWidth() >= Constantes.ANCHURA)
            nave.posicion.x = Constantes.ANCHURA - nave.imagen.getWidth();

        // Comprueba los limites con la pantalla a la izquierda
        if (nave.posicion.x < 0)
            nave.posicion.x = 0;
    }
	
	@Override
	public void dispose () {
		batch.dispose();
		nave.dispose();
	}
}
