package ru.example;

public class Position {
    private double x;
    private double y;
    private boolean wheather;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(double x, double y, boolean wheather) {
        this.wheather = wheather;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
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

    public boolean isWheather() {
        return wheather;
    }

    public void setWheather(boolean earth) {
        this.wheather = earth;
    }
}

