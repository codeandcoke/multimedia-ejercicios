package com.sfaci.holalibgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sfaci.holalibgdx.Principal;
import com.sfaci.holalibgdx.util.Constantes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constantes.ANCHURA;
        config.height = Constantes.ALTURA;
        config.fullscreen = false;

		new LwjglApplication(new Principal(), config);
	}
}
