package com.vadimksn.game.tetris.view;

import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.controller.PaintController;
import com.vadimksn.game.tetris.model.Player;
import com.vadimksn.game.tetris.model.Tile;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    public static final int ROW_COUNT = 9;
    public static final int COLUMNS_COUNT = 5;
    public static final int TILE_SIZE = GamePanel.TILE_SIZE;
    public static final int BORDER_SIZE = GamePanel.TILE_SIZE + GamePanel.BORDER_SIZE;
    public static final int PANEL_WIDTH = GamePanel.TILE_SIZE * 5 + BORDER_SIZE * 2;
    public static final int PANEL_HEIGHT = GamePanel.PANEL_HEIGHT;
    public static final Color DARK_COLOR = BaseColors.DARK_COLOR.getColor();
    public static final Color LIGHT_COLOR = BaseColors.LIGHT_COLOR.getColor();
    public static final Color LIGHT_COLOR2 = BaseColors.LIGHT_COLOR2.getColor();
    public static final Color FONT_COLOR = BaseColors.FONT_COLOR.getColor();
    public static final LeftPanel INSTANCE = new LeftPanel();
    public static final Font SMALL_FONT = BaseFonts.SMALL_FONT.getFont();
    public static final Font LARGE_FONT = BaseFonts.LARGE_FONT.getFont();
    public GameController gameController = GameController.getINSTANCE();


    private LeftPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(DARK_COLOR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(BORDER_SIZE, BORDER_SIZE);

        g.setColor(DARK_COLOR.darker());
        g.fillRoundRect(0, 0, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * ROW_COUNT, 10, 10);

        g.setFont(LARGE_FONT);
        g.setColor(FONT_COLOR);
        g.drawString("Next", TILE_SIZE * 2 - 5, -5);
        if (gameController.isGameRunning()) {
            g.setColor(LIGHT_COLOR);
            g.drawLine(0, TILE_SIZE * 6, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * 6);
            g.setColor(LIGHT_COLOR2);
            g.drawLine(0, TILE_SIZE * 3, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * 3);
            paintNextShape(6, gameController.getNextShape0(), g);
            paintNextShape(3, gameController.getNextShape1(), g);
            paintNextShape(0, gameController.getNextShape2(), g);
        }
        g.setColor(LIGHT_COLOR);
        g.drawRoundRect(0, 0, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * ROW_COUNT, 10, 10);


        int offsetByY = TILE_SIZE * 11;
        g.setColor(DARK_COLOR.darker());
        g.fillRoundRect(-15, offsetByY, TILE_SIZE * COLUMNS_COUNT + 30, TILE_SIZE * 19 - offsetByY, 10, 10);
        g.setColor(LIGHT_COLOR);
        g.drawRoundRect(-15, offsetByY, TILE_SIZE * COLUMNS_COUNT + 30, TILE_SIZE * 19 - offsetByY, 10, 10);
        g.setColor(FONT_COLOR);
        g.drawString("High Scores", TILE_SIZE, TILE_SIZE * 11 - 5);


        g.setFont(SMALL_FONT);
        for (int i = 0; i < 8; i++) {
            if (gameController.getHighScores()[i] != null) {
                Player player = gameController.getHighScores()[i];
                g.setColor(FONT_COLOR);
                g.drawString(player.getName(), -5, (offsetByY += TILE_SIZE) - 5);
                g.drawString(String.valueOf(player.getScore()), TILE_SIZE * 3, offsetByY - 5);
            } else offsetByY += TILE_SIZE;
            g.setColor(LIGHT_COLOR2);
            g.drawLine(TILE_SIZE * 3 - 10, offsetByY, TILE_SIZE * 3 - 10, offsetByY - 5);
            g.drawLine(-10, offsetByY, TILE_SIZE * COLUMNS_COUNT + 10, offsetByY);
        }
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

    public static Color getDarkColor() {
        return DARK_COLOR;
    }

    public static Color getLightColor() {
        return LIGHT_COLOR;
    }

    public static Color getLightColor2() {
        return LIGHT_COLOR2;
    }

    public static LeftPanel getINSTANCE() {
        return INSTANCE;
    }
}
