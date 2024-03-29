package leetcode.matrix;

import java.util.Arrays;

public class RotateImage {

	public static void main(String[] args) {
		check(new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } },
				new int[][] { { 15, 13, 2, 5 }, { 14, 3, 4, 1 }, { 12, 6, 8, 9 }, { 16, 7, 10, 11 } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/rotate-image. This solution
	 * rotates the outer layer of the matrix then moves to the next layer until
	 * there are no positions left. Time complexity is O(n^2) where n is the length
	 * of the side of the matrix.
	 * 
	 * @param matrix
	 */
	public static void rotate(int[][] matrix) {
		int length = matrix.length;
		int start = 0;
		int end = length - 1;
		// rotate one layer for each iteration
		while (start < end) {
			// swap first row with first column
			for (int i = start; i <= end; i++) {
				int temp = matrix[i][start];
				matrix[i][start] = matrix[start][end - i + start];
				matrix[start][end - i + start] = temp;
			}
			// swap first column with last row
			for (int i = start + 1; i <= end; i++) {
				int temp = matrix[end][i];
				matrix[end][i] = matrix[i][start];
				matrix[i][start] = temp;
			}
			// swap last column with last row
			for (int i = start + 1; i < end; i++) {
				int temp = matrix[i][end];
				matrix[i][end] = matrix[end][end - i + start];
				matrix[end][end - i + start] = temp;
			}
			// move to the next layer
			start++;
			end--;
		}
	}

	private static void check(int[][] matrix, int[][] expectedMatrix) {
		System.out.println("matrix is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println("expectedMatrix is: ");
		for (int i = 0; i < expectedMatrix.length; i++) {
			System.out.println(Arrays.toString(expectedMatrix[i]));
		}
		rotate(matrix); // Calls your implementation
		System.out.println("rotate is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
}
