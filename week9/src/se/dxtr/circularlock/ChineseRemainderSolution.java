package se.dxtr.circularlock;

import java.util.HashSet;
import java.util.Set;
import static se.dxtr.circularlock.ModularOperations.*;

/**
 * Value class containing the solutions x mod k to a chinese remainder problem instance.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class ChineseRemainderSolution {
    public final long x;
    public final long k;
    public Set<Long> allSolutions;

    public ChineseRemainderSolution(long x, long k) {
        this.x = x;
        this.k = k;
        allSolutions = new HashSet<>();

        long curr = x;
        while (true) {
            allSolutions.add(curr);
            curr = add(x, curr, k);
            if (curr == x)
                break;
        }
    }


    @Override
    public String toString() {
        return "ChineseRemainderSolution{" +
                "x=" + x +
                ", k=" + k +
                '}';
    }
}
