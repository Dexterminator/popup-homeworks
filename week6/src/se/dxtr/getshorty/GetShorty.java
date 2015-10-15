package se.dxtr.getshorty;

import java.text.DecimalFormat;

/**
 * Created by dexter on 14/10/15.
 */
public class GetShorty {

    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();

            if (m == 0 && n == 0)
                break;

            Graph<Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int x = io.getInt ();
                int y = io.getInt ();
                double f = io.getDouble ();
                graph.addDirectedEdge (x, y, new Weight (f));
                graph.addDirectedEdge (y, x, new Weight (f));
            }

            double[] largestSize = Dijkstra.largestSize (graph, graph.getVertices ().get (0));
            double goalSize = largestSize[n - 1];
            DecimalFormat df = new DecimalFormat ("0.0000");
            io.println (df.format (goalSize));
        }
        io.close ();
    }
}
