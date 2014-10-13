package ru.areaOfPolygon.services;

import ru.areaOfPolygon.location.*;


/**
 * Сервис для работы с матемматическими функциями и рассчётами.
 */
public class MathService {

    /**
     * Вычисляет позицию центральной точки многоугольника
     * @return центральная точка многоугольника.
     */
    public static Position positionOfCenter(Polygon polygon) {
        int sumX = 0, sumY = 0;
        for (Position position : polygon.getPositions()) {
            sumX += position.getX();
            sumY += position.getY();
        }
        return new Position(sumX / polygon.size(), sumY / polygon.size());
    }

    /**
     * Определяет с какой стороны от линии находится центр многоугольника.
     *
     * @param line           линия.
     * @param centerPosition центр многоугольника.
     * @return "тип CenterPosition" (с какой стороны находится центр)
     */
    public static CenterPosition locationCenter(Line line, Position centerPosition) {
        Position a = line.getA();
        Position b = line.getB();
        if (b.getX() == a.getX())
            return CenterPosition.DOWN;

        double y = ((centerPosition.getX() - a.getX()) * (b.getY() - a.getY())) / (b.getX() - a.getX()) + a.getY();
        return y >= centerPosition.getY() ? CenterPosition.DOWN : CenterPosition.UP;
    }

    /**
     * Определяет точку пересечения двух параллельных на определённом расстоянии линий относитльно заданных координат.
     * первая линия "firstLine" вторая линия "secondLine". Если линия является примыкающей к морю, то точка пересечения
     * ищется относительно этой линии.
     *
     * @param distanceOfView радиус действия радара.
     * @return точку пересичения.
     */
    public static Position intersection(Polygon polygon, Line firstLine, Line secondLine, int distanceOfView) {
        CenterPosition centerPosition = locationCenter(firstLine, positionOfCenter(polygon));
        Line lineA = firstLine;
        if (!firstLine.isWheather() || firstLine.length() < distanceOfView * 2)
            lineA = parallelLine(firstLine, centerPosition, distanceOfView);

        centerPosition = locationCenter(secondLine, positionOfCenter(polygon));
        Line lineB = secondLine;
        if (!secondLine.isWheather() || secondLine.length() < distanceOfView * 2)
            lineB = parallelLine(secondLine, centerPosition, distanceOfView);

//        Graphics g = AreaOfaPolygon.frame.getGraphics();
//        Panel.drawPosition(g, intersectionLines(lineA, lineB));
        return intersectionLines(lineA, lineB);
    }

    /**
     * Вычисляет линию параллельную текущей на расстояние <code>distanceOfView<code/>, которая будет
     * ближе к центру многоугольника.
     *
     * @param current        текущая линия.
     * @param centerPosition расположение центра.
     * @param distanceOfView радиус действия радара.
     * @return линию параллельную текущей.
     */
    public static Line parallelLine(Line current, CenterPosition centerPosition, int distanceOfView) {
        Position a = current.getA();
        Position b = current.getB();

        int distanceX = distanceOfView;
        int distanceY = distanceOfView;

        if (CenterPosition.DOWN.equals(centerPosition))
            distanceY = (-1) * distanceY;

        if (CenterPosition.UP.equals(centerPosition))
            distanceX = (-1) * distanceX;

        if (a.getY() > b.getY())
            distanceX = (-1) * distanceX;

        if (a.getX() > b.getX())
            distanceX = (-1) * distanceX;

        Position newA = new Position(a.getX() - distanceX, a.getY() + distanceY);
        Position newB = new Position(b.getX() - distanceX, b.getY() + distanceY);

//        Graphics g = AreaOfaPolygon.frame.getGraphics();
//        Panel.drawPosition(g, newA, Color.BLACK);
//        Panel.drawPosition(g, newB, Color.BLACK);

        return new Line(newA, newB);
    }

    /**
     * Находит точку пересечения между двумя линиями A и B
     * @return точку пересечения.
     */
    public static Position intersectionLines(Line A, Line B) {
        double k = A.getB().getY() - A.getA().getY() / A.getB().getX() - A.getA().getX();
        double b2 = A.getA().getY() - k * A.getA().getX();

        double g = B.getB().getY() - B.getA().getY() / B.getB().getX() - B.getA().getX();
        double a2 = B.getA().getY() - g * B.getA().getX();

        double x = (a2 - b2) / (k - g);
        double y = k * x + b2;

        return new Position(x, y, (A.isWheather() || B.isWheather() ? PositionType.WATER : PositionType.COAST));
    }


    /**
     * Высчитывает растояние от точки "pointA" до точки "pointB" по теореме Пифагора.
     *
     * @return растояние между точками.
     */
    public static double hypot(Position pointA, Position pointB) {
        return Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2) + Math.pow(pointA.getY() - pointB.getY(), 2));
    }
}