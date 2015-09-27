package se.dxtr;

public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            String number = io.getWord ();
            if (number.equals ("0"))
                break;
            calculateFraction (number);
        }
        io.close ();
    }

    private static void calculateFraction (String number) {
        String s = getRelevantNumbers (number.split ("\\.")[1]);
        Fraction bestFraction = new Fraction (0, Integer.MAX_VALUE);
        int numberOfRepeatingDigits = s.length ();
        while (numberOfRepeatingDigits > 0) {
            long equation1LeftSide = (long) Math.pow (10, numberOfRepeatingDigits);
            long equation2LeftSide = equation1LeftSide / 10;

            long equation1RightSide = Integer.valueOf (s);
            long equation2RightSide = numberOfRepeatingDigits - 1 == 0 ? 0 : Integer.valueOf (s.substring (0, numberOfRepeatingDigits - 1));

            long denominator = equation1LeftSide - equation2LeftSide;
            long numerator = equation1RightSide - equation2RightSide;

            Fraction fraction = getSimplifiedFraction (numerator, denominator);
            if (fraction.denominator < bestFraction.denominator)
                bestFraction = fraction;
//            io.println (fraction);
            numberOfRepeatingDigits--;
        }

        io.println (bestFraction.numerator + "/" + bestFraction.denominator);
    }

    private static Fraction getSimplifiedFraction (long numerator, long denominator) {
        long gcd = gcd (numerator, denominator);
        while (gcd != 1) {
            denominator /= gcd;
            numerator /= gcd;
            gcd = gcd (numerator, denominator);
        }

        return new Fraction (numerator, denominator);
    }

    private static String getRelevantNumbers (String s1) {
        String s = s1;
        char repeating = s.charAt (s.length () - 1);
        char[] digits = s.toCharArray ();
        int lastRepeatingIndex = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != repeating) {
                lastRepeatingIndex = i + 1;
                break;
            }
        }
        s = s.substring (0, lastRepeatingIndex + 1);
        return s;
    }

    private static long gcd (long a, long b) {
        if (b == 0)
            return a;
        return gcd (b, a % b);
    }

    static class Fraction {
        public final long numerator;
        public final long denominator;

        public Fraction (long numerator, long denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }
}
