package se.dxtr.evilstraw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dexter on 13/11/15.
 */
public class EvilStraw {

    static Kattio io = new Kattio();

    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            char[] word = io.getWord().toCharArray();
            if (transformationPossible(word)) {
                io.println(countSwaps(word));
            } else {
                io.println("Impossible");
            }
        }
        io.close();
    }

    private static boolean transformationPossible(char[] word) {
        Map<Character, Integer> letterCounts = new HashMap<>();
        for (int i = 0; i < word.length; i++)
            letterCounts.merge(word[i], 1, (a, b) -> a + b);
        
        if (word.length % 2 == 0)
            return transformationPossibleEvenLength(letterCounts);
        return transformationPossibleOddLength(letterCounts);
    }

    private static boolean transformationPossibleEvenLength(Map<Character, Integer> letterCounts) {
        for (Integer count : letterCounts.values()) {
            if (count % 2 != 0)
                return false;
        }
        return true;
    }

    private static boolean transformationPossibleOddLength(Map<Character, Integer> letterCounts) {
        int odds = 0;
        for (Integer count : letterCounts.values()) {
            if (count % 2 != 0)
                odds++;
            if (odds > 1)
                return false;
        }
        return true;
    }

    private static int countSwaps(char[] word) {
        return -1;
    }

    public static boolean isPalindrome(char[] word) {
        int i = 0;
        int j = word.length - 1;
        while (j > i) {
            if (word[i] != word[j])
                return false;
            i++;
            j--;
        }
        return true;
    }
}
