package com.sfaci.plataformas.managers;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.sfaci.plataformas.characters.Enemigo;
import com.sfaci.plataformas.characters.Mario;

public class SpriteManager {

    public LevelManager levelManager;
    public Mario mario;
    Array<Enemigo> enemigos;

    public SpriteManager() {
        mario = new Mario(0, 100, 3, R.getTextura("mario_idle_right"));
    }

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    /**
     * Comprueba las colisiones con los objetos que forman el suelo
     */
    public void comprobarColisiones() {

        // Comprueba colisiones con el suelo
        MapLayer capaSuelo = levelManager.mapa.getLayers().get("objects");
        for (MapObject objetoMapa : capaSuelo.getObjects()) {
            if (objetoMapa.getProperties().containsKey("suelo")) {
                RectangleMapObject objectoRect = (RectangleMapObject) objetoMapa;
                Rectangle rect = objectoRect.getRectangle();
                // Comprueba si el jugador colisiona con algún objeto suelo
                if (mario.rect.overlaps(rect)) {
                    mario.posicion.y = rect.y + rect.getHeight();
                    mario.rect.y = mario.posicion.y;
                    mario.saltando = false;
                }
                // Comprueba si algún enemigo colisiona con algún objeto suelo
                for (Enemigo enemigo : enemigos) {
                    if (enemigo.rect.overlaps(rect)) {
                        enemigo.posicion.y = rect.y + rect.getHeight();
                        enemigo.rect.y = enemigo.posicion.y;
                    }
                }
            }
        }

        // Comprueba colisiones con los enemigos
        for (Enemigo enemigo : enemigos) {
            if (enemigo.rect.overlaps(mario.rect)) {
                // Si Mario está más alto mata al enemigo
                if (mario.posicion.y > enemigo.rect.y) {
                    enemigos.removeValue(enemigo, true);
                    if (ConfigurationManager.haySonido())
                        R.getSonido("bump.wav").play();
                    mario.saltar();
                }
                else {
                    if (!mario.reposo) {
                        mario.vidas -= 1;
                        if (mario.vidas == 0) {
                            // TODO Fin de partida
                        }
                        mario.reposo = true;
                        Timer.schedule(new Timer.Task() {

                            @Override
                            public void run() {
                                mario.reposo = false;
                            }
                        }, 0.5f);
                    }
                }
            }
        }
    }

    /**
     * Controla la cámara para que siempre enfoque al jugador
     */
    public void controlarCamara() {

    }

    public void update(float dt) {

        controlarCamara();
        comprobarColisiones();

        mario.update(dt);
        for (Enemigo enemigo : enemigos)
            enemigo.update(dt);
    }
}
