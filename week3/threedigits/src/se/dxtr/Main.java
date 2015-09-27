package se.dxtr;

public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
//        int n = io.getInt ();
//        int n = 10000000;
//        int n = 10000;
//        int n = 1000;
//        int n = 14;
//        int n = 7;
        int n = 5;
        String threeDigits = threeDigits (n);
        io.println (threeDigits);
        io.close ();
    }

    private static String threeDigits (int n) {
        long factorial = 1;
        int significance = 0;
//        int lastTrailingZeroes = 0;
        int maxTrailingZeroes = findTrailingZeros (n);
        int zeroesLeft = maxTrailingZeroes;
        for (int i = 2; i <= n; i++) {
            long factorial0 = factorial;
            factorial *= i;
//            System.err.println ("factorial: " + factorial);
//            long factorial1 = factorial;
            int trailingZeroes = findTrailingZeros (i);
            int zeroesToRemove = trailingZeroes - (zeroesLeft - maxTrailingZeroes);
            io.println (zeroesToRemove);
            if (i == 5624 || i == 5624)
                System.err.println ("wat");
            String truncated = String.valueOf (factorial);
            if (zeroesToRemove > 0) {
                factorial /= (Math.pow (10, zeroesToRemove));
//                truncated = truncated.substring (0, truncated.length () - zeroesToRemove);
                zeroesLeft -= zeroesToRemove;
            }

            long factorial2 = factorial;
            if (truncated.endsWith ("0")) {
                System.err.println ("WRONG: " + truncated);
            }

//            System.err.println ("trailing zeroes of " + i + ": " + trailingZeroes);
//            System.err.println ("truncated: " + truncated);

            significance = updateSignificance (significance, truncated);

            String substring = truncated.length () < 3 ? truncated
                    : truncated.substring (truncated.length () - 3, truncated.length ());

//            System.err.println ("substring: " + substring);


            factorial = Long.valueOf (substring);
//            System.err.println ("truncated and substringed factorial: " + factorial);
        }

        String s = String.valueOf (factorial);
        if (s.length () == 1) {
            if (significance > 2)
                return "00" + s;
            if (significance == 1)
                return "0" + s;
            return s;
        }

        if (s.length () == 2) {
            if (significance > 1)
                return "0" + s;
            return s;
        }

        if (s.length () > 3)
            return s.substring (s.length () - 3, s.length ());
        return s;
    }

    private static int updateSignificance (int significance, String truncated) {
        int newSignificance = truncated.length () - 1;
        if (newSignificance > significance)
            significance = newSignificance;
        return significance;
    }

    public static int factorialTrailingZeroes (int n) {
        int trailingZeroes = 0;
        while (n > 0) {
            n /= 5;
            trailingZeroes += n;
        }

        return trailingZeroes;
    }

    public static int findTrailingZeros (int number) {
        int count = 0;
        if (number == 5) {
            return 1;
        }

        for (int j = 5; number / j >= 1; j *= 5) {
            count += number / j;
        }

        return count;
    }
}
