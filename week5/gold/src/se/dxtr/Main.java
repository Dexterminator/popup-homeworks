package se.dxtr;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main(String[] args) {
        int w = io.getInt ();
        int h = io.getInt ();
        Square[][] map = new Square[h][w];
        Square playerSquare = null;
        for (int i = 0; i < h; i++) {
            String mapRow = io.getWord ();
            char[] chars = mapRow.toCharArray ();
            for (int j = 0; j < w; j++) {
                char c = chars[j];
                Square square = new Square (i, j, c);
                map[i][j] = square;
                if (c == 'P')
                    playerSquare = square;
            }
        }

        Queue<Square> queue = new LinkedList<> ();
        queue.add (playerSquare);
        playerSquare.visited = true;

        int gold = 0;
        while (!queue.isEmpty ()) {
            Square square = queue.poll ();

            if (square.type == 'P' || square.type == 'G' || square.type == '.') {
                if (square.type == 'G')
                    gold++;

                int row_up = square.row - 1;
                Square up = map[row_up][square.column];

                int row_down = square.row + 1;
                Square down = map[row_down][square.column];

                int column_right = square.column + 1;
                Square right = map[square.row][column_right];

                int column_left = square.column - 1;
                Square left = map[square.row][column_left];

                if (up.type != 'T' && down.type != 'T' && left.type != 'T' && right.type != 'T') {
                    if (!up.visited) {
                        up.visited = true;
                        queue.add (up);
                    }

                    if (!down.visited) {
                        down.visited = true;
                        queue.add (down);
                    }

                    if (!right.visited) {
                        right.visited = true;
                        queue.add (right);
                    }

                    if (!left.visited) {
                        left.visited = true;
                        queue.add (left);
                    }
                }
            }
        }
        io.println (gold);
        io.close ();
    }

    static class Square {
        public final int row;
        public final int column;
        public final char type;
        public boolean visited;

        public Square (int row, int column, char type) {
            this.row = row;
            this.column = column;
            this.type = type;
            visited = false;
        }
    }
}
