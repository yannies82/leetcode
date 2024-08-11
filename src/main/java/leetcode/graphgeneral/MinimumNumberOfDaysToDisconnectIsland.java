package leetcode.graphgeneral;

import java.util.Arrays;

public class MinimumNumberOfDaysToDisconnectIsland {

	public static void main(String[] args) {
		check(new int[][] { { 0, 1, 1 }, { 1, 1, 1 }, { 1, 1, 0 } }, 1);
		check(new int[][] { { 1, 1, 0, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1 } }, 1);
		check(new int[][] { { 1, 1, 0, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1 }, { 1, 1, 1, 1, 1 } }, 2);
		check(new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, 2);
		check(new int[][] { { 1, 1 } }, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island.
	 * This solution counts the number of islands. If it is different than 1 it
	 * means that the islands are already disconnected. If it is 1, it searches for
	 * candidate cells to turn from 0 to 1 and then count the number of islands
	 * again. If a cell is found that, after being switched to 0 the number of
	 * islands becomes 1, then 1 is returned, otherwise 2 is returned. Time
	 * complexity is O(m^2*n^2) where m is the number of rows and n is the number of
	 * columns.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minDays(int[][] grid) {
		// find the initial number of islands
		int numIslands = numIslands(grid);
		// early exit
		if (numIslands != 1) {
			return 0;
		}
		int rowCount = grid.length;
		int lastRow = rowCount - 1;
		int columnCount = grid[0].length;
		int lastColumn = columnCount - 1;
		// keeps count of the 1s
		int count = 0;
		boolean singleExists = false;
		// traverse all nodes
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (grid[i][j] == 1) {
					count++;
					if (singleExists) {
						continue;
					}
					// find number of diagonal, vertical and horizontal neighbors of the cell
					int numOfVerticalNeighbors = 0;
					int numOfHorizontalNeighbors = 0;
					int numOfDiagonalNeighbors = 0;
					if (i > 0 && grid[i - 1][j] == 1) {
						numOfVerticalNeighbors++;
						if (j > 0 && grid[i - 1][j - 1] == 1) {
							numOfDiagonalNeighbors++;
						}
						if (j < lastColumn && grid[i - 1][j + 1] == 1) {
							numOfDiagonalNeighbors++;
						}
					}
					if (i < lastRow && grid[i + 1][j] == 1) {
						numOfVerticalNeighbors++;
						if (j > 0 && grid[i + 1][j - 1] == 1) {
							numOfDiagonalNeighbors++;
						}
						if (j < lastColumn && grid[i + 1][j + 1] == 1) {
							numOfDiagonalNeighbors++;
						}
					}
					if (j > 0 && grid[i][j - 1] == 1) {
						numOfHorizontalNeighbors++;
					}
					if (j < lastColumn && grid[i][j + 1] == 1) {
						numOfHorizontalNeighbors++;
					}
					int numOfNeighbors = numOfVerticalNeighbors + numOfHorizontalNeighbors;
					if (numOfNeighbors == 1) {
						// the cell has a single neighbor, this means that we can cut it off from the
						// island by removing its single neighbor
						// do not search further
						singleExists = true;
					} else if ((numOfNeighbors == 2 && (numOfVerticalNeighbors == 0 || numOfHorizontalNeighbors == 0))
							|| (numOfNeighbors > 2 && numOfDiagonalNeighbors < 3)) {
						// the cell is a candidate for removal because it has no vertical neigbors or no
						// horizontal neighbors or less than 2 diagonal neighbors
						grid[i][j] = 0;
						int newIslands = numIslands(grid);
						if (newIslands > 1) {
							return 1;
						}
						grid[i][j] = 1;
					}
				}
			}
		}
		if (count == 1) {
			return 1;
		} else if (count == 2) {
			return 2;
		} else if (singleExists) {
			return 1;
		}
		return 2;
	}

	public static int numIslands(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		// keeps count of the different islands
		int count = 0;
		// traverse all nodes
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// for every '1' node that is not visited yet
				// increase the islands count and perform DFS
				// in order to mark adjacent '1' nodes as visited
				if (grid[i][j] == 1 && !visited[i][j]) {
					count++;
					performDfsRecursive(grid, visited, i, j);
				}
			}
		}
		return count;
	}

	private static void performDfsRecursive(int[][] grid, boolean[][] visited, int i, int j) {
		// mark current node as visited
		visited[i][j] = true;
		// perform DFS on the above node if it contains '1' and is not visited yet
		if (i > 0 && grid[i - 1][j] == 1 && !visited[i - 1][j]) {
			performDfsRecursive(grid, visited, i - 1, j);
		}
		// perform DFS on the below node if it contains '1' and is not visited yet
		if (i < grid.length - 1 && grid[i + 1][j] == 1 && !visited[i + 1][j]) {
			performDfsRecursive(grid, visited, i + 1, j);
		}
		// perform DFS on the left node if it contains '1' and is not visited yet
		if (j > 0 && grid[i][j - 1] == 1 && !visited[i][j - 1]) {
			performDfsRecursive(grid, visited, i, j - 1);
		}
		// perform DFS on the right node if it contains '1' and is not visited yet
		if (j < grid[0].length - 1 && grid[i][j + 1] == 1 && !visited[i][j + 1]) {
			performDfsRecursive(grid, visited, i, j + 1);
		}
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int minDays = minDays(grid); // Calls your implementation
		System.out.println("minDays is: " + minDays);
	}
}
