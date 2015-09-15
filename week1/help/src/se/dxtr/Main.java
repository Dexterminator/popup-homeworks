package se.dxtr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, String> mappings1 = new HashMap<> ();
    static Map<String, String> mappings2 = new HashMap<> ();
    private static Kattio io;

    public static void main(String[] args) {
        Scanner s = new Scanner (new BufferedReader (new InputStreamReader (System.in)));
        io = new Kattio (System.in, System.out);
        int n = s.nextInt ();
        s.nextLine ();
        for (int i = 0; i < n; i++)
            matchPatterns (s);
        io.flush ();
        io.close ();
    }

    private static void matchPatterns (Scanner s) {
        String s1 = s.nextLine ();
        String s2 = s.nextLine ();
        String[] tokens1 = s1.split (" ");
        String[] tokens2 = s2.split (" ");
        for (String str : findMatch (tokens1, tokens2)) {
            io.print (str + " ");
        }
        io.println ();
    }

    private static String[] findMatch (String[] tokens1, String[] tokens2) {
        if (tokens1.length != tokens2.length)
            return new String[]{"-"};

        mappings1.clear ();
        mappings2.clear ();

        createExplicitMappings (tokens1, tokens2);
        resolveOtherMappings (tokens1, tokens2);

        return getResult (tokens1, tokens2);
    }

    private static void createExplicitMappings (String[] tokens1, String[] tokens2) {
        for (int j = 0; j < tokens1.length; j++) {
            String token1 = tokens1[j];
            String token2 = tokens2[j];

            if (isPlaceholder (token1) && !isPlaceholder (token2)) {
                mappings1.put (token1, token2);
            } else if (!isPlaceholder (token1) && isPlaceholder (token2)) {
                mappings2.put (token2, token1);
            }
        }
    }

    private static void resolveOtherMappings (String[] tokens1, String[] tokens2) {
        for (int j = 0; j < tokens1.length; j++) {
            String token1 = tokens1[j];
            String token2 = tokens2[j];

            if (isPlaceholder (token1) && isPlaceholder (token2)) {
                if (mappings1.containsKey (token1) && !mappings2.containsKey (token2)) {
                    mappings2.put (token2, mappings1.get (token1));
                } else if (mappings2.containsKey (token2) && !mappings1.containsKey (token1)) {
                    mappings1.put (token1, mappings2.get (token2));
                }
            }
        }
    }

    private static String[] getResult (String[] tokens1, String[] tokens2) {
        String[] result = new String[tokens1.length];
        for (int j = 0; j < tokens1.length; j++) {
            String token1 = tokens1[j];
            String token2 = tokens2[j];

            String string1 = isPlaceholder (token1) ? mappings1.getOrDefault (token1, "a") : token1;
            String string2 = isPlaceholder (token2) ? mappings2.getOrDefault (token2, "a") : token2;

            if (!string1.equals (string2))
                return new String[] {"-"};

            result[j] = string1;
        }

        return result;
    }

    private static boolean isPlaceholder (String token1) {
        return token1.startsWith ("<");
    }
}
