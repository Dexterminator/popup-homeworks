package se.dxtr;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main (String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        chop (io);
        io.close ();
    }

    private static void chop (Kattio io) {
        int n = io.getInt ();
        int[] v = new int[n];
        int[] usesLeft = new int[n + 2];
        int[] u = new int[n];

        for (int i = 0; i < n; i++) {
            int num = io.getInt ();
            v[i] = num;
            usesLeft[num]++;
        }

        // Last value in v has to be max value in graph
        if (v[n - 1] != n + 1) {
            io.println ("Error");
            return;
        }

        PriorityQueue<Integer> leaves = new PriorityQueue<Integer> (n);
        for (int i = 1; i <= n + 1; i++) {
            if (usesLeft[i] == 0)
                leaves.add (i);
        }

        for (int i = 0; i < n; i++) {
            if (leaves.isEmpty ()) {
                io.println ("Error");
                return;
            }
            int leaf = leaves.poll ();
            u[i] = leaf;
            usesLeft[v[i]]--;
            if (usesLeft[v[i]] == 0)
                leaves.add (v[i]);
        }

        for (int i = 0; i < n; i++) {
            io.println (u[i]);
        }
    }
}
