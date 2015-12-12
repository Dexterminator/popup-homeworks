package se.dxtr.matchsticks;

/**
 * Created by dexter on 12/12/15.
 */
public class Matchsticks {

    static Kattio io = new Kattio();
    static int[] sticksNeeded = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    public static void main(String[] args) {
        int n = io.getInt();
        for (int i = 0; i < n; i++) {
            int matchstickCount = io.getInt();
            String smallest = findSmallest(matchstickCount);
            String highest = findHighest(matchstickCount);
            io.println(smallest + " " + highest);
        }
        io.close();
    }

    private static String findSmallest(int matchstickCount) {
        if (matchstickCount < 15)
            return findSmallestRecursive(matchstickCount, "");

        StringBuilder number = new StringBuilder();
        switch (matchstickCount % 7) {
            case 1:
                number.append(108);
                matchstickCount -= sticksNeeded(1, 0, 8);
                break;
            case 2:
                number.append(188);
                matchstickCount -= sticksNeeded(1, 8, 8);
                break;
            case 3:
                number.append(200);
                matchstickCount -= sticksNeeded(2, 0, 0);
                break;
            case 4:
                number.append(208);
                matchstickCount -= sticksNeeded(2, 0, 8);
                break;
            case 5:
                number.append(288);
                matchstickCount -= sticksNeeded(2, 8, 8);
                break;
            case 6:
                number.append(688);
                matchstickCount -= sticksNeeded(6, 8, 8);
                break;
            case 7:
                number.append(888);
                matchstickCount -= sticksNeeded(8, 8, 8);
        }

        while (matchstickCount > 0) {
            number.append(8);
            matchstickCount -= sticksNeeded[8];
        }

        return number.toString();
    }

    private static String findSmallestRecursive(int matchStickCount, String number) {
        if (matchStickCount == 0) {
            if (number.charAt(0) == '0')
                return String.valueOf(Integer.MAX_VALUE);
            return number;
        }

        if (matchStickCount < 0)
            return String.valueOf(Integer.MAX_VALUE);
        int smallest = Integer.MAX_VALUE;
        String smallestStr = String.valueOf(smallest);
        for (int i = 0; i <= 9; i++) {
            String res = findSmallestRecursive(matchStickCount - sticksNeeded[i], number + i);
            int resInt = Integer.parseInt(res);
            if (resInt < smallest) {
                smallest = resInt;
                smallestStr = res;
            }
        }
        return smallestStr;
    }

    private static int sticksNeeded(int... args) {
        int res = 0;
        for (int arg : args) {
            res += sticksNeeded[arg];
        }
        return res;
    }

    private static String findHighest(int matchstickCount) {
        StringBuilder number = new StringBuilder();
        if (matchstickCount % 2 == 1) {
            number.append(7);
            matchstickCount -= sticksNeeded[7];
        } else {
            number.append(1);
            matchstickCount -= sticksNeeded[1];
        }

        while (matchstickCount > 0) {
            number.append(1);
            matchstickCount -= sticksNeeded[1];
        }

        return number.toString();
    }
}
