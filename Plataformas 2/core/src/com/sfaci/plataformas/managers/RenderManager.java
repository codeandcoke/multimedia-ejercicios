package com.sfaci.plataformas.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sfaci.plataformas.characters.Enemigo;

import static com.sfaci.plataformas.utils.Constantes.ALTURA_EN_CELDAS;
import static com.sfaci.plataformas.utils.Constantes.ANCHURA_CELDA;
import static com.sfaci.plataformas.utils.Constantes.ANCHURA_EN_CELDAS;

/**
 * Created by dam on 27/02/18.
 */
public class RenderManager {

    OrthographicCamera camara;
    Batch batch;
    BitmapFont fuente;
    OrthogonalTiledMapRenderer mapRenderer;
    SpriteManager spriteManager;
    LevelManager levelManager;

    public RenderManager(SpriteManager spriteManager,
                         LevelManager levelManager) {
        this.spriteManager = spriteManager;
        this.levelManager = levelManager;

        batch = new SpriteBatch();
        fuente = new BitmapFont();

        camara = new OrthographicCamera();
        // Fija la anchura y altura de la camara en base al número de tiles que se mostrarán
        camara.setToOrtho(false, ANCHURA_EN_CELDAS * ANCHURA_CELDA, ALTURA_EN_CELDAS * ANCHURA_CELDA);
        camara.update();

        mapRenderer = new OrthogonalTiledMapRenderer(levelManager.mapa);
        batch = mapRenderer.getBatch();

        mapRenderer.setView(camara);
    }

    /**
     * Muestra información del juego en pantalla (Head-up display)
     * @param batch
     */
    private void mostrarHud(Batch batch) {

        batch.draw(R.getTextura("mario_idle_right"), camara.position.x - ANCHURA_CELDA * ANCHURA_EN_CELDAS / 2 + 5, ANCHURA_CELDA * ALTURA_EN_CELDAS - 14, 0, 0, 10, 12, 1, 1, 0);
        fuente.getData().setScale(0.5f);
        fuente.draw(batch, "X " + spriteManager.mario.vidas, camara.position.x - ANCHURA_CELDA * ANCHURA_EN_CELDAS / 2 + 17, ANCHURA_CELDA * ALTURA_EN_CELDAS - 5);
    }

    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();

        batch.begin();
        spriteManager.mario.render(batch);
        for (Enemigo enemigo : spriteManager.enemigos)
            enemigo.render(batch);
        mostrarHud(batch);
        batch.end();
    }
}
