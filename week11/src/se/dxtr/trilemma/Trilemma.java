package se.dxtr.trilemma;

import java.awt.geom.Point2D;

public class Trilemma {
    static Kattio io = new Kattio();
    static final double EPSILON = 1e-10;

    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            int x1 = io.getInt();
            int y1 = io.getInt();
            int x2 = io.getInt();
            int y2 = io.getInt();
            int x3 = io.getInt();
            int y3 = io.getInt();
            solve(x1, y1, x2, y2, x3, y3, i + 1);
        }
        io.close();
    }

    private static void solve(int x1, int y1, int x2, int y2, int x3, int y3, int i) {
        double side1 = distance(x1, y1, x2, y2);
        double side2 = distance(x2, y2, x3, y3);
        double side3 = distance(x3, y3, x1, y1);

        if (equals(area(side1, side2, side3), 0)) {
            io.println("Case #" + i + ": not a triangle");
            return;
        }

        double angle1 = angle(side1, side2, side3);
        double angle2 = angle(side2, side3, side1);
        double angle3 = angle(side3, side1, side2);

        String sideType = findSideType(side1, side2, side3);
        String angleType = findAngleType(angle1, angle2, angle3);
        io.println("Case #" + i + ": " + sideType + " " + angleType + " triangle");
    }

    private static String findAngleType(double angle1, double angle2, double angle3) {
        if (equals(angle1, 90.0) || equals(angle2, 90.0) || equals(angle3, 90.0))
            return "right";
        if (angle1 > 90.0 || angle2 > 90.0 || angle3 > 90.0)
            return "obtuse";
        return "acute";
    }

    private static String findSideType(double side1, double side2, double side3) {
        if (!equals(side1, side2) && !equals(side1, side3) && !equals(side2, side3))
            return "scalene";
        return "isosceles";
    }

    private static double area(double side1, double side2, double side3) {
        double s = (side1 + side2 + side3) / 2.0;
        double x = (s * (s - side1) * (s - side2) * (s - side3));
        return Math.sqrt(x);
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Point2D.distance(x1, y1, x2, y2);
    }

    public static boolean equals(double a, double b) {
        return a == b || Math.abs(a - b) < EPSILON;
    }

    public static double angle(double side1, double side2, double side3) {
        return Math.toDegrees(Math.acos((side1 * side1 + side2 * side2 - side3 * side3) / (2 * side1 * side2)));
    }
}
