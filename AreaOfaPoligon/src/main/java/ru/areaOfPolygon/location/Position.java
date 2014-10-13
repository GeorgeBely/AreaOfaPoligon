package ru.areaOfPolygon.location;


import ru.areaOfPolygon.services.MathService;

public class Position {

    /** ѕозици€ по абсцисе */
    private double x;

    /** ѕозици€ по ординате */
    private double y;

    /** “ип позиции */
    private PositionType positionType;

    /** «начение итерации в пути */
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
     * @return {true}, если позици€ €вл€етс€ примыкающей к акватории.
     */
    public boolean isWheather() {
        return PositionType.WATER.equals(positionType);
    }

    /**
     * –ассчитывает дистанцию до точки.
     *
     * @param position точка до которой рассчитать дистанцию.
     * @return рассто€ние до точки.
     */
    public double getDistanceTo(Position position) {
        return MathService.hypot(this, position);
    }

    /**
     * @return {true}, если позици€ одна и таже.
     */
    public boolean equals(Position position) {
        return x == position.getX() && y == position.getY();
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}

