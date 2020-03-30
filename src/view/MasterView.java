package view;

import ui.ColorFactory;

import javax.swing.*;
import java.awt.*;

public class MasterView extends JFrame {
    public static MasterView inst;

    public MasterView() {
        super("BI2DetectionCarer");
        setPreferredSize(new Dimension(1000, 700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(ColorFactory.back);


        pack();
        setVisible(true);
        inst = this;
        inst.changeScene(new InitialSettingView());
    }

    public void changeScene(JPanel scene) {
        getContentPane().removeAll();
        getContentPane().add(scene);
        validate();
    }
}
