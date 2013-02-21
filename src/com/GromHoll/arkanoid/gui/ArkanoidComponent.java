package com.GromHoll.arkanoid.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

public class ArkanoidComponent extends Canvas {
    private static final long serialVersionUID = 1L;
    
    public static final int GAME_WIDTH  = 800;
    public static final int GAME_HEIGHT = 450;
    
    public ArkanoidComponent() {
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setMinimumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setMaximumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }


    public void start() {
        // TODO add Timer's starting logic
        Graphics g = getGraphics();
        
        g.dispose();
    }
    
}
