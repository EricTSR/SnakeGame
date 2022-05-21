package de.erictsr.snake.main.OBJ;

import de.erictsr.snake.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class OBJ_Apple {

    public static int appleX, appleY;

    int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH;
    int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    int UNIT_SIZE = GamePanel.UNIT_SIZE;

    public void newApple() {
        Random random = new Random();
        appleX = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt((int) SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void checkApple() {
        if ((GamePanel.x[0] == appleX) && GamePanel.y[0] == appleY) {
            GamePanel.bodyParts++;
            GamePanel.applesEaten++;
            newApple();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    }
}
