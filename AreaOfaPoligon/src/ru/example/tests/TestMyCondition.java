package ru.example.tests;

import ru.areaOfPolygon.location.Position;
import ru.example.common.MyCondition;

public class TestMyCondition {
    public static void main(String[] args) {
        MyCondition condition;
        //x: 69 y: 81  x: 331 y: 91 x: 141 y: 191
        condition = new MyCondition(new Position(0, 0), new Position(1, 1), new Position(1, 2));

       // System.out.println(condition.check(new Position(-10,1)));
        System.out.println(condition.distance(new Position(0,1)));
        System.out.println();
    }
}
