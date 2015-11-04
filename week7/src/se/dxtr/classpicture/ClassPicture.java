package se.dxtr.classpicture;

import java.util.*;

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

            lineUp(n, disLikes, idToName);
        }
    }

    private static void lineUp(int n, boolean[][] dislikes, Map<Integer, String> idToName) {
        int[] ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        permute(ids, 0, dislikes, idToName);
    }

    static void permute(int[] ids, int k, boolean[][] dislikes, Map<Integer, String> idToName) {
        for (int i = k; i < ids.length; i++) {
            swap(ids, i, k);
            permute(ids, k + 1, dislikes, idToName);
            swap(ids, k, i);
        }

        if (k == ids.length - 1) {
//            System.err.println(Arrays.toString(ids));
//            System.err.println(correctSolution(ids, dislikes));
            if (correctSolution(ids, dislikes)) {
                System.err.print("VALID: ");
            }
            for (int id : ids) {
                System.err.print(idToName.get(id) + " ");
            }
            System.err.println("");
        }
    }

    static boolean correctSolution(int[] ids, boolean[][] dislikes) {
        //TODO Check for out of bounds
        for (int i = 0; i < ids.length - 1; i++) {
            int id1 = ids[i];
            int id2 = ids[i + 1];
            if (dislikes[id1][id2])
                return false;
        }
        return true;
    }

    static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
