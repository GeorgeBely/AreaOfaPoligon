package ru.example;

public class CommonHelper {
    static int n;
    static Position[] positions;
    static Frame frame;

    public static void mergePoints(Frame frame) {
        CommonHelper.frame = frame;

        positions = frame.positions;
        n = frame.n;
        for (int i = 0; i < n; i++) {
            if (i != 0 || true) {
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
        int prevI = i - 1;
        if (prevI < 0) {
            prevI = frame.n - 1;
        }
        positions[prevI].setX((positions[prevI].getX() + positions[i].getX()) / 2);
        positions[prevI].setY((positions[prevI].getY() + positions[i].getY()) / 2);
    }

    private static boolean checkDistance(int i) {

        int prevI = i - 1;
        if (prevI < 0) {
            prevI = frame.n - 1 ;
        }
        double distance = Math.sqrt(Math.pow(positions[i].getX() - positions[prevI].getX(), 2) + Math.pow(positions[i].getY() - positions[prevI].getY(), 2));
        return distance <= Frame.DISTANCE_OF_VIEW;
    }
}
