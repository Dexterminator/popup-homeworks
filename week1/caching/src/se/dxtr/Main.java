package se.dxtr;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int maxSize = s.nextInt ();
        int numTypes = s.nextInt ();
        int numAccesses = s.nextInt ();
        int[] accesses = new int[numAccesses];
        for (int i = 0; i < numAccesses; i++)
            accesses[i] = s.nextInt ();

        Cache cache = new Cache (maxSize, accesses);
        int reads = cache.calculateLeastReads ();
        System.out.println (reads);
    }

}
