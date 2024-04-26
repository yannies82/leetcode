package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class MinimumFallingPathSum2 {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, 13);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-falling-path-sum-ii.
	 * This solution uses bottom up dynamic programming, calculating the solutions
	 * for each row when each element is selected. The final answer is the min of
	 * the last row solutions. Time complexity is O(n^2) where n is the number of
	 * rows and columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minFallingPathSum(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		if (rowCount == 1) {
			// early exit if the grid has a single element
			return grid[0][0];
		}
		// this is the array which keeps the solutions to the subproblems
		// dpArray[i][j] contains the best solution for row i when the jth element
		// is selected
		int[][] dpArray = new int[rowCount][columnCount];
		// keep track of the minDp and secondMinDp values for the current row
		// as well as the index of the minDp value for the current row
		int minDp = 0, secondMinDp = 0, minDpIndex = -1;
		// iterate all rows
		for (int i = 0; i < rowCount; i++) {
			// keep values of the previous row before calculating new ones for the current
			// row
			int rowMinDp = minDp;
			int rowSecondMinDp = secondMinDp;
			int rowMinIndex = minDpIndex;
			// initialize values for the new row
			minDp = 1000000000;
			secondMinDp = 1000000000;
			minDpIndex = -1;
			// iterate all columns of the current row
			for (int j = 0; j < columnCount; j++) {
				if (j == rowMinIndex) {
					// if this is the same column as the one with minDp from the previous row
					// we should add the secondMinDp instead to the current grid value
					dpArray[i][j] = rowSecondMinDp + grid[i][j];
				} else {
					// this is a different column from the one with minDp from the previous row
					// add minDp to the current grid value
					dpArray[i][j] = rowMinDp + grid[i][j];
				}
				if (dpArray[i][j] < minDp) {
					// the dpArray[i][j] value is a new min for the current row
					// update minDp, minDpIndex and secondMinDp accordingly
					secondMinDp = minDp;
					minDp = dpArray[i][j];
					minDpIndex = j;
				} else if (dpArray[i][j] < secondMinDp) {
					// the dpArray[i][j] value is not less than minDp but it is
					// less than secondMinDp, so update it accordingly
					secondMinDp = dpArray[i][j];
				}
			}
		}
		// minDp has the min dpArray value for the last row, so it is the solution to
		// our problem
		return minDp;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int minFallingPathSum = minFallingPathSum(grid); // Calls your implementation
		System.out.println("minFallingPathSum is: " + minFallingPathSum);
	}

}
