package com.eurostudios.game_engine_classes;

import com.eurostudios.entities.*;
import java.util.ArrayList;

public class GameLoop implements Runnable {

    private static ArrayList<Entity> entityArrayList; // stores the entities
    private static Render pongRender; // used to render the game content
    private static int milliseconds;
    private static int seconds;
    private static boolean reset;

    // TIMER GETTERS
    public static int getTimeInMilliseconds() {return milliseconds;}
    public static int getTotTimeInSeconds() {return seconds;}

    public GameLoop() { // inits the Thread of the game loop
        entityArrayList = new ArrayList<>();
        pongRender = new Render();
        new Thread(this).start();
    }

    public static void resetEntities() {
        reset = true;
    }

    private void initEntities () { // adds the entities to its arraylist and calls start() method od the entities

        entityArrayList.add(new BackGround());

        // CURRENT FASE
        if (!Menu.isInMenu) {
            seconds = 0;
            milliseconds = 0;
            entityArrayList.add(new Player());
            entityArrayList.add(new Enemy());
            entityArrayList.add(new Ball());
        }
        // MENU
        else {
            entityArrayList.add(new Menu());
        }

        for (Entity e: entityArrayList) e.start();
    }

    private void mainUpdate() { // calls entities' updates and timer
        milliseconds++;
        seconds = milliseconds/60;
        for (Entity e: entityArrayList) e.update();
    }
    private void mainRender() { // calls entities' render
        pongRender.render(entityArrayList);
    }

    @Override
    public void run() {
        initEntities();
        while (true) {
            if (reset) {
                entityArrayList.clear();
                initEntities();
                reset = false;
            }
            mainUpdate();
            mainRender();
            // 60 FPS LOCKER
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
