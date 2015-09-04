package se.dxtr;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        long numberOfFiles = s.nextLong ();
        int maxBits = s.nextInt ();

        // The log base 2 of an integer is the same as the position of the highest bit set
        int log2 = 0;
        while (numberOfFiles > 1) {
            numberOfFiles >>= 1;
            log2++;
        }

        System.out.println (log2 <= maxBits ? "yes" : "no");
    }
}
