package com.vadimksn.game.tetris.view;

import java.awt.*;

public enum BaseColors {
    FONT_COLOR(Color.WHITE),
    DARK_TRANSPARENT_COLOR(new Color(26, 63, 81, 150)),
    DARK_COLOR(new Color(26, 63, 81)),
    LIGHT_COLOR(new Color(45, 151, 166)),
    LIGHT_COLOR2(new Color(31, 103, 114)),
    PAUSE_COLOR(Color.WHITE),
    GAME_OVER_COLOR(Color.WHITE);

    private Color color;

    BaseColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
