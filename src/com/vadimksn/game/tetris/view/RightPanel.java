package com.vadimksn.game.tetris.view;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    public static final int BORDER_SIZE = GamePanel.TILE_SIZE;
    public static final int PANEL_WIDTH = GamePanel.TILE_SIZE * 5 + BORDER_SIZE * 2;
    public static final int PANEL_HEIGHT = GamePanel.PANEL_HEIGHT;
    public static final Color BASE_DARK_COLOR = BaseColors.BASE_DARK_COLOR.getColor();
    public static final Color BASE_LIGHT_COLOR = BaseColors.BASE_LIGHT_COLOR.getColor();
    public static final Color BASE_LIGHT_COLOR2 = BaseColors.BASE_LIGHT_COLOR2.getColor();

    public RightPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BASE_DARK_COLOR);
    }


}
