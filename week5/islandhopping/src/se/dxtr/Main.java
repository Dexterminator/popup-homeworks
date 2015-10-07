package se.dxtr;

import java.awt.geom.Point2D;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main(String[] args) {
        int n = io.getInt ();
        for (int i = 0; i < n; i++) {
            getShortestBridgeLength ();
        }
    }

    private static void getShortestBridgeLength () {
        int m = io.getInt ();
        Point2D.Double[] islands = new Point2D.Double[m];
        for (int i = 0; i < m; i++) {
            double x = io.getDouble ();
            double y = io.getDouble ();
            Point2D.Double island = new Point2D.Double (x, y);
            islands[i] = island;
        }
    }
}
