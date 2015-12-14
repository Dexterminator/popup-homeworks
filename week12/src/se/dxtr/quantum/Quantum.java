package se.dxtr.quantum;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Quantum {

    static Kattio io = new Kattio();
    static Graph<Weight> graph;
    static Set<BitSet> handled = new HashSet<>();
    public static final long INFINITY = Long.MAX_VALUE;

    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            solveTestCase();
        }
        io.close();
    }

    private static void solveTestCase() {
        int length = io.getInt();
        int operationCount = io.getInt();
        int wordCount = io.getInt();
        Operation[] operations = new Operation[operationCount];
        for (int j = 0; j < operationCount; j++) {
            String operation = io.getWord();
            int cost = io.getInt();
            operations[j] = new Operation(operation, cost);
        }
        BitSet[] fromWords = new BitSet[wordCount];
        BitSet[] toWords = new BitSet[wordCount];
        for (int j = 0; j < wordCount; j++) {
            String fromStr = io.getWord();
            String toStr = io.getWord();
            BitSet from = new BitSet(length);
            for (int k = 0; k < length; k++) {
                if (fromStr.charAt(k) == '1')
                    from.set(k);
            }
            fromWords[j] = from;
            BitSet to = new BitSet(length);
            for (int k = 0; k < length; k++) {
                if (toStr.charAt(k) == '1')
                    to.set(k);
            }
            toWords[j] = to;
        }
        solve(operations, fromWords, toWords);
    }

    private static void solve(Operation[] operations, BitSet[] fromWords, BitSet[] toWords) {
        graph = new Graph<>();
        handled.clear();
        for (int i = 0; i < fromWords.length; i++) {
            BitSet fromWord = fromWords[i];
            BitSet toWord = toWords[i];
            if (fromWord.equals(toWord)) {
                io.print("0 ");
            } else {
                fillGraph(fromWord, operations);
                Map<BitSet, Long> distances = Dijkstra.shortestPath(graph, graph.getVertices().get(fromWord));
                if (distances.containsKey(toWord) && distances.get(toWord) != INFINITY) {
                    io.print(distances.get(toWord) + " ");
                } else {
                    io.print("NP ");
                }
            }
        }
        io.println();
    }

    private static void fillGraph(BitSet fromWord, Operation[] operations) {
        if (!handled.contains(fromWord)) {
            if (!graph.containsWord(fromWord))
                graph.addVertex(new Vertex<>(fromWord));
            for (Operation operation : operations) {
                BitSet neighbor = performOperation(fromWord, operation.operation);
                if (!graph.containsWord(neighbor))
                    graph.addVertex(new Vertex<>(neighbor));
                graph.addDirectedEdge(fromWord, neighbor, new Weight(operation.cost));
            }
            handled.add(fromWord);
            for (Edge<Weight> edge : graph.getVertices().get(fromWord).getEdges()) {
                fillGraph(edge.getTo().getId(), operations);
            }
        }
    }

    private static BitSet performOperation(BitSet word, String operation) {
        BitSet res = (BitSet) word.clone();
        for (int i = 0; i < operation.length(); i++) {
            char opAtIndex = operation.charAt(i);
            if (opAtIndex == 'F') {
                res.flip(i);
            } else if (opAtIndex == 'S') {
                res.set(i);
            } else if (opAtIndex == 'C') {
                res.clear(i);
            }
        }
        return res;
    }

    static class Operation {
        public final String operation;
        public final int cost;

        public Operation(String operation, int cost) {
            this.operation = operation;
            this.cost = cost;
        }
    }
}
