package ru.areaOfPolygon.location;


import ru.areaOfPolygon.services.MathService;

public class Position {

    /** Позиция по абсцисе */
    private double x;

    /** Позиция по ординате */
    private double y;

    /** Тип позиции */
    private PositionType positionType;

    /** Значение итерации в пути */
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
     * @return {true}, если позиция является примыкающей к акватории.
     */
    public boolean isWheather() {
        return PositionType.WATER.equals(positionType);
    }

    /**
     * Рассчитывает дистанцию до точки.
     *
     * @param position точка до которой рассчитать дистанцию.
     * @return расстояние до точки.
     */
    public double getDistanceTo(Position position) {
        return MathService.hypot(this, position);
    }

    /**
     * @return {true}, если позиция одна и таже.
     */
    public boolean equals(Position position) {
        return x == position.getX() && y == position.getY();
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}

