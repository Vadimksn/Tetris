package com.vadimksn.game.tetris.controller;

import com.vadimksn.game.tetris.model.Tile;
import com.vadimksn.game.tetris.model.TileColor;
import com.vadimksn.game.tetris.view.BaseColors;
import com.vadimksn.game.tetris.view.GamePanel;
import com.vadimksn.game.tetris.view.LeftPanel;
import com.vadimksn.game.tetris.view.RightPanel;

import java.awt.*;

public class PaintController {
    private static final PaintController INSTANCE = new PaintController();
    private int[][] tetrisView = {
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1,},
            {0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0,},
            {0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1,},
            {0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1,},
            {0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1,},
    };


    private PaintController() {
    }

    public void paintTile(Tile tile, Graphics g) {
        int tileSize = GamePanel.TILE_SIZE;
        g.setColor(tile.getTileColor());
        g.fill3DRect(tile.getX() * tileSize, tile.getY() * tileSize, tileSize, tileSize, true);
    }

    public void paintTile(int x, int y, int shifyByY, int shifyByX, int tileSize, Color color, Graphics g) {
        g.setColor(color);
        g.fill3DRect(x * tileSize + shifyByX, y * tileSize + shifyByY, tileSize, tileSize, true);
    }

    public void moveAndPaintTile(int x, int pixelsByX, int y, int pixelsByY, Tile tile, Graphics g) {
        int tileSize = GamePanel.TILE_SIZE;
        g.setColor(tile.getTileColor());
        g.fill3DRect((tile.getX() + x) * tileSize + pixelsByX, (tile.getY() + y) * tileSize + pixelsByY, tileSize, tileSize, true);
    }

    public void paintGameMas(Tile gameMas[][], Graphics g) {
        for (Tile[] gameMasY : gameMas) {
            for (Tile tile : gameMasY) {
                if (tile != null) paintTile(tile, g);
            }
        }
    }

    public void paintShape(Tile[][] shape, Graphics g) {
        for (Tile[] aShape : shape) {
            for (Tile anAShape : aShape) {
                if (anAShape != null) paintTile(anAShape, g);
            }
        }
    }

    public void moveAndPaintShape(int x, int pixelsByX, int y, int pixelsByY, Tile[][] shape, Graphics g) {
        for (Tile[] shapeY : shape) {
            for (Tile tile : shapeY) {
                if (tile != null) moveAndPaintTile(x, pixelsByX, y, pixelsByY, tile, g);
            }
        }
    }

    public void paintStartView(Graphics g) {
        int tileSize = 11;
        int rows = 5;
        int columns = 21;
        int shiftByY = GamePanel.TILE_SIZE * 4 + 10;
        int shiftByX = 10;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (tetrisView[y][x] == 1) {
                    Color color = Color.WHITE;
                    if (x < 3) {
                        color = TileColor.Z.getColor();
                    } else if (x > 3 && x < 7) {
                        color = TileColor.L.getColor();
                    } else if (x > 7 && x < 11) {
                        color = TileColor.O.getColor();
                    } else if (x > 11 && x < 15) {
                        color = TileColor.S.getColor();
                    } else if (x > 15 && x < 17) {
                        color = TileColor.I.getColor();
                    } else if (x > 17 && x < 21) {
                        color = TileColor.T.getColor();
                    }
                    paintTile(x, y, shiftByY, shiftByX, tileSize, color, g);
                }
            }
        }
        g.setColor(BaseColors.BASE_DARK_COLOR.getColor());
        Polygon polygon;
        int[] yPoints1 = {shiftByY, shiftByY, shiftByY + tileSize};
        int[] xPoints1 = {shiftByX + tileSize * 14, shiftByX + tileSize * 15, shiftByX + tileSize * 15};
        polygon = new Polygon(xPoints1, yPoints1, 3);
        g.fillPolygon(polygon);
        int[] yPoints2 = {shiftByY + tileSize * 2, shiftByY + tileSize * 3, shiftByY + tileSize * 2 + tileSize / 2};
        int[] xPoints2 = {shiftByX + tileSize * 15, shiftByX + tileSize * 15, shiftByX + tileSize * 14 + tileSize / 2};
        polygon = new Polygon(xPoints2, yPoints2, 3);
        g.fillPolygon(polygon);


    }

    public void paintGrid(int rows, int columns, int tileSize, Color color, Graphics g) {


    }

    public static PaintController getINSTANCE() {
        return INSTANCE;
    }
}
