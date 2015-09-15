package se.dxtr;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int maxSize = io.getInt ();
        int numTypes = io.getInt ();
        int numAccesses = io.getInt ();
        int[] accesses = new int[numAccesses];
        Map<Integer, Queue<Integer>> accessLists = new HashMap<> ();
        for (int i = 0; i < numTypes; i++)
            accessLists.put (i, new LinkedList<> ());
        for (int i = 0; i < numAccesses; i++) {
            int object = io.getInt ();
            accesses[i] = object;
            accessLists.get (object).add (i);
        }

        Cache cache = new Cache (maxSize, accesses, accessLists);
        int reads = cache.calculateLeastReads ();
        io.println (reads);
        io.flush ();
        io.close ();
    }

}
