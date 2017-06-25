package com.vadimksn.game.tetris.controller;

import com.vadimksn.game.tetris.model.Tile;
import com.vadimksn.game.tetris.view.GamePanel;

import java.awt.*;

public class PaintController {
    private static final PaintController INSTANCE = new PaintController();


    private PaintController() {
    }

    public void paintTile(Tile tile, Graphics g) {
        int tileSize = GamePanel.TILE_SIZE;
        g.setColor(tile.getTileColor());
        g.fill3DRect(tile.getX() * tileSize, tile.getY() * tileSize, tileSize, tileSize, true);
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

    public static PaintController getINSTANCE() {
        return INSTANCE;
    }
}
