package se.dxtr;

import java.util.*;

/**
 * Created by dexter on 05/10/15.
 */
public class Kruskal {
    static Comparator<Edge<Void, Weight>> weightEdgeComparator = (edge1, edge2) -> {
        double cmp = edge1.getData ().weight - edge2.getData ().weight;
        if (cmp == 0)
            return 0;
        else if (cmp < 0)
            return -1;
        return 1;
    };

    static Comparator<Edge> lexicoGraphicalOrder = (edge1, edge2) -> {
        if (edge1.getFrom ().getId () == edge2.getFrom ().getId ())
            return edge1.getTo ().getId () - edge2.getTo ().getId ();
        return edge1.getFrom ().getId () - edge2.getFrom ().getId ();
    };

    static public List<Edge<Void, Weight>> kruskal (Graph<Void, Weight> graph) {
        DisjointSets sets = new DisjointSets (graph.size ());
        List<Edge<Void, Weight>> edges = new ArrayList<> (graph.getEdges ());
        edges.sort (weightEdgeComparator);
        List<Edge<Void, Weight>> tree = new ArrayList<> ();

        for (Edge<Void, Weight> edge : edges) {
            int a = edge.getFrom ().getId ();
            int b = edge.getTo ().getId ();
            if (!sets.inSameSet (a, b)) {
                tree.add (edge);
                sets.union (a, b);
            }
        }

        return tree;
    }

    public static boolean isSpanningTree (SortedSet<Edge<Void, Weight>> tree, Graph<Void, Weight> graph) {
        return tree.size () == graph.size () - 1;
    }

}
