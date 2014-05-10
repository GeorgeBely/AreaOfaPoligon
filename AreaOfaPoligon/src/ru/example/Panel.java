package ru.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Panel extends JPanel {
    private static BufferedImage grass;
    private static BufferedImage water;
    public static final int DISTANCE_OF_CELL = 20;

    static {
        try {
            grass = ImageIO.read(new File("c:\\Диплом\\1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawRect(Graphics g, Position point) {
        g.setColor(Color.RED);
        g.fillOval(point.getX(), point.getY(), 10, 10);
    }

    public static void drawRect(Graphics g, Position point, Color c){
        g.setColor(c);
        g.fillOval(point.getX(), point.getY(), 10, 10);
    }

    public static void drawLine(Graphics g, Position a, Position b, Color color) {
        g.setColor(color);
        g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public static void paintImage(Graphics g) {
        g.drawImage(grass, 1, 1, null);
    }

    public static void drawCell(Graphics g) {
        g.setColor(Color.GRAY);
        int i = 20;
        while (i < AreaOfaPolygon.frame.getWidth()) {
            g.drawLine(i, 50, i, AreaOfaPolygon.frame.getHeight());
            i += DISTANCE_OF_CELL;
        }

        i = 50;
        while (i < AreaOfaPolygon.frame.getHeight()) {
            g.drawLine(20, i, AreaOfaPolygon.frame.getWidth(), i);
            i += 20;
        }
    }
}