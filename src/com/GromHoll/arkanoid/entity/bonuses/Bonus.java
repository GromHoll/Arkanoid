package com.GromHoll.arkanoid.entity.bonuses;

import com.GromHoll.arkanoid.entity.Entity;
import com.GromHoll.arkanoid.entity.IMovable;

public abstract class Bonus extends Entity implements IMovable {
    
    protected Bonus(double x, double y) {
        super(x, y);
    }
}
