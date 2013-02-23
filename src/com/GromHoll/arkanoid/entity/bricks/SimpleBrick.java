package com.GromHoll.arkanoid.entity.bricks;

import com.GromHoll.arkanoid.screen.Art;
import com.GromHoll.arkanoid.screen.Bitmap;

public class SimpleBrick extends Brick {

    public SimpleBrick(double x, double y) {
        super(x, y);
    }
    
    @Override
    public void hit() {
        destroyed = true;
    }

    @Override
    public Bitmap getTile() {
        return Art.brickTiles[0][0];
    }

}
