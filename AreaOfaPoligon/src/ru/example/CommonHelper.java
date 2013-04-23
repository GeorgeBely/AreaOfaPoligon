package ru.example;

public class CommonHelper {
    static int n;
    static Position[] positions;
    static Frame frame;

    public static void mergePoints(Frame frame) {
        CommonHelper.frame = frame;

        positions = frame.massp;
        n = frame.n;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                if (checkDistance(i)) {
                    calculateAveragePosition(i);
                    removeExcessPosition(i);
                    // рекурсивный вызов
                    mergePoints(frame);
                }
            }
        }
    }

    private static void removeExcessPosition(int i) {
        for (int j = i; j <= n; j++) {
            positions[j] = positions[j + 1];
        }
        frame.n--;
    }

    private static void calculateAveragePosition(int i) {
        positions[i - 1].setX((positions[i - 1].getX() + positions[i].getX()) / 2);
        positions[i - 1].setY((positions[i - 1].getY() + positions[i].getY()) / 2);
    }

    private static boolean checkDistance(int i) {
        double distance = Math.sqrt(Math.pow(positions[i].getX() - positions[i - 1].getX(), 2) + Math.pow(positions[i].getY() - positions[i - 1].getY(), 2));
        return distance <= Frame.DISTANCE_OF_VIEW;
    }
}
