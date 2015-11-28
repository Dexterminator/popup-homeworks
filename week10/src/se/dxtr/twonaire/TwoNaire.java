package se.dxtr.twonaire;

import java.text.DecimalFormat;

public class TwoNaire {

    static Kattio io = new Kattio();

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.000");
        while (true) {
            int questionCount = io.getInt();
            if (questionCount == 0)
                break;
            double t = io.getDouble();
            double expectedPrize = calculate(questionCount, t);
            io.println(df.format(expectedPrize));
        }
        io.close();
    }

    private static double calculate(int questionCount, double t) {
        double expected = Math.pow(2, questionCount);
        for (int i = questionCount - 1; i >= 0; i--) {
            double currentPrize = Math.pow(2, i);
            double thresholdProbability = currentPrize / expected; // Smallest probability when it is worth answering the next question
            if (thresholdProbability <= t) { // Always guess if threshold is smaller than t
                expected = (t + 1) / 2 * expected;
            } else { // Else calculate average values of stopping and playing
                double stopValue = (thresholdProbability - t) / (1 - t) * currentPrize;
                double playValue = (1 - thresholdProbability) / (1 - t) * ((1 + thresholdProbability) / 2) * expected;
                expected = stopValue + playValue;
            }
        }
        return expected;
    }
}
