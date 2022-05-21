package de.erictsr.snake.main;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Jannis die Schlange");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
