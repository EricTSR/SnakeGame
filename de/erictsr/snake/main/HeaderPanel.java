package de.erictsr.snake.main;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HeaderPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 900;
    final int SCREEN_HEIGHT = 150;
    private final String NAME = "Jannis die Schlange";


    public HeaderPanel() {
        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GREEN);
        this.setFocusable(true);
        this.addKeyListener(new GamePanel.MyKeyAdapter());

        start();

    }

    public static int findHorizontalCenter(Graphics2D g2d, String s, int w) {
        Font f = g2d.getFont();
        FontMetrics fm = g2d.getFontMetrics(f);
        int width = fm.stringWidth(s);
        return (SCREEN_WIDTH - width) / 2;
    }

    public void start() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        g.drawString(NAME, findHorizontalCenter((Graphics2D) g, NAME, SCREEN_WIDTH), g.getFont().getSize() * 2);

        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        g.drawString("by EricTSR", findHorizontalCenter((Graphics2D) g, "by EricTSR", SCREEN_WIDTH), g.getFont().getSize() * 4);

    }

    public void actionPerformed(ActionEvent e) {

    }
}
