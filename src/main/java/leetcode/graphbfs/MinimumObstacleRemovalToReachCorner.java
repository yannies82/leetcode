package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinimumObstacleRemovalToReachCorner {

	public static void main(String[] args) {
		int[][] grid1 = { { 0, 1, 1 }, { 1, 1, 0 }, { 1, 1, 0 } };
		check(grid1, 2);
		int[][] grid2 = { { 0, 1, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 1, 0 } };
		check(grid2, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner. This
	 * solution calculates the min distance from the first cell to all other cells
	 * of the array. The result is the min distance to the last cell of the last
	 * row. Time complexity is O(m*n) where m is the number of rows and n is the
	 * number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minimumObstacles(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		// initialize minDistance array to max values except for the first cell
		int[][] minDistance = new int[m][n];
		for (int i = 0; i < minDistance.length; i++) {
			Arrays.fill(minDistance[i], Integer.MAX_VALUE);
		}
		minDistance[0][0] = 0;
		// this deque keeps positions to be traversed, initialize with first cell
		Deque<int[]> deque = new ArrayDeque<>();
		deque.offerFirst(new int[] { 0, 0 });
		// these are the possible transitions from each position
		int[][] transitions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		while (!deque.isEmpty()) {
			int[] current = deque.pollFirst();
			int rowIndex = current[0];
			int columnIndex = current[1];
			// attempt all possible transitions
			for (int i = 0; i < transitions.length; i++) {
				int nextRowIndex = current[0] + transitions[i][0];
				int nextColumnIndex = current[1] + transitions[i][1];
				if (nextRowIndex >= 0 && nextRowIndex < m && nextColumnIndex >= 0 && nextColumnIndex < n) {
					// discard transitions which lead out of the array bounds
					int newDistance = minDistance[rowIndex][columnIndex] + grid[nextRowIndex][nextColumnIndex];
					if (newDistance < minDistance[nextRowIndex][nextColumnIndex]) {
						// discard transitions to cells which do not improve the minDistance
						minDistance[nextRowIndex][nextColumnIndex] = newDistance;
						// give precedence to cells with 0 value because they will lead to results with
						// less distance, therefore less calculations
						if (grid[nextRowIndex][nextColumnIndex] == 0) {
							deque.offerFirst(new int[] { nextRowIndex, nextColumnIndex });
						} else {
							deque.offerLast(new int[] { nextRowIndex, nextColumnIndex });
						}
					}
				}
			}
		}
		return minDistance[m - 1][n - 1];
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int minimumObstacles = minimumObstacles(grid); // Calls your implementation
		System.out.println("minimumObstacles is: " + minimumObstacles);
	}
}
