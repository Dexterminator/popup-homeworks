package se.dxtr;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int maxSize = s.nextInt ();
        int numTypes = s.nextInt ();
        int numAccesses = s.nextInt ();
        int[] accesses = new int[numAccesses];
        Map<Integer, Queue<Integer>> accessLists = new HashMap<> ();
        for (int i = 0; i < numTypes; i++)
            accessLists.put (i, new LinkedList<> ());
        for (int i = 0; i < numAccesses; i++) {
            int object = s.nextInt ();
            accesses[i] = object;
            accessLists.get (object).add (i);
        }

        Cache cache = new Cache (maxSize, accesses, accessLists);
        int reads = cache.calculateLeastReads ();
        System.out.println (reads);
    }

}
