package com.GromHoll.arkanoid.entity;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.GromHoll.arkanoid.screen.Art;
import com.GromHoll.arkanoid.screen.Bitmap;

public class Paddle extends Entity implements KeyListener, IMovable {
    public static final int W = Art.paddle.w;
    public static final int H = Art.paddle.h;
    
    public boolean rPress = false;
    public boolean lPress = false;
    
    public Paddle(double x, double y) {
        super(x, y);
    }
    
    @Override
    public Bitmap getTile() {
        return Art.paddle;
    }

    @Override
    public void move() {
        if(rPress) x += 2;
        if(lPress) x -= 2;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            lPress = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rPress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            lPress = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rPress = false;
        }   
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public Rectangle getBB() {
        return new Rectangle((int) x, (int) y, W, H);
    }
    
}
