package ru.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

class Panel extends JPanel {
    private static BufferedImage grass;
    private static BufferedImage water;

    static {
        try {
            final String dir = System.getProperty("user.dir");
            System.out.println("current dir = " + dir);
            grass = ImageIO.read(new File("AreaOfaPoligon/res/grass.png"));
            water = ImageIO.read(new File("AreaOfaPoligon/res/water.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Position[] beachLine;
    private List<Position[]> allPositions;
    private int beachLineN = 0;
    private int n;

    public static void paintSquare(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D field = new Rectangle2D.Double(ru.example.Frame.Ax, ru.example.Frame.Ay, 3, 3);
        g2.setPaint(Color.white);
        g2.fill(field);
    }

    public static void paintLine(Graphics g, int Ax, int Ay, int Bx, int By) {
        g.setColor(Color.DARK_GRAY);
        g.drawLine(Ax, Ay, Bx, By);
    }

    private void paintLine(Graphics g, Position pos1, Position pos2) {
        Panel.paintLine(g, pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY());
    }

    public static void drawDot(Graphics graphics, Position pos) {
        Graphics2D g2 = (Graphics2D) graphics;
        pos.getX();
        Rectangle2D field = new Rectangle2D.Double(pos.getX(), pos.getY(), 4, 4);
        g2.setPaint(Color.red);
        g2.fill(field);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (beachLine == null) {
            return;
        }
        DrawHelper.drawGrass(g);
      /*  for (Position pos : beachLine) {
            if (pos != null) {
                g.drawImage(water, pos.getX(), pos.getY(), null);
            }
        }*/

        DrawHelper.drawWater(g, beachLine, beachLineN);
        double previousLength = -1;
        for (Position[] posArray : allPositions) {
            double currentLength = DrawHelper.getTotalLinesLength(posArray, n);
            if (previousLength == -1) {
                previousLength = currentLength;
            } else if (previousLength > currentLength)  {
                previousLength = currentLength;
            }   else if (previousLength < currentLength){
              //  continue;
                //TODO нужно реализовать корректное вычисление длины всех линий, с учетом того что n меняется
            }
           // System.out.println("currentLength " + currentLength);
            for (int i = 0; i < posArray.length; i++) {
                Position pos = posArray[i];
                if (pos != null) {
                    if (i != 0) {
                        paintLine(g, pos, posArray[i - 1]);
                    }
                    Panel.drawDot(g, pos);
                }
            }
        }
    }


    public void drawTerrain(Graphics g, List<Position[]> positions, int n) {
        if (beachLineN == 0) {
            this.beachLineN = n;
        }
        this.n = n;
        this.beachLine = positions.get(0);
        this.allPositions = positions;
        paintComponent(g);
        // JLabel waterTile = new JLabel(new ImageIcon(water));
        //  this.add(waterTile);
        //waterTile.setAlignmentX(CENTER_ALIGNMENT);
        //waterTile.setX
    }
}