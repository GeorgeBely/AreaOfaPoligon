package ru.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class Panel extends JPanel
{
    public static void paintSquare(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D field = new Rectangle2D.Double(ru.example.Frame.Ax, ru.example.Frame.Ay, 3, 3);
        g2.setPaint(Color.white);
        g2.fill(field);
    }
    public static void paintLine(Graphics g, int Ax, int Ay , int Bx, int By)
    {
        g.setColor(Color.DARK_GRAY);
        g.drawLine(Ax, Ay, Bx, By);

    }
}