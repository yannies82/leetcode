package leetcode.matrix;

import java.util.Arrays;

public class LargestLocalValuesInAMatrix {

	public static void main(String[] args) {
		check(new int[][] { { 9, 9, 8, 1 }, { 5, 6, 2, 6 }, { 8, 2, 6, 4 }, { 6, 2, 2, 2 } },
				new int[][] { { 9, 9 }, { 8, 6 } });
		check(new int[][] { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 2, 1, 1 }, { 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 } }, new int[][] { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/largest-local-values-in-a-matrix. This solution
	 * calculates the largest value for each one of the result array positions by
	 * comparing the values of all 9 adjacent positions of the grid array. Time
	 * complexity is O(n^2) where n is the number of rows in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int[][] largestLocal(int[][] grid) {
		int n = grid.length;
		int[][] result = new int[n - 2][n - 2];
		// iterate all positions of the result array
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				int max = 0;
				// calculate the max for each result array position by comparing
				// all 9 adjacent positions of the input grid
				for (int k = 0; k <= 2; k++) {
					int rowIndex = i + k;
					for (int l = 0; l <= 2; l++) {
						max = Math.max(max, grid[rowIndex][j + l]);
					}
				}
				result[i][j] = max;
			}
		}
		return result;
	}

	private static void check(int[][] grid, int[][] expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] largestLocal = largestLocal(grid); // Calls your implementation
		System.out.println("largestLocal is: ");
		for (int i = 0; i < largestLocal.length; i++) {
			System.out.println(Arrays.toString(largestLocal[i]));
		}
	}
}
