package leetcode.matrix;

import java.util.Arrays;

public class SetMatrixZeroes {

	public static void main(String[] args) {
		check(new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } },
				new int[][] { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } });
		check(new int[][] { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } },
				new int[][] { { 0, 0, 0, 0 }, { 0, 4, 5, 0 }, { 0, 3, 1, 0 } });
		check(new int[][] { { 1, 2, 3, 4 }, { 5, 0, 7, 8 }, { 0, 10, 11, 12 }, { 13, 14, 15, 0 } },
				new int[][] { { 0, 0, 3, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/set-matrix-zeroes. This
	 * solution searches all positions except for first column. If the position i, j
	 * has a 0 then the positions i, 0 and 0, j are set to 0. It then traverses all
	 * positions except for first row and first column and sets all i, j positions
	 * to 0 if positions i, 0 or 0, j are 0. Time complexity is O(m * n) where m is
	 * the number of rows and n is the number of columns.
	 * 
	 * @param matrix
	 */
	public static void setZeroes(int[][] matrix) {
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		int temp = 1;
		// temp should be 0 if the first column contains a 0, should be 1 otherwise
		for (int i = 0; i < rowCount; i++) {
			if (matrix[i][0] == 0) {
				temp = 0;
				break;
			}
		}
		// traverse all positions exxcept for first column
		// if position i, j is 0 set positions i, 0 and 0, j to 0
		for (int i = 0; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		// traverse all positions except for first row and first column
		// if positions i, 0 or 0, j are 0 set position i, j to 0
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		// if position 0, 0 is 0 set first row positions to 0
		for (int i = 0; i < columnCount; i++) {
			if (matrix[0][0] == 0) {
				matrix[0][i] = 0;
			}
		}
		// if temp is 0 set first column positions to 0
		for (int i = 0; i < rowCount; i++) {
			if (temp == 0) {
				matrix[i][0] = 0;
			}
		}
	}

	/**
	 * Alternate solution, simpler and possibly faster but uses O(m+n) extra space
	 * instead of O(1). Time complexity is O(m * n) where m is the number of rows
	 * and n is the number of columns.
	 * 
	 * @param matrix
	 */
	public static void setZeroes2(int[][] matrix) {
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		boolean[] rowFlags = new boolean[rowCount];
		boolean[] columnFlags = new boolean[columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (matrix[i][j] == 0) {
					rowFlags[i] = true;
					columnFlags[j] = true;
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (rowFlags[i] || columnFlags[j]) {
					matrix[i][j] = 0;
				}
			}
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
		setZeroes(matrix); // Calls your implementation
		System.out.println("setZeroes is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
}
