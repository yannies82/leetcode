package leetcode.dynamicprogramming;

import java.util.Arrays;

public class MaximumNumberOfPointsWithCost {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2, 3 }, { 1, 5, 1 }, { 3, 1, 1 } }, 9);
		check(new int[][] { { 1, 5 }, { 2, 3 }, { 4, 2 } }, 11);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-number-of-points-with-cost. This
	 * solution calculates the results for each row when selecting the item in
	 * position i,j. For this calculation it populates and uses two prefix arrays,
	 * leftMax and rightMax. Time complexity is O(m*n) where m is the number of rows
	 * and n is the number of columns in the points array.
	 * 
	 * @param points
	 * @return
	 */
	public static long maxPoints(int[][] points) {
		int m = points.length;
		int n = points[0].length;
		int lastColIndex = n - 1;
		long[] dpArray = new long[n];
		// initialize the values of dpArray
		for (int i = 0; i < n; i++) {
			dpArray[i] = points[0][i];
		}
		// iterate all rows and update the values of dpArray
		for (int i = 1; i < m; i++) {
			// contains the maximum points from the left up to column i before the current
			// row
			long[] leftMax = new long[n];
			// contains the maximum points from the right up to column i before the current
			// row
			long[] rightMax = new long[n];
			// populate leftMax and rightMax values
			leftMax[0] = dpArray[0];
			rightMax[lastColIndex] = dpArray[lastColIndex] - lastColIndex;
			for (int j = 1; j < n; j++) {
				leftMax[j] = Math.max(leftMax[j - 1], dpArray[j] + j);
				int rightIndex = lastColIndex - j;
				rightMax[rightIndex] = Math.max(rightMax[rightIndex + 1], dpArray[rightIndex] - rightIndex);
			}
			// calculate and update dpArray values
			for (int j = 0; j < n; j++) {
				dpArray[j] = Math.max(leftMax[j] - j, rightMax[j] + j) + points[i][j];
			}
		}
		// the result is the max dpArray value
		long result = 0;
		for (int i = 0; i < n; i++) {
			if (dpArray[i] > result) {
				result = dpArray[i];
			}
		}
		return result;
	}

	/**
	 * Alternate solution. Time complexity is O(m * n * n) where m is the number of
	 * rows and n is the number of columns in the points array.
	 * 
	 * @param points
	 * @return
	 */
	public static long maxPoints2(int[][] points) {
		int m = points.length;
		int lastRowIndex = m - 1;
		int n = points[0].length;
		long[][] dpArray = new long[m][n];
		int maxFirst = 0;
		for (int j = 0; j < n; j++) {
			if (points[0][j] > maxFirst) {
				maxFirst = points[0][j];
			}
			dpArray[0][j] = points[0][j];
		}
		if (m == 1) {
			return maxFirst;
		}
		for (int i = 1; i < lastRowIndex; i++) {
			for (int j = 0; j < n; j++) {
				long maxPrev = 0;
				for (int k = 0; k < n; k++) {
					long prev = dpArray[i - 1][k] - Math.abs(j - k);
					if (prev > maxPrev) {
						maxPrev = prev;
					}
				}
				dpArray[i][j] = points[i][j] + maxPrev;
			}
		}
		long result = 0;
		for (int j = 0; j < n; j++) {
			long maxPrev = 0;
			for (int k = 0; k < n; k++) {
				long prev = dpArray[lastRowIndex - 1][k] - Math.abs(j - k);
				if (prev > maxPrev) {
					maxPrev = prev;
				}
			}
			dpArray[lastRowIndex][j] = points[lastRowIndex][j] + maxPrev;
			if (dpArray[lastRowIndex][j] > result) {
				result = dpArray[lastRowIndex][j];
			}
		}
		return result;
	}

	private static void check(int[][] points, long expected) {
		System.out.println("points is: ");
		for (int[] point : points) {
			System.out.println(Arrays.toString(point));
		}
		System.out.println("expected is: " + expected);
		long maxPoints = maxPoints(points); // Calls your implementation
		System.out.println("maxPoints is: " + maxPoints);
	}

}
