package se.dxtr;

import java.util.*;

/**
 * Created by dexter on 02/09/15.
 */
public class Cache {

    private final int maxSize;
    private final int[] accesses;
    private Map<Integer, Queue<Integer>> accessLists;
    private final Set<Integer> currentCache = new HashSet<> ();
    private final PriorityQueue<ObjectAndAccessTime> objectsByDistance;
    private int reads = 0;

    public Cache (int maxSize, int[] accesses, Map<Integer, Queue<Integer>> accessLists) {
        this.maxSize = maxSize;
        this.accesses = accesses;
        this.accessLists = accessLists;
        Comparator<ObjectAndAccessTime> furthestNextAccess = ((object1, object2) -> object2.accessTime - object1.accessTime);
        this.objectsByDistance = new PriorityQueue<> (furthestNextAccess);
    }

    public int calculateLeastReads () {
        for (int accessIndex = 0; accessIndex < accesses.length; accessIndex++) {
            int access = accesses[accessIndex];
            accessLists.get (access).remove ();
            if (cacheMiss (access))
                handleCacheMiss (access);
            updateObjectsByDistance (access);
        }
        return reads;
    }

    private void updateObjectsByDistance (int access) {
        Queue<Integer> accessList = accessLists.get (access);
        Integer nextAccessTime = accessList.isEmpty () ? Integer.MAX_VALUE : accessList.peek ();
        objectsByDistance.add (new ObjectAndAccessTime (nextAccessTime, access));
    }

    private void handleCacheMiss (int access) {
        reads++;
        if (currentCache.size () < maxSize) {
            currentCache.add (access);
        } else {
            ObjectAndAccessTime furthestAway = objectsByDistance.poll ();
            if (furthestAway != null)
                currentCache.remove (furthestAway.object);
            currentCache.add (access);
        }
    }

    private boolean cacheMiss (int access) {
        return !currentCache.contains (access);
    }

    private static class ObjectAndAccessTime {
        public final int accessTime;
        public final int object;

        public ObjectAndAccessTime (int accessTime, int object) {
            this.accessTime = accessTime;
            this.object = object;
        }
    }
}
