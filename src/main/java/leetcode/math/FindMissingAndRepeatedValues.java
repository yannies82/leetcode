package leetcode.math;

import java.util.Arrays;

public class FindMissingAndRepeatedValues {

	public static void main(String[] args) {
		check(new int[][] { { 1, 3 }, { 2, 2 } }, new int[] { 2, 4 });
		check(new int[][] { { 9, 1, 7 }, { 8, 9, 2 }, { 3, 4, 6 } }, new int[] { 9, 5 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-missing-and-repeated-values. This solution
	 * calculates the sum and sum of the squares of all numbers in the grid and
	 * compares them with the expected sums to find the duplicate and the missing
	 * numbers. Time complexity is O(n^2) where n is the number of rows (and
	 * columns) in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int[] findMissingAndRepeatedValues(int[][] grid) {
		int n = grid.length;
		int squareN = n * n;
		int expectedSum = (squareN * (squareN + 1)) / 2;
		long expectedSquareSum = ((long) squareN * (squareN + 1) * (2 * squareN + 1)) / 6;
		int sum = 0;
		long squareSum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += grid[i][j];
				squareSum += grid[i][j] * grid[i][j];
			}
		}
		int diffSum = sum - expectedSum;
		long diffSquareSum = (squareSum - expectedSquareSum) / diffSum;
		long duplicate = (diffSum + diffSquareSum) / 2;
		long missing = duplicate - diffSum;
		return new int[] { (int) duplicate, (int) missing };
	}

	/**
	 * Alternative solution. Time complexity is O(n^2) where n is the number of rows
	 * (and columns) in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int[] findMissingAndRepeatedValues2(int[][] grid) {
		int expectedSum = 0;
		int realSum = 0;
		int duplicate = 0;
		boolean[] exists = new boolean[grid.length * grid[0].length + 1];
		for (int i = 0; i < grid.length; i++) {
			int offset = i * grid[0].length;
			for (int j = 0; j < grid[0].length; j++) {
				expectedSum += offset + j + 1;
				realSum += grid[i][j];
				if (exists[grid[i][j]]) {
					duplicate = grid[i][j];
				}
				exists[grid[i][j]] = true;
			}
		}
		int diff = expectedSum - realSum;
		return new int[] { duplicate, duplicate + diff };
	}

	private static void check(int[][] grid, int[] expected) {
		System.out.println("grid is: ");
		for (int[] row : grid) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] findMissingAndRepeatedValues = findMissingAndRepeatedValues(grid); // Calls your implementation
		System.out.println("findMissingAndRepeatedValues is: " + Arrays.toString(findMissingAndRepeatedValues));
	}
}
