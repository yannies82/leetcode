package leetcode.dynamicprogramming;

public class DominoAndTrominoTiling {

    public static void main(String[] args) {
        check(4, 11);
        check(3, 5);
        check(1, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/domino-and-tromino-tiling.
     * This solution uses top down dynamic programming. Time complexity is O(n);
     *
     * @param n
     * @return
     */
    public static int numTilings(int n) {
        if (n == 1) {
            return 1;
        }
        // this array caches the intermediate results
        int[] dpArray = new int[n + 1];
        // set the known values for n = 0 and n = 1 and n = 2
        dpArray[0] = 1;
        dpArray[1] = 1;
        dpArray[2] = 2;
        return dp(n, dpArray);
    }

    private static int dp(int target, int[] dpArray) {
        if (dpArray[target] == 0) {
            dpArray[target] = (int) ((2L * dp(target - 1, dpArray) + dp(target - 3, dpArray)) % 1000000007);
        }
        return dpArray[target];
    }

    /**
     * This solution uses bottom up dynamic programming. Time complexity is O(n).
     *
     * @param n
     * @return
     */
    public static int numTilings2(int n) {
        // early exit if there is only one house
        if (n == 1) {
            return 1;
        }
        // this array caches the intermediate results up to index i
        int[] dpArray = new int[n + 1];
        // set the known values for n = 0 and n = 1 and n = 2
        dpArray[0] = 1;
        dpArray[1] = 1;
        dpArray[2] = 2;
        // calculate the rest of the dpArray values bottom up
        for (int i = 3; i <= n; i++) {
            dpArray[i] = (int) ((2L * dpArray[i - 1] + dpArray[i - 3]) % 1000000007);
        }
        // return the last value of the dpArray which is the answer to our problem
        return dpArray[n];
    }

    private static void check(int n, int expected) {
        System.out.println("n is: " + n);
        System.out.println("expected is: " + expected);
        int numTilings = numTilings(n); // Calls your implementation
        System.out.println("numTilings is: " + numTilings);
    }

}
