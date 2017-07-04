package com.vadimksn.game.tetris.view;

import java.awt.*;

public enum BaseColors {
    BASE_FONT_COLOR(Color.WHITE),
    BASE_DARK_COLOR(new Color(26, 63, 81)),
    BASE_LIGHT_COLOR(new Color(45, 151, 166)),
    BASE_LIGHT_COLOR2(new Color(31, 103, 114));

    private Color color;

    BaseColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
