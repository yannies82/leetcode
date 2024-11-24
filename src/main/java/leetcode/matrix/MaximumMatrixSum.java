package leetcode.matrix;

import java.util.Arrays;

public class MaximumMatrixSum {

	public static void main(String[] args) {
		int[][] board1 = { { 1, -1 }, { -1, 1 } };
		check(board1, 4);
		int[][] board2 = { { 1, 2, 3 }, { -1, -2, -3 }, { 1, 2, 3 } };
		check(board2, 16);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-matrix-sum. This
	 * solution counts the total sum of the absolute values in the array and the
	 * number of negative values. If the number of negative values is odd, it
	 * subtracts the smaller absolute value in the array from the sum to calculate
	 * the final result. Time complexity is O(m*n) where m is the number of rows and
	 * n is the number of columns in matrix.
	 * 
	 * @param matrix
	 * @return
	 */
	public static long maxMatrixSum(int[][] matrix) {
		int numOfNegative = 0;
		int minAbs = Integer.MAX_VALUE;
		long sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				numOfNegative += matrix[i][j] >>> 31;
				int abs = Math.abs(matrix[i][j]);
				sum += abs;
				minAbs = Math.min(minAbs, abs);
			}
		}
		if ((numOfNegative & 1) == 1) {
			sum -= minAbs << 1;
		}
		return sum;
	}

	private static void check(int[][] matrix, int expected) {
		System.out.println("matrix is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println("expected is: " + expected);
		long maxMatrixSum = maxMatrixSum(matrix); // Calls your implementation
		System.out.println("maxMatrixSum is: " + maxMatrixSum);
	}
}
