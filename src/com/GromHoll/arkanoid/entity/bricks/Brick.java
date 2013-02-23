package com.GromHoll.arkanoid.entity.bricks;

import com.GromHoll.arkanoid.entity.Entity;
import com.GromHoll.arkanoid.math.BoundingBox;

public abstract class Brick extends Entity {
    public static final int W = 20;
    public static final int H = 20;
    
    protected Brick(double x, double y) {
        super(x, y);
    }
    
    public abstract void hit();
    
    public void loot() {
        // TODO Looting
    }
    
    public void destroy() {
        
    }
    
    @Override
    public BoundingBox getBB() {
        return new BoundingBox(x, y, x + W, y + H);
    }
    
}
