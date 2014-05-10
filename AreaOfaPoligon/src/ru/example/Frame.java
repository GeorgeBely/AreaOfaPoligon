package ru.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class Frame extends JFrame {
    public static final int DEFAULT_WIDTH = 1000, DEFAULT_HEIGHT = 600;
    public static final int DISTANCE_OF_VIEW = 40;
    public static int distance = 10;
    public static final String NAME = "Polygon";
    private int countPolygon = 0;

    public ArrayList<Polygon> polygons = new ArrayList<Polygon>(){{ add(new Polygon()); }};
    public boolean centerCheck = true;


    public Frame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        setLocation((screenSize.width / 2) - DEFAULT_WIDTH / 2, (screenSize.height / 2) - DEFAULT_HEIGHT / 2);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle(NAME);
        setResizable(true);

        final Panel panel = new Panel() {{
            setFocusable(true);
            setBackground(Color.LIGHT_GRAY);
        }};

        add(panel);
        JButton buttonDrawPath = new JButton("Draw path");
        panel.add(buttonDrawPath);

        JButton buttonDrawEarth = new JButton("Draw earth");
        panel.add(buttonDrawEarth);

        JButton buttonDrawImage = new JButton("Draw image");
        panel.add(buttonDrawImage);

        final JCheckBox wheather = new JCheckBox("береговая линия");
        wheather.setSelected(true);
        panel.add(wheather);

        JButton buttonDrawCell = new JButton("Draw cell");
        panel.add(buttonDrawCell);

        JButton buttonClearPolygon = new JButton("Clear Polygon");
        panel.add(buttonClearPolygon);

        panel.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    Point location = MouseInfo.getPointerInfo().getLocation();
                    polygons.get(0).add(new Position((int) location.getX() - getLocation().x, (int) location.getY() - getLocation().y, wheather.isSelected()));
                    Panel.drawRect(getGraphics(), polygons.get(0).getTailPosition(), wheather.isSelected() ? Color.BLUE : Color.RED);
                }
            }
        });

        buttonDrawPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (countPolygon == 0)
                    polygons = CommonHelper.partitionPolygon(polygons);

                if (distance == 0)
                    distance = DISTANCE_OF_VIEW;
                if (countPolygon == 1)
                    distance += distance;
                for (Polygon polygon : polygons) {
                    centerCheck = true;
                    for (int j = 0; j < polygon.getN(); j++) {
                        Position centerPosition = MathFunction.positionOfCenter(polygon);
                        if (MathFunction.hypotenusePifagor(polygon.getPositions()[j], centerPosition) < distance)
                            centerCheck = false;
                    }
                    if (centerCheck) {
                        Color color = Color.WHITE;
                        if (countPolygon == 0)
                            color = Color.BLACK;
                        else if (countPolygon == 1)
                            color = Color.RED;

                        for (int i = 0; i < polygon.getN(); i++) {
                            Position a = polygon.getPositions()[i];
                            Position b = polygon.getPositions()[i+1>polygon.getN()-1 ? 0 : i+1];
                            if (!a.isWheather() || !b.isWheather())
                                Panel.drawLine(getGraphics(), a, b, color);
                        }

                        CommonHelper.mergePoints(polygon);

                        Polygon newPositions = new Polygon();
                        for (int i = 0; i < polygon.getN(); i++) {
                            newPositions.getPositions()[i] = MathFunction.intersection(getGraphics(), polygon,
                                                                                  polygon.getPositions()[i-1 < 0 ? polygon.getN()-1 : i-1],
                                                                                  polygon.getPositions()[i],
                                                                                  polygon.getPositions()[i+1>polygon.getN()-1 ? 0 : i+1], distance);
                            newPositions.setN(newPositions.getN() + 1);
                        }
                        if (countPolygon % 2 == 0)
                            Panel.drawLine(getGraphics(), polygon.getTailPosition(), newPositions.getTailPosition(), color);
                        else
                            Panel.drawLine(getGraphics(), polygon.getPositions()[0], newPositions.getPositions()[0], color);


                        polygon.setPositions(newPositions.getPositions());
                    }
                }
                countPolygon++;
            }
        });

        buttonDrawEarth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonDrawImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Panel.paintImage(getGraphics());
            }
        });

        buttonClearPolygon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                polygons = new ArrayList<Polygon>(){{ add(new Polygon()); }};
                panel.setBackground(Color.WHITE);
                panel.setBackground(Color.LIGHT_GRAY);
            }
        });


        buttonDrawCell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Panel.drawCell(getGraphics());
            }
           });
    }
}