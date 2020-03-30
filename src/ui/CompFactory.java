package ui;

import javax.swing.*;

public class CompFactory {
    public static JButton button() {
        JButton b = new JButton();
        b.setFont(FontFactory.main);
        b.setBackground(ColorFactory.main);
        b.setForeground(ColorFactory.white);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        return b;
    }

    public static JLabel label() {
        JLabel l = new JLabel();
        l.setFont(FontFactory.main);
        l.setBackground(ColorFactory.back);
        l.setForeground(ColorFactory.white);
        return l;
    }
}
