package se.dxtr;

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
                    disjointSets.union (p, q);
                } else if (op == 2) {
                    int p = io.getInt ();
                    int q = io.getInt ();
                    disjointSets.move (p, q);
                } else if (op == 3) {
                    int p = io.getInt ();
                    int[] sizeAndSum = disjointSets.getSizeAndSum (p);
                    io.println (sizeAndSumString (sizeAndSum));
                }
            }
        }

        io.close ();
    }

    private static String sizeAndSumString (int[] sizeAndSum) {
        return sizeAndSum[0] + " " + sizeAndSum[1];
    }
}
