package com.vadimksn.game.tetris.view;

import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.controller.PaintController;
import com.vadimksn.game.tetris.model.Tile;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private static final int ROW_COUNT = 9;
    private static final int COLUMNS_COUNT = 5;
    private static final int TILE_SIZE = GamePanel.TILE_SIZE;
    private static final int BORDER_SIZE = GamePanel.TILE_SIZE + GamePanel.BORDER_SIZE;
    private static final int PANEL_WIDTH = GamePanel.TILE_SIZE * 5 + BORDER_SIZE * 2;
    private static final int PANEL_HEIGHT = GamePanel.PANEL_HEIGHT;
    private static final Color BASE_DARK_COLOR = BaseColors.BASE_DARK_COLOR.getColor();
    private static final Color BASE_LIGHT_COLOR = BaseColors.BASE_LIGHT_COLOR.getColor();
    private static final Color BASE_LIGHT_COLOR2 = BaseColors.BASE_LIGHT_COLOR2.getColor();
    private static final LeftPanel INSTANCE = new LeftPanel();
    private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
    private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 14);
    private GameController gameController = GameController.getINSTANCE();


    private LeftPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BASE_DARK_COLOR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(BORDER_SIZE, BORDER_SIZE);

        g.setColor(BASE_LIGHT_COLOR);
        g.setFont(LARGE_FONT);
        g.drawString("Next", TILE_SIZE * 2 - 5, -5);
//        g.drawLine(0, 0, TILE_SIZE * COLUMNS_COUNT, 0);
        g.drawLine(0, TILE_SIZE * 6, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * 6);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(0, TILE_SIZE * 3, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * 3);
        if (gameController.isGameRunning()) {
            paintNextShape(6, gameController.getNextShape0(), g);
            paintNextShape(3, gameController.getNextShape1(), g);
            paintNextShape(0, gameController.getNextShape2(), g);
        }
        g.setColor(BASE_LIGHT_COLOR);
        g.drawRoundRect(0, 0, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * ROW_COUNT, 10, 10);


        int offsetByY = TILE_SIZE * 11;
        g.drawRoundRect(-15, offsetByY, TILE_SIZE * COLUMNS_COUNT + 30, TILE_SIZE * 19 - offsetByY, 10, 10);
//        g.drawString("High Scores", TILE_SIZE, TILE_SIZE * 11 - 5);
        g.drawString("HIGH SCORES", TILE_SIZE - 12, offsetByY - 5);
//        g.drawLine(-15, offsetByY, TILE_SIZE * COLUMNS_COUNT + 15, offsetByY);


        g.setFont(SMALL_FONT);
        g.drawString("Raman", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("1000000", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("Batya Oleg", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("100000", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("Vadya", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("10000", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("Imant", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("1000", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("Vasya", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("100", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("Vasya", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("100", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("Vasya", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("100", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawString("Vasya", -5, (offsetByY += TILE_SIZE) - 5);
        g.drawString("100", TILE_SIZE * 3, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR2);
        g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
        g.setColor(BASE_LIGHT_COLOR);
        g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);


//        g.drawLine(TILE_SIZE * 3 - 10, TILE_SIZE * 13, TILE_SIZE * 3 - 10, TILE_SIZE * 13 - 5);
//        g.drawLine(-10, TILE_SIZE * 13, TILE_SIZE * COLUMNS_COUNT + 10, TILE_SIZE * 13);
//        g.drawLine(TILE_SIZE * 3 - 10, TILE_SIZE * 14, TILE_SIZE * 3 - 10, TILE_SIZE * 14 - 5);
//        g.drawLine(-10, TILE_SIZE * 14, TILE_SIZE * COLUMNS_COUNT + 10, TILE_SIZE * 14);
//        g.drawLine(TILE_SIZE * 3 - 10, TILE_SIZE * 15, TILE_SIZE * 3 - 10, TILE_SIZE * 15 - 5);
//        g.drawLine(-10, TILE_SIZE * 15, TILE_SIZE * COLUMNS_COUNT + 10, TILE_SIZE * 15);
//        g.drawLine(TILE_SIZE * 3 - 10, TILE_SIZE * 16, TILE_SIZE * 3 - 10, TILE_SIZE * 16 - 5);
//        g.drawLine(-10, TILE_SIZE * 16, TILE_SIZE * COLUMNS_COUNT + 10, TILE_SIZE * 16);

    }


    public void paintNextShape(int y, Tile[][] nextShape, Graphics g) {
        if (nextShape[0].length == 2) {
            PaintController.getINSTANCE().moveAndPaintShape(-3, TILE_SIZE / 2 + 1, y, TILE_SIZE / 2 + 1, nextShape, g);
        } else if (nextShape[0].length == 4) {
            PaintController.getINSTANCE().moveAndPaintShape(-3, TILE_SIZE / 2 + 1, 1 + y, 0, nextShape, g);
        } else {
            PaintController.getINSTANCE().moveAndPaintShape(-3, 0, y, TILE_SIZE / 2 + 1, nextShape, g);
        }
    }

    public static int getRowCount() {
        return ROW_COUNT;
    }

    public static int getColumnsCount() {
        return COLUMNS_COUNT;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getBorderSize() {
        return BORDER_SIZE;
    }

    public static int getPanelWidth() {
        return PANEL_WIDTH;
    }

    public static int getPanelHeight() {
        return PANEL_HEIGHT;
    }

    public static Color getBaseDarkColor() {
        return BASE_DARK_COLOR;
    }

    public static Color getBaseLightColor() {
        return BASE_LIGHT_COLOR;
    }

    public static Color getBaseLightColor2() {
        return BASE_LIGHT_COLOR2;
    }

    public static LeftPanel getINSTANCE() {
        return INSTANCE;
    }
}
