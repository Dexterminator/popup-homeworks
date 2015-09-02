package se.dxtr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 02/09/15.
 */
public class Cache {

    private final int maxSize;
    private final int[] accesses;
    private final List<Integer> currentCache = new ArrayList<> ();
    private int reads = 0;

    public Cache (int maxSize, int[] accesses) {
        this.maxSize = maxSize;
        this.accesses = accesses;
    }

    public int calculateLeastReads () {
        for (int accessIndex = 0; accessIndex < accesses.length; accessIndex++) {
            int access = accesses[accessIndex];
            if (cacheMiss (access)) {
                handleCacheMiss (access, accessIndex);
            }
        }
        return reads;
    }

    private void handleCacheMiss (int access, int accessIndex) {
        reads++;
        if (currentCache.size () < maxSize) {
            currentCache.add (access);
        } else {
            Integer furthestAway = findObjectFurthestAway (accessIndex);
            currentCache.remove (furthestAway);
            currentCache.add (access);
        }
    }

    private Integer findObjectFurthestAway (int accessIndex) {
        int furthest = Integer.MIN_VALUE;
        Integer furthestObject = null;

        for (Integer object : currentCache) {
            int distance = 1;
            for (int i = accessIndex; i < accesses.length; i++) {
                if (accesses[i] == object) {
                    if (distance > furthest) {
                        furthest = distance;
                        furthestObject = object;
                    }
                }
            }
        }
        return furthestObject;
    }

    private boolean cacheMiss (int access) {
        return !currentCache.contains (access);
    }
}
