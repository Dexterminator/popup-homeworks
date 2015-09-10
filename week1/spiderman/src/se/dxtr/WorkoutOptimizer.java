package se.dxtr;

public class WorkoutOptimizer {
    static int IMPOSSIBLE = -1;

    static String getOptimalWorkout (int[] distances, int totalDistance) {
        if (distances.length == 1)
            return "IMPOSSIBLE";

        // heights[i][h] = lowest possible height at height h at distance step i
        int[][] heights = new int[distances.length][totalDistance];
        // path[i][h] = The direction to climb at height i at distance step i to achieve the lowest height
        char[][] path = new char[distances.length][totalDistance];

        int distance = distances[distances.length - 1];
        for (int i = 0; i < totalDistance; i++)
            heights[distances.length - 1][i] = IMPOSSIBLE;
        heights[distances.length - 1][distance] = distance;
        path[distances.length - 1][distance] = 'D';

        for (int i = distances.length - 2; i >= 0; i--) {
            for (int height = 0; height < totalDistance; height++) {
                int climbUpHeight = height + distances[i];
                int nextStepHeightUp = climbUpHeight >= totalDistance ? IMPOSSIBLE : heights[i + 1][climbUpHeight];

                int climbDownHeight = height - distances[i];
                int nextStepHeightDown = climbDownHeight < 0 ? IMPOSSIBLE : heights[i + 1][climbDownHeight];

                if (nextStepHeightUp == IMPOSSIBLE && nextStepHeightDown == IMPOSSIBLE) {
                    heights[i][height] = IMPOSSIBLE;
                } else if (nextStepHeightUp != IMPOSSIBLE && nextStepHeightDown == IMPOSSIBLE) {
                    heights[i][height] = Math.max (nextStepHeightUp, height);
                    path[i][height] = 'U';
                } else if (nextStepHeightUp == IMPOSSIBLE && nextStepHeightDown != IMPOSSIBLE) {
                    heights[i][height] = Math.max (nextStepHeightDown, height);
                    path[i][height] = 'D';
                } else if (nextStepHeightUp != IMPOSSIBLE && nextStepHeightDown != IMPOSSIBLE) {
                    if (nextStepHeightUp < nextStepHeightDown) {
                        path[i][height] = 'U';
                        heights[i][height] = Math.max (nextStepHeightUp, height);
                    } else {
                        path[i][height] = 'D';
                        heights[i][height] = Math.max (nextStepHeightDown, height);
                    }
                }
            }
        }

        if (heights[0][0] == IMPOSSIBLE) {
            return "IMPOSSIBLE";
        }

        int currentHeight = 0;
        char[] solutionPath = new char[distances.length];
        for (int i = 0; i < distances.length; i++) {
            char direction = path[i][currentHeight];
            solutionPath[i] = direction;
            currentHeight = direction == 'U' ? currentHeight + distances[i] : currentHeight - distances[i];
        }

        return String.valueOf (solutionPath);
    }

}
