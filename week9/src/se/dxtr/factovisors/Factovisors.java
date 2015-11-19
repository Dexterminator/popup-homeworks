package se.dxtr.factovisors;

/**
 * Created by dexter on 19/11/15.
 */
public class Factovisors {

    static Kattio io = new Kattio();
    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            int m = io.getInt();
            System.err.println("n = " + n);
            System.err.println("m = " + m);
        }
        io.close();
    }
}
