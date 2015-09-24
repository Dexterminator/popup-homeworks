package se.dxtr;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            long x = io.getLong ();
            if (x == 0)
                break;
            int p = findP (x);
            io.println (p);
        }
//        io.println (findP (Integer.MAX_VALUE));
//        io.println (findP (2));
//        io.println (findP (Integer.MIN_VALUE));
//        io.println (findP (3600));
        io.close ();
    }

    private static int findP (long x) {
//        io.println ("x: " + x);
        long absX = Math.abs (x);
        long n = absX;

//        io.println ("absx: " + absX);
        List<Integer> factorPowers = new ArrayList<Integer> ();
        int sqrtX = ((int) Math.sqrt (absX));
        for (long i = 2; i*i <= n; i++) {
            int powerOfFactor = 0;
            while (n % i == 0) {
                powerOfFactor++;
                n /= i;
            }

            if (powerOfFactor > 0)
                factorPowers.add (powerOfFactor);
        }
//        io.println (factorPowers);

        if (factorPowers.isEmpty ())
            return 1;

        long powGcd = factorPowers.get (0);
        for (int factorPower : factorPowers.subList (1, factorPowers.size ()))
            powGcd = gcd (powGcd, factorPower);

        if (x < 0 && powGcd % 2 != 1)
            return 1;
        return (int) powGcd;
    }

    private static long gcd (long a, long b) {
        if (b == 0)
            return a;
        return gcd (b, a % b);
    }
}
