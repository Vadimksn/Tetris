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

    public void paintGameMas(Tile gameMas[][], Graphics g) {
        for (Tile[] gameMasY : gameMas) {
            for (Tile tile : gameMasY) {
                if (tile != null) paintTile(tile, g);
            }
        }
    }

    public void paintShape(Tile[][] shape, Graphics g) {
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] != null) paintTile(shape[y][x], g);
            }
        }


    }

    public static PaintController getINSTANCE() {
        return INSTANCE;
    }
}
