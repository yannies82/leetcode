package leetcode.graphgeneral;

import java.util.Arrays;

public class MaximumNumberOfFishInAGrid {

	public static void main(String[] args) {
		check(new int[][] { { 0, 2, 1, 0 }, { 4, 0, 0, 3 }, { 1, 0, 0, 4 }, { 0, 3, 2, 0 } }, 7);
		check(new int[][] { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 1 } }, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-number-of-fish-in-a-grid. This solution
	 * uses DFS to calculate the island with the most fish. It alters the original
	 * grid, eventually modifying all cells to be 0. Time complexity is O(m*n) where
	 * m is the number of rows and n is the number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int findMaxFish(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		// keeps count of the different islands
		int max = 0;
		// traverse all nodes
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// for every node that is not 0 that is not visited yet
				// perform DFS to find the number of fish, then update max
				if (grid[i][j] > 0 && !visited[i][j]) {
					int current = performDfsRecursive(grid, i, j);
					max = Math.max(max, current);
				}
			}
		}
		return max;
	}

	private static int performDfsRecursive(int[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
			return 0;
		}
		int current = grid[i][j];
		grid[i][j] = 0;
		return current + performDfsRecursive(grid, i - 1, j) + performDfsRecursive(grid, i + 1, j)
				+ performDfsRecursive(grid, i, j - 1) + performDfsRecursive(grid, i, j + 1);
	}

	/**
	 * This solution uses DFS to calculate the island with the most fish. It does
	 * not modify the initial grid. Time complexity is O(m*n) where m is the number
	 * of rows and n is the number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int findMaxFish2(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		// keeps count of the different islands
		int max = 0;
		// traverse all nodes
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// for every node that is not 0 that is not visited yet
				// perform DFS to find the number of fish, then update max
				if (grid[i][j] > 0 && !visited[i][j]) {
					int current = performDfsRecursive(grid, visited, i, j);
					max = Math.max(max, current);
				}
			}
		}
		return max;
	}

	private static int performDfsRecursive(int[][] grid, boolean[][] visited, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
			return 0;
		}
		visited[i][j] = true;
		return grid[i][j] + performDfsRecursive(grid, visited, i - 1, j) + performDfsRecursive(grid, visited, i + 1, j)
				+ performDfsRecursive(grid, visited, i, j - 1) + performDfsRecursive(grid, visited, i, j + 1);
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int findMaxFish = findMaxFish(grid); // Calls your implementation
		System.out.println("findMaxFish is: " + findMaxFish);
	}
}
