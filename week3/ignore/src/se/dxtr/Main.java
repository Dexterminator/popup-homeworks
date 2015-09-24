package se.dxtr;

public class Main {

    static Kattio io;
    static int[] below7 = new int[]{1, 2, 5, 9, 8, 6};

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int k = io.getInt ();
            if (k < 7)
                io.println (below7[k-1]);
            else
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
            if (chars[i] == '6')
                upsideDownChars[upsideDownIndex] = '9';
            else if (chars[i] == '9')
                upsideDownChars[upsideDownIndex] = '6';
            else
                upsideDownChars[upsideDownIndex] = chars[i];
        }
        io.println (String.valueOf (upsideDownChars));
    }
}
