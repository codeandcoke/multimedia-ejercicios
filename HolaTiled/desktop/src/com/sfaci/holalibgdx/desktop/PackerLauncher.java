package com.sfaci.holalibgdx.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

/**
 * Created by dam on 7/02/17.
 */
public class PackerLauncher {

    public static void main(String args[]) {
        TexturePacker2.Settings settings = new TexturePacker2.Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        settings.filterMag = Texture.TextureFilter.Linear;
        settings.filterMin = Texture.TextureFilter.Linear;

        TexturePacker2.process(settings,
                "core/assets/texturas",
                "core/assets",
                "atlas.pack");
    }
}
