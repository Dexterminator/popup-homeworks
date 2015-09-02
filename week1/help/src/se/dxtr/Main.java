package se.dxtr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int n = s.nextInt ();
        s.nextLine ();
        for (int i = 0; i < n; i++) {
            String s1 = s.nextLine ();
            String s2 = s.nextLine ();
            String[] tokens1 = s1.split (" ");
            String[] tokens2 = s2.split (" ");
            System.out.println (findMatch (tokens1, tokens2));
        }
    }

    private static String findMatch (String[] tokens1, String[] tokens2) {
        if (tokens1.length != tokens2.length) {
            return "-";
        }

        Map<String, String> mappings1 = new HashMap<> ();
        Map<String, String> mappings2 = new HashMap<> ();
        createMappings (tokens1, tokens2, mappings1, mappings2);

        String[] result = new String[tokens1.length];
        for (int j = 0; j < tokens1.length; j++) {
            String token1 = tokens1[j];
            String token2 = tokens2[j];

            String string1;
            if (isPlaceholder (token1)) {
                if (mappings1.containsKey (token1)) {
                    string1 = mappings1.get (token1);
                } else if (mappings2.containsKey (token2)) {
                    string1 = mappings2.get (token2);
                } else {
                    string1 = "a";
                }
            } else {
                string1 = token1;
            }

            String string2;
            if (isPlaceholder (token2)) {
                if (mappings2.containsKey (token2)) {
                    string2 = mappings2.get (token2);
                } else if (mappings1.containsKey (token1)) {
                    string2 = mappings1.get (token1);
                } else {
                    string2 = "a";
                }
            } else {
                string2 = token2;
            }

            if (!string1.equals (string2))
                return "-";

            result[j] = string1;
        }

        return Arrays.stream (result).collect (Collectors.joining (" "));
    }

    private static void createMappings (String[] tokens1, String[] tokens2, Map<String, String> mappings1, Map<String, String> mappings2) {
        for (int j = 0; j < tokens1.length; j++) {
            String token1 = tokens1[j];
            String token2 = tokens2[j];

            if (isPlaceholder (token1) && !isPlaceholder (token2)) {
                mappings1.putIfAbsent (token1, token2);
            } else if (!isPlaceholder (token1) && isPlaceholder (token2)) {
                mappings2.putIfAbsent (token2, token1);
            }
        }
    }

    private static boolean isPlaceholder (String token1) {
        return token1.startsWith ("<");
    }
}
