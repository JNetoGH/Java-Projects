package com.eurostudios.game_engine_classes;

import com.eurostudios.io_controllers.KeyboardController;
import com.eurostudios.io_controllers.MouseController;

public class PongExe {

    public static AppWindow pongWindow;
    public static GameLoop pongGameLoop;
    public static KeyboardController keyboardController;
    public static MouseController mouseController;

    public PongExe() {
        // INITS WINDOW, GAME LOOP AND KEYBOARD CONTROLLER
        pongWindow = new AppWindow();
        pongGameLoop = new GameLoop();
        keyboardController = new KeyboardController();
        mouseController = new MouseController();

    }

    public static void main(String[] args) {
        new PongExe();
    }

}

