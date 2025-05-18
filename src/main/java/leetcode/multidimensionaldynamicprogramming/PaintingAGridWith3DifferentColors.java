package leetcode.multidimensionaldynamicprogramming;

public class PaintingAGridWith3DifferentColors {

    public static void main(String[] args) {
        check(1, 1, 3);
        check(1, 2, 6);
        check(5, 5, 580986);
    }

    private static final int MOD = 1000000007;

    /**
     * Leetcode problem: https://leetcode.com/problems/painting-a-grid-with-three-different-colors.
     * This solution uses dynamic programming.
     *
     * @param m
     * @param n
     * @return
     */
    public static int colorTheGrid(int m, int n) {
        // total number of combinations for each column is 3^m
        int total = (int) Math.pow(3, m);
        int[][] dpArray = new int[n + 1][total];
        int[][] pattern = new int[total][m];
        int[] validPatterns = new int[total];
        int index = 0;
        // generate all possible patterns for each row and column and keep the valid ones
        outer:
        for (int i = 0; i < total; i++) {
            // generate pattern
            int val = i;
            for (int j = 0; j < m; j++) {
                pattern[i][j] = val % 3;
                val /= 3;
            }
            // find invalid patterns
            for (int j = 1; j < m; j++) {
                if (pattern[i][j] == pattern[i][j - 1]) {
                    continue outer;
                }
            }
            // add to valid column patterns
            validPatterns[index++] = i;
        }
        // mark valid pattern combinations out of all possible combinations of patterns for rows and columns
        int[][] rowValid = new int[total][total];
        for (int iIndex = 0; iIndex < index; iIndex++) {
            int i = validPatterns[iIndex];
            dpArray[1][i] = 1;
            for (int jIndex = 0; jIndex < index; jIndex++) {
                int j = validPatterns[jIndex];
                rowValid[i][j] = 1;
                for (int k = 0; k < m; k++) {
                    if (pattern[i][k] == pattern[j][k]) {
                        rowValid[i][j] = 0;
                        break;
                    }
                }
            }
        }
        // calculate valid pattern combinations for each column
        for (int col = 2; col <= n; col++) {
            for (int iIndex = 0; iIndex < index; iIndex++) {
                int i = validPatterns[iIndex];
                long sum = 0;
                for (int jIndex = 0; jIndex < index; jIndex++) {
                    int j = validPatterns[jIndex];
                    if (rowValid[i][j] == 1) {
                        sum += dpArray[col - 1][j];
                    }
                }
                dpArray[col][i] = (int) (sum % MOD);
            }
        }
        long result = 0;
        for (int i = 0; i < total; i++) {
            result += dpArray[n][i];
        }
        return (int) (result % MOD);
    }

    private static void check(int m, int n, int expected) {
        System.out.println("m is: " + m);
        System.out.println("n is: " + n);
        System.out.println("expected is: " + expected);
        int colorTheGrid = colorTheGrid(m, n); // Calls your implementation
        System.out.println("colorTheGrid is: " + colorTheGrid);
    }
}
