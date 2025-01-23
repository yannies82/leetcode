package leetcode.matrix;

import java.util.Arrays;

public class CountServersThatCommunicate {

	public static void main(String[] args) {
		check(new int[][] { { 1, 0 }, { 0, 1 } }, 0);
		check(new int[][] { { 1, 0 }, { 1, 1 } }, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-servers-that-communicate. Time complexity
	 * is O(m*n) where m is the number of rows and n is the number of columns in the
	 * grid.
	 * 
	 * 
	 * @param grid
	 * @return
	 */
	public static int countServers(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// keeps count of servers per row
		int[] rowFrequency = new int[rowCount];
		// keeps count of servers per column
		int[] columnFrequency = new int[columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				rowFrequency[i] += grid[i][j];
				columnFrequency[j] += grid[i][j];
			}
		}
		int total = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// count each server if other servers exist in the same row or column
				if (grid[i][j] == 1 && (rowFrequency[i] > 1 || columnFrequency[j] > 1)) {
					total++;
				}
			}
		}
		return total;
	}

	/**
	 * Alternative solution. Time complexity is O(m*n) where m is the number of rows
	 * and n is the number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int countServers2(int[][] grid) {
		// keeps count of servers per row
		int[] rowFrequency = new int[grid.length];
		// keeps count of servers per column
		int[] columnFrequency = new int[grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				rowFrequency[i] += grid[i][j];
				columnFrequency[j] += grid[i][j];
			}
		}
		int total = 0;
		// keeps servers per column which have been double counted
		int[] columnFrequencyOffset = new int[grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			if (rowFrequency[i] > 1) {
				// if more than one servers exist in the same row, add them to the total
				total += rowFrequency[i];
				// mark double counted servers in all columns which have more than two servers
				for (int j = 0; j < grid[0].length; j++) {
					if (columnFrequency[j] > 1 && grid[i][j] == 1) {
						columnFrequencyOffset[j]++;
					}
				}
			}
		}
		for (int i = 0; i < grid[0].length; i++) {
			// if more than one servers exist in the same column, add them to the total
			// after subtracting the servers that have already been counted
			if (columnFrequency[i] > 1) {
				total += columnFrequency[i] - columnFrequencyOffset[i];
			}
		}
		return total;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int countServers = countServers(grid); // Calls your implementation
		System.out.println("countServers is: " + countServers);
	}
}
