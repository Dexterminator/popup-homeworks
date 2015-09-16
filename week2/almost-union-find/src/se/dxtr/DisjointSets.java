package se.dxtr;

/**
 * Created by dexter on 10/09/15.
 */
public class DisjointSets {
    private int[] ids;
    private int[] heights;
    private int[] sizes;
    private int[] sums;

    public DisjointSets (int n) {
        ids = new int[n];
        heights = new int[n];
        sizes = new int[n];
        sums = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            heights[i] = 1;
            sums[i] = i;
            sizes[i] = 1;
        }
    }

    public void union (int a, int b) {
        int aRoot = root (a);
        int bRoot = root (b);
        if (aRoot == bRoot)
            return;
        if (heights[aRoot] < heights[bRoot]) {
            ids[aRoot] = bRoot;
            sizes[bRoot] += sizes[aRoot];
            sums[bRoot] += sums[aRoot];
        } else if (heights[aRoot] > heights[bRoot]){
            ids[bRoot] = aRoot;
            sizes[aRoot] += sizes[bRoot];
            sums[aRoot] += sums[bRoot];
        } else { // heights equal, attaching the tree will increase height by 1
            ids[aRoot] = bRoot;
            sizes[bRoot] += sizes[aRoot];
            sums[bRoot] += sums[aRoot];
            heights[bRoot]++;
        }
    }

    public void move (int a, int b) {
        int aRoot = root (a);
        int bRoot = root (b);
        ids[a] = bRoot;
        sizes[aRoot]--;
        sizes[bRoot]++;
        sums[aRoot] -= a;
        sums[bRoot] += a;
    }

    public int[] getSizeAndSum (int a) {
        int aRoot = root (a);
        return new int[]{sizes[aRoot], sums[aRoot]};
    }

    private int root (int a) {
        if (a != ids[a])
            ids[a] = root (ids[a]);
        return ids[a];
    }
}
