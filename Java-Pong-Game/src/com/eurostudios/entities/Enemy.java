package com.eurostudios.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.eurostudios.game_engine_classes.Entity;
import com.eurostudios.game_engine_classes.AppWindow;
import com.eurostudios.game_engine_classes.SpriteHandler;

public class Enemy implements Entity {

    public static Color enemyColor = new Color(255,0,0);
    private BufferedImage sprite;

    public static boolean goingUp;
    public static boolean goingDown;
    public static double posX;
    public static double posY;
    public static double speed = 2.5;
    public static Dimension dimensions;
    public static double speedDecrement = 0.8; // (0.01 <=> 1) the lowest the easiest, because the enemy slows down, 1 == impossible

    @Override
    public void start() {
        sprite = new SpriteHandler("/vacina_2.png").getSprite();
        dimensions = new Dimension(sprite.getWidth(), sprite.getHeight());
        posX = (int) (AppWindow.WIDTH - GraphicalSettings.LATERAL_MARGIN - dimensions.getWidth());
        posY = (int) (AppWindow.HEIGHT/2-dimensions.getHeight()/2);
    }

    @Override
    public void update() {
        if (Menu.isPVE) {
            double newPosiY = (posY + (Ball.posY - posY) - dimensions.height/2 + Ball.dimensions.height/2) * speedDecrement;
            if (!(newPosiY > AppWindow.HEIGHT - GraphicalSettings.BOTTOM_MARGIN - dimensions.height)) { // ! > bottom
                if (!(newPosiY < GraphicalSettings.TOP_MARGIN)) { // ! < top
                    posY = newPosiY;
                }
            }
        }
        else if (Menu.isPVP) {
            if(goingUp) {
                if (!(posY < GraphicalSettings.TOP_MARGIN)) {
                    posY -= speed;
                }
            }
            else if(goingDown) {
                if (!(posY + dimensions.getHeight() > AppWindow.HEIGHT - GraphicalSettings.BOTTOM_MARGIN)) {
                    posY += speed;
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        //Bar
        graphics.setColor(enemyColor);
        graphics.fillRect((int) posX, (int) posY, 2,(int) dimensions.getHeight()); // rendering a rectangle X, Y, WIDTH, HEIGHT
        graphics.drawImage(sprite, (int) posX, (int) posY, null);
    }
}
