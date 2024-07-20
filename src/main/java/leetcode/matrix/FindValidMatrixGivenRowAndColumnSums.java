package leetcode.matrix;

import java.util.Arrays;

public class FindValidMatrixGivenRowAndColumnSums {

	public static void main(String[] args) {
		check(new int[] { 3, 8 }, new int[] { 4, 7 }, new int[][] { { 3, 0 }, { 1, 7 } });
		check(new int[] { 5, 7, 10 }, new int[] { 8, 6, 8 }, new int[][] { { 5, 0, 0 }, { 3, 4, 0 }, { 0, 2, 8 } });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums.
	 * This solution fills the result matrix using the min value of the rowSum and
	 * colSum values for each array position and advances the indexes accordingly.
	 * Time complexity is O(m * n) where m is the number of rows and n is the number
	 * of columns.
	 * 
	 * @param rowSum
	 * @param colSum
	 * @return
	 */
	public static int[][] restoreMatrix(int[] rowSum, int[] colSum) {
		int rowCount = rowSum.length;
		int columnCount = colSum.length;
		int lastRowIndex = rowCount - 1;
		int lastColumnIndex = columnCount - 1;
		// start populating the result array from position 0, 0
		int rowIndex = 0;
		int columnIndex = 0;
		int[][] result = new int[rowCount][columnCount];
		// populate the array until we reach the last row or column
		while (rowIndex < lastRowIndex && columnIndex < lastColumnIndex) {
			// get the min of the rowSum and colSum for this position and set as the array
			// value, also subtract value from rowSum and colSum
			int nextValue = Math.min(rowSum[rowIndex], colSum[columnIndex]);
			rowSum[rowIndex] -= nextValue;
			colSum[columnIndex] -= nextValue;
			result[rowIndex][columnIndex] = nextValue;
			if (rowSum[rowIndex] == 0) {
				// the full rowSum has been set to this position, this row cannot contain any
				// more sums, advance row index
				rowIndex++;
			}
			if (colSum[columnIndex] == 0) {
				// the full colSum has been set to this position, this column cannot contain any
				// more sums, advance column index
				columnIndex++;
			}
		}
		if (rowIndex == lastRowIndex) {
			// set remaining colSum values to the last row of the array
			for (int i = columnIndex; i < columnCount; i++) {
				result[rowIndex][i] = colSum[i];
			}
		} else {
			// set remaining rowSum values to the last column of the array
			for (int i = rowIndex; i < rowCount; i++) {
				result[i][columnIndex] = rowSum[i];
			}
		}
		return result;
	}

	private static void check(int[] rowSum, int[] colSum, int[][] expected) {
		System.out.println("rowSum is: " + Arrays.toString(rowSum));
		System.out.println("colSum is: " + Arrays.toString(colSum));
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] restoreMatrix = restoreMatrix(rowSum, colSum); // Calls your implementation
		System.out.println("restoreMatrix is: ");
		for (int i = 0; i < restoreMatrix.length; i++) {
			System.out.println(Arrays.toString(restoreMatrix[i]));
		}
	}
}
