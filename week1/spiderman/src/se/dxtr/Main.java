package se.dxtr;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int numberOfScenarios = s.nextInt ();
        for (int i = 0; i < numberOfScenarios; i++) {
            int numberOfDistances = s.nextInt ();
            int[] distances = new int[numberOfDistances];
            for (int j = 0; j < numberOfDistances; j++) {
                distances[j] = s.nextInt ();
            }
            String workout = WorkoutOptimizer.getOptimalWorkout (distances);
            System.out.println (workout);
        }
    }
}
