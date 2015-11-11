package se.dxtr.dictionaryattack;

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
        for (String dictionaryWord : dictionaryWords) {
            int distance = distance(password, dictionaryWord);
            if (distance <= 3) {
                return false;
            }
        }
        return true;
    }

    static int partDist(String pw, String dw, int pwLen, int dwLen) {
        if (pwLen == 0)
            return dwLen;
        if (dwLen == 0)
            return pwLen;
        int exchangeDigitDistance = getExchangeDigitDistance(pw, dw, pwLen, dwLen);
        int transposeDistance = getTransposeDistance(pw, dw, pwLen, dwLen);
        return Math.min(exchangeDigitDistance, transposeDistance);
    }

    private static int getTransposeDistance(String pw, String dw, int pwLen, int dwLen) {
        int transposeDistance = Integer.MAX_VALUE;
        if (pwLen > 1 && dwLen > 1 && pw.charAt(pwLen - 1) == dw.charAt(dwLen - 2) && pw.charAt(pwLen - 2) == dw.charAt(dwLen - 1)) {
            transposeDistance = partDist(pw, dw, pwLen - 2, dwLen - 2);
            if (transposeDistance != Integer.MAX_VALUE)
                transposeDistance += 1;
        } else if (pwLen > 1) {
            String swapped = swap(pw, pwLen - 1, pwLen - 2);
            transposeDistance = partDist(swapped, dw, pwLen - 1, dwLen - 1);
            if (transposeDistance != Integer.MAX_VALUE)
                transposeDistance += 1;
        }

        return transposeDistance;
    }

    private static String swap(String pw, int i, int j) {
        char[] chars = pw.toCharArray();
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
        return String.valueOf(chars);
    }

    private static int getExchangeDigitDistance(String pw, String dw, int w1len, int w2len) {
        int digitExchangeCost = Integer.MAX_VALUE;
        char pwChar = pw.charAt(w1len - 1);
        char dwChar = dw.charAt(w2len - 1);
        if (pwChar == dwChar) {
            digitExchangeCost = 0;
        } else if (Character.isDigit(pwChar)) {
            digitExchangeCost = 1;
        }

        int exchangeDigit;
        int dist = partDist(pw, dw, w1len - 1, w2len - 1);
        if (dist == Integer.MAX_VALUE || digitExchangeCost == Integer.MAX_VALUE) {
            exchangeDigit = Integer.MAX_VALUE;
        } else {
            exchangeDigit = dist + digitExchangeCost;
        }
        return exchangeDigit;
    }

    static int distance(String w1, String w2) {
        return partDist(w1, w2, w1.length(), w2.length());
    }

}