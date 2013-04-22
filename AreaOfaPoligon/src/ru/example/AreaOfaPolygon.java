package ru.example;

import java.awt.*;
import javax.swing.*;

public class AreaOfaPolygon {
    public static Frame frame;
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                frame = new Frame();
                frame.toFront();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}