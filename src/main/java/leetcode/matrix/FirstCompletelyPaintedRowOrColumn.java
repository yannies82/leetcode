package leetcode.matrix;

import java.util.Arrays;

public class FirstCompletelyPaintedRowOrColumn {

	public static void main(String[] args) {
		check(new int[] { 1, 3, 4, 2 }, new int[][] { { 1, 4 }, { 2, 3 } }, 2);
		check(new int[] { 2, 8, 7, 4, 1, 3, 5, 6, 9 }, new int[][] { { 3, 2, 5 }, { 1, 4, 6 }, { 8, 7, 9 } }, 3);
		check(new int[] { 1, 4, 5, 2, 6, 3 }, new int[][] { { 4, 3, 5 }, { 1, 2, 6 } }, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/first-completely-painted-row-or-column. This
	 * solution creates two arrays which inverse the mapping of indexes i, j in mat
	 * and their value. It then uses the inverse mapping to count when a full row or
	 * column has been completed. Time complexity is O(m*n) where m is the number of
	 * rows in mat and n is the number of columns in mat.
	 * 
	 * @param arr
	 * @param mat
	 * @return
	 */
	public static int firstCompleteIndex(int[] arr, int[][] mat) {
		int rowCount = mat.length;
		int columnCount = mat[0].length;
		int[] valueToRow = new int[arr.length + 1];
		int[] valueToColumn = new int[arr.length + 1];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				valueToRow[mat[i][j]] = i;
				valueToColumn[mat[i][j]] = j;
			}
		}
		int[] countPerRow = new int[rowCount];
		int[] countPerColumn = new int[columnCount];
		for (int i = 0; i < arr.length; i++) {
			countPerRow[valueToRow[arr[i]]]++;
			countPerColumn[valueToColumn[arr[i]]]++;
			if (countPerRow[valueToRow[arr[i]]] == columnCount || countPerColumn[valueToColumn[arr[i]]] == rowCount) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Similar solution, uses a single 2D array for inverse mapping instead of 2
	 * separate arrays. Time complexity is O(m*n) where m is the number of rows in
	 * mat and n is the number of columns in mat.
	 * 
	 * @param arr
	 * @param mat
	 * @return
	 */
	public static int firstCompleteIndex2(int[] arr, int[][] mat) {
		int rowCount = mat.length;
		int columnCount = mat[0].length;
		int[][] valueToCell = new int[arr.length + 1][2];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				valueToCell[mat[i][j]][0] = i;
				valueToCell[mat[i][j]][1] = j;
			}
		}
		int[] countPerRow = new int[rowCount];
		int[] countPerColumn = new int[columnCount];
		for (int i = 0; i < arr.length; i++) {
			int row = valueToCell[arr[i]][0];
			int column = valueToCell[arr[i]][1];
			countPerRow[row]++;
			countPerColumn[column]++;
			if (countPerRow[row] == columnCount || countPerColumn[column] == rowCount) {
				return i;
			}
		}
		return -1;
	}

	private static void check(int[] arr, int[][] mat, int expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("mat is: ");
		for (int i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
		System.out.println("expected is: " + expected);
		int firstCompleteIndex = firstCompleteIndex(arr, mat);
		System.out.println("firstCompleteIndex is: " + firstCompleteIndex);
	}
}
