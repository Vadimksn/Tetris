package com.vadimksn.game.tetris.model;

import java.awt.*;

public enum TileColor {
    O(new Color(0xf0f000)),
    I(new Color(0x00f0f0)),
    T(new Color(0xa000f0)),
    Z(new Color(0xf00000)),
    S(new Color(0x00f000)),
    J(new Color(0x0000f0)),
    L(new Color(0xf0a000)),;

    private Color color;

    TileColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
