package ru.example.common;

import ru.areaOfPolygon.location.Position;

/**
 * Created with IntelliJ IDEA.
 * User: krld
 * Date: 04.05.13
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class MyCondition {
    private final double startX;
    private final double endX;
    private double b;
    private double k;
    TypeCondition typeCondition;

    public MyCondition(Position pos1, Position pos2, Position averagePoint) {
        if (pos2.getX() < pos1.getX()) {
            Position posTemp = pos1;
            pos1 = pos2;
            pos2 = posTemp;
        }
        startX = pos1.getX();
        endX = pos2.getX();
       // System.out.println();
       // System.out.println(pos1 + "  " + pos2 + " " + averagePoint);
        double divider = pos2.getX() - pos1.getX();
        if (divider == 0) {
            System.out.println("error: divider == 0");
            k = 0;
        } else {
            k = (pos2.getY() - pos1.getY()) / divider;
        }
        b = pos1.getY() - k * pos1.getX();

        double y = calculate(averagePoint.getX());
        if (y > averagePoint.getY()) {
            typeCondition = TypeCondition.LESS;
        } else {
            typeCondition = TypeCondition.GREATER;
        }
      //  System.out.println(this);

    }

    private double calculate(double x) {
        return k * x + b;
    }

    @Override
    public String toString() {
        return "condition: y = " + k + "x + " + b + " typeCondition: " + typeCondition;
    }

    public Boolean check(Position pos) {
        if (startX > pos.getX() || endX < pos.getX()) {
            return true;
        }
        double y = calculate(pos.getX());
        if (y == pos.getY()) {
            return true;
        }
        if (y > pos.getY()) {
            if (typeCondition.equals(TypeCondition.LESS)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (typeCondition.equals(TypeCondition.LESS)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public double distance(Position pos) {

        double distance = (Math.abs(k * pos.getX() - pos.getY() + b))/(Math.sqrt(k*k + 1));
        return distance;
       /* double y = calculate(pos.getX());
        return  Math.abs(y - pos.getY());
*/
    }
}
