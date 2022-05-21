package de.erictsr.snake.main.OBJ;

import de.erictsr.snake.main.GamePanel;

import java.awt.*;
import java.util.Random;
import java.util.TimerTask;

public class OBJ_LSD {

    public static int speedX, speedY;
    public static boolean lsd;

    public void newLSD() {
        Random random = new Random();
        speedX = random.nextInt((int) GamePanel.SCREEN_WIDTH / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
        speedY = random.nextInt((int) GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
    }

    public void checkLSD() {
        if ((GamePanel.x[0] == speedX) && GamePanel.y[0] == speedY) {
            newLSD();
            setLSD();
        }
    }

    public void setLSD() {

        java.util.Timer lsdTimer = new java.util.Timer();
        lsd = true;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GamePanel.timer.setDelay(75);
                lsd = false;
            }
        };
        lsdTimer.schedule(task, 1000 * 5);
    }


    public void draw(Graphics g) {
        g.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
        g.fillOval(speedX, speedY, GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
    }

    public boolean getLSD() {
        return lsd;
    }
}
