package de.erictsr.snake.main.OBJ;

import de.erictsr.snake.main.GamePanel;

import java.awt.*;
import java.util.Random;
import java.util.TimerTask;

public class OBJ_Speed {

    public static int speedX, speedY;

    public void newSpeed() {
        //random.nextInt(10) * größe
        Random random = new Random();
        speedX = random.nextInt((int) GamePanel.SCREEN_WIDTH / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
        speedY = random.nextInt((int) GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
    }

    public void checkSpeed() {
        if ((GamePanel.x[0] == speedX) && GamePanel.y[0] == speedY) {
            setSpeed();
            newSpeed();
        }
    }

    public void setSpeed() {

        java.util.Timer speedTimer = new java.util.Timer();
        GamePanel.timer.setDelay(50);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GamePanel.timer.setDelay(75);
                System.out.println("Speed ended");
            }
        };
        speedTimer.schedule(task, 1000 * 5);
    }


    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(speedX, speedY, GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
    }

}
