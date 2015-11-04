package se.dxtr.classpicture;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dexter on 04/11/15.
 */
public class ClassPicture {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            Map<String, Integer> nameToid = new HashMap<>();
            Map<Integer, String> idToName = new HashMap<>();
            int n = io.getInt();
            for (int i = 0; i < n; i++) {
                String name = io.getWord();
                nameToid.put(name, i);
                idToName.put(i, name);
            }

            boolean[][] disLikes = new boolean[n][n];
            int m = io.getInt();
            for (int i = 0; i < m; i++) {
                String name1 = io.getWord();
                int id1 = nameToid.get(name1);
                String name2 = io.getWord();
                int id2 = nameToid.get(name2);
                disLikes[id1][id2] = true;
                disLikes[id2][id1] = true;
            }

            Permuter permuter = new Permuter(idToName);
            permuter.lineUp(n, disLikes);
            System.err.println(permuter.bestSolution);
        }
    }

    private static class Permuter {
        final Map<Integer, String> idToName;
        String bestSolution = null;

        public Permuter(Map<Integer, String> idToName) {
            this.idToName = idToName;
        }

        public void lineUp(int n, boolean[][] dislikes) {
            int[] ids = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }
            permute(ids, 0, dislikes);
        }

        private void permute(int[] ids, int k, boolean[][] dislikes) {
            for (int i = k; i < ids.length; i++) {
                swap(ids, i, k);
                permute(ids, k + 1, dislikes);
                swap(ids, k, i);
            }

            if (k == ids.length - 1) {
                if (correctSolution(ids, dislikes)) {
                    System.err.print("VALID: ");
                    updateSolutionIfBetter(ids);
                }
                System.err.println(Arrays.toString(ids));
            }
        }

        private void updateSolutionIfBetter(int[] ids) {
            if (bestSolution == null) {
                bestSolution = Arrays.stream(ids).mapToObj(idToName::get).collect(Collectors.joining(" "));
            } else {
                String newSolution = Arrays.stream(ids).mapToObj(idToName::get).collect(Collectors.joining(" "));
                if (newSolution.compareTo(newSolution) > 0) {
                    bestSolution = newSolution;
                }
            }
        }

        private boolean correctSolution(int[] ids, boolean[][] dislikes) {
            //TODO Check for out of bounds
            for (int i = 0; i < ids.length - 1; i++) {
                int id1 = ids[i];
                int id2 = ids[i + 1];
                if (dislikes[id1][id2])
                    return false;
            }
            return true;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
