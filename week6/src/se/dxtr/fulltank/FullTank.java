package se.dxtr.fulltank;

public class FullTank {

    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        int n = io.getInt ();
        int m = io.getInt ();
        int maxFuel = Integer.MIN_VALUE;
        Graph<Integer> graph = new Graph<> ();
        for (int i = 0; i < n; i++) {
            int fuelPrice = io.getInt ();
            graph.addVertex (new Vertex (i, fuelPrice));
        }

        for (int i = 0; i < m; i++) {
            int from = io.getInt ();
            int to = io.getInt ();
            int weight = io.getInt ();
            graph.addDirectedEdge (from, to, weight);
            graph.addDirectedEdge (to, from, weight);
        }


        int q = io.getInt ();
        for (int i = 0; i < q; i++) {
            int fuelCapacity = io.getInt ();
            int start = io.getInt ();
            int goal = io.getInt ();
            long[][] cheapestTrip = Dijkstra.cheapestTrip (graph, graph.getVertices ().get (start), fuelCapacity);
            io.println (cheapestTrip[fuelCapacity][goal]);
        }
        io.close ();
    }
}
