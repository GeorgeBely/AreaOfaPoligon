package ru.areaOfPolygon.location;

import java.util.*;

public class Path {

    /** Очередь точек движения катера, состовляющяя путь */
    private SortedSet<Position> elements;


    public SortedSet<Position> getElements() {
        return elements;
    }

    public void setElements(SortedSet<Position> elements) {
        this.elements = elements;
    }

    /**
     * Добавляет позицию в очередь. Если очередь не инициализированна инициализирует её.
     *
     * @param position позиция.
     */
    public void add(Position position) {
        if (elements == null)
            elements = new TreeSet<>();

        elements.add(position);
    }

    /**
     * Добавляет коллекцию позиций в очередь. Если очередь не инициализированна инициализирует её.
     *
     * @param positions коллекция позиций.
     */
    public void addAll(Collection<Position> positions) {
        if (elements == null)
            elements = new TreeSet<>();

        elements.addAll(positions);
    }

    /**
     * @return последний элемент в пути
     */
    public Position lastElement() {
        if (elements == null || elements.isEmpty())
            return null;

        return elements.last();
    }
}
