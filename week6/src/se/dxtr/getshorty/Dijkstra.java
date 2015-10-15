package se.dxtr.getshorty;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Dijkstra {

    public static final int INFINITY = Integer.MAX_VALUE;

    public static double[] largestSize (Graph<Weight> graph, Vertex<Weight> source) {
        double[] size = new double[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        size[source.getId ()] = 1.0;
        NavigableSet<Vertex<Weight>> queue = getVerticesSortedByDistanceQueue (size);

        // Initialize all distances as unknown
        for (Vertex<Weight> vertex : graph.getVertices ()) {
            if (vertex.getId () != source.getId ())
                size[vertex.getId ()] = 0.0;
            queue.add (vertex);
        }

        // Constantly select the cheapest vertex, and update the shortest path array if the path through
        // this vertex to a node is cheaper than the best known path
        while (!queue.isEmpty ()) {
            Vertex<Weight> current = queue.pollFirst ();
            for (Edge<Weight> edge : current.getEdges ()) {
                Vertex<Weight> to = edge.getTo ();
                double alternativeSize = size[current.getId ()] * edge.getData ().weight;
                if (alternativeSize > size[to.getId ()]) {
                    size[to.getId ()] = alternativeSize;
                    parent[to.getId ()] = current;
                    updatePriority (queue, to);
                }
            }
        }

        return size;
    }

    private static void updatePriority (NavigableSet<Vertex<Weight>> queue, Vertex<Weight> vertex) {
        queue.remove (vertex);
        queue.add (vertex);
    }

    private static <V> NavigableSet<Vertex<V>> getVerticesSortedByDistanceQueue (double[] size) {
        return new TreeSet<> ((vertex1, vertex2) -> {
            if (size[vertex1.getId ()] == size[vertex2.getId ()])
                return vertex1.getId () - vertex2.getId ();
            if (size[vertex2.getId ()] - size[vertex1.getId ()] < 0)
                return -1;
            return 1;
        });
    }
}
