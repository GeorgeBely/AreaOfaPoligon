package ru.areaOfPolygon.location;


import ru.areaOfPolygon.services.MathService;

public class Line {

    /** Позиция из какой точки начинается линия */
    private Position a;

    /** Позиция в какую точку начинается линия */
    private Position b;


    public Line(Position A, Position B) {
        this.a = A;
        this.b = B;
    }

    public Position getA() {
        return a;
    }

    public void setA(Position a) {
        this.a = a;
    }

    public Position getB() {
        return b;
    }

    public void setB(Position b) {
        this.b = b;
    }

    /**
     * @return {true}, если линия проходет между точками примыкающими к акватории.
     */
    public boolean isWheather() {
        return a.isWheather() && b.isWheather();
    }

    /**
     * @return расстояние от точка a до точки b.
     */
    public double length() {
        return MathService.hypot(a, b);
    }
}
