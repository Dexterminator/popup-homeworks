package se.dxtr.happyprime;

public class HappyPrime {

    static Kattio io = new Kattio();
    public static void main(String[] args) {
        int p = io.getInt();
        for (int i = 0; i < p; i++) {
            int datasetNumber = io.getInt();
            int candidate = io.getInt();
            System.err.println("candidate = " + candidate);
            System.err.println("datasetNumber = " + datasetNumber);
        }
        io.close();
    }
}
