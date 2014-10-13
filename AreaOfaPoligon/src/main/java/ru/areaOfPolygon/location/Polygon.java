package ru.areaOfPolygon.location;


import java.util.ArrayList;
import java.util.List;

/**
 * ����� ����������� �������������.
 */
public class Polygon {

    /** ������ ����� �������������� */
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
     * ��������� ����� � ��������������.
     *
     * @param position �����.
     */
    public void add(Position position) {
        positions.add(position);
    }

    /**
     * @return ���������� ����� � ��������������.
     */
    public int size() {
        if (positions == null)
            return 0;
        return positions.size();
    }

    /**
     * ���������� ����� �� �������.
     *
     * @param index ������
     * @return ������� �����.
     */
    public Position get(int index) {
        int i = index/size();
        i = index - (i * size());
        if (i < 0)
            i = size() + i;

        return positions.get(i);
    }

    /**
     * @return ��������� ����� ��������������, ��� <code>null</code>, ���� ����� ���.
     */
    public Position getTailPosition() {
        if (size() == 0)
            return null;
        return positions.get(size() - 1);
    }
}
