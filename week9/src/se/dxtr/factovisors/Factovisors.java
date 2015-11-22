package se.dxtr.factovisors;

import java.util.LinkedHashMap;
import java.util.Map;

public class Factovisors {

    static Kattio io = new Kattio();

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
        Map<Integer, Integer> factorPowers = new LinkedHashMap<>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factorPowers.merge(i, 1, (a, b) -> a + b);
                n /= i;
            }
        }
        if (n > 1)
            factorPowers.merge(n, 1, (a, b) -> a + b);
        return factorPowers;
    }
}
