package se.dxtr;

import java.awt.geom.Point2D;
import java.util.List;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main(String[] args) {
        int n = io.getInt ();
        for (int i = 0; i < n; i++) {
            getShortestBridgeLength ();
        }
        io.close ();
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

        Graph<Void, Weight> graph = new Graph<> (m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if (i != j)
                    graph.addEdge (i, j, new Weight (islands[i].distance (islands[j])));
            }
        }
        List<Edge<Void, Weight>> minimumSpanningTree = Kruskal.kruskal (graph);
        double sum = 0;
        for (Edge<Void, Weight> edge : minimumSpanningTree)
            sum += edge.getData ().weight;
        io.println (sum);
    }
}
