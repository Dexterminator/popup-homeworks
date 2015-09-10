package se.dxtr;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int numberOfScenarios = io.getInt ();
        for (int i = 0; i < numberOfScenarios; i++) {
            int numberOfDistances = io.getInt ();
            int[] distances = new int[numberOfDistances];
            int totalDistance = 0;
            for (int j = 0; j < numberOfDistances; j++) {
                int distance = io.getInt ();
                distances[j] = distance;
                totalDistance += distance;
            }
            String workout = WorkoutOptimizer.getOptimalWorkout (distances, totalDistance);
            io.println (workout);
        }
        io.flush ();
        io.close ();
    }
}
