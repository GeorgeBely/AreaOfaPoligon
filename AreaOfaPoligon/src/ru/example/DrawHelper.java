package ru.example;

import ru.example.common.MyCondition;
import ru.example.common.WaterTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawHelper {
    public static final int NEAR_WATER_DISTANCE = 20;
    private static final int DRAWING_SAND_DISTANCE = 2;

    private static BufferedImage grass;

    static {
        try {
            final String dir = System.getProperty("user.dir");
            grass = ImageIO.read(new File("AreaOfaPoligon/res/grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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

    public static void drawGrass(Graphics g) {
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                g.drawImage(grass, x * 36, y * 36, null);
            }
        }
    }

    public static void drawWater(Graphics g, Position[] beachLine, int n) {
        Position averagePoint;
        int sumX = 0;
        int sumY = 0;
        Integer minX = null;
        Integer maxX = null;

        for (int i = 0; i < n; i++) {
            sumX += beachLine[i].getX();
            sumY += beachLine[i].getY();
            if (minX == null || minX > beachLine[i].getX()) {
                minX = beachLine[i].getX();
            }
            if (maxX == null || maxX < beachLine[i].getX()) {
                maxX = beachLine[i].getX();
            }
        }
        averagePoint = new Position(sumX / n, sumY / n);

        ArrayList<MyCondition> conditions = new ArrayList<MyCondition>();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                conditions.add(new MyCondition(beachLine[n - 1], beachLine[i], averagePoint));
            } else {
                conditions.add(new MyCondition(beachLine[i - 1], beachLine[i], averagePoint));
            }
        }
        ArrayList<WaterTile> waterTiles = new ArrayList<WaterTile>();
        for (int x = 0; x < 500; x++) {
            for (int y = 0; y < 500; y++) {
                int scaledX = x * 4;
                int scaledY = y * 4;
                Position checkedPos = new Position(scaledX, scaledY);
                if (checkConditions(conditions, checkedPos)) {
                    if (scaledX > minX && scaledX < maxX) {
                        double distance = getMinDistanceToLine(conditions, checkedPos);
                        boolean nearBeachLine;
                        if (distance < NEAR_WATER_DISTANCE) {
                            nearBeachLine = true;
                        } else {
                            nearBeachLine = false;
                        }
                        boolean drawSand;
                        if (distance < DRAWING_SAND_DISTANCE) {
                            drawSand = true;
                        } else {
                            drawSand = false;
                        }
                        waterTiles.add(new WaterTile(scaledX, scaledY, nearBeachLine, drawSand));
                        //drawWaterWithSand(g, scaledX, scaledY);
                    }
                }
            }
        }
        for (WaterTile waterTile : waterTiles) {
            waterTile.drawSand(g);
        }
        for (WaterTile waterTile : waterTiles) {
            waterTile.draw(g);
        }
    }

    private static double getMinDistanceToLine(ArrayList<MyCondition> conditions, Position pos) {
        Double minDistance = null;
        for (MyCondition condition : conditions) {
            double distance = condition.distance(pos);
            if (minDistance == null || minDistance > distance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }

    private static boolean checkConditions(ArrayList<MyCondition> conditions, Position pos) {
        for (MyCondition condition : conditions) {
            if (condition.check(pos)) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static double getTotalLinesLength(Position[] posArray, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            try {
            if (i == 0) {
                totalDistance += getDistanceBetweenPositions(posArray[n-1], posArray[0]);
            } else if (i != 0) {
                totalDistance += getDistanceBetweenPositions(posArray[i], posArray[i - 1]);
            }    } catch (Exception e) {
                //ignore
                e.printStackTrace();
            }
          //  System.out.println("totalDistance " + totalDistance);
        }
        return totalDistance;
    }

    private static double getDistanceBetweenPositions(Position pos1, Position pos2) {
        return Math.sqrt(Math.pow(pos2.getX() - pos1.getX(), 2) + Math.pow(pos2.getY() - pos2.getY(), 2));
    }
}
