/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.swing;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class CustomCombobox extends JComboBox {

    public CustomCombobox() {
        this.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new BasicArrowButton(BasicArrowButton.SOUTH);
                button.setContentAreaFilled(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g,
                                                    Rectangle bounds,
                                                    boolean hasFocus)
            {
                g.setColor(Color.WHITE);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.WHITE);
    }
}
