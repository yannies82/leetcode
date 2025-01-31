package leetcode.graphgeneral;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MakingALargeIsland {

	public static void main(String[] args) {
		check(new int[][] { { 1, 1 }, { 1, 0 } }, 4);
		check(new int[][] { { 0, 1 }, { 1, 0 } }, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/making-a-large-island. This
	 * solution finds the discrete islands and calculates their sizes while
	 * assigning a unique id to the cells of each discrete island. It then traverses
	 * all water cells and checks the maxSize if neighboring islands are connected.
	 * Time complexity is O(m*n) where m is the number of rows and n is the number
	 * of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int largestIsland(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		int numCells = rowCount * columnCount;
		// keeps track of assigned island ids
		int[][] islandIds = new int[rowCount][columnCount];
		// keeps track of island size per id
		int[] islandSizes = new int[(numCells >>> 1) + 2];
		int id = 1;
		// traverse all nodes to find islands and calculate their sizes
		int zeroCount = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				zeroCount += 1 - grid[i][j];
				if (grid[i][j] == 1 && islandIds[i][j] == 0) {
					islandSizes[id] = performDfsRecursive(grid, islandIds, i, j, id);
					id++;
				}
			}
		}
		if (zeroCount == 0) {
			// there are no water cells
			return numCells;
		}
		if (zeroCount == numCells) {
			// all cells are water cells
			return 1;
		}
		// traverse all nodes to find water cells and calculate aggregate size
		// of adjacent islands if they are connected
		int maxSize = 1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (grid[i][j] == 0) {
					int size = 1;
					Set<Integer> uniqueIds = new HashSet<>();
					if (i > 0) {
						uniqueIds.add(islandIds[i - 1][j]);
					}
					if (i < grid.length - 1) {
						uniqueIds.add(islandIds[i + 1][j]);
					}
					if (j > 0) {
						uniqueIds.add(islandIds[i][j - 1]);
					}
					if (j < grid[0].length - 1) {
						uniqueIds.add(islandIds[i][j + 1]);
					}
					for (int uniqueId : uniqueIds) {
						size += islandSizes[uniqueId];
					}
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		return maxSize;
	}

	private static int performDfsRecursive(int[][] grid, int[][] islandIds, int i, int j, int id) {
		if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == 0 || islandIds[i][j] > 0) {
			// cell is out of bounds or already visited
			return 0;
		}
		// mark current node as visited
		islandIds[i][j] = id;
		// calculate and return result
		return performDfsRecursive(grid, islandIds, i - 1, j, id) + performDfsRecursive(grid, islandIds, i + 1, j, id)
				+ performDfsRecursive(grid, islandIds, i, j - 1, id)
				+ performDfsRecursive(grid, islandIds, i, j + 1, id) + 1;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int largestIsland = largestIsland(grid); // Calls your implementation
		System.out.println("largestIsland is: " + largestIsland);
	}
}
