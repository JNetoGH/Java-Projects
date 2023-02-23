package com.eurostudios.entities;

import com.eurostudios.game_engine_classes.AppWindow;
import com.eurostudios.game_engine_classes.Entity;
import com.eurostudios.game_engine_classes.GameLoop;

import java.awt.*;

public class BackGround implements Entity {

    private static int playerPoints;
    private static int enemyPoints;

    public static void addPointToPlayer() {playerPoints++;}
    public static void addPointToEnemy() {enemyPoints++;}
    public static void resetPoints() {
        playerPoints = 0;
        enemyPoints = 0;
    }

    @Override
    public void render(Graphics graphics) {
        // background rectangle
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0,0, AppWindow.WIDTH, AppWindow.HEIGHT);

        // timer
        graphics.setColor(Color.gray);
        graphics.drawString("SEC: " + GameLoop.getTotTimeInSeconds(), 10,20);

        // ball speed
        graphics.drawString("BALL SPEED: " + Ball.speed, 80,20);

        // background middle rectangle
        graphics.fillRect(AppWindow.WIDTH/2-1, GraphicalSettings.TOP_MARGIN, 3,
                AppWindow.HEIGHT - GraphicalSettings.BOTTOM_MARGIN - GraphicalSettings.TOP_MARGIN);

        // esc
        graphics.drawString("esc => menu", AppWindow.WIDTH/2-35, AppWindow.HEIGHT-GraphicalSettings.BOTTOM_MARGIN/2);

        // Scoreboard
        graphics.drawString(String.valueOf(playerPoints), AppWindow.WIDTH/2-26, GraphicalSettings.TOP_MARGIN);
        graphics.drawString(String.valueOf(enemyPoints), AppWindow.WIDTH/2+20, GraphicalSettings.TOP_MARGIN);
    }

    @Override
    public void start() {}
    @Override
    public void update() {}

}
