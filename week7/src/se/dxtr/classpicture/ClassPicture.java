package se.dxtr.classpicture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
//            System.err.println(permuter.times);
            if (permuter.bestSolution == null) {
                io.println("You all need therapy.");
            } else {
                for (String name : permuter.bestSolution)
                    io.print(name + " ");
                io.println();
            }
        }
        io.close();
    }

    private static class Permuter {
        final Map<Integer, String> idToName;
        int times = 0;
        String[] bestSolution = null;

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
            for (int i = 0; i < k; i++) {
                if (i + 1 < ids.length && dislikes[ids[i]][ids[i + 1]]) {
                    return;
                }
            }
            for (int i = k; i < ids.length; i++) {
                swap(ids, i, k);
                if (k + 1 < ids.length && !dislikes[ids[k]][ids[k + 1]]) {
                    permute(ids, k + 1, dislikes);
                    times++;
                }
                swap(ids, k, i);
            }
//            System.err.println("ids = " + Arrays.toString(ids) + ", k =  " + k);
//            System.err.println("names = " + Arrays.stream(ids).mapToObj(idToName::get).collect(Collectors.joining(" ")) + ", k =  " + k);

            if (k == ids.length - 1) {
                if (correctSolution(ids, dislikes)) {
//                    System.err.print("VALID: ");
                    updateSolutionIfBetter(ids);
                }
//                System.err.println(Arrays.toString(ids));
            }
        }

        private void updateSolutionIfBetter(int[] ids) {
            String[] newSolution = getNames(ids);
            if (bestSolution == null) {
                bestSolution = newSolution;
            } else {
                for (int i = 0; i < newSolution.length; i++) {
                    if (newSolution[i].compareTo(bestSolution[i]) > 0) {
                        break;
                    } else if (newSolution[i].compareTo(bestSolution[i]) < 0) {
                        bestSolution = newSolution;
                        break;
                    }
                }
            }
        }

        private String[] getNames(int[] ids) {
            String[] newSolution = new String[ids.length];
            for (int i = 0; i < ids.length; i++) {
                newSolution[i] = idToName.get(ids[i]);
            }
            return newSolution;
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
