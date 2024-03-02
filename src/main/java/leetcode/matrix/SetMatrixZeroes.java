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

	public static void setZeroes(int[][] matrix) {
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		int temp = 1;
		for (int i = 0; i < rowCount; i++) {
			if (matrix[i][0] == 0) {
				temp = 0;
				break;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < columnCount; i++) {
			if (matrix[0][0] == 0) {
				matrix[0][i] = 0;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			if (temp == 0) {
				matrix[i][0] = 0;
			}
		}
	}

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

	// βασιλικη
	// γιαννης

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
