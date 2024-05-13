package leetcode.matrix;

import java.util.Arrays;

public class ScoreAfterFlippingMatrix {

	public static void main(String[] args) {
		check(new int[][] { { 0, 0, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 0 } }, 39);
		check(new int[][] { { 0 } }, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/score-after-flipping-matrix.
	 * This solution flips every row that does not start with 1, then flips every
	 * column with more 0s than 1s. Finally it calculates the sum of the binary
	 * numbers represented by each row. Time complexity is O(m*n) where m is the
	 * number of rows and n is the number of columns.
	 * 
	 * @param grid
	 * @return
	 */
	public static int matrixScore(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// iterate all rows
		for (int i = 0; i < rowCount; i++) {
			if (grid[i][0] == 0) {
				// flip every row that starts with a 0
				// so that all rows start with 1
				for (int j = 0; j < columnCount; j++) {
					grid[i][j] = 1 - grid[i][j];
				}
			}
		}
		// iterate all columns except for the first one
		for (int j = 1; j < columnCount; j++) {
			// calculate the sum of the column numbers
			int sum = 0;
			for (int i = 0; i < rowCount; i++) {
				sum += grid[i][j];
			}
			// if 2 * sum is less than the number of rows (there are more 0s than 1s)
			// then flip all row values
			if ((sum << 1) < rowCount) {
				for (int i = 0; i < rowCount; i++) {
					grid[i][j] = 1 - grid[i][j];
				}
			}
		}
		// iterate all rows and calculate the sum of the binary values
		int score = 0;
		for (int i = 0; i < rowCount; i++) {
			int rowScore = 0;
			// calculate the binary number represented by this row
			for (int j = 0; j < columnCount; j++) {
				rowScore = (rowScore << 1) + grid[i][j];
			}
			score += rowScore;
		}
		return score;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int matrixScore = matrixScore(grid); // Calls your implementation
		System.out.println("matrixScore is: " + matrixScore);
	}
}
