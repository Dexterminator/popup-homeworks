package se.dxtr;

public class Main {

    static Kattio io;
    static char[] ledNumbers = new char[]{'0', '1', '2', '5', '9', '8', '6'};

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int k = io.getInt ();
            calculate (k);
        }
        io.close ();
    }

    public static void calculate (int k) {
        String baseSeven = Integer.toString (k, 7);
        char[] chars = baseSeven.toCharArray ();
        char[] upsideDownChars = new char[baseSeven.length ()];
        for (int i = 0; i < baseSeven.length (); i++) {
            int upsideDownIndex = baseSeven.length () - i - 1;
            int baseSevenNumber = Character.getNumericValue (chars[i]);
            upsideDownChars[upsideDownIndex] = ledNumbers[baseSevenNumber];
        }
        io.println (String.valueOf (upsideDownChars));
    }
}
