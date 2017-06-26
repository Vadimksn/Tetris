package com.vadimksn.game.tetris.view;

import com.vadimksn.game.tetris.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    public static final int TILE_SIZE = GamePanel.TILE_SIZE;
    public static final int BORDER_SIZE = GamePanel.BORDER_SIZE;
    public static final int PANEL_WIDTH = GamePanel.TILE_SIZE * 5 + BORDER_SIZE * 2;
    public static final int PANEL_HEIGHT = GamePanel.PANEL_HEIGHT;
    public static final Color BASE_DARK_COLOR = BaseColors.BASE_DARK_COLOR.getColor();
    public static final Color BASE_LIGHT_COLOR = BaseColors.BASE_LIGHT_COLOR.getColor();
    public static final Color BASE_LIGHT_COLOR2 = BaseColors.BASE_LIGHT_COLOR2.getColor();
    private static final RightPanel INSTANCE = new RightPanel();


    private RightPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BASE_DARK_COLOR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(BORDER_SIZE, BORDER_SIZE);

        g.setColor(BASE_LIGHT_COLOR);
        Font font = new Font("Helvetica-Bold", Font.CENTER_BASELINE, 20);
        g.setFont(font);
        g.drawString("SCORE", TILE_SIZE, TILE_SIZE - BORDER_SIZE);
        g.drawString("LINES", TILE_SIZE + BORDER_SIZE * 2, TILE_SIZE * 3 - BORDER_SIZE);
        g.drawString("LEVEL", TILE_SIZE + BORDER_SIZE * 2, TILE_SIZE * 5 - BORDER_SIZE);

        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("" + GameController.getINSTANCE().getScore(), TILE_SIZE * 2, TILE_SIZE * 2 - BORDER_SIZE);
        g.drawRoundRect(0, TILE_SIZE, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
        g.drawRoundRect(0, TILE_SIZE * 3, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
        g.drawRoundRect(0, TILE_SIZE * 5, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
    }

    public static RightPanel getINSTANCE() {
        return INSTANCE;
    }
}
