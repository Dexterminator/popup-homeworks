package se.dxtr;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> upsideDown = new ArrayList<String> ();
    static int lastCalculated = 0;
    static Kattio io;

    public static void main(String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int k = io.getInt ();
            calculate (k);
        }
//        io.println (upsideDown);
        io.close ();
    }

    public static void calculate (int k) {
        while (upsideDown.size () < k) {
            lastCalculated++;
            String s = String.valueOf (lastCalculated);
            if (!(s.contains ("3") || s.contains ("4") || s.contains ("7"))) {
                char[] chars = s.toCharArray ();
                char[] upsideDownChars = new char[s.length ()];
                for (int i = 0; i < s.length (); i++) {
                    int upsideDownIndex = s.length () - i - 1;
                    if (chars[i] == '6')
                        upsideDownChars[upsideDownIndex] = '9';
                    else if (chars[i] == '9')
                        upsideDownChars[upsideDownIndex] = '6';
                    else
                        upsideDownChars[upsideDownIndex] = chars[i];
                }
                upsideDown.add (String.valueOf (upsideDownChars));
            }
        }
        io.println (upsideDown.get (upsideDown.size () - 1));
    }
}
