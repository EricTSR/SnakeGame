package de.erictsr.snake.main;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static boolean running = false;

    GameFrame() {

        HeaderPanel headerPanel = new HeaderPanel();
        headerPanel.setBounds(0, 0, 1000, 150);
        headerPanel.setBackground(Color.lightGray);

        CurrentItemsPanel currentItemsPanel = new CurrentItemsPanel();
        currentItemsPanel.setBounds(0, 150, 150, 600);
        currentItemsPanel.setBackground(Color.green);

        GamePanel gamePanel = new GamePanel();
        gamePanel.setBounds(150, 150, 600, 600);
        gamePanel.setBackground(Color.CYAN);

        InfoPanel infoPanel = new InfoPanel();
        infoPanel.setBounds(750, 150, 150, 600);
        infoPanel.setBackground(Color.PINK);

        FooterPanel footerPanel = new FooterPanel();
        footerPanel.setBounds(0, 750, 900, 150);
        footerPanel.setBackground(Color.YELLOW);


        this.setSize(900, 900);
        this.setTitle("Jannis die Schlange");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(gamePanel);
        this.add(headerPanel);
        this.add(infoPanel);
        this.add(footerPanel);
        this.add(currentItemsPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
