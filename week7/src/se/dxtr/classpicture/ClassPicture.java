package se.dxtr.classpicture;

/**
 * Created by dexter on 04/11/15.
 */
public class ClassPicture {
    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            for (int i = 0; i < n; i++) {
                String name = io.getWord ();
                System.err.println("name = " + name);
            }

            int m = io.getInt ();
            for (int i = 0; i < m; i++) {
                String name1 = io.getWord ();
                String name2 = io.getWord ();
                System.err.println("name1 = " + name1);
                System.err.println("name2 = " + name2);
            }
        }
    }
}
