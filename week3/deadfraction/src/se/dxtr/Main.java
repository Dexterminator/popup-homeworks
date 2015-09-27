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
        String digits = number.split ("\\.")[1];
        Fraction bestFraction = new Fraction (0, Integer.MAX_VALUE);
        int numberOfRepeatingDigits = 1;
        while (numberOfRepeatingDigits <= digits.length ()) {
            Fraction fraction = getFraction (digits, numberOfRepeatingDigits);
            if (fraction.denominator >= fraction.numerator && fraction.denominator < bestFraction.denominator)
                bestFraction = fraction;
            numberOfRepeatingDigits++;
        }
        io.println (bestFraction.numerator + "/" + bestFraction.denominator);
    }

    private static Fraction getFraction (String digits, int numberOfRepeatingDigits) {
        long equation1LeftSide = (long) Math.pow (10, digits.length ());
        long equation2LeftSide = (long) Math.pow (10, digits.length () - numberOfRepeatingDigits);

        long equation1RightSide = Integer.valueOf (digits);
        long equation2RightSide;
        if (digits.length () - numberOfRepeatingDigits == 0)
            equation2RightSide = 0;
        else
            equation2RightSide = Integer.valueOf (digits.substring (0, digits.length () - numberOfRepeatingDigits));

        long denominator = equation1LeftSide - equation2LeftSide;
        long numerator = equation1RightSide - equation2RightSide;

        return getSimplifiedFraction (numerator, denominator);
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
