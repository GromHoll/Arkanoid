package com.GromHoll.arkanoid.entity.balls;

import com.GromHoll.arkanoid.screen.Art;
import com.GromHoll.arkanoid.screen.Bitmap;

public class SimpleBall extends Ball {

    public SimpleBall(double x, double y) {
        super(x, y);
    }
    
    @Override
    public Bitmap getTile() {
        return Art.ballsTiles[0][0];
    }

}
