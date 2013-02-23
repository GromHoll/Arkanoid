package com.GromHoll.arkanoid.screen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.GromHoll.arkanoid.entity.balls.Ball;
import com.GromHoll.arkanoid.entity.bricks.Brick;

public class Art {

    public static Bitmap gameArea = load("/res/GameArea.png");
    public static Bitmap gameOver = load("/res/GameOver.png");
    public static Bitmap paddle = load("/res/Paddle.png");
    public static Bitmap[][] brickTiles = cut("/res/Bricks.png", Brick.W, Brick.H);
    public static Bitmap[][] ballsTiles = cut("/res/Balls.png", Ball.W, Ball.H);
    
    private static Bitmap load(String string) {
        try {
            BufferedImage bi = ImageIO.read(Art.class.getResource(string));

            int w = bi.getWidth();
            int h = bi.getHeight();

            Bitmap result = new Bitmap(w, h);
            bi.getRGB(0, 0, w, h, result.pixels, 0, w);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Bitmap[][] cut(String string, int w, int h) {
        return cut(string, w, h, 0, 0);
    }
    
    private static Bitmap[][] cut(String string, int w, int h, int bx, int by) {
        try {
            BufferedImage bi = ImageIO.read(Art.class.getResource(string));

            int xTiles = (bi.getWidth() - bx)/w;
            int yTiles = (bi.getHeight() - by)/h;

            Bitmap[][] result = new Bitmap[xTiles][yTiles];

            for (int x = 0; x < xTiles; x++) {
                for (int y = 0; y < yTiles; y++) {
                    result[x][y] = new Bitmap(w, h);
                    bi.getRGB(bx + x*w, by + y*h, w, h, result[x][y].pixels, 0, w);
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
