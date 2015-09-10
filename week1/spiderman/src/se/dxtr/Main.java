package se.dxtr;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int numberOfScenarios = s.nextInt ();
        for (int i = 0; i < numberOfScenarios; i++) {
            int numberOfDistances = s.nextInt ();
            int[] distances = new int[numberOfDistances];
            int totalDistance = 0;
            for (int j = 0; j < numberOfDistances; j++) {
                int distance = s.nextInt ();
                distances[j] = distance;
                totalDistance += distance;
            }
            String workout = WorkoutOptimizer.getOptimalWorkout (distances, totalDistance);
            System.out.println (workout);
        }
    }
}
