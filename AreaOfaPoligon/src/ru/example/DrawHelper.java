package ru.example;

public class DrawHelper {
    public static Position intersection(int x1, int y1, int x2, int y2, int x3, int y3, int distanceOfView) {
        int Ax, Ay, Bx, By, Cx, Cy, Dx, Dy;
        if (x1 > x2) {
            if (y1 > y2) {
                Ax = x1 + distanceOfView;
                Ay = y1 - distanceOfView;
                Bx = x2 + distanceOfView;
                By = y2 - distanceOfView;
            } else {
                Ax = x1 - distanceOfView;
                Ay = y1 - distanceOfView;
                Bx = x2 - distanceOfView;
                By = y2 - distanceOfView;
            }
        } else {
            if (y1 > y2) {
                Ax = x1 + distanceOfView;
                Ay = y1 + distanceOfView;
                Bx = x2 + distanceOfView;
                By = y2 + distanceOfView;
            } else {
                Ax = x1 - distanceOfView;
                Ay = y1 + distanceOfView;
                Bx = x2 - distanceOfView;
                By = y2 + distanceOfView;
            }
        }
        if (x2 > x3) {
            if (y2 > y3) {
                Cx = x2 + distanceOfView;
                Cy = y2 - distanceOfView;
                Dx = x3 + distanceOfView;
                Dy = y3 - distanceOfView;
            } else {
                Cx = x2 - distanceOfView;
                Cy = y2 - distanceOfView;
                Dx = x3 - distanceOfView;
                Dy = y3 - distanceOfView;
            }
        } else {
            if (y2 > y3) {
                Cx = x2 + distanceOfView;
                Cy = y2 + distanceOfView;
                Dx = x3 + distanceOfView;
                Dy = y3 + distanceOfView;
            } else {
                Cx = x2 - distanceOfView;
                Cy = y2 + distanceOfView;
                Dx = x3 - distanceOfView;
                Dy = y3 + distanceOfView;
            }
        }
        Position position = new Position();
        double k = (double) (By - Ay) / (Bx - Ax);
        double b = (double) Ay - k * Ax;

        double g = (double) (Dy - Cy) / (Dx - Cx);
        double a = (double) Cy - g * Cx;
        int x = (int) Math.round((a - b) / (k - g));
        int y = (int) Math.round(k * x + b);
        position.setX(x);
        position.setY(y);
        return position;
    }
}
