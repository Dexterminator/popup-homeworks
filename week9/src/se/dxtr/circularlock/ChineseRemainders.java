package se.dxtr.circularlock;

import static se.dxtr.circularlock.ModularOperations.multiplicativeInverse;

/**
 * Utility class containing a method for solving a chinese remainder problem instance.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class ChineseRemainders {

    /**
     * Calculate and return the chinese remainder solution x mod K where K = mn,
     * for the system:
     * <p>
     * x = a (mod n)
     * x = b (mod m)
     * @return a {@link ChineseRemainderSolution} containing the solution x mod K
     */
    public static ChineseRemainderSolution solveCoprime(long a, long n, long b, long m) {

        // k = m*n
        long k = n * m;

        // m^-1 mod n
        long mInverseModn = multiplicativeInverse(m , n);

        // n^-1 mod m
        long nInverseModm = multiplicativeInverse(n, m);

        // a * m * (m^-1 mod n)
        long aTimesMTimesMInverse = a * mInverseModn * m;

        // b * n * (n^-1 mod m)
        long bTimesNTimesNInverse = b * nInverseModm * n;

        // x = a * m * m^-1 + b * n * n^-1 (mod k)
        long x = (aTimesMTimesMInverse + bTimesNTimesNInverse) % k;
        return new ChineseRemainderSolution(x, k);
    }
}
