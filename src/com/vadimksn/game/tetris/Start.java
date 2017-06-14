package com.vadimksn.game.tetris;

import com.vadimksn.game.tetris.controller.Direction;
import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Start extends JFrame {

    public Start() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                GameController gameController = GameController.getINSTANCE();

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_LEFT:
                        if (gameController.canMoveShape(Direction.LEFT, gameController.getCurrentShape()))
                            gameController.move(Direction.LEFT, gameController.getCurrentShape());
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (gameController.canMoveShape(Direction.RIGHT, gameController.getCurrentShape()))
                            gameController.move(Direction.RIGHT, gameController.getCurrentShape());
                        break;
                    case KeyEvent.VK_DOWN:
                        if (gameController.canShapeStepDown(gameController.getCurrentShape()))
                            gameController.stepDownShape(gameController.getCurrentShape());
                        break;
                    case KeyEvent.VK_UP:
                        gameController.rotate(gameController.getCurrentShape());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_DOWN:

                        break;
                }

            }

        });
    }

    public static void main(String[] args) {
        Start start = new Start();


    }
}
