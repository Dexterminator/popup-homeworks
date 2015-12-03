package se.dxtr.rafting;

import java.awt.geom.Point2D;

public class Rafting {

    static Kattio io = new Kattio();
    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            int innerCount = io.getInt();
            for (int j = 0; j < innerCount; j++) {
                Point2D.Double point = new Point2D.Double(io.getInt(), io.getInt());
                System.err.println("point = " + point);
            }
            int outerCount = io.getInt();
            for (int j = 0; j < outerCount; j++) {
                Point2D.Double point = new Point2D.Double(io.getInt(), io.getInt());
                System.err.println("point = " + point);
            }
        }
        io.close();
    }
}
