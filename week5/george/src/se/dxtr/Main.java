package se.dxtr;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main(String[] args) {
        int n = io.getInt ();
        int m = io.getInt ();
        int start = io.getInt ();
        int goal = io.getInt ();
        int startingTimeDiff = io.getInt ();
        int georgeIntersectionCount = io.getInt ();

        int[] georgeVertices = new int[georgeIntersectionCount];
        if (georgeIntersectionCount > 0) {

            for (int i = 0; i < georgeIntersectionCount; i++) {
                georgeVertices[i] = io.getInt ();
            }
        }

        Graph<Time> graph = new Graph<> (n+1);
        for (int i = 0; i < m; i++) {
            int from = io.getInt ();
            int to = io.getInt ();
            int traversalTime = io.getInt ();
            Edge<Time> edge = graph.addDirectedEdge (from, to, new Time (traversalTime));
            Edge<Time> reverseEdge = graph.addDirectedEdge (to, from, new Time (traversalTime));
            edge.getData ().reverseEdge = reverseEdge;
            reverseEdge.getData ().reverseEdge = edge;
        }

        if (georgeIntersectionCount > 0) {
            int time = 0;
            int from = georgeVertices[0];
            int to;
            for (int i = 1; i < georgeIntersectionCount; i++) {
                to = georgeVertices[i];
                Edge<Time> georgeEdge = null;
                for (Edge<Time> edge : graph.getVertices ().get (from).getEdges ()) {
                    if (edge.getTo ().getId () == to) {
                        georgeEdge = edge;
                        break;
                    }
                }
                georgeEdge.getData ().georgeVisit (time);
                georgeEdge.getData ().reverseEdge.getData ().georgeVisit (time);
                time += georgeEdge.getData ().traversalTime;
                from = to;
            }
        }

        DistanceResult distanceResult = Dijkstra.shortestPath (graph, graph.getVertices ().get (start), startingTimeDiff);
        long shortest = distanceResult.distance[goal];
        io.println (shortest);
        io.close ();
    }
}
