package se.dxtr.classpicture;

import java.util.*;

/**
 * Created by dexter on 04/11/15.
 */
public class ClassPicture {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            Map<String, Set<String>> dislikes = new HashMap<>();
            String[] names = new String[n];
            for (int i = 0; i < n; i++) {
                String name = io.getWord();
                dislikes.put(name, new HashSet<>());
                names[i] = name;
            }

            int m = io.getInt();
            for (int i = 0; i < m; i++) {
                String name1 = io.getWord();
                String name2 = io.getWord();
                dislikes.get(name1).add(name2);
                dislikes.get(name2).add(name1);
            }

            Permuter permuter = new Permuter(names, dislikes);
            permuter.lineUp();
        }
        io.close();
    }

    private static class Permuter {
        int times = 0;
        public boolean foundSolution = false;
        final String[] names;
        final Map<String, Set<String>> dislikes;

        public Permuter(String[] names, Map<String, Set<String>> dislikes) {
            this.names = names;
            this.dislikes = dislikes;
            Arrays.sort(names);
        }

        public void lineUp() {
            permute(0);
            System.err.println("times = " + times);
            if (!foundSolution)
                io.println("You all need therapy.");
        }

        private void permute(int k) {
            if (foundSolution)
                return;

            if (k == names.length) {
                foundSolution = true;
                printSolution();
                return;
            }

            for (int i = k; i < names.length; i++) {
                swap(names, i, k);
                if (!badBranch(k)) {
                    permute(k + 1);
                    times++;
                }
                swap(names, k, i);
            }
        }

        private boolean badBranch(int k) {
            for (int i = 0; i < k; i++) {
                if (dislikes(names[i], names[i+1])) {
                    return true;
                }
            }
            return false;
        }

        private boolean correctSolution() {
            for (int i = 0; i < names.length - 1; i++) {
                if (dislikes(names[i], names[i + 1])) {
                    return false;
                }
            }
            return true;
        }

        private void printSolution() {
            for (String name : names)
                io.print(name + " ");
            io.println();
        }

        private boolean dislikes(String name1, String name2) {
            return dislikes.get(name1).contains(name2);
        }

        private void swap(String[] names, int i, int j) {
            String tmp = names[i];
            names[i] = names[j];
            names[j] = tmp;
        }
    }
}
