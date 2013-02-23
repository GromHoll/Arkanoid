package com.GromHoll.arkanoid.entity;

import java.awt.Rectangle;

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
        
    public abstract Rectangle getBB();
    
    public abstract Bitmap getTile();
    
}
