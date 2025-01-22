package leetcode.matrix;

import java.util.Arrays;

public class GridGame {

	public static void main(String[] args) {
		check(new int[][] { { 1, 3, 1, 15 }, { 1, 3, 3, 1 } }, 7L);
		check(new int[][] { { 20, 3, 20, 17, 2, 12, 15, 17, 4, 15 }, { 20, 10, 13, 14, 15, 5, 2, 3, 14, 3 } }, 63L);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/grid-game. This solution
	 * considers the fact that the first robot allows the second robot to choose one
	 * of the two sections with numbers. The best route for the first robot is when
	 * the max sum of the cells of each of the two sections is minimum. Time
	 * complexity is O(n) where n is the number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static long gridGame(int[][] grid) {
		long firstRowSum = 0;
		for (int i = 1; i < grid[0].length; i++) {
			firstRowSum += grid[0][i];
		}
		long secondRowSum = 0;
		long result = Long.MAX_VALUE;
		for (int i = 1; i < grid[0].length; i++) {
			long newResult = Math.max(firstRowSum, secondRowSum);
			result = Math.min(result, newResult);
			firstRowSum -= grid[0][i];
			secondRowSum += grid[1][i - 1];
		}
		long newResult = Math.max(firstRowSum, secondRowSum);
		result = Math.min(result, newResult);
		return result;
	}

	private static void check(int[][] grid, long expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		long gridGame = gridGame(grid);
		System.out.println("gridGame is: " + gridGame);
	}
}
