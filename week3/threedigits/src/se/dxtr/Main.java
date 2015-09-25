package se.dxtr;

public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
//        int n = io.getInt ();
        int n = 10000000-100;
        String threeDigits = threeDigits (n);
        io.println (threeDigits);
        io.close ();
    }

    private static String threeDigits (int n) {
        long factorial = 1;
        int signifcance = 0;
        for (int i = 2; i <= n; i++) {
            String truncatedI = removeTrailingZeroes (i);
            factorial *= Integer.valueOf (truncatedI);
            String truncated = removeTrailingZeroes (factorial);
//            int toKeep = truncated.length () > 3 ? getToKeep (truncated) : truncated.length ();
//            String substring = truncated.substring (truncated.length () - toKeep, truncated.length ());
            int newSignificane = truncated.length () - 1;
            if (newSignificane > signifcance)
                signifcance = newSignificane;
            String substring = truncated.length () <= 3 ? truncated : truncated.substring (truncated.length () - 3, truncated.length ());
//            System.err.println ("truncated: " + truncated);
//            System.err.println ("substring: " + substring);
            factorial = Long.valueOf (substring);
//            System.err.println ("factorial after substring: " + factorial);
        }

        String s = String.valueOf (factorial);
        if (s.length () == 1) {
            if (signifcance > 2)
                return "00" + s;
            if (signifcance == 1)
                return "0" + s;
            return s;
        }

        if (s.length () == 2) {
            if (signifcance > 1)
                return "0" + s;
            return s;
        }

        if (s.length () > 3)
            return s.substring (s.length () - 3, s.length ());
        return s;
    }

    private static String removeTrailingZeroes (long factorial) {
        String asString = String.valueOf (factorial);
        char[] digits = asString.toCharArray ();
        int j = digits.length - 1;
        int trailingZeroes = 0;
        while (digits[j] == '0') {
            trailingZeroes++;
            j--;
        }
        return asString.substring (0, asString.length () - trailingZeroes);
    }
}
