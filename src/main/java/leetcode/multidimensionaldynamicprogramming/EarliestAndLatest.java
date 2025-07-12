package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class EarliestAndLatest {

    public static void main(String[] args) {
        check(11, 2, 4, new int[]{3, 4});
        check(5, 1, 5, new int[]{1, 1});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete.
     * This solution simulates the rounds and uses dynamic programming for memoization. Time complexity is O(n^4logn).
     *
     * @param n
     * @param firstPlayer
     * @param secondPlayer
     * @return
     */
    public static int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int[] roundLimits = {6, 1};
        boolean[][][] dpArray = new boolean[6][28][29];
        simulate(dpArray, roundLimits, 1, firstPlayer, n + 1 - secondPlayer, n);
        return roundLimits;
    }

    private static void simulate(boolean[][][] dpArray, int[] roundLimits, int round, int left, int right, int n) {
        if (dpArray[round][left][right]) {
            return;
        }
        dpArray[round][left][right] = true;
        if (left > right) {
            simulate(dpArray, roundLimits, round, right, left, n);
            return;
        }

        if (left == right) {
            roundLimits[0] = Math.min(round, roundLimits[0]);
            roundLimits[1] = Math.max(round, roundLimits[1]);
            return;
        }

        int half = (n + 1) / 2;
        int iN = Math.min(left, half);

        for (int i = 1; i <= iN; i++) {
            int j0 = Math.max(left - i + 1, 1);
            for (int j = Math.min(half, right) - i; j >= j0; j--) {
                if (left + right - (i + j) <= n / 2) {
                    simulate(dpArray, roundLimits, round + 1, i, j, half);
                }
            }
        }
    }

    private static void check(int n, int first, int second, int[] expected) {
        System.out.println("n is: " + n);
        System.out.println("first is: " + first);
        System.out.println("second is: " + second);
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] earliestAndLatest = earliestAndLatest(n, first, second); // Calls your implementation
        System.out.println("earliestAndLatest is: " + Arrays.toString(earliestAndLatest));
    }
}
