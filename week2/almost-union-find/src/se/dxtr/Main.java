package se.dxtr;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();
            DisjointSets disjointSets = new DisjointSets (n+1);
            for (int i = 0; i < m; i++) {
                int op = io.getInt ();
                if (op == 1) {
                    int p = io.getInt ();
                    int q = io.getInt ();
                    io.println ("union " + p + ", " + q);
                    disjointSets.union (p, q);
                } else if (op == 2) {
                    int p = io.getInt ();
                    int q = io.getInt ();
                    io.println ("move " + p + ", " + q);
                    disjointSets.move (p, q);
                } else if (op == 3) {
                    int p = io.getInt ();
                    io.println ("size and sum " + p);
                    int[] sizeAndSum = disjointSets.getSizeAndSum (p);
                    io.println (sizeAndSumString (sizeAndSum));
                }
            }
        }
//        DisjointSets disjointSets = new DisjointSets (10);
//        disjointSets.union (1, 2);
//        io.println (Arrays.toString (disjointSets.getSizeAndSum (1)));
//        disjointSets.union (2, 3);
//        io.println (Arrays.toString (disjointSets.getSizeAndSum (1)));
        io.close ();
    }

    private static String sizeAndSumString (int[] sizeAndSum) {
        return sizeAndSum[0] + " " + sizeAndSum[1];
    }
}
