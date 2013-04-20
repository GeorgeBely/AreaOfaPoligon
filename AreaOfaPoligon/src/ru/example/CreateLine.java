package ru.example;

public class CreateLine {

    public static final int DISTANCE_OF_VIEW = 2;

    public static Position intersection(int x1, int y1, int x2, int y2, int x3, int y3) {
        int Ax, Ay, Bx, By, Cx, Cy, Dx, Dy;
        if (x1 > x2) {
            if (y1 > y2) {
                Ax = x1 + DISTANCE_OF_VIEW;
                Ay = y1 - DISTANCE_OF_VIEW;
                Bx = x2 + DISTANCE_OF_VIEW;
                By = y2 - DISTANCE_OF_VIEW;
            } else {
                Ax = x1 - DISTANCE_OF_VIEW;
                Ay = y1 - DISTANCE_OF_VIEW;
                Bx = x2 - DISTANCE_OF_VIEW;
                By = y2 - DISTANCE_OF_VIEW;
            }
        } else {
            if (y1 > y2) {
                Ax = x1 + DISTANCE_OF_VIEW;
                Ay = y1 + DISTANCE_OF_VIEW;
                Bx = x2 + DISTANCE_OF_VIEW;
                By = y2 + DISTANCE_OF_VIEW;
            } else {
                Ax = x1 - DISTANCE_OF_VIEW;
                Ay = y1 + DISTANCE_OF_VIEW;
                Bx = x2 - DISTANCE_OF_VIEW;
                By = y2 + DISTANCE_OF_VIEW;
            }
        }
        if (x2 > x3) {
            if (y2 > y3) {
                Cx = x2 + DISTANCE_OF_VIEW;
                Cy = y2 - DISTANCE_OF_VIEW;
                Dx = x3 + DISTANCE_OF_VIEW;
                Dy = y3 - DISTANCE_OF_VIEW;
            } else {
                Cx = x2 - DISTANCE_OF_VIEW;
                Cy = y2 - DISTANCE_OF_VIEW;
                Dx = x3 - DISTANCE_OF_VIEW;
                Dy = y3 - DISTANCE_OF_VIEW;
            }
        } else {
            if (y2 > y3) {
                Cx = x2 + DISTANCE_OF_VIEW;
                Cy = y2 + DISTANCE_OF_VIEW;
                Dx = x3 + DISTANCE_OF_VIEW;
                Dy = y3 + DISTANCE_OF_VIEW;
            } else {
                Cx = x2 - DISTANCE_OF_VIEW;
                Cy = y2 + DISTANCE_OF_VIEW;
                Dx = x3 - DISTANCE_OF_VIEW;
                Dy = y3 + DISTANCE_OF_VIEW;
            }
        }
        Position position = new Position();
        double k = (double) (By - Ay) / (Bx - Ax);
        double b = (double) Ay - k * Ax;

        double g = (double) (Dy - Cy) / (Dx - Cx);
        double a = (double) Cy - g * Cx;
        int x = (int) Math.round((a - b) / (k - g));
        int y = (int) Math.round(k * x + b);
        position.setAx(x);
        position.setAy(y);
        return position;
    }
}
