package se.dxtr.dictionaryattack;

import java.util.Arrays;

/**
 * Created by dexter on 11/11/15.
 */
public class DictionaryAttack {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int n = io.getInt();
        String[] dictionaryWords = new String[n];
        for (int i = 0; i < n; i++)
            dictionaryWords[i] = io.getWord();

        while (io.hasMoreTokens()) {
            String password = io.getWord();
            if (acceptable(password, dictionaryWords)) {
                io.println(password);
            }
        }
        io.close();
    }

    private static boolean acceptable(String password, String[] dictionaryWords) {
        int digitCount = countDigits(password);

        if (digitCount > 3)
            return true;

        for (String dictionaryWord : dictionaryWords) {
            if (password.length() != dictionaryWord.length())
                continue;
            int transpositionCount = countTranspositions(password.toCharArray(), dictionaryWord.toCharArray(), 0);
            if (transpositionCount == Integer.MAX_VALUE)
                continue;
            if (transpositionCount + digitCount <= 3)
                return false;
        }

        return true;
    }

    private static int countTranspositions(char[] pw, char[] dw, int count) {
        if (essentiallyEqual(pw, dw))
            return count;
        if (count > 3)
            return Integer.MAX_VALUE;

        int transpositionCount = Integer.MAX_VALUE;
        for (int i = 0; i < pw.length - 1; i++) {
            swap(pw, i, i + 1);
            transpositionCount = Math.min(transpositionCount, countTranspositions(pw, dw, count + 1));
            swap(pw, i, i + 1);
        }

        return transpositionCount;
    }

    private static int countDigits(String dictionaryWord) {
        int count = 0;
        for (int i = 0; i < dictionaryWord.length(); i++) {
            if (Character.isDigit(dictionaryWord.charAt(i)))
                count++;
        }
        return count;
    }

    private static boolean essentiallyEqual(char[] pw, char[] dw) {
        for (int i = 0; i < pw.length; i++) {
            if (pw[i] != dw[i] && !Character.isDigit(pw[i]))
                return false;
        }
        return true;
    }

    private static String swap(char[] pw, int i, int j) {
        char tmp = pw[i];
        pw[i] = pw[j];
        pw[j] = tmp;
        return String.valueOf(pw);
    }

}
