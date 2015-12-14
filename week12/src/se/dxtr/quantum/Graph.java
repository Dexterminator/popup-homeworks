package se.dxtr.quantum;

import java.util.*;

/**
 * Generic graph class representing a graph with edges with some data associated with them.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Graph<E> {
    private final Map<BitSet, Vertex<E>> vertices;
    private final List<Edge<E>> edges;

    public Graph () {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    /**
     * Add a new undirected edge without data between two vertices to the graph
     *
     * @param from the id of the first vertice
     * @param to   the id of the second vertice
     */
    public void addEdge (BitSet from, BitSet to) {
        addEdge (from, to, null);
    }

    /**
     * Add a new undirected edge with data between two vertices to the graph
     *  @param from the id of the first vertice
     * @param to   the id of the second vertice
     * @param data the data the edge contains
     */
    public void addEdge (BitSet from, BitSet to, E data) {
        Vertex<E> fromVertex = vertices.get (from);
        Vertex<E> toVertex = vertices.get (to);
        Edge<E> edge = new Edge<> (fromVertex, toVertex, data);
        edges.add (edge);
        fromVertex.addEdge (edge);
        toVertex.addEdge (edge);
    }

    public void addVertex(Vertex<E> vertex) {
        vertices.put(vertex.getId(), vertex);
    }

    public boolean containsWord(BitSet set) {
        return vertices.containsKey(set);
    }

    /**
     * Add a new directed edge with data between two vertices to the graph
     *
     * @param from the id of the first vertice
     * @param to   the id of the second vertice
     * @param data the data the edge contains
     */
    public Edge<E> addDirectedEdge (BitSet from, BitSet to, E data) {
        Vertex<E> fromVertex = vertices.get (from);
        Vertex<E> toVertex = vertices.get (to);
        Edge<E> edge = new Edge<> (fromVertex, toVertex, data);
        edges.add (edge);
        fromVertex.addEdge (edge);
        toVertex.incrementInDegree ();
        return edge;
    }

//    /**
//     * Add a new directed edge without data between two vertices to the graph
//     *
//     * @param from the id of the first vertice
//     * @param to   the id of the second vertice
//     */
//    public void addDirectedEdge (int from, int to) {
//        addDirectedEdge (from, to, null);
//    }

    /**
     * Returns an unmodifiable view of all edges in the graph.
     *
     * @return an unmodifiable view of all edges in the graph
     */
    public List<Edge<E>> getEdges () {
        return Collections.unmodifiableList (edges);
    }

    /**
     * Returns an unmodifiable view of all vertices in the graph.
     *
     * @return an unmodifiable view of all vertices in the graph
     */
    public Map<BitSet, Vertex<E>> getVertices () {
        return vertices;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    public int size () {
        return vertices.size ();
    }

    @Override
    public String toString () {
        return "Graph{" +
                "edges=" + edges +
                '}';
    }
}
