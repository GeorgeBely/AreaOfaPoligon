package ru.areaOfPolygon.location;


import ru.areaOfPolygon.services.MathService;

public class Line {

    /** ������� �� ����� ����� ���������� ����� */
    private Position a;

    /** ������� � ����� ����� ���������� ����� */
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
     * @return {true}, ���� ����� �������� ����� ������� ������������ � ���������.
     */
    public boolean isWheather() {
        return a.isWheather() && b.isWheather();
    }

    /**
     * @return ���������� �� ����� a �� ����� b.
     */
    public double length() {
        return MathService.hypot(a, b);
    }
}
