package com.centroafuera.holalibgdx.screens;

import com.badlogic.gdx.*;
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
import com.centroafuera.holalibgdx.util.Constantes;

public class PantallaJuego implements Screen {

    private SpriteBatch batch;
    private Nave nave;
    private Array<Marciano> marcianos;
    private Array<Roca> rocas;
    private long tiempoJuego;

    @Override
    public void show() {
        batch = new SpriteBatch();
        nave = new Nave(Vector2.Zero, new Texture("ship/f1.png"), 3, Constantes.VELOCIDAD_NAVE,
                Nave.TipoDisparo.RAFAGA, false);
        marcianos = new Array<>();
        rocas = new Array<>();

        tiempoJuego = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        actualizar();
        pintar();
    }

    private void actualizar() {
        // Input de usuario (teclado)
        comprobarTeclado();
        // Generar enemigos
        generarEnemigos();
        // Mover enemigos
        moverEnemigos();
        // Comprobar colisiones
        comprobarColisiones();
        // Comprobaciones varias . . .
    }

    private void comprobarTeclado() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            nave.moverArriba();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            nave.moverAbajo();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            nave.moverIzquierda();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            nave.moverDerecha();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrincipal());
        }
    }

    private void generarEnemigos() {
        if (TimeUtils.millis() - tiempoJuego > Constantes.TIEMPO_ENTRE_MARCIANOS) {
            Texture textura = new Texture("enemy/e_f1.png");
            Vector2 posicion = new Vector2(Gdx.graphics.getWidth() - textura.getWidth(),
                    MathUtils.random(0, Gdx.graphics.getHeight()));
            Marciano marciano = new Marciano(posicion, textura, 1, 2);
            marcianos.add(marciano);

            tiempoJuego = TimeUtils.millis();
        }
    }

    private void moverEnemigos() {
        for (Marciano marciano : marcianos) {
            marciano.moverIzquierda();
            if (marciano.getPosicion().x < 0) {
                marcianos.removeValue(marciano, true);
            }
        }
    }

    private void comprobarColisiones() {
        for (Marciano marciano : marcianos) {
            if (marciano.rect.overlaps(nave.rect)) {
                Preferences prefs = Gdx.app.getPreferences("marcianos");
                if (prefs.getBoolean("sonido")) {
                    // Hacer sonar
                }
                marciano.quitarVida();
                nave.quitarVida();
                // Comprobar si deben seguir vivos . . .
            }
        }
    }

    private void pintar() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        // Pintar la nave
        nave.pintar(batch);
        // Pintar los enemigos
        for (Marciano marciano : marcianos)
            marciano.pintar(batch);
        for (Roca roca : rocas)
            roca.pintar(batch);

        batch.end();
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
        batch.dispose();
    }
}
