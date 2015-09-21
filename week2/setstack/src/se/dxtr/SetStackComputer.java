package se.dxtr;

import java.util.*;

/**
 * Created by dexter on 20/09/15.
 */
public class SetStackComputer {
    private Deque<Integer> stack = new LinkedList<> ();
    private Map<Integer, Set<Integer>> ids2Sets = new HashMap<> ();
    private int currentId = 1;

    public SetStackComputer () {
        ids2Sets.put (0, new HashSet<Integer> ());
    }

    public void push () {
        stack.push (0);
    }

    public void dup () {
        int setId = stack.peek ();
        stack.push (setId);
    }

    public void union () {
        int s1 = stack.pop ();
        int s2 = stack.pop ();
        Set<Integer> union = new HashSet<> (ids2Sets.get (s1));
        union.addAll (ids2Sets.get (s2));
        updateStack (union);
    }

    public void intersect () {
        int s1 = stack.pop ();
        int s2 = stack.pop ();
        Set<Integer> intersection = new HashSet<> (ids2Sets.get (s1));
        intersection.retainAll (ids2Sets.get (s2));
        updateStack (intersection);
    }

    public void add () {
        int s1 = stack.pop ();
        int s2 = stack.pop ();
        Set<Integer> newSet = new HashSet<> (ids2Sets.get (s2));
        newSet.add (s1);
        updateStack (newSet);
    }

    private void updateStack (Set<Integer> newSet) {
        Integer equalSet = findEqualSet (newSet);
        if (equalSet != null) {
            stack.push (equalSet);
        } else {
            ids2Sets.put (currentId, newSet);
            stack.push (currentId);
            currentId++;
        }
    }

    public Integer findEqualSet (Set<Integer> set) {
        for (Integer id : ids2Sets.keySet ()) {
            if (ids2Sets.get (id).equals (set))
                return id;
        }
        return null;
    }

    public int topCardinality () {
        return ids2Sets.get (stack.peek ()).size ();
    }
}
