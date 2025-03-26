package leetcode.matrix;

import java.util.Arrays;

public class MinimumOperationsToMakeAUniValueGrid {

    public static void main(String[] args) {
        check(new int[][]{{2, 4}, {6, 8}}, 2, 4);
        check(new int[][]{{1, 5}, {2, 3}}, 1, 5);
        check(new int[][]{{1, 2}, {3, 4}}, 2, -1);
        check(new int[][]{{529, 529, 989}, {989, 529, 345}, {989, 805, 69}}, 92, 25);
        check(new int[][]{{931, 128}, {639, 712}}, 73, 12);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid.
     * This solution creates a linearized array out of the grid, checks that each cell has the same modulo
     * with x and calculates the sum of the absolute values of the array elements, after subtracting the median.
     * Time complexity is O(m*n*log(m*n)) where m is the number of rows and n is the number of columns in the grid.
     *
     * @param grid
     * @param x
     * @return
     */
    public static int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] linearized = new int[m * n];
        int expectedMod = grid[0][0] % x;
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] % x != expectedMod) {
                    return -1;
                }
                linearized[index++] = grid[i][j];
            }
        }
        Arrays.sort(linearized);
        int median = linearized[linearized.length / 2];
        int result = 0;
        for (int i = 0; i < linearized.length; i++) {
            result += Math.abs(linearized[i] - median);
        }
        return result / x;
    }

    private static void check(int[][] grid, int x, int expected) {
        System.out.println("grid is: ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("x is: " + x);
        System.out.println("expected is: " + expected);
        int minOperations = minOperations(grid, x); // Calls your implementation
        System.out.println("minOperations is: " + minOperations);
    }
}
