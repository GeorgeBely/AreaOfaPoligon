package ru.example;

import java.util.ArrayList;
import java.util.List;

public class CommonHelper {

    public static void calculatePositions(Integer distance, Polygon polygon) {
        CommonHelper.mergePoints(polygon);

        Polygon newPositions = new Polygon();
        for (int i = 0; i < polygon.getN(); i++) {
            newPositions.getPositions().set(i, MathFunction.intersection(AreaOfaPolygon.frame.getGraphics(), polygon,
                    polygon.getPositions().get(i-1 < 0 ? polygon.getN()-1 : i-1),
                    polygon.getPositions().get(i),
                    polygon.getPositions().get(i+1>polygon.getN()-1 ? 0 : i+1), distance));
            newPositions.setN(newPositions.getN() + 1);
        }
//            if (countPolygon % 2 == 0)
//                Panel.drawLine(AreaOfaPolygon.frame.getGraphics(), polygon.getTailPosition(), newPositions.getTailPosition(), color);
//            else
//                Panel.drawLine(AreaOfaPolygon.frame.getGraphics(), polygon.getPositions()[0], newPositions.getPositions()[0], color);


        polygon.setPositions(newPositions.getPositions());
    }

    /**
     * Определяет имеется ли точка, от которой до центра многоугольника расстояние меньше <code>distance</code>
     *
     * @param polygon  многоугольник
     * @param distance радиус радара
     * @return {true}, если есть точка от которой расстояние до центра меньше <code>distance</code>
     */
    public static boolean isHavePositionCloseToCenter(Polygon polygon, double distance) {
        for (Position position : polygon.getPositions()) {
            Position centerPosition = MathFunction.positionOfCenter(polygon);
            if (MathFunction.hypotenusePifagor(position, centerPosition) < distance)
                return true;
        }
        return false;
    }

    /**
     * Проходит по всем точкам многоугольника и соединяет близкие.
     * @param polygon многоугольник.
     */
    public static void mergePoints(Polygon polygon) {
        for (int i = 0; i < polygon.getN(); i++) {
            if (checkDistance(polygon.getPositions().get(i), polygon.getPositions().get(i + 1 > polygon.getN() - 1 ? 0 : i + 1))) {
                calculateAveragePosition(i, polygon, polygon.getPositions().get(i), polygon.getPositions().get(i + 1 > polygon.getN() - 1 ? 0 : i + 1));
                // рекурсивный вызов
                mergePoints(polygon);
            }
        }
    }

    /**
     * Соединяет близкие точки.
     */
    private static void calculateAveragePosition(int i, Polygon polygon, Position a, Position b) {
        a.setX((a.getX() + b.getX()) / 2);
        a.setY((a.getY() + b.getY()) / 2);

        polygon.getPositions().remove(i + 1);
//        for (int j = i + 1; j <= polygon.getN() + 3; j++)
//            polygon.getPositions().get(j) = polygon.getPositions().get(j + 1);

        polygon.setN(polygon.getN() - 1);
    }

    /**
     * Находит растояние от точки a до точки b
     * @return true, если растояние меньше дистанции осмотра
     */
    private static boolean checkDistance(Position a, Position b) {
        return MathFunction.hypotenusePifagor(a, b) <= Frame.distance;
    }

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
     * Разбивает многоугольник на 2 в случае если есть вершина, у которой есть вершина растояние до которой меньше чем
     * до ближайщих двух вершин. Разбивает относительно этих двух вершин.
     * @param polygon многоугольник.
     */
    private static ArrayList<Polygon> partition(Polygon polygon) {
        ArrayList<Polygon> result  = new ArrayList<Polygon>();
        for (int i = 0; i < polygon.getN(); i++) {
            double distanceA = MathFunction.hypotenusePifagor(polygon.getPositions().get(i), polygon.getPositions().get(i != polygon.getN() - 1 ? i + 1 : 0));
            double distanceB = MathFunction.hypotenusePifagor(polygon.getPositions().get(i), i != 0 ? polygon.getPositions().get(i-1) : polygon.getTailPosition());
            double distance = Math.min(distanceA, distanceB);
            for (int j = 0; j < polygon.getN(); j++) {
                if (j != (i != 0 ? i-1 : polygon.getN()) && j != i && j != (i != polygon.getN() ? i+1 : 0)) {
                    if (MathFunction.hypotenusePifagor(polygon.getPositions().get(i), polygon.getPositions().get(j)) < distance) {
                        final Polygon polygon1 = new Polygon();
                        final Polygon polygon2 = new Polygon();

                        for (int k = 0; k < i+1; k++)
                            polygon1.add(polygon.getPositions().get(k));
                        for (int k = j; k < polygon.getN(); k++)
                            polygon1.add(polygon.getPositions().get(k));

                        for (int k = i; k < j+1; k++)
                            polygon2.add(polygon.getPositions().get(k));

                        result.add(polygon1);
                        result.add(polygon2);
                        return result;
                    }
                }
            }
        }
        result.add(polygon);
        return result;
    }
}
