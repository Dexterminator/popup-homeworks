package se.dxtr;

/**
 * Created by dexter on 10/09/15.
 */
public class DisjointSets {
    private int[] ids;
    private int[] sizes;
    private int[] sums;

    public DisjointSets (int n) {
        ids = new int[n];
        sizes = new int[n];
        sums = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sums[i] = i;
            sizes[i] = 1;
        }
    }

    public void union (int a, int b) {
        int aId = ids[a];
        if (aId == ids[b])
            return;
        for (int i = 1; i < ids.length; i++) {
            if (ids[i] == aId) {
                sums[ids[b]] += i;
                sizes[ids[b]]++;
                ids[i] = ids[b];
            }
        }
    }

    public void move (int a, int b) {
        sums[ids[a]] -= a;
        sums[ids[b]] += a;
        sizes[ids[a]]--;
        sizes[ids[b]]++;
        ids[a] = ids[b];
    }

    public int[] getSizeAndSum (int a) {
        return new int[]{sizes[ids[a]], sums[ids[a]]};
    }
}
