package se.dxtr;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int candies = io.getInt ();
        int numberOfChildren = io.getInt ();

        Map<Integer, Integer> candyNumbers = new HashMap<> ();

        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < numberOfChildren; i++) {
            int amount = io.getInt ();
            largest = amount > largest ? amount : largest;
            candyNumbers.merge (amount, 1, Integer::sum);
        }

        while (candies > 0) {
            Integer amount = candyNumbers.get (largest);
            if (candies > amount) {
                candies -= amount;
                candyNumbers.remove (largest);
                candyNumbers.merge (largest - 1, amount, Integer::sum);
            } else {
                candyNumbers.put (largest, amount - candies);
                if (largest - 1 > 0)
                    candyNumbers.merge (largest - 1, candies, Integer::sum);
                candies = 0;
            }
            largest--;
        }

        long squaredAngers = 0;
        for (int candyNumber : candyNumbers.keySet ())
            squaredAngers += (long)candyNumber * (long)candyNumber * candyNumbers.get (candyNumber);
        io.println (squaredAngers);
        io.flush ();
        io.close ();
    }
}
