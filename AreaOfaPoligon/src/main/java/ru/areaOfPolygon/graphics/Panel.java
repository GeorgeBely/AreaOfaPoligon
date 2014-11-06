package ru.areaOfPolygon.graphics;

import ru.areaOfPolygon.location.Line;
import ru.areaOfPolygon.location.Path;
import ru.areaOfPolygon.location.Position;
import ru.areaOfPolygon.location.Polygon;

import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel {

    /** Радиус рисующейся точки */
    private static final int RADIUS_POINT = 10;

    /** Расстояние между клетками в сетке */
    public static final int DISTANCE_OF_CELL = 20;


    /**
     * Рисует точку радиусом {RADIUS_POINT} на панели.
     *
     * @param g        объект графической панели.
     * @param position позиция точки.
     */
    public static void drawPosition(Graphics g, Position position){
        Color color = Color.RED;
        if (position.isWheather())
            color = Color.BLUE;
        g.setColor(color);
        g.fillOval((int) position.getX(), (int) position.getY(), RADIUS_POINT, RADIUS_POINT);
    }

    /**
     * Рисует линию между заданными позициями.
     *
     * @param g     объект графической панели.
     * @param line  линия, которую надо нарисовать.
     * @param color цвет линии.
     */
    public static void drawLine(Graphics g, Line line, Color color) {
        g.setColor(color);
        g.drawLine((int) line.getA().getX(), (int) line.getA().getY(), (int) line.getB().getX(), (int) line.getB().getY());
    }

    /**
     * Рисует путь
     *
     * @param g    объект графической панели.
     * @param path путь.
     */
    public static void drawPath(Graphics g, Path path) {
        Position lastPosition = path.lastElement();
        for (Position position : path.getElements()) {
            Color color = Color.WHITE;
            if (lastPosition.getCountLine() == 1)
                color = Color.RED;

            drawLine(g, new Line(position, lastPosition), color);
            lastPosition = position;
        }
    }

    /**
     * Рисует многоугольник
     *
     * @param g    объект графической панели.
     * @param polygon многоугольник.
     */
    public static void drawPolygon(Graphics g, Polygon polygon) {
        Color color = Color.BLACK;
        Position lastPosition = polygon.getPositions().get(polygon.size()-1);
        for (Position position : polygon.getPositions()) {
            drawLine(g, new Line(position, lastPosition), color);
            lastPosition = position;
        }
    }

    /**
     * Рисует картинку.
     *
     * @param g        объект графической панели.
     * @param position позиция в которой нарисовать картинку.
     * @param image    картинка.
     */
    public static void drawImage(Graphics g, Position position, Image image) {
        g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
    }

    /**
     * Рисует сетку во весь фрейм
     *
     * @param frame фрейм на котором нарисовать сетку.
     */
    public static void drawCell(JFrame frame) {
        Graphics g = frame.getGraphics();
        g.setColor(Color.GRAY);
        int i = 20;
        while (i < frame.getWidth()) {
            g.drawLine(i, 50, i, frame.getHeight());
            i += DISTANCE_OF_CELL;
        }

        i = 50;
        while (i < frame.getHeight()) {
            g.drawLine(20, i, frame.getWidth(), i);
            i += DISTANCE_OF_CELL;
        }
    }
}