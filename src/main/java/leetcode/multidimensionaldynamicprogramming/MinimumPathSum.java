package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class MinimumPathSum {

	public static void main(String[] args) {
		check(new int[][] { { 0, 0 }, { 0, 0 } }, 0);
		check(new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } }, 7);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-path-sum. This
	 * solution uses top down dynamic programming to store the subproblem solutions
	 * and reuse them in order to produce the final problem solution. Time
	 * complexity is O(m*n) where m is the grid row count and n is the grid column
	 * count.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minPathSum(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// early exit for 1X1 grid
		if (rowCount == 1 && columnCount == 1) {
			return grid[0][0];
		}
		// this 2D array stores the subproblem solutions
		// initialize with negative value so that we know which subproblems
		// have not been calculated yet
		int[][] dpArray = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			Arrays.fill(dpArray[i], -1);
		}
		// solution for the first element of the 2D grid
		dpArray[0][0] = grid[0][0];
		// recursively calculate the solution for the last array element
		return dp(grid, rowCount - 1, columnCount - 1, dpArray);
	}

	private static int dp(int[][] grid, int i, int j, int[][] dpArray) {
		// early exit if index is out of range
		if (i < 0 || j < 0) {
			return Integer.MAX_VALUE;
		}
		// if the subproblem has been solved return solution
		if (dpArray[i][j] >= 0) {
			return dpArray[i][j];
		}
		// recursively solve subproblem and return solution
		dpArray[i][j] = Math.min(dp(grid, i, j - 1, dpArray), dp(grid, i - 1, j, dpArray)) + grid[i][j];
		return dpArray[i][j];
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int minPathSum = minPathSum(grid); // Calls your implementation
		System.out.println("minPathSum is: " + minPathSum);
	}

}
