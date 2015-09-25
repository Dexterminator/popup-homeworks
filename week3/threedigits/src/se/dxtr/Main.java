package se.dxtr;

public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        String threeDigits = threeDigits (n);
        io.println (threeDigits);
        io.close ();
    }

    private static String threeDigits (int n) {
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
            String truncated = removeTrailingZeroes (factorial);
            int toKeep = truncated.length () > 3 ? getToKeep (truncated) : truncated.length ();
            String substring = truncated.substring (truncated.length () - toKeep, truncated.length ());
            factorial = Long.valueOf (substring);
        }
        String s = String.valueOf (factorial);
        return s.length () <= 3 ? s : s.substring (s.length () - 3, s.length ());
    }

    private static int getToKeep (String truncated) {
        int i;
        char[] digits = truncated.toCharArray ();
        i = digits.length - 2; // Last digit cannot be zero
        int toKeep = 1;
        while (digits[i] == '0' || toKeep < 3) {
            toKeep++;
            i--;
        }
        return toKeep + 1;
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
