package ru.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class Frame extends JFrame {

    /** Ширина окна */
    public static final int WIDTH = 1000;

    /** Высота окна */
    public static final int HEIGHT = 600;

    /** Наименование окна */
    public static final String TITLE = "Polygon";

    /** Радиус надводного радара */
    public static final int DISTANCE_OF_VIEW = 40;


    public static int distance = 10;
    private int countPolygon = 0;

    public ArrayList<Polygon> polygons = new ArrayList<Polygon>(){{ add(new Polygon()); }};

    public Frame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width/2) - WIDTH /2, (screenSize.height/2) - HEIGHT /2);
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);
        setResizable(true);
        toFront();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

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
                if (e.getButton() == MouseEvent.BUTTON3 && polygons.size() == 1) {
                    Point location = MouseInfo.getPointerInfo().getLocation();
                    polygons.get(0).add(new Position(location.getX() - getLocation().getX(),
                                                     location.getY() - getLocation().getY(), wheather.isSelected()));

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
                    Color color = Color.WHITE;
                    if (countPolygon == 0)
                        color = Color.BLACK;
                    else if (countPolygon == 1)
                        color = Color.RED;

                    if (CommonHelper.isHavePositionCloseToCenter(polygon, distance)) {
                        for (int i = 0; i < polygon.getN(); i++) {
                            Position a = polygon.getPositions().get(i);
                            Position b = polygon.getPositions().get(i + 1 > polygon.getN() - 1 ? 0 : i + 1);
                            if (!a.isWheather() || !b.isWheather())
                                Panel.drawLine(AreaOfaPolygon.frame.getGraphics(), a, b, color);
                        }

                        CommonHelper.calculatePositions(distance, polygon);
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