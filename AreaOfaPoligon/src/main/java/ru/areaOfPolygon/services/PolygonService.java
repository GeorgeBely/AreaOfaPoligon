package ru.areaOfPolygon.services;

import ru.areaOfPolygon.location.Line;
import ru.areaOfPolygon.location.Polygon;
import ru.areaOfPolygon.location.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с многоугольниками.
 */
public class PolygonService {

    /**
     * Рассчитывает позиции следующего многоугольника.
     *
     * @param distance радиус радара.
     * @param polygon  многоугольник.
     * @return список новых точек многоугольника.
     */
    public static List<Position> calculatePositions(Integer distance, Polygon polygon) {
        PolygonService.mergePoints(polygon, distance);

        List<Position> newPositions = new ArrayList<Position>();
        for (int i = 0; i < polygon.size(); i++) {
            Line firstLine = new Line(polygon.get(i - 1), polygon.get(i));
            Line secondLine = new Line(polygon.get(i), polygon.get(i + 1));
            newPositions.add(MathService.intersection(polygon, firstLine, secondLine, distance));
        }

        return newPositions;
    }

    /**
     * Определяет имеется ли точка, от которой до центра многоугольника расстояние меньше <code>distance</code>
     *
     * @param polygon  многоугольник
     * @param distance радиус радара
     * @return {true}, если есть точка от которой расстояние до центра меньше <code>distance</code>
     */
    public static boolean isHavePositionCloseToCenter(Polygon polygon, Integer distance) {
        if (distance == null)
            return false;

        for (Position position : polygon.getPositions()) {
            Position centerPosition = MathService.positionOfCenter(polygon);
            if (position.getDistanceTo(centerPosition) < distance)
                return true;
        }
        return false;
    }

    /**
     * Проходит по всем точкам многоугольника и соединяет близкие.
     * @param polygon многоугольник.
     */
    public static void mergePoints(Polygon polygon, double distance) {
        for (int i = 0; i < polygon.size(); i++) {
            Position pointA = polygon.get(i);
            Position pointB = polygon.get(i + 1);
            if (pointA.getDistanceTo(pointB) < distance) {
                pointA.setX((pointA.getX() + pointB.getX()) / 2);
                pointA.setY((pointA.getY() + pointB.getY()) / 2);

                polygon.getPositions().remove(pointB);
                mergePoints(polygon, distance);
            }
        }
    }

    /**
     * Разбивает многоугольники, пока все не будут выпуклыми.
     *
     * @param polygons многоугольники.
     * @return список многоугольников.
     */
    public static ArrayList<Polygon> partitionPolygon(List<Polygon> polygons) {
        ArrayList<Polygon> result = new ArrayList<Polygon>();
        for (Polygon polygon : polygons)
            result.addAll(partition(polygon));

        if (result.size() == polygons.size())
            return result;
        else
            return partitionPolygon(result);
    }

    /**
     * Разбивает многоугольник на несколько в случае если есть вершина, у которой есть вершина растояние
     * до которой меньше чем до ближайщих двух вершин. Разбивает относительно этих двух вершин.
     *
     * @param polygon многоугольник.
     * @return список многоугольников получившихся при разбиении.
     */
    private static ArrayList<Polygon> partition(Polygon polygon) {
        ArrayList<Polygon> result  = new ArrayList<Polygon>();
        for (int i = 0; i < polygon.size(); i++) {
            Position point = polygon.get(i);
            double distanceA = point.getDistanceTo(polygon.get(i + 1));
            double distanceB = point.getDistanceTo(polygon.get(i - 1));
            double distance = Math.min(distanceA, distanceB);
            for (int j = 0; j < polygon.size(); j++) {
                Position brotherPoint = polygon.get(j);
                if (!brotherPoint.equals(polygon.get(i - 1)) && i != j && !brotherPoint.equals(polygon.get(i + 1))) {
                    if (MathService.hypot(point, brotherPoint) < distance) {
                        Polygon polygon1 = new Polygon();
                        Polygon polygon2 = new Polygon();

                        for (int k = 0; k < i + 1; k++)
                            polygon1.add(polygon.get(k));
                        for (int k = j; k < polygon.size(); k++)
                            polygon1.add(polygon.get(k));

                        for (int k = i; k < j + 1; k++)
                            polygon2.add(polygon.get(k));

                        result.add(polygon1);
                        result.add(polygon2);
                        return result;
                    }
                }
            }
        }
        if (result.isEmpty())
            result.add(polygon);
        return result;
    }
}
