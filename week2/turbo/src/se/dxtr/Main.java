package se.dxtr;

public class Main {

    public static void main (String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        int[] numbers = new int[n + 1];
        int[] indices = new int[n + 1];
        FenwickTree prefixSums = new FenwickTree (n + 1);

        for (int i = 1; i <= n; i++) {
            int number = io.getInt ();
            numbers[i] = number;
            indices[number] = i;
            prefixSums.add (i, 1);
        }

        int small = 1;
        int big = n;

        for (int phase = 1; phase <= n; phase++) {
            int swaps;
            if (phase % 2 == 0) {
                prefixSums.add (indices[big], -1);
                swaps = prefixSums.sum (indices[big], n);
                big--;
            } else {
                prefixSums.add (indices[small], -1);
                swaps = prefixSums.sum (1, indices[small]);
                small++;
            }
            io.println (swaps);
        }
        io.close ();
    }
}
