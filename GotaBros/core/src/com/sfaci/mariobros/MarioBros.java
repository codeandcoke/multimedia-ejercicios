package com.sfaci.mariobros;

import com.badlogic.gdx.ApplicationAdapter;
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

public class MarioBros extends ApplicationAdapter {
	SpriteBatch batch;
	Cubo cubo;
	Array<Gota> gotas;
	final float RITMO_GOTAS = 0.1f;
	long tiempoUltimaGota = 0;
	long ritmoGotas = 10;
	boolean estaPausado = false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cubo = new Cubo(new Texture("sprites/bucket.png"));
		gotas = new Array<Gota>();

		Music musicaLluvia = Gdx.audio.newMusic(new FileHandle("sounds/undertreeinrain.mp3"));
		musicaLluvia.setLooping(true);
		musicaLluvia.play();

		tiempoUltimaGota = TimeUtils.millis();
		//generarLluviaConTimer();
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyJustPressed(Input.Keys.P))
			estaPausado = !estaPausado;

		if (!estaPausado) {
			comprobarTeclado();

			generarLluvia();
			comprobarBordes();

			for (Gota gota : gotas) {
				gota.caer();
				// La gota desaparece cuando llega al suelo
				if ((gota.posicion.y + gota.tamano.y) < 0) {
					gotas.removeValue(gota, true);
				}
				// La gota desaparece cuando choca con el cubo
				if (gota.rect.overlaps(cubo.rect)) {
					gotas.removeValue(gota, true);
					Sound sound = Gdx.audio.newSound(new FileHandle("sounds/waterdrop.wav"));
					sound.play();
				}
			}
		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		cubo.pintar(batch);
		for (Gota gota : gotas)
			gota.pintar(batch);
		batch.end();
	}

	private void generarLluviaConTimer() {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				int posicionX = MathUtils.random(0,
						Gdx.graphics.getWidth() - Gota.ANCHURA);
				Gota gota = new Gota(new Texture("sprites/droplet.png"), posicionX);
				gotas.add(gota);
			}
		}, 2, RITMO_GOTAS);
	}

	private void generarLluvia() {
		if ((TimeUtils.timeSinceMillis(tiempoUltimaGota)) > ritmoGotas) {
			int posicionX = MathUtils.random(0,
					Gdx.graphics.getWidth() - Gota.ANCHURA);
			Gota gota = new Gota(new Texture("sprites/droplet.png"), posicionX);
			gotas.add(gota);
			tiempoUltimaGota = TimeUtils.millis();
		}
	}

	private void comprobarBordes() {
		if (cubo.posicion.x < 0)
			cubo.posicion.x = 0;

		if ((cubo.posicion.x + cubo.tamano.x) > Gdx.graphics.getWidth())
			cubo.posicion.x = Gdx.graphics.getWidth() - cubo.tamano.x;
	}

	private void comprobarTeclado() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cubo.mover(cubo.velocidad);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cubo.mover(new Vector2(-10, 0));
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cubo.dispose();
	}
}
