package se.dxtr;

import java.util.BitSet;

public class Main {
    static Kattio io = new Kattio (System.in, System.out);

    public static void main(String[] args) {
        int n = io.getInt ();
        for (int i = 0; i < n; i++)
            fewestPebbles ();
    }

    private static void fewestPebbles () {
        BitSet root = new BitSet (23);
        char[] game = io.getWord ().toCharArray ();
        for (int i = 0; i < game.length; i++) {
            if (game[i] == 'o')
                root.set (i);
        }
        
    }
}
