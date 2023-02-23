package com.eurostudios.entities;

import java.awt.*;

public class EuroStdsButton {

    private static Color shadowColor = new Color(0,0,0, 30);
    public boolean isPressed = false;
    public Rectangle buttonFrame;
    private String str3char;

    EuroStdsButton(String str3char, int x, int y, int width, int height) {
        buttonFrame = new Rectangle(x, y, width, height);
        this.str3char = str3char;
    }

    public void render(Graphics graphics) {
        if (!isPressed) {
            graphics.setColor(shadowColor);
            graphics.fillRect(buttonFrame.x + 3, buttonFrame.y + 3, buttonFrame.width, buttonFrame.height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(buttonFrame.x, buttonFrame.y, buttonFrame.width, buttonFrame.height);
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawString(str3char, buttonFrame.x + 14, buttonFrame.y + 18);
        }
        else {
            graphics.setColor(shadowColor);
            graphics.fillRect(buttonFrame.x + 3, buttonFrame.y + 3, buttonFrame.width, buttonFrame.height);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(buttonFrame.x + 1, buttonFrame.y + 1, buttonFrame.width, buttonFrame.height);
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawString(str3char, buttonFrame.x + 14 + 1, buttonFrame.y + 18 + 1);
            graphics.setColor(shadowColor);
            graphics.fillRect(buttonFrame.x + 1, buttonFrame.y + 1, buttonFrame.width, buttonFrame.height);
        }
    }

}
