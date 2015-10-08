package se.dxtr;

/**
 * Created by dexter on 08/10/15.
 */
public class Time {
    public final int traversalTime;
    public Edge<Time> reverseEdge;
    public boolean georgeVisited = false;
    public int georgeStart;
    public int georgeEnd;

    public Time (int traversalTime) {
        this.traversalTime = traversalTime;
    }

    public void georgeVisit (int georgeStart) {
        georgeVisited = true;
        this.georgeStart = georgeStart;
        georgeEnd = georgeStart + traversalTime - 1;
    }

    @Override
    public String toString () {
        return "Time{" +
                "traversalTime=" + traversalTime +
                ", georgeVisited=" + georgeVisited +
                ", georgeStart=" + georgeStart +
                ", georgeEnd=" + georgeEnd +
                '}';
    }
}
