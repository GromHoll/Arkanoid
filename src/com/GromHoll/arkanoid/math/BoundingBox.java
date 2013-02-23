package com.GromHoll.arkanoid.math;

public class BoundingBox {
    
    public int x0, y0;
    public int x1, y1;
    
    public BoundingBox(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }
    
    public BoundingBox(double x0, double y0, double x1, double y1) {
        this.x0 = (int) x0;
        this.x1 = (int) x1;
        this.y0 = (int) y0;
        this.y1 = (int) y1;
    }
    
    public int getMaxY() {
        if(y0 > y1) return y0;
        return y1;
    }    
    public int getMinY() {
        if(y0 < y1) return y0;
        return y1;
    }
    
    public int getMaxX() {
        if(x0 > x1) return x0;
        return x1;
    }    
    public int getMinX() {
        if(x0 < x1) return x0;
        return x1;
    }
    
    public boolean intersects(BoundingBox other) {
        return !(other.x0 >= x1 || other.y0 >= y1 || other.x1 <= x0 || other.y1 <= y0);
    }
}
