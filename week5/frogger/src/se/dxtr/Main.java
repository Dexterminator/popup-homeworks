package se.dxtr;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main(String[] args) {
        int scenarios = io.getInt ();
        for (int i = 0; i < scenarios; i++) {
            int maxRounds = io.getInt ();
            int n = io.getInt ();
            int m = io.getInt ();

            for (int j = 0; j < n + 2; j++) {
                String lane = io.getWord ();
                io.println (lane);
            }
        }

        io.close ();
    }
}
