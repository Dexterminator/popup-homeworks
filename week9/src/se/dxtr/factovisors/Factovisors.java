package se.dxtr.factovisors;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Factovisors {

    static Kattio io = new Kattio();
    static BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            int m = io.getInt();
            String out;
            if (dividesFactorial(n, m)) {
                out = String.format("%d divides %d!", m, n);
            } else {
                out = String.format("%d does not divide %d!", m, n);
            }
            io.println(out);
        }
        io.close();
    }

    private static boolean dividesFactorial(int n, int m) {
        if (m == 0)
            return false;
        if (n == 0)
            n = 1;
        Map<Integer, Integer> primeFactors = primeFactors(m);
        for (int factor : primeFactors.keySet()) {
            int factorPower = primeFactors.get(factor);
            if (factorPowerInFactorial(factor, n) < factorPower)
                return false;
        }
        return true;
    }

    private static int factorPowerInFactorial(int p, int n) {
        int pow = 0;
        while (n / p > 0) {
            pow += n / p;
            n /= p;
        }
        return pow;
    }

    private static Map<Integer, Integer> primeFactors(int n) {
        Map<Integer, Integer> factorPowers = new HashMap<>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factorPowers.merge(i, 1, add);
                n /= i;
            }
        }

        if (n > 1)
            factorPowers.merge(n, 1, add);
        return factorPowers;
    }
}
