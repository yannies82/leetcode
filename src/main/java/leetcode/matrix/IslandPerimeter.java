package leetcode.matrix;

import java.util.Arrays;

public class IslandPerimeter {

	public static void main(String[] args) {
		check(new int[][] { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } }, 16);
		check(new int[][] { { 1 } }, 4);
		check(new int[][] { { 1, 0 } }, 4);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/island-perimeter. This
	 * solution traverses the grid and determines the perimeter of each cell by
	 * checking the neighbouring ones. Time complexity is O(m * n) where m is the
	 * number of rows and n is the number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int islandPerimeter(int[][] grid) {
		int numRows = grid.length;
		int numColumns = grid[0].length;
		int perimeter = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (grid[i][j] == 1) {
					int cellPerimeter = 4;
					// subtract the value of each neighbouring cell
					// from the cell perimeter
					if (i > 0) {
						cellPerimeter -= grid[i - 1][j];
					}
					if (i < numRows - 1) {
						cellPerimeter -= grid[i + 1][j];
					}
					if (j > 0) {
						cellPerimeter -= grid[i][j - 1];
					}
					if (j < numColumns - 1) {
						cellPerimeter -= grid[i][j + 1];
					}
					perimeter += cellPerimeter;
				}
			}
		}
		return perimeter;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int islandPerimeter = islandPerimeter(grid);
		System.out.println("islandPerimeter is: " + islandPerimeter);
	}
}
