package com.eurostudios.entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.eurostudios.game_engine_classes.*;

public class Ball implements Entity {

    private BufferedImage sprite;
    public static double posX;
    public static double posY;
    public static Dimension dimensions;

    // can be 1 or -1 indicates were the ball is going
    public static double dx;
    public static double dy;

    private static boolean enhanced = false;
    private static double additive = 0.5;
    private static int totSecToEnhancement = 5;
    private static double initialSpeed = 2;
    public static double speed;
    private void enhanceSpeed() {
        if (GameLoop.getTotTimeInSeconds() != 0 && GameLoop.getTotTimeInSeconds() % totSecToEnhancement == 0 && !enhanced) {
            enhanced = true;
            speed += additive;
        }
        if (GameLoop.getTotTimeInSeconds() % totSecToEnhancement != 0) {
            enhanced = false;
        }
    }

    private double roundGaussian(double num) {
        if (num > 0) return 1;
        else return  -1;
    }

    @Override
    public void start() {
        sprite = new SpriteHandler("/covid_ball.png").getSprite();
        dimensions = new Dimension(sprite.getWidth(),sprite.getHeight());
        speed = initialSpeed;
        posX = (int) (AppWindow.WIDTH/2-dimensions.getWidth());
        posY = (int) (AppWindow.HEIGHT/2-dimensions.getHeight());
        dx = roundGaussian(new Random().nextGaussian());
        dy = roundGaussian(new Random().nextGaussian());
    }


    @Override
    public void update() {

        // enhances ball speed fo each 15 seconds
        enhanceSpeed();

        // COLLISIONS WITH WALLS
        if(posY + dx * speed + dimensions.height >= AppWindow.HEIGHT - GraphicalSettings.BOTTOM_MARGIN||
                posY + dy * speed < GraphicalSettings.TOP_MARGIN) {
            dy *= -1;
        }

        // ENTITIES HIT BOXES
        Rectangle ballHitBox = new Rectangle((int)(posX + dx * speed), (int)(posY + dy * speed), dimensions.width, dimensions.height);
        Rectangle playerHitBox = new Rectangle((int) Player.posX, (int) Player.posY, Player.dimensions.width, Player.dimensions.height);
        Rectangle enemyHitBox = new Rectangle((int) Enemy.posX, (int) Enemy.posY, Enemy.dimensions.width, Enemy.dimensions.height);

        // COLLISIONS WITH ENTITIES: 30% of chance of change the dy in each tackle
        if (ballHitBox.intersects(playerHitBox) || ballHitBox.intersects(enemyHitBox)) {
            dx *= -1;
            int num = new Random().nextInt(10) + 1; //1<=>10
            if (num >= 8) dy *= -1;
        }

        // MOVES THE BALL
        posX += dx * speed;
        posY += dy * speed;

        if (posX > AppWindow.WIDTH) { // player point
            BackGround.addPointToPlayer();
            GameLoop.resetEntities();
        }
        else if (posX < 0) { // enemy point
            BackGround.addPointToEnemy();
            GameLoop.resetEntities();
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(sprite, (int) posX, (int) posY, null);
    }
}
