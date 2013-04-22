package ru.example;

public class Position {
    private int positionAx;
    private int positionAy;

    public Position(int Ax, int Ay ) {
        positionAx = Ax;
        positionAy = Ay;
    }
    public Position(int Ax, int Ay, int Bx, int By) {
        positionAx = Ax;
        positionAy = Ay;
    }

    public Position() {
    }

    public int getAx() {
        return positionAx;
    }

    public void setAx(int positionAx) {
        this.positionAx = positionAx;
    }

    public int getAy() {
        return positionAy;
    }

    public void setAy(int positionAy) {
        this.positionAy = positionAy;
    }

}

