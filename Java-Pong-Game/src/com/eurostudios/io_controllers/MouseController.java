package com.eurostudios.io_controllers;

import com.eurostudios.game_engine_classes.GameLoop;
import com.eurostudios.game_engine_classes.AppWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.eurostudios.entities.Menu.*;

public class MouseController implements MouseListener {


    public MouseController() {
        AppWindow.canvas.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isInMenu) {
            int axisX = e.getX()/ AppWindow.SCALE;
            int axisY = e.getY()/ AppWindow.SCALE;
            if (!infoTrigger) { // can only press a button if its not on information menu
                if (axisX >= buttonPVP.buttonFrame.x && axisX <= buttonPVP.buttonFrame.x + buttonPVP.buttonFrame.width &&
                        axisY >= buttonPVP.buttonFrame.y && axisY <= buttonPVP.buttonFrame.y + buttonPVP.buttonFrame.height) {
                    buttonPVP.isPressed = true;
                }
                else if (axisX >= buttonPVE.buttonFrame.x && axisX <= buttonPVE.buttonFrame.x + buttonPVE.buttonFrame.width &&
                        axisY >= buttonPVE.buttonFrame.y && axisY <= buttonPVE.buttonFrame.y + buttonPVE.buttonFrame.height) {
                    buttonPVE.isPressed = true;
                }
                else if (axisX >= buttonInfo.buttonFrame.x && axisX <= buttonInfo.buttonFrame.x + buttonInfo.buttonFrame.width &&
                        axisY >= buttonInfo.buttonFrame.y && axisY <= buttonInfo.buttonFrame.y + buttonInfo.buttonFrame.height) {
                    buttonInfo.isPressed = true;
                }
            }
            else if (axisX >= buttonExitInfo.buttonFrame.x && axisX <= buttonExitInfo.buttonFrame.x + buttonExitInfo.buttonFrame.width &&
                    axisY >= buttonExitInfo.buttonFrame.y && axisY <= buttonExitInfo.buttonFrame.y + buttonExitInfo.buttonFrame.height) {
                buttonExitInfo.isPressed = true;
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (buttonPVP.isPressed && !infoTrigger) {
            buttonPVP.isPressed = false;
            isInMenu = false;
            isPVP = true;
            GameLoop.resetEntities();
        }
        else if (buttonPVE.isPressed && !infoTrigger) {
            buttonPVE.isPressed = false;
            isInMenu = false;
            isPVE = true;
            GameLoop.resetEntities();
        }
        else if (buttonInfo.isPressed) {
            buttonInfo.isPressed = false;
            infoTrigger = true;
        }
        else if(buttonExitInfo.isPressed) {
            buttonExitInfo.isPressed = false;
            infoTrigger = false;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
