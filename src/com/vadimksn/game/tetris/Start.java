package com.vadimksn.game.tetris;

import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {
    private GameController gameController = GameController.getINSTANCE();

    public Start() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Start start = new Start();


    }
}
