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
    private final TreeSet<Integer> cacheByDistance;
    private int reads = 0;

    public Cache (int maxSize, int[] accesses, Map<Integer, Queue<Integer>> accessLists) {
        this.maxSize = maxSize;
        this.accesses = accesses;
        this.accessLists = accessLists;
        Comparator<Integer> furthestNextAccess = ((object1, object2) -> {
            if (accessLists.get (object1).isEmpty ())
                return 1;
            if (accessLists.get (object2).isEmpty ())
                return -1;

            return accessLists.get (object1).peek () - accessLists.get (object2).peek ();
        });
        this.cacheByDistance = new TreeSet<> (furthestNextAccess);
    }

    public int calculateLeastReads () {
        for (int accessIndex = 0; accessIndex < accesses.length; accessIndex++) {
            int access = accesses[accessIndex];
            accessLists.get (access).remove ();
            if (cacheMiss (access)) {
                handleCacheMiss (access);
            }
        }
        return reads;
    }

    private void handleCacheMiss (int access) {
        reads++;
        if (currentCache.size () < maxSize) {
            currentCache.add (access);
            cacheByDistance.add (access);
        } else {
            Integer furthestAway = findObjectFurthestAway ();
            if (furthestAway != null) {
                currentCache.remove (furthestAway);
                currentCache.add (access);
                cacheByDistance.add (access);
            }
        }
    }

    private Integer findObjectFurthestAway () {
        return cacheByDistance.isEmpty () ? null : cacheByDistance.pollLast ();
    }

    private boolean cacheMiss (int access) {
        return !currentCache.contains (access);
    }
}
