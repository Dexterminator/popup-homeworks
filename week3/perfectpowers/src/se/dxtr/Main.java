package se.dxtr;

public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            long x = io.getLong ();
            if (x == 0)
                break;
            long p = findP (x);
            io.println (p);
        }
        io.close ();
    }

    private static long findP (long x) {
        long absX = Math.abs (x);
        long n = absX;

        long sqrtX = (long) (Math.sqrt (absX));
        long powGcd = -1;
        for (long i = 2; i <= sqrtX; i++) {
            if (n % i == 0) {
                int powerOfFactor = 0;
                while (n % i == 0) {
                    powerOfFactor++;
                    n /= i;
                }
                powGcd = powGcd == -1 ? powerOfFactor : gcd (powGcd, powerOfFactor);
            }
        }

        if (powGcd == -1)
            return 1;

        if (x < 0) {
            while (powGcd % 2 == 0)
                powGcd /= 2;
        }

        return powGcd;
    }

    private static long gcd (long a, long b) {
        if (b == 0)
            return a;
        return gcd (b, a % b);
    }
}
