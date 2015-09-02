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

            if (tokens1.length != tokens2.length) {
                System.out.println ("-");
                continue;
            }

            String[] result = new String[tokens1.length];
            Map<String, String> mappings1 = new HashMap<String, String> ();
            Map<String, String> mappings2 = new HashMap<String, String> ();

            createMappings (tokens1, tokens2, mappings1, mappings2);

            boolean noMatch = false;
            for (int j = 0; j < tokens1.length; j++) {
                String token1 = tokens1[j];
                String token2 = tokens2[j];
                String string1 = isPlaceholder (token1) ? mappings1.get (token1) : token1;
                String string2 = isPlaceholder (token2) ? mappings2.get (token2) : token2;

                if (string1.equals (string2)) {
                    result[j] = string1;
                } else {
                    noMatch = true;
                    break;
                }
            }

            if (noMatch) {
                System.out.println ("-");
            } else {
                System.out.println (Arrays.stream (result).collect (Collectors.joining (" ")));
            }
        }
    }

    private static void createMappings (String[] tokens1, String[] tokens2, Map<String, String> mappings1, Map<String, String> mappings2) {
        for (int j = 0; j < tokens1.length; j++) {
            String token1 = tokens1[j];
            String token2 = tokens2[j];

            if (isPlaceholder (token1) && !isPlaceholder (token2)) {
                mappings1.putIfAbsent (token1, token2);
            }

            if (!isPlaceholder (token1) && isPlaceholder (token2)) {
                mappings2.putIfAbsent (token2, token1);
            }

            if (isPlaceholder (token1) && isPlaceholder (token2)) {
                mappings1.put (token1, "a");
                mappings2.put (token2, "a");
            }
        }
    }

    private static boolean isPlaceholder (String token1) {
        return token1.matches ("<.+>");
    }
}
