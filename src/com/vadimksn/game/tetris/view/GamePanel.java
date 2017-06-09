package com.vadimksn.game.tetris.view;


import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.controller.PaintController;
import com.vadimksn.game.tetris.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class GamePanel extends JPanel implements ActionListener {
    public static final int ROW_COUNT = 20;
    public static final int COLUMNS_COUNT = 10;
    public static final int BORDER_SIZE = 5;
    public static final int TILE_SIZE = 25;
    public static final int PANEL_WIDTH = TILE_SIZE * COLUMNS_COUNT + BORDER_SIZE * 2;
    public static final int PANEL_HEIGHT = TILE_SIZE * ROW_COUNT + BORDER_SIZE * 2;
    public static final Color BASE_DARK_COLOR = new Color(26, 63, 81);
    public static final Color BASE_LIGHT_COLOR = new Color(45, 151, 166);
    public static final Color BASE_LIGHT_COLOR2 = new Color(31, 103, 114);

    private Timer timer = new Timer(300, this);

    public static void main(String[] args) {
        int[][] mas = new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}, {5, 5, 5}};
        System.out.println(mas.length);
        System.out.println(Arrays.deepToString(mas));
        System.arraycopy(mas, 0, mas, 1, 3);
        int s[]=new int[3];
        mas[0]=s;
        System.out.println(Arrays.deepToString(mas));

    }


    public GamePanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BASE_DARK_COLOR);
        timer.start();
    }


    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.translate(BORDER_SIZE, BORDER_SIZE);

        PaintController.getINSTANCE().paintGameMas(GameController.getINSTANCE().getGameMas(), g);
        PaintController.getINSTANCE().paintShape(GameController.getINSTANCE().getCurrentShape(), g);

        g.setColor(BASE_LIGHT_COLOR2);
        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROW_COUNT; y++) {
                if (y > 0) g.drawLine(0, y * TILE_SIZE, TILE_SIZE * COLUMNS_COUNT, y * TILE_SIZE);
                if (x > 0) g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, TILE_SIZE * ROW_COUNT);
            }
        }
        g.setColor(BASE_LIGHT_COLOR);
        g.drawRoundRect(0, 0, TILE_SIZE * COLUMNS_COUNT, TILE_SIZE * ROW_COUNT, 10, 10);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        GameController.getINSTANCE().start();
        repaint();
    }
}
