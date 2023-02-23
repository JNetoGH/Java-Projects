package com.eurostudios.game_engine_classes;

import javax.swing.*;
import java.awt.*;

public class AppWindow {

    public static final String FRAME_NAME = "Super Pong";
    public static JFrame frame = new JFrame(FRAME_NAME);
    public static Canvas canvas = new  Canvas();
    public static final int WIDTH = 250;
    public static final int HEIGHT = 300;
    public static final int SCALE = 2; //multiplies everything in the Canvas

    public AppWindow() {
        initCanvas();
        initFrame();
    }

    private void initCanvas() {
        canvas.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
    }

    private void initFrame() {
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when x is pressed uses the System.exit() to kill everything
        frame.add(canvas); // adds a Canvas to the Frame
        frame.pack();
        frame.setLocationRelativeTo(null); // sets the default location in the middle of the screen
        frame.setVisible(true); // shows the frame
    }
}
