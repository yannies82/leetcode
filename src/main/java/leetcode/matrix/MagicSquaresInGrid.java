package leetcode.matrix;

import java.util.Arrays;

public class MagicSquaresInGrid {

	public static void main(String[] args) {
		int[][] board1 = { { 4, 3, 8, 4 }, { 9, 5, 1, 9 }, { 2, 7, 6, 2 } };
		check(board1, 1);
		int[][] board2 = { { 8 } };
		check(board2, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/magic-squares-in-grid. Time
	 * complexity is O(m * n) where n is the number of rows and m is the number of
	 * columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int numMagicSquaresInside(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		if (rows < 3 || cols < 3) {
			return 0;
		}
		int result = 0;
		int rowLimit = rows - 2;
		int colLimit = cols - 2;
		for (int i = 0; i < rowLimit; i++) {
			for (int j = 0; j < colLimit; j++) {
				result += checkValid(grid, i, j);
			}
		}
		return result;
	}

	private static int checkValid(int[][] grid, int row, int col) {
		boolean[] found = new boolean[10];
		int[] colSum = new int[3];
		int rowLimit = row + 3;
		int colLimit = col + 3;
		for (int i = row; i < rowLimit; i++) {
			int rowSum = 0;
			for (int j = col; j < colLimit; j++) {
				int num = grid[i][j];
				// check number range and duplication
				if (num < 1 || num > 9 || found[num]) {
					return 0;
				}
				found[num] = true;
				rowSum += num;
				colSum[j - col] += num;
			}
			// check row sums
			if (rowSum != 15) {
				return 0;
			}
		}
		// check column sums
		for (int i = 0; i < colSum.length; i++) {
			if (colSum[i] != 15) {
				return 0;
			}
		}
		// check diagonals
		if (grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2] != 15
				|| grid[row][col + 2] + grid[row + 1][col + 1] + grid[row + 2][col] != 15) {
			return 0;
		}
		return 1;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int numMagicSquaresInside = numMagicSquaresInside(grid); // Calls your implementation
		System.out.println("numMagicSquaresInside is: " + numMagicSquaresInside);
	}
}
