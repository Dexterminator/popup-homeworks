package se.dxtr.quantum;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Quantum {

    static Kattio io = new Kattio();
    static Map<BitSet, Integer> stateCosts = new HashMap<>();
    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
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
        io.close();
    }

    private static void solve(Operation[] operations, BitSet[] fromWords, BitSet[] toWords) {
        for (int i = 0; i < fromWords.length; i++) {
            stateCosts.clear();
            if (fromWords[i].equals(toWords[i])) {
                io.print("0 ");
            } else {
                cost(operations, fromWords[i], toWords[i], 0);
                if (stateCosts.containsKey(toWords[i])) {
                    io.print(stateCosts.get(toWords[i]) + " ");
                } else {
                    io.print("NP ");
                }
            }
        }
        io.println();
    }

    private static void cost(Operation[] operations, BitSet fromWord, BitSet toWord, int cost) {
        for (Operation operation : operations) {
            BitSet result = performOperation(fromWord, operation.operation);
            int opCost = cost + operation.cost;
            if (!stateCosts.containsKey(result) || opCost < stateCosts.get(result)) {
                stateCosts.put(result, opCost);
                if (result.equals(toWord) || (stateCosts.containsKey(toWord) && stateCosts.get(toWord) < opCost)) {
                    return;
                }
                cost(operations, result, toWord, opCost);
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
