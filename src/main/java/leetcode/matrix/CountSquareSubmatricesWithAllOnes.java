package leetcode.matrix;

public class CountSquareSubmatricesWithAllOnes {

	public static void main(String[] args) {
		int[][] matrix1 = { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 } };
		check(matrix1, 15);
		int[][] matrix2 = { { 1, 0, 1 }, { 1, 1, 0 }, { 1, 1, 0 } };
		check(matrix2, 7);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-square-submatrices-with-all-ones. This
	 * solution keeps an array with the counts of all squares having position i,j as
	 * their lowest right and adds all counts to get the final result. Time
	 * complexity is O(n*m) where n is the number of rows and n is the number of
	 * columns in the matrix array.
	 * 
	 * @param matrix
	 * @return
	 */
	public static int countSquares(int[][] matrix) {
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		// keeps the count of squares that have i,j as the lowest right position
		int[][] squareCount = new int[rowCount][columnCount];
		int totalSquareCount = 0;
		// count first column squares
		for (int i = 0; i < rowCount; i++) {
			squareCount[i][0] = matrix[i][0];
			totalSquareCount += squareCount[i][0];
		}
		// count first row squares
		for (int i = 1; i < columnCount; i++) {
			squareCount[0][i] = matrix[0][i];
			totalSquareCount += squareCount[0][i];
		}
		// iterate all other positions
		for (int i = 1; i < rowCount; i++) {
			int prevI = i - 1;
			for (int j = 1; j < columnCount; j++) {
				if (matrix[i][j] == 1) {
					int prevJ = j - 1;
					// count the number of squares which have position i,j as their lowest right
					squareCount[i][j] = 1 + Math.min(Math.min(squareCount[i][prevJ], squareCount[prevI][j]),
							squareCount[prevI][prevJ]);
					totalSquareCount += squareCount[i][j];
				}
			}
		}
		return totalSquareCount;
	}

	private static void check(int[][] matrix, int expected) {
		System.out.println("matrix is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(matrix[i]);
		}
		System.out.println("expected is: " + expected);
		int maximalSquare = countSquares(matrix); // Calls your implementation
		System.out.println("maximalSquare is: " + maximalSquare);
	}

}
