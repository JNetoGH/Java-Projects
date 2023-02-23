package com.eurostudios.game_engine_classes;

import java.awt.*;

public abstract interface Entity {

    public abstract void start(); // called at the beginning of the game loop
    public abstract void update(); // updates entity fields like pos, etc
    public abstract void render(Graphics graphics); // draw the entity at screen
}
