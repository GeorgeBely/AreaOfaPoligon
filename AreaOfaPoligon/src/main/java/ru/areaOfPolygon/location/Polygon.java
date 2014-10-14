package ru.areaOfPolygon.location;


import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывающий многоугольник.
 */
public class Polygon {

    /** Список точек многоугольника */
    public List<Position> positions;


    public Polygon() {
        this.positions = new ArrayList<Position>();
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    /**
     * Добавляет точку к многоугольнику.
     *
     * @param position точка.
     */
    public void add(Position position) {
        positions.add(position);
    }

    /**
     * @return количество точек в многоугольнике.
     */
    public int size() {
        if (positions == null)
            return 0;
        return positions.size();
    }

    /**
     * Возвращает точку по индексу.
     *
     * @param index индекс
     * @return позицию точки.
     */
    public Position get(int index) {
        int i = index/size();
        i = index - (i * size());
        if (i < 0)
            i = size() + i;

        return positions.get(i);
    }

    /**
     * @return последнюю точку многоугольника, или <code>null</code>, если точек нет.
     */
    public Position getTailPosition() {
        if (size() == 0)
            return null;
        return positions.get(size() - 1);
    }
}
