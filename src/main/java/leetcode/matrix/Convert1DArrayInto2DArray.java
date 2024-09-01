package leetcode.matrix;

import java.util.Arrays;

public class Convert1DArrayInto2DArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4 }, 2, 2, new int[][] { { 1, 2 }, { 3, 4 } });
		check(new int[] { 1, 2, 3 }, 1, 3, new int[][] { { 1, 2, 3 } });
		check(new int[] { 1, 2 }, 1, 1, new int[][] {});
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/convert-1d-array-into-2d-array. Time complexity
	 * is O(m*n).
	 * 
	 * @param original
	 * @param m
	 * @param n
	 * @return
	 */
	public static int[][] construct2DArray(int[] original, int m, int n) {
		if (original.length != m * n) {
			return new int[][] {};
		}
		int[][] result = new int[m][n];
		int k = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = original[k++];
			}
		}
		return result;
	}

	public static int[][] construct2DArray2(int[] original, int m, int n) {
		if (original.length != m * n) {
			return new int[][] {};
		}
		int[][] result = new int[m][n];
		int row = -1;
		for (int i = 0; i < original.length; i++) {
			int column = i % n;
			if (column == 0) {
				row++;
			}
			result[row][column] = original[i];
		}
		return result;
	}

	private static void check(int[] original, int m, int n, int[][] expected) {
		System.out.println("board is: " + Arrays.toString(original));
		System.out.println("m is: " + m);
		System.out.println("n is: " + n);
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] construct2DArray = construct2DArray(original, m, n); // Calls your implementation
		System.out.println("construct2DArray is: ");
		for (int i = 0; i < construct2DArray.length; i++) {
			System.out.println(Arrays.toString(construct2DArray[i]));
		}
	}
}
