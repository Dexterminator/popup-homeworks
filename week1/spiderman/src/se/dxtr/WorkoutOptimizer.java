package se.dxtr;

/**
 * Created by dexter on 04/09/15.
 */
public class WorkoutOptimizer {
    static int IMPOSSIBLE = -1;

    static String getOptimalWorkout (int[] distances, int totalDistance) {
        if (distances.length == 1)
            return "IMPOSSIBLE";

        int[][] heights = new int[distances.length][totalDistance];
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
                    int best;
                    if (nextStepHeightUp < nextStepHeightDown) {
                        path[i][height] = 'U';
                        best = nextStepHeightUp;
                    } else {
                        path[i][height] = 'D';
                        best = nextStepHeightDown;
                    }
                    heights[i][height] = best;
                }
            }
        }
//
//        for (int i = 0; i < path.length; i++) {
//            for (char i1 : path[i]) {
//                System.out.print ("." + i1 + " ");
//            }
//            System.out.println ();
//        }

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
