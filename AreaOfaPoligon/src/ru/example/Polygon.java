package ru.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Многоугольник
 */
public class Polygon {

    public List<Position> positions;
    public int n;

    public Polygon() {
        this.positions = new ArrayList<Position>();
        this.n = 0;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void add(Position position) {
        positions.add(position);
        n++;
    }

    public Position getTailPosition() {
        return positions.get(positions.size()-1);
    }
}
