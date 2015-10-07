package se.dxtr;

public class Weight {
    public final double weight;

    public Weight (double weight) {
        this.weight = weight;
    }

    @Override
    public String toString () {
        return "Weight{" +
                "weight=" + weight +
                '}';
    }
}
