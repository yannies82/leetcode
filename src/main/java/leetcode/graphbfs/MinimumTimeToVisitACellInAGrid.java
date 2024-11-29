package leetcode.graphbfs;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumTimeToVisitACellInAGrid {

	public static void main(String[] args) {
		int[][] grid1 = { { 0, 1, 3, 2 }, { 5, 1, 2, 5 }, { 4, 3, 8, 6 } };
		check(grid1, 7);
		int[][] grid3 = { { 0, 1, 3, 2 }, { 5, 4, 2, 5 }, { 4, 3, 8, 6 } };
		check(grid3, 7);
		int[][] grid2 = { { 0, 2, 4 }, { 3, 2, 1 }, { 1, 0, 4 } };
		check(grid2, -1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid. This
	 * solution applies the Djikstra algorithm for traversing a graph with weighted
	 * edges, taking into account the delay for transitions which are blocked by the
	 * time required. Time complexity is O(m*n/log(m*n)) where m is the number of
	 * rows in the grid and n is the number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minimumTime(int[][] grid) {
		if (grid[0][1] > 1 && grid[1][0] > 1) {
			// only case where the grid cannot be traversed
			return -1;
		}
		int m = grid.length;
		int n = grid[0].length;
		int lastRowIndex = m - 1;
		int lastColumnIndex = n - 1;
		// this priority queue keeps positions to be traversed sorted by increasing time
		Queue<int[]> deque = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		deque.offer(new int[] { 0, 0, 0 });
		// keeps visited cells
		boolean[][] visited = new boolean[m][n];
		visited[0][0] = true;
		// these are the possible transitions from each position
		int[][] transitions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		while (!deque.isEmpty()) {
			int[] current = deque.poll();
			int nextTime = current[2] + 1;
			// attempt all possible transitions
			for (int j = 0; j < transitions.length; j++) {
				int nextRowIndex = current[0] + transitions[j][0];
				int nextColumnIndex = current[1] + transitions[j][1];
				if (nextRowIndex >= 0 && nextRowIndex < m && nextColumnIndex >= 0 && nextColumnIndex < n
						&& !visited[nextRowIndex][nextColumnIndex]) {
					// skip position if out of bounds or already visited
					// calculate time when next position can be traversed
					int diff = grid[nextRowIndex][nextColumnIndex] - current[2];
					int newCurrentTime = nextTime + (1 - (diff >>> 31)) * (diff >>> 1 << 1);
					if (nextRowIndex == lastRowIndex && nextColumnIndex == lastColumnIndex) {
						// last cell has been reached
						return newCurrentTime;
					}
					visited[nextRowIndex][nextColumnIndex] = true;
					deque.offer(new int[] { nextRowIndex, nextColumnIndex, newCurrentTime });
				}
			}
		}
		return -1;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int minimumTime = minimumTime(grid); // Calls your implementation
		System.out.println("minimumTime is: " + minimumTime);
	}
}
