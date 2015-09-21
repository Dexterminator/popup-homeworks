package se.dxtr;

import java.util.*;

/**
 * Created by dexter on 20/09/15.
 */
public class SetStackComputer {
    private Deque<Integer> stack = new LinkedList<> ();
    private Map<Integer, Set<Integer>> ids2Sets = new HashMap<> ();
    private int currentId = 1;
    private boolean debug = false;
    private Kattio io;

    public SetStackComputer (Kattio io) {
        this.io = io;
        ids2Sets.put (0, new HashSet<Integer> ());
    }

    public void push () {
        if (debug) System.out.println ("push");
        stack.push (0);
        if (debug) {
            System.out.println ("stack: " + stack);
            System.out.println ("|" + stack.peek () + "|: " + ids2Sets.get (stack.peek ()));
        }
    }

    public void dup () {
        if (debug) System.out.println ("dup");
        int setId = stack.peek ();
        stack.push (setId);
        if (debug) {
            System.out.println ("stack: " + stack);
            System.out.println ("|" + stack.peek () + "|: " + ids2Sets.get (stack.peek ()));
        }
    }

    public void union () {
        if (debug) System.out.println ("union");
        int s1 = stack.pop ();
        int s2 = stack.pop ();
        HashSet<Integer> union = new HashSet<> (ids2Sets.get (s1));
        union.addAll (ids2Sets.get (s2));
        ids2Sets.put (currentId, union);
        stack.push (currentId);
        currentId++;
        if (debug) {
            System.out.println ("stack: " + stack);
            System.out.println ("|" + stack.peek () + "|: " + ids2Sets.get (stack.peek ()));
        }
    }

    public void intersect () {
        int s1 = stack.pop ();
        int s2 = stack.pop ();
        HashSet<Integer> intersection = new HashSet<> (ids2Sets.get (s1));
        intersection.retainAll (ids2Sets.get (s2));
        ids2Sets.put (currentId, intersection);
        stack.push (currentId);
        currentId++;
        if (debug) System.out.println ("intersect");
    }

    public void add () {
        if (debug) System.out.println ("add");
        int s1 = stack.pop ();
        int s2 = stack.pop ();
        Set<Integer> newSet = new HashSet<> (ids2Sets.get (s2));
        newSet.add (s1);
        ids2Sets.put (currentId, newSet);
        stack.push (currentId);
        currentId++;
        if (debug) {
            System.out.println ("stack: " + stack);
            System.out.println ("|" + stack.peek () + "|: " + ids2Sets.get (stack.peek ()));
        }
    }

    public int topCardinality () {
        return ids2Sets.get (stack.peek ()).size ();
    }
}
