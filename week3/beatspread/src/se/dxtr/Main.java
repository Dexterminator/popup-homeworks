package se.dxtr;

public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        for (int i = 0; i < n; i++) {
            int sum = io.getInt ();
            int diff = io.getInt ();
            solve (sum, diff);
        }
        io.close ();
    }

    private static void solve (int sum, int diff) {
        int score2 = -((diff - sum) / 2);
        int score1 = sum - score2;
        if (score1 < 0 || score2 < 0 || Math.abs (score1 - score2) != diff || score1 + score2 != sum) {
            io.println ("impossible");
        } else {
            if (score1 > score2)
                io.println (score1 + " " + score2);
            else
                io.println (score2 + " " + score1);
        }
    }
}
