package se.dxtr.howmanyzeros;

public class HowManyZeros {

    static Kattio io = new Kattio();
    public static void main(String[] args) {
        while (true) {
            long m = io.getLong();
            long n = io.getLong();
            if (m == -1 && n == -1)
                break;
            io.println(countZeros(m, n));
        }
        io.close();
    }

    private static long countZeros(long m, long n) {
        int zeros = 0;
        return zeros;
    }
}
