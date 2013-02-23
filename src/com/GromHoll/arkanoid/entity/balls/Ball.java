package com.GromHoll.arkanoid.entity.balls;

import com.GromHoll.arkanoid.entity.Entity;
import com.GromHoll.arkanoid.entity.IMovable;
import com.GromHoll.arkanoid.math.BoundingBox;

public abstract class Ball extends Entity implements IMovable{
    public static final int W = 10;
    public static final int H = 10;
    
    public double dx =  1;
    public double dy = -1;
    
    protected Ball(double x, double y) {
        super(x, y);
    }

    @Override
    public void move() {
        x += dx;
        y += dy;
    }
    
    public void setDirection(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    @Override
    public BoundingBox getBB() {
        return new BoundingBox(x, y, x + W, y + H);
    }
    
}
