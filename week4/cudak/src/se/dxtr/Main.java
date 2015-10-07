package se.dxtr;

public class Main {
    static Kattio io = new Kattio (System.in, System.out);

    public static void main(String[] args) {
        int left = io.getInt ();
        int right = io.getInt ();
        int digitSum = io.getInt ();
        io.close ();
    }

    public static int digitSum (int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }

        return sum;
    }
}
