package se.dxtr.evilstraw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        return countSwaps(word, 0, 0, word.length - 1);
    }

    private static int countSwaps(char[] word, int swaps, int swapStart, int swapEnd) {
        if (swapStart > swapEnd)
            return swaps;
        if (word[swapStart] == word[swapEnd])
            return countSwaps(word, swaps, swapStart + 1, swapEnd - 1);

        int fewestSwaps = Integer.MAX_VALUE;
        char[] bestWord = null;
        for (int i = swapStart; i < swapEnd; i++) {
            char[] swapWord = Arrays.copyOf(word, word.length);
            char toSwap = swapWord[i];
            for (int j = i; j < swapEnd; j++) {
                swapWord[j] = swapWord[j + 1];
            }
            swapWord[swapEnd] = toSwap;
            if (swapWord[swapStart] == swapWord[swapEnd]) {
                int currSwaps = swapEnd - i;
                if (currSwaps < fewestSwaps) {
                    fewestSwaps = currSwaps;
                    bestWord = swapWord;
                }
            }
        }

        for (int i = swapEnd; i > swapStart; i--) {
            char[] swapWord = Arrays.copyOf(word, word.length);
            char toSwap = swapWord[i];
            for (int j = i - 1; j >= swapStart; j--) {
                swapWord[j + 1] = swapWord[j];
            }

            swapWord[swapStart] = toSwap;
            if (swapWord[swapStart] == swapWord[swapEnd]) {
                int currSwaps = i - swapStart;
                if (currSwaps < fewestSwaps) {
                    fewestSwaps = currSwaps;
                    bestWord = swapWord;
                }
            }
        }

        return countSwaps(bestWord, swaps + fewestSwaps, swapStart + 1, swapEnd - 1);
    }
}
