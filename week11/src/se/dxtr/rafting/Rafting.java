package se.dxtr.rafting;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

public class Rafting {

    static Kattio io = new Kattio();

    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            int innerCount = io.getInt();
            Point2D.Double[] innerPoints = new Point2D.Double[innerCount];
            Line2D.Double[] innerSegments = new Line2D.Double[innerCount];
            fillPolygon(innerCount, innerPoints, innerSegments);

            int outerCount = io.getInt();
            Point2D.Double[] outerPoints = new Point2D.Double[outerCount];
            Line2D.Double[] outerSegments = new Line2D.Double[outerCount];
            fillPolygon(outerCount, outerPoints, outerSegments);

            double smallest = Double.MAX_VALUE;
            for (int j = 0; j < innerPoints.length; j++) {
                for (int k = 0; k < outerSegments.length; k++) {
                    double distance = outerSegments[k].ptSegDist(innerPoints[j]);
                    smallest = Math.min(smallest, distance);
                }
            }

            for (int j = 0; j < outerPoints.length; j++) {
                for (int k = 0; k < innerSegments.length; k++) {
                    double distance = innerSegments[k].ptSegDist(outerPoints[j]);
                    smallest = Math.min(smallest, distance);
                }
            }

            io.println((smallest / 2));
        }
        io.close();
    }

    private static void fillPolygon(int count, Point2D.Double[] points, Line2D.Double[] segments) {
        points[0] = new Point2D.Double(io.getInt(), io.getInt());
        for (int i = 1; i < count; i++) {
            Point2D.Double point = new Point2D.Double(io.getInt(), io.getInt());
            points[i] = point;
            segments[i - 1] = new Line2D.Double(points[i - 1], point);
        }
        segments[count - 1] = new Line2D.Double(points[count - 1], points[0]);
    }
}
