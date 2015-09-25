package se.dxtr;

public class Main {
    static Kattio io;

    public static void main(String[] args) {
        io = new Kattio (System.in, System.out);
        io.println (1186531.0/2500000.0);
        io.close ();
    }
}
