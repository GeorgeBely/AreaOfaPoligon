package ru;

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

    public int getPositionAx() {
        return positionAx;
    }

    public void setPositionAx(int positionAx) {
        this.positionAx = positionAx;
    }

    public int getPositionAy() {
        return positionAy;
    }

    public void setPositionAy(int positionAy) {
        this.positionAy = positionAy;
    }

}

