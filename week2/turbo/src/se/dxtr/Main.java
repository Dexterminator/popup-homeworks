package se.dxtr;

public class Main {

    public static void main (String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        int[] numbers = new int[n];
        int[] indices = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int number = io.getInt ();
            numbers[i] = number;
            indices[number] = i;
        }
        int small = 1;
        int big = n;

        for (int phase = 1; phase <= n; phase++) {
            int swaps = 0;
            if (phase % 2 == 0) {
                int i = indices[big];
                while (i != big - 1) {
                    int swapped = numbers[i + 1];
                    numbers[i + 1] = big;
                    numbers[i] = swapped;
                    indices[swapped] = i;
                    i++;
                    swaps++;
                }
                big--;
            } else {
                int i = indices[small];
                while (i != small - 1) {
                    int swapped = numbers[i - 1];
                    numbers[i - 1] = small;
                    numbers[i] = swapped;
                    indices[swapped] = i;
                    i--;
                    swaps++;
                }
                small++;
            }
            io.println (swaps);
        }
        io.close ();
    }
}
