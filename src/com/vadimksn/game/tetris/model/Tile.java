package com.vadimksn.game.tetris.model;

import java.awt.*;

public class Tile {
    private int x, y;
    private Color tileColor;

    public Tile(int y, int x, Color tileColor) {
        this.x = x;
        this.y = y;
        this.tileColor = tileColor;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public void setTileColor(Color tileColor) {
        this.tileColor = tileColor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", tileColor=" + tileColor +
                '}';
    }
}
