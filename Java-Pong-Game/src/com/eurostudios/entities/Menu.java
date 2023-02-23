package com.eurostudios.entities;

import java.awt.*;

import com.eurostudios.game_engine_classes.*;

public class Menu implements Entity{

    public static boolean isInMenu = true;
    public static boolean isPVP = false;
    public static boolean isPVE = false;

    public static Color darkTransparent = new Color(0,0,0, 225);

    @Override
    public void start() {}
    @Override
    public void update() {}


    public static final EuroStdsButton buttonPVP = new EuroStdsButton("PVP", GraphicalSettings.LATERAL_MARGIN*2, 40, 50,25);
    public static final EuroStdsButton buttonPVE = new EuroStdsButton("PVE", GraphicalSettings.LATERAL_MARGIN*2, 80, 50,25);
    public static final EuroStdsButton buttonInfo = new EuroStdsButton("INF", GraphicalSettings.LATERAL_MARGIN*2, 120, 50,25);
    public static final EuroStdsButton buttonExitInfo = new EuroStdsButton("  X", AppWindow.WIDTH/2-25, AppWindow.HEIGHT- GraphicalSettings.LATERAL_MARGIN*4, 50,25);
    public static boolean infoTrigger = false; // used to display the information menu

    @Override
    public void render(Graphics graphics) {
        buttonPVP.render(graphics);
        buttonPVE.render(graphics);
        buttonInfo.render(graphics);
        if (infoTrigger) { // information menu rendering
            // BACKGROUND
            graphics.setColor(darkTransparent);
            graphics.fillRect(GraphicalSettings.LATERAL_MARGIN, GraphicalSettings.LATERAL_MARGIN,
                    AppWindow.WIDTH - GraphicalSettings.LATERAL_MARGIN*2, AppWindow.HEIGHT - GraphicalSettings.LATERAL_MARGIN*2);
            // BORDER
            graphics.setColor(Color.WHITE);
            graphics.drawRect(GraphicalSettings.LATERAL_MARGIN, GraphicalSettings.LATERAL_MARGIN,
                    AppWindow.WIDTH - GraphicalSettings.LATERAL_MARGIN*2, AppWindow.HEIGHT - GraphicalSettings.LATERAL_MARGIN*2);
            // TEXT
            graphics.drawString("PLAYER1:      PLAYER2:", GraphicalSettings.LATERAL_MARGIN*2, GraphicalSettings.LATERAL_MARGIN*2+10);
            graphics.drawString("    W =>      UP      <= ▲ ", GraphicalSettings.LATERAL_MARGIN*2, GraphicalSettings.LATERAL_MARGIN*3+15);
            graphics.drawString("    S =>   DOWN   <= ▼", GraphicalSettings.LATERAL_MARGIN*2, GraphicalSettings.LATERAL_MARGIN*4+20);
            buttonExitInfo.render(graphics);
        }
    }

}
