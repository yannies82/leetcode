package leetcode.graphgeneral;

import java.util.Arrays;

public class CountSubIslands {

	public static void main(String[] args) {
		int[][] grid1 = new int[][] { { 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0 },
				{ 1, 1, 0, 1, 1 } };
		int[][] grid2 = new int[][] { { 1, 1, 1, 0, 0 }, { 0, 0, 1, 1, 1 }, { 0, 1, 0, 0, 0 }, { 1, 0, 1, 1, 0 },
				{ 0, 1, 0, 1, 0 } };
		check(grid1, grid2, 3);
		grid1 = new int[][] { { 1, 0, 1, 0, 1 }, { 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 },
				{ 1, 0, 1, 0, 1 } };
		grid2 = new int[][] { { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 },
				{ 1, 0, 0, 0, 1 } };
		check(grid1, grid2, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/count-sub-islands. Solution
	 * with recursive DFS graph traversal. Time complexity is O(m * n) where m is
	 * the number of rows and n is the number of columns in the grid.
	 * 
	 * @param grid1
	 * @param grid2
	 * @return
	 */
	public static int countSubIslands(int[][] grid1, int[][] grid2) {
		int rowCount = grid2.length;
		int columnCount = grid2[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		// keeps count of the different islands
		int count = 0;
		// traverse all nodes and find islands of grid2
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// for every 1 node that is not visited yet
				// perform DFS in order to mark adjacent 1 nodes as visited
				// and increase the islands count if all island cells of grid2 also have
				// a value of 1 in grid1
				if (grid2[i][j] == 1 && !visited[i][j] && performDfsRecursive(grid2, grid1, visited, i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean performDfsRecursive(int[][] grid2, int[][] grid1, boolean[][] visited, int i, int j) {
		// mark current node as visited
		visited[i][j] = true;
		// initialize result with true if the respective cell of grid1 is 1
		boolean result = grid1[i][j] == 1;
		// do not short circuit when calculating the result, we want to mark all
		// cells of the island as visited
		// perform DFS on the above node if it contains 1 and is not visited yet
		if (i > 0 && grid2[i - 1][j] == 1 && !visited[i - 1][j]) {
			result = performDfsRecursive(grid2, grid1, visited, i - 1, j) && result;
		}
		// perform DFS on the below node if it contains 1 and is not visited yet
		if (i < grid2.length - 1 && grid2[i + 1][j] == 1 && !visited[i + 1][j]) {
			result = performDfsRecursive(grid2, grid1, visited, i + 1, j) && result;
		}
		// perform DFS on the left node if it contains 1 and is not visited yet
		if (j > 0 && grid2[i][j - 1] == 1 && !visited[i][j - 1]) {
			result = performDfsRecursive(grid2, grid1, visited, i, j - 1) && result;
		}
		// perform DFS on the right node if it contains 1 and is not visited yet
		if (j < grid2[0].length - 1 && grid2[i][j + 1] == 1 && !visited[i][j + 1]) {
			result = performDfsRecursive(grid2, grid1, visited, i, j + 1) && result;
		}
		return result;
	}

	private static void check(int[][] grid1, int[][] grid2, int expected) {
		System.out.println("grid1 is: ");
		for (int i = 0; i < grid1.length; i++) {
			System.out.println(Arrays.toString(grid1[i]));
		}
		System.out.println("grid2 is: ");
		for (int i = 0; i < grid2.length; i++) {
			System.out.println(Arrays.toString(grid2[i]));
		}
		System.out.println("expected is: " + expected);
		int countSubIslands = countSubIslands(grid1, grid2); // Calls your implementation
		System.out.println("countSubIslands is: " + countSubIslands);
	}
}
