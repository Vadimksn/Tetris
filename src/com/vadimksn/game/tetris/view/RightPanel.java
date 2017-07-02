package com.vadimksn.game.tetris.view;

import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.model.Tile;

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
    private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
    private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 14);
    private static final Font FONT_FOR_ARROWS = new Font("Times New Roman", Font.PLAIN, 14);


    private RightPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BASE_DARK_COLOR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(BORDER_SIZE, BORDER_SIZE);
        g.setColor(BASE_LIGHT_COLOR);
        int offsetByY = TILE_SIZE;
        int smallOffsetByX = 0;
        int largeOffsetByX = 20;
//        g.setFont(LARGE_FONT);
//        g.drawString("SCORE", TILE_SIZE, TILE_SIZE - BORDER_SIZE);
//        g.drawString("LINES", TILE_SIZE + BORDER_SIZE * 2, TILE_SIZE * 3 - BORDER_SIZE);
//        g.drawString("LEVEL", TILE_SIZE + BORDER_SIZE * 2, TILE_SIZE * 5 - BORDER_SIZE);
//
//        g.setColor(BASE_LIGHT_COLOR);
//        g.drawString("" + GameController.getINSTANCE().getScore(), BORDER_SIZE, TILE_SIZE * 2 - BORDER_SIZE);
//        g.drawString("" + GameController.getINSTANCE().getLines(), TILE_SIZE * 2, TILE_SIZE * 4 - BORDER_SIZE);
//        g.drawString("" + GameController.getINSTANCE().getLevel(), TILE_SIZE * 2, TILE_SIZE * 6 - BORDER_SIZE);
//        g.drawRoundRect(0, TILE_SIZE, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
//        g.drawRoundRect(0, TILE_SIZE * 3, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
//        g.drawRoundRect(0, TILE_SIZE * 5, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);

        g.setFont(LARGE_FONT);
        g.drawString("Stats", smallOffsetByX, offsetByY);
        g.setFont(SMALL_FONT);
//        g.drawRoundRect(0, TILE_SIZE, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
//        g.drawRoundRect(0, TILE_SIZE*2, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
//        g.drawRoundRect(0, TILE_SIZE*3, PANEL_WIDTH - BORDER_SIZE * 3, TILE_SIZE, 10, 10);
        g.drawString("Score: " + GameController.getINSTANCE().getScore(), largeOffsetByX, offsetByY += TILE_SIZE);
        g.drawString("Lines : " + GameController.getINSTANCE().getLines(), largeOffsetByX, offsetByY += TILE_SIZE);
        g.drawString("Level : " + GameController.getINSTANCE().getLevel(), largeOffsetByX, offsetByY += TILE_SIZE);

        g.setFont(LARGE_FONT);
        g.drawString("Controls", smallOffsetByX, (offsetByY += TILE_SIZE * 8)-5);
        g.setFont(FONT_FOR_ARROWS);
        g.drawString("←", largeOffsetByX, offsetByY += TILE_SIZE);
        g.setFont(SMALL_FONT);
        g.drawString(" Move Left", largeOffsetByX * 2, offsetByY);
        g.setFont(FONT_FOR_ARROWS);
        g.drawString("→", largeOffsetByX, offsetByY += TILE_SIZE);
        g.setFont(SMALL_FONT);
        g.drawString(" Move Right", largeOffsetByX * 2, offsetByY);
        g.setFont(FONT_FOR_ARROWS);
        g.drawString(" ↑ ", largeOffsetByX, offsetByY += TILE_SIZE);
        g.setFont(SMALL_FONT);
        g.drawString(" Rotate", largeOffsetByX * 2, offsetByY);
        g.setFont(FONT_FOR_ARROWS);
        g.drawString(" ↓ ", largeOffsetByX, offsetByY += TILE_SIZE);
        g.setFont(SMALL_FONT);
        g.drawString(" Drop", largeOffsetByX * 2, offsetByY);


        g.drawString(" P - Pause", largeOffsetByX, offsetByY += TILE_SIZE);
        g.drawString(" R - Restart", largeOffsetByX, offsetByY += TILE_SIZE);


    }

    public static RightPanel getINSTANCE() {
        return INSTANCE;
    }
}
