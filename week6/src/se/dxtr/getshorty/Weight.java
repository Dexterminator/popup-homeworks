package se.dxtr.getshorty;

/**
 * Container class for an edge weight value.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Weight {
    public final double weight;

    public Weight (double weight) {
        this.weight = weight;
    }

    public double getWeight () {
        return weight;
    }

    @Override
    public String toString () {
        return "Weight{" +
                "weight=" + weight +
                '}';
    }
}
