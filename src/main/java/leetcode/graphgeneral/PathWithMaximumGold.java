package leetcode.graphgeneral;

import java.util.Arrays;

public class PathWithMaximumGold {

	public static void main(String[] args) {
		int[][] grid1 = { { 0, 6, 0 }, { 5, 8, 7 }, { 0, 9, 0 } };
		check(grid1, 24);
		int[][] grid2 = { { 1, 0, 7 }, { 2, 0, 6 }, { 3, 4, 5 }, { 0, 3, 0 }, { 9, 0, 20 } };
		check(grid2, 28);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/path-with-maximum-gold. This
	 * solution performs DFS from every position of the grid in order to find the
	 * max possible path. It uses backtracking in order to traverse the positions
	 * again from different starting nodes. Time complexity (worst case upper bound)
	 * is O(4^(m*n)) because for each position there are theoretically 4 adjacent
	 * positions that can be checked. However the problem constraints (at most 25
	 * cells with gold in the grid) imply that the runtime is much faster.
	 * 
	 * @param grid
	 * @return
	 */
	public static int getMaximumGold(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		int maxGold = 0;
		// traverse all nodes
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// perform DFS from every non zero node that is not visited yet
				// in order to check all possible paths for traversing the gold
				// so that we can find the maximum path
				if (grid[i][j] > 0) {
					int totalGold = performDfsRecursive(grid, visited, i, j, 0);
					maxGold = Math.max(maxGold, totalGold);
				}
			}
		}
		return maxGold;
	}

	private static int performDfsRecursive(int[][] grid, boolean[][] visited, int i, int j, int gold) {
		// mark current node as visited
		visited[i][j] = true;
		// add current node gold to the gold sum
		int currentGold = gold + grid[i][j];
		int maxGold = currentGold;
		// perform DFS on the above node if it contains gold and is not visited yet
		if (i > 0 && grid[i - 1][j] > 0 && !visited[i - 1][j]) {
			int newGold = performDfsRecursive(grid, visited, i - 1, j, currentGold);
			maxGold = Math.max(maxGold, newGold);
		}
		// perform DFS on the below node if it contains gold and is not visited yet
		if (i < grid.length - 1 && grid[i + 1][j] > 0 && !visited[i + 1][j]) {
			int newGold = performDfsRecursive(grid, visited, i + 1, j, currentGold);
			maxGold = Math.max(maxGold, newGold);
		}
		// perform DFS on the left node if it contains gold and is not visited yet
		if (j > 0 && grid[i][j - 1] > 0 && !visited[i][j - 1]) {
			int newGold = performDfsRecursive(grid, visited, i, j - 1, currentGold);
			maxGold = Math.max(maxGold, newGold);
		}
		// perform DFS on the right node if it contains gold and is not visited yet
		if (j < grid[0].length - 1 && grid[i][j + 1] > 0 && !visited[i][j + 1]) {
			int newGold = performDfsRecursive(grid, visited, i, j + 1, currentGold);
			maxGold = Math.max(maxGold, newGold);
		}
		// unmark current node from visited (backtrack) so that it can be traversed
		// again from other starting positions
		visited[i][j] = false;
		return maxGold;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int getMaximumGold = getMaximumGold(grid); // Calls your implementation
		System.out.println("getMaximumGold is: " + getMaximumGold);
	}
}
