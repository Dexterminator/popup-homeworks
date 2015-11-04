package se.dxtr.fulltank;

import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Utility class with methods for finding shortest paths from a single source.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Dijkstra {

    public static final int INFINITY = Integer.MAX_VALUE;

    /**
     * Uses Dijkstra's algorithm to find the shortest path from a vertex to all other vertices in a graph.
     *
     * @param graph  the graph in which to find the shortest path
     * @param source the vertex from which to find shortest paths
     * @param fuelCapacity
     * @return a DistanceResult containing an array of the lengths of the shortest paths, and a parent array specifying the paths.
     * A distance value of Integer.MAX_VALUE indicates that the vertex is not reachable form the source.
     */
    public static long[][] cheapestTrip (Graph<Integer> graph, Vertex<Integer> source, int fuelCapacity) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[source.getId ()] = 0;
        NavigableSet<Vertex<Integer>> queue = getVerticesSortedByDistanceQueue (distance);

        // Initialize all distances as unknown
        for (Vertex<Integer> vertex : graph.getVertices ()) {
            if (vertex.getId () != source.getId ())
                distance[vertex.getId ()] = INFINITY;
            queue.add (vertex);
        }

        // Constantly select the cheapest vertex, and update the shortest path array if the path through
        // this vertex to a node is cheaper than the best known path
        while (!queue.isEmpty ()) {
            Vertex<Integer> current = queue.pollFirst ();
            for (Edge<Integer> edge : current.getEdges ()) {
                Vertex<Integer> to = edge.getTo ();
                long alternativeDistance = distance[current.getId ()] + edge.getData ();
                if (alternativeDistance < distance[to.getId ()]) {
                    queue.remove (to);
                    distance[to.getId ()] = alternativeDistance;
                    parent[to.getId ()] = current;
                    queue.add (to);
                }
            }
        }

        return null;
    }

    /**
     * Get a queue with vertices sorted by their distance from a source node.
     * Vertices are sorted by ID if their distance is equal.
     */
    private static <V> NavigableSet<Vertex<V>> getVerticesSortedByDistanceQueue (long[] distance) {
        return new TreeSet<> ((vertex1, vertex2) -> {
            if (distance[vertex1.getId ()] == distance[vertex2.getId ()])
                return vertex1.getId () - vertex2.getId ();
            if (distance[vertex1.getId ()] - distance[vertex2.getId ()] < 0)
                return -1;
            return 1;
        });
    }
}
