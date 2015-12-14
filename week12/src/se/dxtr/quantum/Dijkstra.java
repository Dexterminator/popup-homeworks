package se.dxtr.quantum;

import java.util.*;

/**
 * Utility class with methods for finding shortest paths from a single source.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Dijkstra {

    public static final long INFINITY = Long.MAX_VALUE;

    /**
     * Uses Dijkstra's algorithm to find the shortest path from a vertex to all other vertices in a graph.
     *
     * @param graph  the graph in which to find the shortest path
     * @param source the vertex from which to find shortest paths
     * @return a DistanceResult containing an array of the lengths of the shortest paths, and a parent array specifying the paths.
     * A distance value of Integer.MAX_VALUE indicates that the vertex is not reachable form the source.
     */
    public static Map<BitSet, Long> shortestPath (Graph<Weight> graph, Vertex<Weight> source) {
        Map<BitSet, Long> distance = new HashMap<>(graph.getVertices().size());
        distance.put(source.getId(), 0L);
        NavigableSet<Vertex<Weight>> queue = getVerticesSortedByDistanceQueue (distance);

        // Initialize all distances as unknown
        for (Vertex<Weight> vertex : graph.getVertices ().values()) {
            if (vertex.getId () != source.getId ())
                distance.put(vertex.getId(), INFINITY);
            queue.add (vertex);
        }

        // Constantly select the cheapest vertex, and update the shortest path array if the path through
        // this vertex to a node is cheaper than the best known path
        while (!queue.isEmpty ()) {
            Vertex<Weight> current = queue.pollFirst ();
            if (distance.get(current.getId()).equals(INFINITY)) {
                continue;
            }
            for (Edge<Weight> edge : current.getEdges ()) {
                Vertex<Weight> to = edge.getTo ();
                long alternativeDistance = distance.get(current.getId ()) + edge.getData ().weight;
                if (alternativeDistance < distance.get(to.getId ())) {
                    queue.remove (to);
                    distance.put(to.getId(), alternativeDistance);
                    queue.add (to);
                }
            }
        }

        return distance;
    }

    /**
     * Get a queue with vertices sorted by their distance from a source node.
     * Vertices are sorted by ID if their distance is equal.
     */
    private static <V> NavigableSet<Vertex<V>> getVerticesSortedByDistanceQueue (Map<BitSet, Long> distance) {
        return new TreeSet<> ((vertex1, vertex2) -> {
            if (distance.get(vertex1.getId()).equals(distance.get(vertex2.getId()))) {
                int i1 = vertex1.getId().nextSetBit(0);
                int i2 = vertex2.getId().nextSetBit(0);
                while (true) {
                    if (i1 == i2) {
                        if (i1 == -1 && i2 == -1)
                            return 0;
                        i1 = vertex1.getId().nextSetBit(i1 + 1);
                        i2 = vertex2.getId().nextSetBit(i2 + 1);
                    } else if (i1 < i2) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
            if (distance.get(vertex1.getId ()) - distance.get(vertex2.getId ()) < 0)
                return -1;
            return 1;
        });
    }
}
