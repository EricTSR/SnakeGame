package de.erictsr.snake.main;

import de.erictsr.snake.main.OBJ.OBJ_Apple;
import de.erictsr.snake.main.OBJ.OBJ_Inverter;
import de.erictsr.snake.main.OBJ.OBJ_Speed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;

    public static final int UNIT_SIZE = 25;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int DELAY = 200;

    public static final int[] x = new int[GAME_UNITS];
    public static final int[] y = new int[GAME_UNITS];
    public static int bodyParts = 6;
    public static int applesEaten = 0;
    int speedX, speedY;
    static char direction = 'R';
    boolean running = false;
    public static Timer timer;
    Random random;

    OBJ_Apple apple = new OBJ_Apple();
    OBJ_Speed speed = new OBJ_Speed();
    OBJ_Inverter inverter = new OBJ_Inverter();

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        startGame();
    }

    public void startGame() {
        apple.newApple();
        speed.newSpeed();
        inverter.newInverter();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {

            this.setBackground(Color.BLACK);

            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            apple.draw(g);
            speed.draw(g);
            inverter.draw(g);


            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 25));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

            g.setFont(new Font("Ink Free", Font.BOLD, 25));
            FontMetrics metrics3 = getFontMetrics(g.getFont());
            g.drawString("Speed: " + timer.getDelay(), (SCREEN_WIDTH - metrics3.stringWidth("Speed: " + timer.getDelay())) / 2, g.getFont().getSize() * 2);

        } else {
            gameOver(g);
        }

    }

    int temp = 0;
    char lastKey;

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (inverter.getInverted() && (lastKey != direction || temp >= 2)) {
            temp++;
            switch (direction) {
                case 'U' -> y[0] = y[0] + UNIT_SIZE;
                case 'D' -> y[0] = y[0] - UNIT_SIZE;
                case 'L' -> x[0] = x[0] + UNIT_SIZE;
                case 'R' -> x[0] = x[0] - UNIT_SIZE;
            }
        } else {
            switch (direction) {
                case 'U' -> y[0] = y[0] - UNIT_SIZE;
                case 'D' -> y[0] = y[0] + UNIT_SIZE;
                case 'L' -> x[0] = x[0] - UNIT_SIZE;
                case 'R' -> x[0] = x[0] + UNIT_SIZE;
            }
        }
        lastKey = direction;

    }

    public void checkCollisions() {

        //checks if head collides with body
        for (int i = bodyParts; i < 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        //checks if head touches left boarder
        if (x[0] < 0) {
            running = false;
        }

        //checks if head touches right boarder
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }

        //checks if head touches top boarder
        if (y[0] < 0) {
            running = false;
        }

        //checks if head touches bottom boarder
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }

    }

    public void gameOver(Graphics g) {

        //Score
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        //GameOverText
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    public static class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_D:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_W:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_S:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;

            }

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            apple.checkApple();
            speed.checkSpeed();
            inverter.checkInverter();
            checkCollisions();
        }
        repaint();
    }
}
