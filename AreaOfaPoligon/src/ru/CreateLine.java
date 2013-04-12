package ru;

import java.awt.*;


public class CreateLine {
    public static Position intersection(int x1, int y1, int x2, int y2, int x3, int y3) {
        int Ax, Ay, Bx, By, Cx, Cy, Dx, Dy;
        if (x1 > x2) {
            if (y1 > y2) {
               Ax=x1+20;
               Ay=y1-20;
               Bx=x2+20;
               By=y2-20;
            } else {
                Ax=x1-20;
                Ay=y1-20;
                Bx=x2-20;
                By=y2-20;
            }
        } else {
            if (y1 > y2) {
                Ax=x1+20;
                Ay=y1+20;
                Bx=x2+20;
                By=y2+20;
            } else {
                Ax=x1-20;
                Ay=y1+20;
                Bx=x2-20;
                By=y2+20;
            }
        }
        if (x2 > x3) {
            if (y2 > y3) {
                Cx=x2+20;
                Cy=y2-20;
                Dx=x3+20;
                Dy=y3-20;
            } else {
                Cx=x2-20;
                Cy=y2-20;
                Dx=x3-20;
                Dy=y3-20;
            }
        } else {
            if (y2 > y3) {
                Cx=x2+20;
                Cy=y2+20;
                Dx=x3+20;
                Dy=y3+20;
            } else {
                Cx=x2-20;
                Cy=y2+20;
                Dx=x3-20;
                Dy=y3+20;
            }
        }
        Position position = new Position();
        double k = (double)(By-Ay)/(Bx-Ax);
        double b = (double) Ay-k*Ax;

        double g = (double) (Dy-Cy)/(Dx-Cx);
        double a = (double) Cy-g*Cx;
        int x = (int) Math.round((a-b)/(k-g));
        int y = (int) Math.round(k*x+b);
        position.setPositionAx(x);
        position.setPositionAy(y);
        return position;
    }
}
