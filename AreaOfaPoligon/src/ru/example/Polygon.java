package ru.example;

public class Polygon {
    public Position[] positions;
    public int n;

    public Polygon() {
        this.positions = new Position[100];
        this.n = 0;
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void add(Position position) {
        positions[n] = position;
        n++;
    }

    public Position getTailPosition() {
        return positions[n-1];
    }
}
