package se.dxtr.circularlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static se.dxtr.circularlock.ModularOperations.subtract;

public class CircularLock {

    static Kattio io = new Kattio();

    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            int[][] state = new int[2][2];
            int[][] period = new int[2][2];
            for (int j = 0; j < 2; j++) {
                state[j][0] = io.getInt();
                state[j][1] = io.getInt();
                period[j][0] = io.getInt();
                period[j][1] = io.getInt();
            }

            if (unlockable(state, period))
                io.println("Yes");
            else
                io.println("No");
        }
        io.close();
    }

    private static boolean unlockable(int[][] state, int[][] period) {
        int p = Arrays.stream(period)
                .flatMapToInt(row -> Arrays.stream(row))
                .reduce(CircularLock::gcd)
                .getAsInt();
        int[][] statePrimes = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                statePrimes[i][j] = state[i][j] % p;
            }
        }

        List<ChineseRemainderSolution> solutions = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            long n = period[i][0];
            long m = period[i][1];
            long a = subtract(statePrimes[i][0], state[i][0], n);
            long b = subtract(statePrimes[i][1], state[i][1], m);
            ChineseRemainderSolution solution = ChineseRemainders.solveCoprime(a, n, b, m);
            solutions.add(solution);
//            System.err.println("solution = " + solution);
            if (solution.x == 0)
                return false;
        }

        for (int i = 0; i < 2; i++) {
            long n = period[0][i];
            long m = period[1][i];
            long a = subtract(statePrimes[0][i], state[0][i], n);
            long b = subtract(statePrimes[1][i], state[1][i], m);
            ChineseRemainderSolution solution = ChineseRemainders.solveCoprime(a, n, b, m);
            solutions.add(solution);
//            System.err.println("solution = " + solution);
            if (solution.x == 0)
                return false;
        }

        ChineseRemainderSolution[] last = new ChineseRemainderSolution[2];
        for (int i = 0, j = 0; i < 4; i += 2, j++) {
            ChineseRemainderSolution s1 = solutions.get(i);
            ChineseRemainderSolution s2 = solutions.get(i + 1);
            long a = s1.x;
            long n = s1.k;
            long b = s2.x;
            long m = s2.k;
            ChineseRemainderSolution lastSolution = ChineseRemainders.solveCoprime(a, n, b, m);
//            System.err.println("lastSolution = " + lastSolution);
            last[j] = lastSolution;
        }

        return last[0].x == last[1].x && last[0].k == last[1].k;
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
