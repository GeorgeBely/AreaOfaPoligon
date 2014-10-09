package ru.example;

import java.awt.*;

public class MathFunction {

    /**
     * Вычисляет позицию центральной точки многоугольника
     * @return центральная точка многоугольника.
     */
    public static Position positionOfCenter(Polygon polygon) {
        int sumX = 0, sumY = 0;
        for (int j = 0; j < polygon.getN(); j++) {
            sumX += polygon.getPositions().get(j).getX();
            sumY += polygon.getPositions().get(j).getY();
        }
        return new Position(sumX / polygon.getN(), sumY / polygon.getN());
    }

    /**
     * Определяет с какой стороны находится центр многоугольника.
     * @param a первая точка
     * @param b вторая точка
     * @param centerPosition центр многоугольника.
     * @return "тип CenterPosition" (с какой стороны находится центр)
     */
    public static CenterPosition locationCenter(Position a, Position b, Position centerPosition) {
        if (b.getX() == a.getX())
            return CenterPosition.down;

        double y = ((centerPosition.getX() - a.getX()) * (b.getY() - a.getY())) / (b.getX() - a.getX()) + a.getY();
        return y >= centerPosition.getY() ? CenterPosition.down : CenterPosition.up;
    }

    /**
     * Определяет точку пересечения двух параллельных на определённом расстоянии линий относитльно заданных координат.
     * первая линия "a->b" вторая линия "b->c"
     * @param distanceOfView расстояние параллельной линии от текущей
     * @return точку пересичения.
     */
    public static Position intersection(Graphics g, Polygon polygon, Position a, Position b, Position c, int distanceOfView) {
        Line lineA, lineB;
        CenterPosition centerPosition = locationCenter(a, b, positionOfCenter(polygon));
        if (a.isWheather() && b.isWheather() && hypotenusePifagor(a, b) - distanceOfView*2 > 0)
            lineA = new Line(a, b);
        else
            lineA = parallelLine(a, b, centerPosition, distanceOfView, g);

        centerPosition = locationCenter(b, c, positionOfCenter(polygon));
        if (b.isWheather() && c.isWheather() && hypotenusePifagor(b, c) - distanceOfView*3 > 0)
            lineB = new Line(b, c);
        else
            lineB = parallelLine(b, c, centerPosition, distanceOfView, g);

        Position pos = intersectionLines(lineA, lineB);
//        Panel.drawRect(g, pos);
        return pos;
    }

    public static Line parallelLine(Position a, Position b, CenterPosition centerPosition, int distanceOfView, Graphics g) {
        Position newA, newB;
        if (a.getX() > b.getX()) {
            if (a.getY() > b.getY()) {
                if (CenterPosition.down.equals(centerPosition)) {
                    newA = new Position(a.getX() + distanceOfView, a.getY() - distanceOfView);
//                    Panel.drawRect(g, newA, Color.BLACK);
                    newB = new Position(b.getX() + distanceOfView, b.getY() - distanceOfView);
//                    Panel.drawRect(g, newB, Color.BLACK);
                } else {
                    newA = new Position(a.getX() - distanceOfView, a.getY() + distanceOfView);
//                    Panel.drawRect(g, newA, Color.BLACK);
                    newB = new Position(b.getX() - distanceOfView, b.getY() + distanceOfView);
//                    Panel.drawRect(g, newB, Color.BLACK);
                }
            } else {
                if (CenterPosition.down.equals(centerPosition)) {
                    newA = new Position(a.getX() - distanceOfView, a.getY() - distanceOfView);
//                    Panel.drawRect(g, newA, Color.CYAN);
                    newB = new Position(b.getX() - distanceOfView, b.getY() - distanceOfView);
//                    Panel.drawRect(g, newB, Color.CYAN);
                } else {
                    newA = new Position(a.getX() + distanceOfView, a.getY() + distanceOfView);
//                    Panel.drawRect(g, newA, Color.CYAN);
                    newB = new Position(b.getX() + distanceOfView, b.getY() + distanceOfView);
//                    Panel.drawRect(g, newB, Color.CYAN);
                }
            }
        } else {
            if (a.getY() > b.getY()) {
                if (CenterPosition.down.equals(centerPosition)) {
                    newA = new Position(a.getX() - distanceOfView, a.getY() - distanceOfView);
//                    Panel.drawRect(g, newA, Color.GREEN);
                    newB = new Position(b.getX() - distanceOfView, b.getY() - distanceOfView);
//                    Panel.drawRect(g, newB, Color.GREEN);
                } else {
                    newA = new Position(a.getX() + distanceOfView, a.getY() + distanceOfView);
//                    Panel.drawRect(g, newA, Color.GREEN);
                    newB = new Position(b.getX() + distanceOfView, b.getY() + distanceOfView);
//                    Panel.drawRect(g, newB, Color.GREEN);
                }
            } else {
                if (CenterPosition.down.equals(centerPosition)) {
                    newA = new Position(a.getX() + distanceOfView, a.getY() - distanceOfView);
//                    Panel.drawRect(g, newA, Color.MAGENTA);
                    newB = new Position(b.getX() + distanceOfView, b.getY() - distanceOfView);
//                    Panel.drawRect(g, newB, Color.MAGENTA);
                } else {
                    newA = new Position(a.getX() - distanceOfView, a.getY() + distanceOfView);
//                    Panel.drawRect(g, newA, Color.MAGENTA);
                    newB = new Position(b.getX() - distanceOfView, b.getY() + distanceOfView);
//                    Panel.drawRect(g, newB, Color.MAGENTA);
                }
            }
        }
        return new Line(newA, newB);
    }



    /**
     * Находит точку пересечения между двумя линиями AB и CD
     * @return точку пересечения.
     */
    public static Position intersectionLines(Line A, Line B) {
        double k = (double) (A.getB().getY() - A.getA().getY()) / (A.getB().getX() - A.getA().getX());
        double b2 = (double) A.getA().getY() - k * A.getA().getX();

        double ge = (double) (B.getB().getY() - B.getA().getY()) / (B.getB().getX() - B.getA().getX());
        double a2 = (double) B.getA().getY() - ge * B.getA().getX();
        int x = (int) Math.round((a2 - b2) / (k - ge));
        int y = (int) Math.round(k * x + b2);
        return new Position(x, y, A.isWheather() || B.isWheather());
    }


    /**
     * Высчитывает растояние от точки "pointA" до точки "pointB" по теореме Пифагора.
     * @return растояние между точками.
     */
    public static double hypotenusePifagor(Position pointA, Position pointB) {
        return Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2) + Math.pow(pointA.getY() - pointB.getY(), 2));
    }
}