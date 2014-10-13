package ru.areaOfPolygon.location;


import ru.areaOfPolygon.services.MathService;

public class Position {

    /** ������� �� ������� */
    private double x;

    /** ������� �� �������� */
    private double y;

    /** ��� ������� */
    private PositionType positionType;

    /** �������� �������� � ���� */
    private int countLine;


    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(double x, double y, PositionType positionType) {
        this.positionType = positionType;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType earth) {
        this.positionType = earth;
    }

    public int getCountLine() {
        return countLine;
    }

    public void setCountLine(int countLine) {
        this.countLine = countLine;
    }

    /**
     * @return {true}, ���� ������� �������� ����������� � ���������.
     */
    public boolean isWheather() {
        return PositionType.WATER.equals(positionType);
    }

    /**
     * ������������ ��������� �� �����.
     *
     * @param position ����� �� ������� ���������� ���������.
     * @return ���������� �� �����.
     */
    public double getDistanceTo(Position position) {
        return MathService.hypot(this, position);
    }

    /**
     * @return {true}, ���� ������� ���� � ����.
     */
    public boolean equals(Position position) {
        return x == position.getX() && y == position.getY();
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}

