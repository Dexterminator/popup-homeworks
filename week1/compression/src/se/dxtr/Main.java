package se.dxtr;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        long numberOfFiles = s.nextLong ();
        int maxBits = s.nextInt ();
        double log2 = Math.floor (Math.log (numberOfFiles) / Math.log (2)) - 1;
        System.out.println (log2 <= maxBits ? "yes" : "no");
    }
}
