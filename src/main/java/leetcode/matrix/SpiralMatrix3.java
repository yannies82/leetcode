package leetcode.matrix;

import java.util.Arrays;

public class SpiralMatrix3 {

	public static void main(String[] args) {
		check(1, 4, 0, 0, new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } });
		check(5, 6, 1, 4,
				new int[][] { { 1, 4 }, { 1, 5 }, { 2, 5 }, { 2, 4 }, { 2, 3 }, { 1, 3 }, { 0, 3 }, { 0, 4 }, { 0, 5 },
						{ 3, 5 }, { 3, 4 }, { 3, 3 }, { 3, 2 }, { 2, 2 }, { 1, 2 }, { 0, 2 }, { 4, 5 }, { 4, 4 },
						{ 4, 3 }, { 4, 2 }, { 4, 1 }, { 3, 1 }, { 2, 1 }, { 1, 1 }, { 0, 1 }, { 4, 0 }, { 3, 0 },
						{ 2, 0 }, { 1, 0 }, { 0, 0 } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/spiral-matrix-iii. This
	 * solution performs the spiral traversal of the array, increasing the step for
	 * each iteration. Time complexity is O(m * n) where m is the number of rows and
	 * n is the number of columns.
	 * 
	 * @param rows
	 * @param cols
	 * @param rStart
	 * @param cStart
	 * @return
	 */
	public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
		int total = rows * cols;
		int[][] result = new int[total][2];
		result[0][0] = rStart;
		result[0][1] = cStart;
		int lastRow = rows - 1;
		int lastCol = cols - 1;
		int step = 1;
		int index = 1;
		do {
			int targetCol = cStart + step;
			int targetRow = rStart + step;
			if (rStart >= 0) {
				int limit = Math.min(lastCol, targetCol);
				for (int i = Math.max(0, cStart + 1); i <= limit; i++) {
					result[index][0] = rStart;
					result[index++][1] = i;
				}
			}
			if (targetCol <= lastCol) {
				int limit = Math.min(lastRow, targetRow);
				for (int i = Math.max(0, rStart + 1); i <= limit; i++) {
					result[index][0] = i;
					result[index++][1] = targetCol;
				}
			}
			int nextTargetCol = cStart - 1;
			int nextTargetRow = rStart - 1;
			if (targetRow <= lastRow) {
				int limit = Math.max(0, nextTargetCol);
				for (int i = Math.min(lastCol, targetCol - 1); i >= limit; i--) {
					result[index][0] = targetRow;
					result[index++][1] = i;
				}
			}
			if (nextTargetCol >= 0) {
				int limit = Math.max(0, nextTargetRow);
				for (int i = Math.min(lastRow, targetRow - 1); i >= limit; i--) {
					result[index][0] = i;
					result[index++][1] = nextTargetCol;
				}
			}
			step += 2;
			rStart = nextTargetRow;
			cStart = nextTargetCol;
		} while (index < total);
		return result;
	}

	private static void check(int rows, int cols, int rStart, int cStart, int[][] expected) {
		System.out.println("rows is: " + rows);
		System.out.println("cols is: " + cols);
		System.out.println("rStart is: " + rStart);
		System.out.println("cStart is: " + cStart);
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] spiralMatrixIII = spiralMatrixIII(rows, cols, rStart, cStart); // Calls your implementation
		System.out.println("spiralMatrixIII is: ");
		for (int i = 0; i < spiralMatrixIII.length; i++) {
			System.out.println(Arrays.toString(spiralMatrixIII[i]));
		}
	}
}
