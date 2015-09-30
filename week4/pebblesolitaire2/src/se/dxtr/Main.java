package se.dxtr;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class Main {
    static Kattio io = new Kattio (System.in, System.out);
    static HashMap<BitSet, Integer> states = new HashMap<> ();
    static final int SIZE = 23;

    public static void main (String[] args) {
        int n = io.getInt ();
        for (int i = 0; i < n; i++)
            solve ();
        io.close ();
    }

    private static void solve () {
        BitSet firstState = new BitSet (SIZE);
        char[] game = io.getWord ().toCharArray ();
        for (int i = 0; i < game.length; i++) {
            if (game[i] == 'o')
                firstState.set (i);
        }
        int fewest = play (firstState);
        io.println (fewest);
    }

    private static int play (BitSet state) {
        if (states.containsKey (state))
            return states.get (state);

        List<BitSet> moves = getPossibleMoves (state);
        if (moves.isEmpty ()) {
            states.put (state, state.cardinality ());
            return state.cardinality ();
        }

        int fewestPebbles = getFewestPebbles (moves);
        states.put (state, fewestPebbles);
        return fewestPebbles;
    }

    private static List<BitSet> getPossibleMoves (BitSet state) {
        int i = state.nextSetBit (0);
        List<BitSet> moves = new ArrayList<> ();
        while (i != -1) {
            if (moveLeftPossible (state, i)) {
                BitSet result = makeMoveLeft (state, i);
                moves.add (result);
            }

            if (moveRightPossible (state, i)) {
                BitSet result = makeMoveRight (state, i);
                moves.add (result);
            }

            i = state.nextSetBit (i + 1);
        }
        return moves;
    }

    private static int getFewestPebbles (List<BitSet> moves) {
        int fewestPebbles = Integer.MAX_VALUE;
        for (BitSet move : moves) {
            int pebbles = play (move);
            if (pebbles < fewestPebbles)
                fewestPebbles = pebbles;
        }
        return fewestPebbles;
    }

    private static BitSet makeMoveRight (BitSet game, int i) {
        BitSet result = (BitSet) game.clone ();
        result.clear (i);
        result.clear (i + 1);
        result.set (i + 2);
        return result;
    }

    private static BitSet makeMoveLeft (BitSet game, int i) {
        BitSet result = (BitSet) game.clone ();
        result.clear (i);
        result.clear (i - 1);
        result.set (i - 2);
        return result;
    }

    private static boolean moveRightPossible (BitSet game, int i) {
        return i + 1 < SIZE && i + 2 < SIZE && game.get (i + 1) && !game.get (i + 2);
    }

    private static boolean moveLeftPossible (BitSet game, int i) {
        return i - 1 >= 0 && i - 2 >= 0 && game.get (i - 1) && !game.get (i - 2);
    }

    private static void printGame (BitSet game) {
        for (int i = 0; i < SIZE; i++) {
            if (game.get (i))
                io.print ('o');
            else
                io.print ('-');
        }
        io.println ();
    }
}
