package com.GromHoll.arkanoid.screen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art {

    public static Bitmap gameArea = load("/res/GameArea.png");
    
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

}
