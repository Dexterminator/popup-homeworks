package se.dxtr.happyprime;

public class HappyPrime {

    static Kattio io = new Kattio();

    public static void main(String[] args) {
        int p = io.getInt();
        for (int i = 0; i < p; i++) {
            int datasetNumber = io.getInt();
            int candidate = io.getInt();
            io.println(datasetNumber + " " + candidate + " " + (isHappyPrime(candidate) ? "YES" : "NO"));
        }
        io.close();
    }

    private static boolean isHappyPrime(int num) {
        return isPrime(num) && isHappy(num);
    }

    private static boolean isHappy(int num) {
        while (true) {
            num = String.valueOf(num).chars()
                    .map(c -> Character.digit(c, 10))
                    .map(d -> d * d)
                    .sum();
            if (num == 1)
                return true;
            if (num == 4)
                return false;
        }
    }

    private static boolean isPrime(int num) {
        if (num == 2) {
            return true;
        } else if (num <= 1 || num % 2 == 0) {
            return false;
        }

        int max = (int) Math.sqrt(num);
        for (int n = 3; n <= max; n += 2) {
            if (num % n == 0)
                return false;
        }
        return true;
    }
}
