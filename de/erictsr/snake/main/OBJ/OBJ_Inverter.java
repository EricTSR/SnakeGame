package de.erictsr.snake.main.OBJ;

import de.erictsr.snake.main.GamePanel;

import java.awt.*;
import java.util.Random;
import java.util.TimerTask;

public class OBJ_Inverter {

    int inverterX, inverterY;
    boolean inverted;

    public void newInverter() {
        //random.nextInt(10) * größe
        Random random = new Random();
        inverterX = random.nextInt((int) GamePanel.SCREEN_WIDTH / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
        inverterY = random.nextInt((int) GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE;
    }

    public void checkInverter() {
        if ((GamePanel.x[0] == inverterX) && GamePanel.y[0] == inverterY) {
            setInverter();
            newInverter();
        }
    }

    public void setInverter() {
        java.util.Timer speedTimer = new java.util.Timer();
        inverted = true;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                inverted = false;
            }
        };
        speedTimer.schedule(task, 1000 * 10);
    }


    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(inverterX, inverterY, GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
    }


    public boolean getInverted() {
        return inverted;
    }
}
