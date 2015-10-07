package se.dxtr;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main(String[] args) {
        int w = io.getInt ();
        int h = io.getInt ();
        for (int i = 0; i < h; i++) {
            String mapRow = io.getWord ();
            for (char c : mapRow.toCharArray ()) {
                if (c == '#') {

                } else if (c == '.') {

                } else if (c == 'T') {

                } else if (c == 'G') {

                } else if (c == 'P') {

                }
            }
            io.println (mapRow);
        }
        io.close ();
    }
}
