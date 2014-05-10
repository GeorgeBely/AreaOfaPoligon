package ru.example;

public class Line {
    private Position a;
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

    public boolean isWheather() {
        return a.isWheather() && b.isWheather();
    }
}
