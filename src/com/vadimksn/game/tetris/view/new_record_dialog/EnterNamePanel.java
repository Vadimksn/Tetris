package com.vadimksn.game.tetris.view.new_record_dialog;

import com.vadimksn.game.tetris.view.BaseColors;
import com.vadimksn.game.tetris.view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class EnterNamePanel extends JPanel {
    private final int TILE_SIZE = GamePanel.TILE_SIZE;
    private final int WIDTH = 0;
    private final int HEIGHT = TILE_SIZE * 2;
    private Color color = BaseColors.DARK_COLOR.getColor();
    private JTextField textField = new JTextField();
    private JLabel jlabel = new JLabel("Enter your name :");
    private Font font = new Font("Tahoma", Font.BOLD, 15);


    public EnterNamePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(color);
        textField.setBounds(0, TILE_SIZE * 5, TILE_SIZE * 4, TILE_SIZE);
        textField.setFont(font);
        textField.setBackground(color);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        add(textField);
        textField.setDocument(new JTextFieldLimit(10));
        jlabel.setFont(font);
        jlabel.setForeground(Color.WHITE);
        add(jlabel, BorderLayout.NORTH);
    }


}
