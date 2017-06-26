package com.vadimksn.game.tetris.view;

import com.vadimksn.game.tetris.controller.PaintController;
import com.vadimksn.game.tetris.model.Figure;
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
    private Tile[][] nextShape0 = Figure.getRandomFigure();
    private Tile[][] nextShape1 = Figure.getRandomFigure();
    private Tile[][] nextShape2 = Figure.getRandomFigure();


    private LeftPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BASE_DARK_COLOR);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(BORDER_SIZE, BORDER_SIZE);

        g.setColor(BASE_LIGHT_COLOR);
        Font font = new Font("Arial", Font.CENTER_BASELINE, 20);
        g.setFont(font);
        g.drawString("NEXT", TILE_SIZE + 10, TILE_SIZE - 5);

        g.setColor(BASE_LIGHT_COLOR2);
//        g.drawLine(0, TILE_SIZE * 4, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * 4);
        g.drawLine(0, TILE_SIZE * 7, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * 7);

        paintNextShape(7, nextShape0, g);
        paintNextShape(4, nextShape1, g);
        paintNextShape(1, nextShape2, g);

        g.setColor(BASE_LIGHT_COLOR);
        g.drawRoundRect(0, TILE_SIZE, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * ROW_COUNT, 10, 10);
    }

    public void update(){
        setNextShape0(nextShape1);
        setNextShape1(nextShape2);
        setNextShape2(Figure.getRandomFigure());
        repaint();
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

    public Tile[][] getNextShape0() {
        return nextShape0;
    }

    public void setNextShape0(Tile[][] nextShape0) {
        this.nextShape0 = nextShape0;
    }

    public Tile[][] getNextShape1() {
        return nextShape1;
    }

    public void setNextShape1(Tile[][] nextShape1) {
        this.nextShape1 = nextShape1;
    }

    public Tile[][] getNextShape2() {
        return nextShape2;
    }

    public void setNextShape2(Tile[][] nextShape2) {
        this.nextShape2 = nextShape2;
    }
}
