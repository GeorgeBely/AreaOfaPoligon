package ru.areaOfPolygon.location;

import java.util.*;

public class Path {

    /** ������� ����� �������� ������, ������������ ���� */
    private SortedSet<Position> elements;


    public SortedSet<Position> getElements() {
        return elements;
    }

    public void setElements(SortedSet<Position> elements) {
        this.elements = elements;
    }

    /**
     * ��������� ������� � �������. ���� ������� �� ����������������� �������������� �.
     *
     * @param position �������.
     */
    public void add(Position position) {
        if (elements == null)
            elements = new TreeSet<Position>();

        elements.add(position);
    }

    /**
     * ��������� ��������� ������� � �������. ���� ������� �� ����������������� �������������� �.
     *
     * @param positions ��������� �������.
     */
    public void addAll(Collection<Position> positions) {
        if (elements == null)
            elements = new TreeSet<Position>();

        elements.addAll(positions);
    }

    /**
     * @return ��������� ������� � ����
     */
    public Position lastElement() {
        if (elements == null || elements.isEmpty())
            return null;

        return elements.last();
    }
}
