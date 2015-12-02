package se.dxtr.trilemma;

/**
 * Created by dexter on 02/12/15.
 */

public class Trilemma {
    static Kattio io = new Kattio();

    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            int x1 = io.getInt();
            int y1 = io.getInt();
            int x2 = io.getInt();
            int y2 = io.getInt();
            int x3 = io.getInt();
            int y3 = io.getInt();
            io.println(distance(x1, y1, x2, y2));
        }
        io.close();
    }

    private static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.hypot(x2 - x1, y2 - y1);
    }
}
