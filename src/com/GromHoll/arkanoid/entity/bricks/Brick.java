package com.GromHoll.arkanoid.entity.bricks;

import java.awt.Rectangle;

import com.GromHoll.arkanoid.entity.Entity;

public abstract class Brick extends Entity {
    public static final int W = 20;
    public static final int H = 20;
    
    public boolean destroyed = false;
    
    protected Brick(double x, double y) {
        super(x, y);
    }
    
    public abstract void hit();
    
    public void loot() {
        // TODO Looting
    }
    
    @Override
    public Rectangle getBB() {
        return new Rectangle((int) x, (int) y, W, H);
    }
    
}
