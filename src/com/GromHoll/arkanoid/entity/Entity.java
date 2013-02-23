package com.GromHoll.arkanoid.entity;

import com.GromHoll.arkanoid.math.BoundingBox;
import com.GromHoll.arkanoid.screen.Bitmap;

public abstract class Entity {
    
    public double x, y;

    protected Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
        
    public abstract BoundingBox getBB();
    
    public abstract Bitmap getTile();
    
}
