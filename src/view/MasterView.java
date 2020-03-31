package view;

import ui.ColorFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MasterView extends JFrame implements KeyListener {
    public static MasterView inst;
    private JPanel scene;

    public MasterView() {
        super("BI2DetectionInspector");
        addKeyListener(this);
        setPreferredSize(new Dimension(1000, 700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(ColorFactory.back);


        pack();
        setVisible(true);
        setFocusable(true);
        inst = this;
        inst.changeScene(new InitialSettingView());
    }

    public void changeScene(JPanel scene) {
        getContentPane().removeAll();
        this.scene=scene;
        getContentPane().add(scene);
        validate();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(scene instanceof KeyListener)((KeyListener) scene).keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(scene instanceof KeyListener)((KeyListener) scene).keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(scene instanceof KeyListener)((KeyListener) scene).keyReleased(e);
    }
}
