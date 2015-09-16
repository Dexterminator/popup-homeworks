package se.dxtr;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            solve (io);
        }
        io.close ();
    }

    private static void solve (Kattio io) {
        int jacksNumberOfCds = io.getInt ();
        int jillsNumberOfCds = io.getInt ();

        if (jacksNumberOfCds == 0 && jillsNumberOfCds == 0) {
            return;
        } else if (jacksNumberOfCds == 0) {
            for (int i = 0; i < jillsNumberOfCds; i++)
                io.getInt ();
            io.println (0);
            return;
        } else if (jillsNumberOfCds == 0) {
            for (int i = 0; i < jacksNumberOfCds; i++)
                io.getInt ();
            io.println (0);
            return;
        }

        int numberOfCdsOwnedByBoth = 0;
        int[] jacksCds = new int[jacksNumberOfCds];

        for (int i = 0; i < jacksNumberOfCds; i++)
            jacksCds[i] = io.getInt ();

        int jackIndex = 0;
        int jillsCd = io.getInt ();
        int jillIndex = 0;
        while (jillIndex < jillsNumberOfCds && jackIndex < jacksNumberOfCds) {
            if (jillsCd == jacksCds[jackIndex]) {
                numberOfCdsOwnedByBoth++;
                if (jillIndex < jillsNumberOfCds - 1)
                    jillsCd = io.getInt ();
                jillIndex++;
                jackIndex++;
            } else if (jillsCd > jacksCds[jackIndex]) {
                jackIndex++;
            } else { // jillsCd < jacksCds[jackIndex]
                if (jillIndex < jillsNumberOfCds - 1)
                    jillsCd = io.getInt ();
                jillIndex++;
            }
        }
        
        while (jillIndex < jillsNumberOfCds - 1) {
            io.getInt ();
            jillIndex++;
        }

        io.println (numberOfCdsOwnedByBoth);
    }
}
