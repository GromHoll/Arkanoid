package com.GromHoll.arkanoid.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.GromHoll.arkanoid.entity.bricks.Brick;
import com.GromHoll.arkanoid.entity.bricks.SimpleBrick;
import com.GromHoll.arkanoid.gui.ArkanoidComponent;
import com.GromHoll.arkanoid.screen.Art;
import com.GromHoll.arkanoid.screen.Screen;

public class Level {
    public static final int LEVEL_W  = 25;
    public static final int LEVEL_H = 15;
    
    public static final int SIMPLE_BRICK_COLOR = 0xFFFFFF00;
    public static final int EMPTY_COLOR = 0xFFFFFFFF;
    
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    
    public Level(String levelFile) {
        try {
            BufferedImage bi = ImageIO.read(Art.class.getResource(levelFile));
            
            if(bi.getHeight() != LEVEL_H || bi.getWidth() != LEVEL_W) {
                System.out.println("Level size incorrect.");
                return;
            }
            
            for(int i = 0; i < LEVEL_W; i++) {
                for(int j = 0; j < LEVEL_H; j++) {
                    int x = ArkanoidComponent.X_OFFSET + i*Brick.W;
                    int y = ArkanoidComponent.Y_OFFSET + j*Brick.H;
                    switch(bi.getRGB(i, j)) {
                        case SIMPLE_BRICK_COLOR:
                            bricks.add(new SimpleBrick(x, y));
                            break;
                        case EMPTY_COLOR:
                            break;
                        default:
                            System.out.println("Unknow brick: " + bi.getRGB(i, j));
                            continue;
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("Level " + levelFile + " not found.");
        }
    }
    
    public void render(Screen screen) {
        for(Brick b : bricks) {
            screen.blit(b.getTile(), (int) b.x, (int) b.y);
        }
    }
    
    public ArrayList<Brick> getBricks() {
        return bricks;
    }
    
    public boolean isFinish() {
        return bricks.isEmpty();
    }
    
}
