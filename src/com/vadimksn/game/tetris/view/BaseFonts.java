package com.vadimksn.game.tetris.view;

import java.awt.*;

public enum BaseFonts {
    SMALL_FONT(new Font("Tahoma", Font.PLAIN, 12)),
    LARGE_FONT(new Font("Tahoma", Font.BOLD, 14));

    private Font font;

    BaseFonts(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

}
