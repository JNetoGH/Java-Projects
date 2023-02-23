package com.eurostudios.game_engine_classes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteHandler {

    private BufferedImage sprite;

    public SpriteHandler (String path) {
        try {
            sprite = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }

}
