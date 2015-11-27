package se.dxtr.howmanyzeros;

public class HowManyZeros {

    static Kattio io = new Kattio();

    public static void main(String[] args) {
        while (true) {
            long m = io.getLong();
            long n = io.getLong();
            if (m == -1 && n == -1)
                break;
            long excludeZeroCount = countZeros2(m - 1);
            long totalZeroCount = countZeros2(n);
            long intervalCount = totalZeroCount - excludeZeroCount;
            if (m == 0)
                intervalCount++;
            io.println(intervalCount);
        }
        io.close();
    }

    private static long countZeros2(long n) {
        long zeros = 0;
        int pos = 1;

        while (true) {
            long div = n / pos;
            long div10 = div / 10;

            if (div10 == 0)
                return zeros;

            zeros += div % 10 == 0 ? (div10 - 1) * pos + (n % pos) + 1 : div10 * pos;
            pos *= 10;
        }
    }

    private static long countZerosNaive(long m, long n) {
        long zeros = 0;

        for (long i = m; i <= n; i++) {
            char[] digits = String.valueOf(i).toCharArray();
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == '0') {
                    zeros++;
                }
            }
        }
        return zeros;
    }
}
