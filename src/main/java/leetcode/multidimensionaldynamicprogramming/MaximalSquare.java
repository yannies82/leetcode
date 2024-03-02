package leetcode.multidimensionaldynamicprogramming;

public class MaximalSquare {

	public static void main(String[] args) {
		char[][] matrix1 = { { '0' } };
		check(matrix1, 0);
		char[][] matrix2 = { { '0', '1' }, { '1', '0' } };
		check(matrix2, 1);
		char[][] matrix3 = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '0' } };
		check(matrix3, 4);
	}

	/**
	 * This solution uses dynamic programming to store the results of intermediate
	 * subproblems. It iterates every position of the matrix and calculates the
	 * largest possible square of '1's that exists with the specific position as
	 * lowest right. Time complexity is O(m*n) where m is the number of rows and n
	 * is the number of columns.
	 * 
	 * @param matrix
	 * @return
	 */
	public static int maximalSquare(char[][] matrix) {
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		// this array stores the results of the intermediate subproblems
		// each result is the max possible side of the square of '1's
		// which has position [i][j] as lowest right
		int[][] dpArray = new int[rowCount][columnCount];
		int maxSideLength = 0;
		// initialize solutions for first column
		for (int i = 0; i < rowCount; i++) {
			if (matrix[i][0] == '1') {
				dpArray[i][0] = maxSideLength = 1;
			}
		}
		// initialize solutions for first row
		for (int j = 1; j < columnCount; j++) {
			if (matrix[0][j] == '1') {
				dpArray[0][j] = maxSideLength = 1;
			}
		}
		// iterate all other rows and columns
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (matrix[i][j] != '1') {
					// if matrix[i][j] == 0 then dpArray[i][j] = 0
					continue;
				}
				// the size of the maximum square for position i, j is dictated by the square
				// sizes of the three adjacent positions (i-1,j-1), (i-1,j), (i,j-1)
				dpArray[i][j] = 1 + Math.min(dpArray[i - 1][j - 1], Math.min(dpArray[i - 1][j], dpArray[i][j - 1]));
				if (dpArray[i][j] > maxSideLength) {
					// update the max side length if applicable
					maxSideLength = dpArray[i][j];
				}
			}
		}
		// return max square area
		return maxSideLength * maxSideLength;
	}

	/**
	 * Simple solution which traverses every matrix position and checks for the
	 * maximum square of '1's by increasing the side size in each iteration. Time
	 * complexity is O(n*m*min(n,m)*(n+m)) where n is the number of rows and m is
	 * the number of columns.
	 * 
	 * @param matrix
	 * @return
	 */
	public static int maximalSquare2(char[][] matrix) {
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		int maxSideLength = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (matrix[i][j] != '1') {
					continue;
				}
				int sideLength = 1;
				boolean all1 = true;
				while (all1 && i + sideLength < rowCount && j + sideLength < columnCount) {
					for (int k = i; k <= i + sideLength && all1; k++) {
						all1 = all1 && matrix[k][j + sideLength] == '1';
					}
					for (int k = j; k < j + sideLength && all1; k++) {
						all1 = all1 && matrix[i + sideLength][k] == '1';
					}
					if (all1) {
						sideLength++;
					}
				}
				if (sideLength > maxSideLength) {
					maxSideLength = sideLength;
				}
			}
		}
		return maxSideLength * maxSideLength;
	}

	private static void check(char[][] matrix, int expected) {
		System.out.println("matrix is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(matrix[i]);
		}
		System.out.println("expected is: " + expected);
		int maximalSquare = maximalSquare(matrix); // Calls your implementation
		System.out.println("maximalSquare is: " + maximalSquare);
	}

}
