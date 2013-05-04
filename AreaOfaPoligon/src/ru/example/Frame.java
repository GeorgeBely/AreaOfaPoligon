package ru.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

//import sun.awt.X11.XAWTIcon32_security_icon_yellow16_png;

class Frame extends JFrame {
    public static final int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 440;
    public static final int DISTANCE_OF_VIEW = 5;
    public static int screenWidth, screenHeight, i, n;
    public static final String NAME = "Polygon";
    public static Panel panel;
    public static int Ax, Ay, xk, yk, count, Bx, By, Cx, Cy, Dx, Dy;
    public static Position[] positions = new Position[100];
    private List<Position[]> allPositions = new ArrayList<Position[]>();

    public Frame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        setLocation((screenWidth / 2) - DEFAULT_WIDTH / 2, (screenHeight / 2) - DEFAULT_HEIGHT / 2);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle(NAME);
        setResizable(true);

        panel = new Panel();
        panel.setFocusable(true);
        add(panel);
        JButton buttonDrawPath = new JButton("Draw path");
        buttonDrawPath.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(buttonDrawPath);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) {
                    Point location = MouseInfo.getPointerInfo().getLocation();
                    Ax = (int) location.getX() - AreaOfaPolygon.frame.getLocation().x;
                    Ay = (int) location.getY() - AreaOfaPolygon.frame.getLocation().y;
                    Panel.paintSquare(getGraphics());
                    positions[n] = new Position(Ax, Ay);
                    n++;
                }
                //		for(Position e1 : positions)
                //			System.out.println(e1.getpositionX() + " " + e1.getpoditionY());
            }
        });
        buttonDrawPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CommonHelper.mergePoints(Frame.this);

                positions[n] = positions[0];
                positions[n + 1] = positions[1];
                positions[n + 2] = positions[2];
                for (i = 0; i < n; i++) {
                    Ax = positions[i].getX();
                    Ay = positions[i].getY();
                    Bx = positions[i + 1].getX();
                    By = positions[i + 1].getY();
                    Cx = positions[i + 2].getX();
                    Cy = positions[i + 2].getY();
                    Dx = positions[i + 3].getX();
                    Dy = positions[i + 3].getY();
                   // Panel.paintLine(getGraphics(), Ax, Ay, Bx, By);
                    Position position1 = DrawHelper.intersection(Ax, Ay, Bx, By, Cx, Cy, DISTANCE_OF_VIEW);
                    Position position2 = DrawHelper.intersection(Bx, By, Cx, Cy, Dx, Dy, DISTANCE_OF_VIEW);
                    positions[i] = position1;
                  //  Panel.paintLine(getGraphics(), position1.getX(), position1.getY(), position2.getX(), position2.getY());
                    if (i == n - 1) {
                        positions[n] = positions[0];
                        positions[n + 1] = positions[1];
                        positions[n + 2] = positions[2];
                    }
                    //                    xk += Ax;
                    //                    yk += Ay;
                    //                    count++;
                }
                allPositions.add(positions.clone());
                panel.drawTerrain(getGraphics(), allPositions, n);
        /*        for (Position[] posArray : allPositions) {
                    for (Position pos : posArray) {
                        if (pos != null) {
                            Panel.drawDot(getGraphics(), pos);
                        }
                    }
                }*/
                //                xk =(xk + Bx)/(count+1);
                //                yk =(yk + By)/(count+1);
                //                Ax = xk;
                //                Ay = yk;
            }
        });
    }
}