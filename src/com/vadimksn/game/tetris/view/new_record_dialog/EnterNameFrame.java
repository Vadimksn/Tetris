package com.vadimksn.game.tetris.view.new_record_dialog;


import com.vadimksn.game.tetris.controller.GameController;
import com.vadimksn.game.tetris.controller.PaintController;
import com.vadimksn.game.tetris.model.Player;
import com.vadimksn.game.tetris.view.BaseColors;
import com.vadimksn.game.tetris.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EnterNameFrame extends JFrame {
    private final int TILE_SIZE = GamePanel.TILE_SIZE;
    private final int WIDTH = 0;
    private final int HEIGHT = TILE_SIZE * 2;
    private Color color = BaseColors.DARK_COLOR.getColor();
    private JTextField textField = new JTextField();
    private JLabel jlabel = new JLabel("Enter your name :");
    private Font font = new Font("Tahoma", Font.BOLD, 15);
    private JPanel panel = new JPanel();


    public EnterNameFrame() {
        PaintController.getINSTANCE().setShowRecordNameInput(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setBackground(color);
        textField.setFont(font);
        textField.setBackground(color);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        panel.add(textField);
        textField.setDocument(new JTextFieldLimit(10));
        jlabel.setFont(font);
        jlabel.setForeground(Color.WHITE);
        panel.add(jlabel, BorderLayout.NORTH);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        textField.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                            Player[] highScores = GameController.getINSTANCE().getHighScores();
                            for (int i = highScores.length - 1; i >= 0; i--) {
                                if (highScores[i].getScore() == GameController.getINSTANCE().getScore()) {
                                    highScores[i].setName(textField.getText());
                                    GameController.getINSTANCE().saveHighScoresToFile();
                                    break;
                                }
                            }
                            dispose();
                        }
                    }
                }
        );
    }


    public static void main(String[] args) {
        EnterNameFrame enterNameFrame = new EnterNameFrame();
    }

}
