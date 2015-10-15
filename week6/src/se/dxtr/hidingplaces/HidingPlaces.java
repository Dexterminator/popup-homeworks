package se.dxtr.hidingplaces;

import java.util.*;

/**
 * Created by dexter on 14/10/15.
 */
public class HidingPlaces {

    public static final int SIZE = 8;
    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        int n = io.getInt ();
        for (int i = 0; i < n; i++) {
            findBestHidingPlace ();
        }
        io.close ();
    }

    private static void findBestHidingPlace () {
        String knightPositionString = io.getWord ();
        int[][] distances = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                distances[i][j] = -1;
            }
        }
        char[] chars = knightPositionString.toCharArray ();
        Position knightPosition = new Position (getNumberForChar (chars[0]) - 1, Character.getNumericValue (chars[1]) - 1);

        List<List<Position>> positionsByDistance = new ArrayList<> ();
        positionsByDistance.add (Collections.singletonList (knightPosition));
        distances[knightPosition.i][knightPosition.j] = 0;
        Queue<Position> queue = new LinkedList<> ();
        queue.add (knightPosition);
        int maxDistance = 0;

        while (!queue.isEmpty ()) {
            Position current = queue.poll ();
            int i = current.i;
            int j = current.j;

            // Large up
            int i1 = i + 2;
            int j1 = j - 1;
            if (i1 < SIZE && j1 >= 0 && distances[i1][j1] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i1, j1, positionsByDistance);
            }


            int i2 = i + 2;
            int j2 = j + 1;
            if (i2 < SIZE && j2 < SIZE && distances[i2][j2] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i2, j2, positionsByDistance);
            }

            // Large right
            int i3 = i + 1;
            int j3 = j + 2;
            if (i3 < SIZE && j3 < SIZE && distances[i3][j3] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i3, j3, positionsByDistance);
            }

            int i4 = i - 1;
            int j4 = j + 2;
            if (i4 >= 0 && j4 < SIZE && distances[i4][j4] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i4, j4, positionsByDistance);
            }

            // Large down
            int i5 = i - 2;
            int j5 = j + 1;
            if (i5 >= 0 && j5 < SIZE && distances[i5][j5] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i5, j5, positionsByDistance);
            }

            int i6 = i - 2;
            int j6 = j - 1;
            if (i6 >= 0 && j6 >= 0 && distances[i6][j6] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i6, j6, positionsByDistance);
            }

            // Large left
            int i7 = i + 1;
            int j7 = j - 2;
            if (i7 < SIZE && j7 >= 0 && distances[i7][j7] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i7, j7, positionsByDistance);
            }

            int i8 = i - 1;
            int j8 = j - 2;
            if (i8 >= 0 && j8 >= 0 && distances[i8][j8] == -1) {
                maxDistance = visit (distances, queue, maxDistance, i, j, i8, j8, positionsByDistance);
            }
        }

        List<Position> bestHidingPlaces = positionsByDistance.get (maxDistance);
        Collections.sort (bestHidingPlaces);
        io.print (maxDistance + " ");
        for (Position hidingPlace : bestHidingPlaces) {
            io.print (chessPos (hidingPlace.i, hidingPlace.j) + " ");
        }
        io.println ();
    }

    private static int visit (int[][] distances, Queue<Position> queue, int maxDistance,
                              int i, int j, int inew, int jnew, List<List<Position>> positionsByDistance) {
        distances[inew][jnew] = distances[i][j] + 1;
        Position pos = new Position (inew, jnew);
        queue.add (pos);
        if (distances[inew][jnew] > maxDistance) {
            maxDistance = distances[inew][jnew];
            positionsByDistance.add (new ArrayList<> ());
        }
        if (distances[inew][jnew] == maxDistance) {
            positionsByDistance.get (maxDistance).add (pos);
        }
        return maxDistance;
    }

    private static String chessPos (int i, int j) {
        return getCharForNumber (i + 1) + (j + 1);
    }

    private static String getCharForNumber (int i) {
        return String.valueOf ((char) (i + 'a' - 1));
    }

    private static int getNumberForChar (char c) {
        return (char) (c - 'a' + 1);
    }

    static class Position implements Comparable<Position>{
        final int i;
        final int j;

        @Override
        public String toString () {
            return "Position{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }

        public Position (int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo (Position o) {
            int cmp = o.j - j;
            if (cmp == 0)
                return i - o.i;
            return cmp;
        }
    }
}
