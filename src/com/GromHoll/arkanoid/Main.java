package com.GromHoll.arkanoid;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.GromHoll.arkanoid.gui.ArkanoidComponent;



public class Main {

    public static void main(String[] args) {
        ArkanoidComponent ac = new ArkanoidComponent();
        JFrame frame = new JFrame("GromHolloid");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(ac);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ac.start();
    }

}
