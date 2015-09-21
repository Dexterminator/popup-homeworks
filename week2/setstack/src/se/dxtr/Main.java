package se.dxtr;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int cases = io.getInt ();
        for (int i = 0; i < cases; i++) {
            performOperations (io);
            io.println ("***");
        }
        io.close ();
    }

    private static void performOperations (Kattio io) {
        int operations = io.getInt ();
        SetStackComputer setStack = new SetStackComputer ();
        for (int j = 0; j < operations; j++) {
            String operation = io.getWord ();
            switch (operation) {
                case "PUSH":
                    setStack.push ();
                    io.println (setStack.topCardinality ());
                    break;
                case "DUP":
                    setStack.dup ();
                    io.println (setStack.topCardinality ());
                    break;
                case "UNION":
                    setStack.union ();
                    io.println (setStack.topCardinality ());
                    break;
                case "INTERSECT":
                    setStack.intersect ();
                    io.println (setStack.topCardinality ());
                    break;
                case "ADD":
                    setStack.add ();
                    io.println (setStack.topCardinality ());
            }
        }
    }
}
