package se.dxtr;

/**
 * Created by dexter on 04/09/15.
 */
public class WorkoutOptimizer {
    static int IMPOSSIBLE = Integer.MAX_VALUE;
    enum Direction {
        UP ("U"),
        DOWN ("D");

        public final String character;
        Direction (String character) {
            this.character = character;
        }
    }

    static String getOptimalWorkout (int[] distances) {
        if (distances.length == 1)
            return Path.impossible ().pathString;
        Path path = new Path ("U", distances[0]);
        Path bestPath = findBestPath (path, Direction.UP, 1, distances, distances[0]);
        return bestPath.pathString;
    }

    static Path findBestPath (Path path, Direction direction, int index, int[] distances, int currentHeight) {
        int climbed = distances[index];
        if (direction == Direction.UP) {
            currentHeight += climbed;
        } else if (direction == Direction.DOWN) {
            currentHeight -= climbed;
        }

        if (currentHeight < 0) {
            return Path.impossible ();
        }

        String updatedPathString = path.pathString + direction.character;
        Path updatedPath = new Path (updatedPathString, Math.max (path.maxHeight, currentHeight));
        if (index == distances.length - 1) {
            return currentHeight == 0 ? updatedPath : Path.impossible ();
        }

        Path path1 = findBestPath (updatedPath, Direction.UP, index + 1, distances, currentHeight);
        Path path2 = findBestPath (updatedPath, Direction.DOWN, index + 1, distances, currentHeight);
        return path1.maxHeight < path2.maxHeight ? path1 : path2;
    }

    static class Path {
        public final String pathString;
        public final int maxHeight;

        public Path (String pathString, int maxHeight) {
            this.pathString = pathString;
            this.maxHeight = maxHeight;
        }

        public static Path impossible () {
            return new Path ("IMPOSSIBLE", IMPOSSIBLE);
        }
    }
}
