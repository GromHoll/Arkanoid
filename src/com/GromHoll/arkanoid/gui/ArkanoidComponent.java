package com.GromHoll.arkanoid.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Iterator;

import com.GromHoll.arkanoid.entity.Paddle;
import com.GromHoll.arkanoid.entity.balls.Ball;
import com.GromHoll.arkanoid.entity.balls.SimpleBall;
import com.GromHoll.arkanoid.entity.bricks.Brick;
import com.GromHoll.arkanoid.level.Level;
import com.GromHoll.arkanoid.screen.Art;
import com.GromHoll.arkanoid.screen.Screen;

public class ArkanoidComponent extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    
    public static final int GAME_W  = 800;
    public static final int GAME_H = 450;
    public static final int X_OFFSET = 10;
    public static final int Y_OFFSET = 10;
  
    private volatile boolean running = false;
    private int delay = 5;
    private int levelNum = 0;
    private Level level;
    private Paddle paddle;
    private Ball ball;
    
    private Screen screen = new Screen(GAME_W, GAME_H);
    
    public ArkanoidComponent() {
        this.setPreferredSize(new Dimension(GAME_W, GAME_H));
        this.setMinimumSize(new Dimension(GAME_W, GAME_H));
        this.setMaximumSize(new Dimension(GAME_W, GAME_H));
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
    
    public KeyListener getKeyListener() {
        return new GameKeyAdapter();
    }
    
    private synchronized void tick() {
        if(level == null || level.isFinish()) {
            levelNum++;
            level = new Level(getLevelName());
            
            if(level.isFinish()) {
                running = false;
            } else {
                paddle = new Paddle(X_OFFSET + (Level.LEVEL_W*Brick.W - Paddle.W)/2,
                                    GAME_H - Paddle.H);
                ball = new SimpleBall(X_OFFSET + (Level.LEVEL_W*Brick.W - Ball.W)/2,
                                      GAME_H - Paddle.H - Ball.H);
            }
            return;
        }

        ball.move();
        paddle.move();  
        
        if(ball.getBB().getMaxY() >= GAME_H) {
            running = false;
            return;
        } else if(ball.getBB().getMinY() <= Y_OFFSET) {
            ball.dy = -ball.dy;
        }  else if(ball.getBB().getMinX() <= X_OFFSET || ball.getBB().getMaxX() >= X_OFFSET + Level.LEVEL_W*Brick.W) {
            ball.dx = -ball.dx;
        }
        
        if(paddle.getBB().getMinX() < X_OFFSET) {
            paddle.x = X_OFFSET;
        }
        if(paddle.getBB().getMaxX() > X_OFFSET + Level.LEVEL_W*Brick.W) {
            paddle.x = X_OFFSET + Level.LEVEL_W*Brick.W - Paddle.W;
        }
        
        boolean xColl = false;
        boolean yColl = false;
        for(Brick b : level.getBricks()) {
            if(ball.getBB().intersects(b.getBB())) {
                Rectangle r = ball.getBB().intersection(b.getBB());
                if(r.height < r.width) {
                    yColl = true;
                } else if(r.height > r.width) {
                    xColl = true;
                } else {
                    yColl = true;
                    xColl = true;
                }
                b.hit();
            }
        }        
        if(xColl)
            ball.dx *= -1;
        if(yColl)
            ball.dy *= -1;
        
        if(ball.getBB().intersects(paddle.getBB())) {
            Rectangle r = ball.getBB().intersection(paddle.getBB());
            if(r.height < r.width) {
                
                int bc = (int) ball.getBB().getCenterX();
                int pl = (int) paddle.getBB().getMinX();
                if(bc <= pl + Paddle.W/4) {
                    ball.dx = -1;
                    ball.dy = -1;
                } else if(bc <= pl + Paddle.W/2) {
                    ball.dx = -0.5;
                    ball.dy = -1.3;
                } else if(bc <= pl + Paddle.W*3/4) {
                    ball.dx = 0.5;
                    ball.dy = -1.3;
                } else {
                    ball.dx = 1;
                    ball.dy = -1;
                }
                
            } else if(r.height >= r.width) {
                ball.dx = -ball.dx;
            }
        }
        
        Iterator<Brick> it = level.getBricks().iterator();
        while(it.hasNext()) {
            if(it.next().destroyed) {
                it.remove();
            }
        }
        
    }
    
    private synchronized void render(Graphics g) {
       
        if(running) {
            screen.blit(Art.gameArea, 0, 0);
            level.render(screen);
            screen.blit(paddle.getTile(), (int) paddle.x, (int) paddle.y);;
            screen.blit(ball.getTile(), (int) ball.x, (int) ball.y);
        } else {
            screen.blit(Art.gameOver, 0, 0);                    
        }
        
        g.drawImage(screen.image, 0, 0, null);
    }
    
    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void update(Graphics g) {
    }
    
    private String getLevelName() {
        return new String("/res/levels/level" + levelNum + ".png");
    }
    
    private class GameKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }        
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        long timeDiff, sleep;

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }
        
        while (running) {
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = delay - timeDiff;
            
            tick();            
            render(bs.getDrawGraphics());
            bs.show();

            if (sleep < 0) {
                sleep = 1;
            }
            
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("GameThread sleeping error.");
            }

            beforeTime = System.currentTimeMillis();
        }
        bs.show();
    }

}
