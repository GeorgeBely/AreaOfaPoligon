package ru.areaOfPolygon.frame;

import ru.areaOfPolygon.AreaOfaPolygon;
import ru.areaOfPolygon.location.*;
import ru.areaOfPolygon.graphics.Panel;
import ru.areaOfPolygon.location.Polygon;
import ru.areaOfPolygon.services.ImagesService;
import ru.areaOfPolygon.services.PolygonService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class Frame extends JFrame {

    /** Ширина окна */
    public static final int WIDTH = 1000;

    /** Высота окна */
    public static final int HEIGHT = 600;

    /** Наименование окна */
    public static final String TITLE = "Polygon";

    /** Радиус надводного радара */
    public static final int DEFAULT_DISTANCE_OF_VIEW = 5;


    /** Список многоугольников */
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>(){{ add(new Polygon()); }};

    private static Integer distance;


    public Frame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2 - WIDTH/2, screenSize.height/2 - HEIGHT/2);
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

        JButton buttonDrawImage = new JButton("Draw image");
        buttonDrawImage.addActionListener(e -> Panel.drawImage(getGraphics(), new Position(1, 1), ImagesService.getImage("beach.png")));
        panel.add(buttonDrawImage);

        final JCheckBox wheather = new JCheckBox("береговая линия");
        wheather.setSelected(true);
        panel.add(wheather);

        JButton buttonDrawCell = new JButton("Draw cell");
        buttonDrawCell.addActionListener(e -> Panel.drawCell(AreaOfaPolygon.frame));
        panel.add(buttonDrawCell);

        JButton buttonClearPolygon = new JButton("Clear Polygon");
        buttonClearPolygon.addActionListener(e -> {
            polygons = new ArrayList<Polygon>(){{ add(new Polygon()); }};
            setBackground(Color.LIGHT_GRAY);
        });
        panel.add(buttonClearPolygon);

        panel.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3 && polygons.size() == 1) {
                    Point location = MouseInfo.getPointerInfo().getLocation();
                    PositionType positionType = wheather.isSelected() ? PositionType.WATER : PositionType.COAST;
                    Position newPosition = new Position(location.getX() - getLocation().getX(),
                                                        location.getY() - getLocation().getY(), positionType, 0);
                    polygons.get(0).add(newPosition);

                    Panel.drawPosition(getGraphics(), newPosition);
                }
            }
        });

        buttonDrawPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                polygons = PolygonService.partitionPolygon(polygons);

                List<Path> paths = new ArrayList<>();
                for (Polygon polygon : polygons) {
                    Path path = new Path();
                    distance = null;
                    int countPolygon = 0;
                    Panel.drawPolygon(getGraphics(), polygon);
                    while (!PolygonService.isHavePositionCloseToCenter(polygon, distance) && countPolygon < 10) {
                        if (distance == null)
                            distance = DEFAULT_DISTANCE_OF_VIEW;
                        else if (distance == DEFAULT_DISTANCE_OF_VIEW)
                            distance += distance;

                        countPolygon++;

                        List<Position> newPositions = PolygonService.calculatePositions(distance, polygon);
                        for (Position position : newPositions) {
                            position.setCountLine(countPolygon);
                            path.add(position);
                        }
                        polygon.setPositions(newPositions);

                    }
                    paths.add(path);
                }

                Path allPath = new Path();
                for (Path path : paths) {
                    allPath.addAll(path.getElements());
                }
                Panel.drawPath(getGraphics(), allPath);
            }
        });
    }
}