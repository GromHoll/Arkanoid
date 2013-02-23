package com.GromHoll.arkanoid.screen;

import java.util.Arrays;

public class Bitmap {
    public int w, h;
    public int[] pixels;

    public Bitmap(int w, int h) {
        this.w = w;
        this.h = h;
        pixels = new int[w*h];
    }

    public void clear(int color) {
        Arrays.fill(pixels, color);
    }
    
    public void blit(Bitmap bitmap, int x, int y) {
        int x0 = x;
        int y0 = y;
        int x1 = x + bitmap.w;
        int y1 = y + bitmap.h;        
        
        if (x0 < 0) x0 = 0;
        if (y0 < 0) y0 = 0;
        if (x1 > w) x1 = w;
        if (y1 > h) y1 = h;
        
        int ww = x1 - x0;
        for(int yy = y0; yy < y1; yy++) {
            int tp = yy*w + x0;
            int sp = (yy - y)*bitmap.w + (x0 - x);
            tp -= sp;
            for (int xx = sp; xx < sp + ww; xx++) {
                int col = bitmap.pixels[xx];
                if (col < 0) pixels[tp + xx] = col;
            }
        }
    }

}