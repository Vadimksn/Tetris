package com.vadimksn.game.tetris;

import com.vadimksn.game.tetris.controller.Direction;
import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.view.GamePanel;
import com.vadimksn.game.tetris.view.LeftPanel;
import com.vadimksn.game.tetris.view.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Start extends JFrame {
    public GamePanel gamePanel = new GamePanel();
    public LeftPanel leftPanel =  LeftPanel.getINSTANCE();
    public RightPanel rightPanel = new RightPanel();

    public Start() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(gamePanel,BorderLayout.CENTER);
        add(leftPanel,BorderLayout.WEST);
        add(rightPanel,BorderLayout.EAST);
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
                        gamePanel.repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (gameController.canMoveShape(Direction.RIGHT, gameController.getCurrentShape()))
                            gameController.move(Direction.RIGHT, gameController.getCurrentShape());
                        gamePanel.repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (gameController.canShapeStepDown(gameController.getCurrentShape()))
                            gameController.stepDownShape(gameController.getCurrentShape());
                        gamePanel.repaint();
                        break;
                    case KeyEvent.VK_UP:
                        gameController.rotate(gameController.getCurrentShape());
                        gamePanel.repaint();
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
