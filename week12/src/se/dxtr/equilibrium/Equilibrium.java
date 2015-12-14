package se.dxtr.equilibrium;

import java.util.HashMap;
import java.util.Map;

public class Equilibrium {

    static Kattio io = new Kattio();
    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            String mobile = io.getWord();
            solve(mobile);
        }
        io.close();
    }

    private static void solve(String mobile) {
        Map<Long, Long> counts = new HashMap<>();
        int level = 0;
        int i = 0;
        while (i < mobile.length()) {
            char c = mobile.charAt(i);
            if (c == ',') {
                i++;
                continue;
            } else if (c == '[') {
                i++;
                level++;
            } else if (c == ']') {
                i++;
                level--;
            } else {
                StringBuilder s = new StringBuilder();
                s.append(c);
                while (i + 1 < mobile.length() && Character.isDigit(mobile.charAt(i + 1))) {
                    s.append(mobile.charAt(i + 1));
                    i++;
                }
                long weight = Long.parseLong(s.toString());
                long fixedVal = (long) (Math.pow(2, level) * weight);
                counts.merge(fixedVal, 1L, (a, b) -> a + b);
                i++;
            }
        }
        Long max = counts.values().stream().max(Long::compare).get();
        Long sum = counts.values().stream().mapToLong(l -> l).sum();
        io.println(sum - max);
    }
}
