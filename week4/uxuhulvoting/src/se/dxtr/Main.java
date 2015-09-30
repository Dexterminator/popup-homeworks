package se.dxtr;

public class Main {
    static Kattio io;
    static String[] outcomes = new String[]{"NNN", "NNY", "NYN", "NYY", "YNN", "YNY", "YYN", "YYY"};
    static int[][] distances = new int[outcomes.length][outcomes.length];

    public static void main (String[] args) {
        calculateFlipDistances ();
        io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        for (int i = 0; i < n; i++) {
            int numberOfPriests = io.getInt ();
            calculateOutcome (numberOfPriests);
        }
        io.close ();
    }

    private static void calculateOutcome (int numberOfPriests) {
        // sortedPreferences[i] contains the outcomes the priest prefers, from most to least
        int[][] sortedPreferences = new int[numberOfPriests][outcomes.length];
        int[][] preferences = new int[numberOfPriests][outcomes.length];
        fillPreferences (numberOfPriests, sortedPreferences, preferences);

        // optimalVotes[i][j] contains the final outcome of the best vote priest i kan make given outcome j
        int[][] optimalVotes = new int[numberOfPriests][outcomes.length];
        fillOldestPriestOutcomes (numberOfPriests, sortedPreferences[numberOfPriests - 1], optimalVotes);
        calculateOptimalVotes (numberOfPriests, preferences, optimalVotes);

        io.println (outcomes[optimalVotes[0][0]]);
    }

    private static void calculateOptimalVotes (int numberOfPriests, int[][] preferences, int[][] optimalVotes) {
        for (int priest = numberOfPriests - 2; priest >= 0; priest--) {
            for (int givenOutcome = 0; givenOutcome < outcomes.length; givenOutcome++) {
                int bestOutcome = getBestOutcome (preferences[priest], optimalVotes[priest + 1], distances[givenOutcome]);
                optimalVotes[priest][givenOutcome] = bestOutcome;
            }
        }
    }

    private static void fillPreferences (int numberOfPriests, int[][] sortedPreferences, int[][] preferences) {
        for (int priest = 0; priest < numberOfPriests; priest++) {
            for (int outcome = 0; outcome < outcomes.length; outcome++) {
                int priority = io.getInt () - 1;
                sortedPreferences[priest][priority] = outcome;
                preferences[priest][outcome] = priority;
            }
        }
    }

    private static int getBestOutcome (int[] preference, int[] optimalVote, int[] distance) {
        int bestOutcome = Integer.MIN_VALUE;
        int bestPriority = Integer.MAX_VALUE;
        for (int resultOutcome = 0; resultOutcome < outcomes.length; resultOutcome++) {
            if (distance[resultOutcome] == 1) {
                int finalOutcome = optimalVote[resultOutcome];
                int priority = preference[finalOutcome];
                if (priority < bestPriority) {
                    bestPriority = priority;
                    bestOutcome = finalOutcome;
                }
            }
        }
        return bestOutcome;
    }


    private static void fillOldestPriestOutcomes (int numberOfPriests, int[] preference, int[][] optimalVotes) {
        for (int givenOutcome = 0; givenOutcome < outcomes.length; givenOutcome++) {
            for (int priority = 0; priority < outcomes.length; priority++) {
                int mostPreferredOutcome = preference[priority];
                if (distances[givenOutcome][mostPreferredOutcome] == 1) {
                    optimalVotes[numberOfPriests - 1][givenOutcome] = mostPreferredOutcome;
                    break;
                }
            }
        }
    }

    private static void calculateFlipDistances () {
        for (int i = 0; i < outcomes.length; i++) {
            for (int j = 0; j < outcomes.length; j++) {
                distances[i][j] = hammingDistance (i, j);
            }
        }
    }

    private static int hammingDistance (int x, int y) {
        int dist = 0;
        int val = x ^ y;
        while (val != 0) {
            dist++;
            val &= val - 1;
        }

        return dist;
    }
}
