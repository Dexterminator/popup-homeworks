package se.dxtr;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int candies = s.nextInt ();
        int numberOfChildren = s.nextInt ();

        Map<Long, Integer> candyNumbers = new HashMap<> ();

        long largest = Integer.MIN_VALUE;
        for (int i = 0; i < numberOfChildren; i++) {
            long amount = s.nextInt ();
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
        for (Long candyNumber : candyNumbers.keySet ())
            squaredAngers += candyNumber * candyNumber * candyNumbers.get (candyNumber);
        System.out.println (squaredAngers);
    }
}
