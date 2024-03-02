package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class UniquePaths2 {

	public static void main(String[] args) {
		check(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, 2);
		check(new int[][] { { 0, 1 }, { 0, 0 } }, 1);
	}

	/**
	 * This solution uses top down dynamic programming to store the subproblem
	 * solutions and reuse them in order to produce the final problem solution. Time
	 * complexity is O(m*n) where m is the grid row count and n is the grid column
	 * count.
	 * 
	 * @param obstacleGrid
	 * @return
	 */
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int rowCount = obstacleGrid.length;
		int columnCount = obstacleGrid[0].length;
		// early exit if there is an obstacle at the target cell
		if (obstacleGrid[rowCount - 1][columnCount - 1] == 1) {
			return 0;
		}
		// early exit for 1X1 grid
		if (rowCount == 1 && columnCount == 1) {
			return 1;
		}
		// this 2D array stores the subproblem solutions
		// initialize with negative value so that we know which subproblems
		// have not been calculated yet
		int[][] dpArray = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			Arrays.fill(dpArray[i], -1);
		}
		// solution for the first element of the 2D grid
		dpArray[0][0] = 1;
		// recursively calculate the solution for the last array element
		return dp(obstacleGrid, rowCount - 1, columnCount - 2, dpArray)
				+ dp(obstacleGrid, rowCount - 2, columnCount - 1, dpArray);
	}

	private static int dp(int[][] obstacleGrid, int i, int j, int[][] dpArray) {
		// early exit if index is out of range or there is an obstacle
		if (i < 0 || j < 0 || obstacleGrid[i][j] == 1) {
			return 0;
		}
		// if the subproblem has been solved return solution
		if (dpArray[i][j] >= 0) {
			return dpArray[i][j];
		}
		// recursively solve subproblem and return solution
		dpArray[i][j] = dp(obstacleGrid, i, j - 1, dpArray) + dp(obstacleGrid, i - 1, j, dpArray);
		return dpArray[i][j];
	}

	private static void check(int[][] obstacleGrid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < obstacleGrid.length; i++) {
			System.out.println(Arrays.toString(obstacleGrid[i]));
		}
		System.out.println("expected is: " + expected);
		int uniquePathsWithObstacles = uniquePathsWithObstacles(obstacleGrid); // Calls your implementation
		System.out.println("uniquePathsWithObstacles is: " + uniquePathsWithObstacles);
	}

}
