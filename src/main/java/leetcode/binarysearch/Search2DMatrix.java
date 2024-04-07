package leetcode.binarysearch;

import java.util.Arrays;

public class Search2DMatrix {

	public static void main(String[] args) {
		int[][] matrix0 = new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
		check(matrix0, 3, true);
		check(matrix0, 13, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/search-a-2d-matrix. Performs
	 * a binary search to find the target value in the sorted 2D array. Time
	 * complexity is O(log(N*M)) where N, M are the dimensions of the array.
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
		return searchRecursive(matrix, 0, matrix.length * matrix[0].length, target);
	}

	private static boolean searchRecursive(int[][] matrix, int start, int end, int target) {
		int mid = (start + end) / 2;
		// map mid to a row and column
		int row = mid / matrix[0].length;
		int column = mid % matrix[0].length;
		if (start == mid) {
			if (matrix[row][column] == target) {
				return true;
			}
			return false;
		} else if (target < matrix[row][column]) {
			return searchRecursive(matrix, start, mid, target);
		}
		return searchRecursive(matrix, mid, end, target);
	}

	private static void check(int[][] matrix, int target, boolean expected) {
		System.out.println("matrix is: ");
		for (int[] arr : matrix) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		boolean searchMatrix = searchMatrix(matrix, target); // Calls your implementation
		System.out.println("searchMatrix is: " + searchMatrix);
	}
}
