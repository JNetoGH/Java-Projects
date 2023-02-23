package com.eurostudios.game_engine_classes;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Render {

    private static final BufferedImage NEW_LAYER = new BufferedImage(AppWindow.WIDTH, AppWindow.HEIGHT, BufferedImage.TYPE_INT_RGB);  // a image layer where our graphics will be rendered

    public void render(ArrayList<Entity> entityArrayList) { // calls entities' render
        BufferStrategy bufferStrategy = AppWindow.canvas.getBufferStrategy(); // a sequence of buffers we put on screen to optimize the rendering
        if (bufferStrategy == null) {   // the first bufferStrategy comes null, so it's required to create a new one
            AppWindow.canvas.createBufferStrategy(3); 	// the ideal is between 2 or 3, it enhances the game performance
            return;	// used just to break the method because on next loop there will already exist a buffer
        }
        Graphics graphics = NEW_LAYER.getGraphics();

        // CLEAN THE SCREEN UP WITH A RECT OF THE SAME SIZE THE NEW IMAGE WILL BE DREW ON TOP
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, AppWindow.WIDTH, AppWindow.HEIGHT);

        // ENTITIES RENDERING
        for (Entity e: entityArrayList) {
            e.render(graphics);
        }

        // SCALES THE ELEMENTS UP AND SHOWS IT
        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(NEW_LAYER, 0, 0, AppWindow.WIDTH * AppWindow.SCALE, AppWindow.HEIGHT * AppWindow.SCALE, null);
        bufferStrategy.show();
    }
}
