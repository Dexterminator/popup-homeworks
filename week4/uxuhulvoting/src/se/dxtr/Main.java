package se.dxtr;

public class Main {
    static Kattio io;
    static String[] outcomes = new String[]{"NNN", "NNY", "NYN", "NYY", "YNN", "YNY", "YYN", "YYY"};

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        for (int i = 0; i < n; i++) {
            int numberOfPriests = io.getInt ();
            calculateOutcome (numberOfPriests);
        }
        io.close ();
    }

    private static void calculateOutcome (int numberOfPriests) {
    }

    private static int hammingDistance (int x, int y) {
        int dist = 0;
        int val = x ^ y;
        while (val != 0) {
            dist++;
            val &= val - 1;
        }

        return dist;
    }
}
