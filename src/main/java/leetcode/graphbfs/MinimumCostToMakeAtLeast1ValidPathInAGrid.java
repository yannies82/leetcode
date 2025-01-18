package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumCostToMakeAtLeast1ValidPathInAGrid {

	public static void main(String[] args) {
		check(new int[][] { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 1, 1, 1, 1 }, { 2, 2, 2, 2 } }, 3);
		check(new int[][] { { 1, 1, 3 }, { 3, 2, 2 }, { 1, 1, 4 } }, 0);
		check(new int[][] { { 1, 2 }, { 4, 3 } }, 1);
	}

	// Direction vectors: right, left, down, up (matching grid values 1,2,3,4)
	private static final int[][] DIRECTIONS = new int[][] { { 0, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, };

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid.
	 * This solution uses a combination of DFS and BFS. It uses DFS to add to the
	 * queue all the paths with a certain cost and uses BFS to increase the cost by
	 * 1 and find the next paths. Time complexity is O(n*m) where n is the number of
	 * rows and m is the number of columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minCost(int[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		int cost = 0;

		// Track minimum cost to reach each cell
		int[][] minCost = new int[rowCount][columnCount];
		for (int row = 0; row < rowCount; row++) {
			Arrays.fill(minCost[row], Integer.MAX_VALUE);
		}

		// Queue for BFS part - stores cells that need cost increment
		Queue<int[]> queue = new ArrayDeque<>();

		// Start DFS from origin with cost 0
		dfs(grid, 0, 0, minCost, cost, queue);

		// BFS part - process cells level by level with increasing cost
		while (!queue.isEmpty()) {
			cost++;
			int levelSize = queue.size();

			while (levelSize-- > 0) {
				int[] current = queue.poll();

				// Try all 4 directions for next level
				for (int direction = 1; direction < 5; direction++) {
					dfs(grid, current[0] + DIRECTIONS[direction][0], current[1] + DIRECTIONS[direction][1], minCost,
							cost, queue);
				}
			}
		}

		return minCost[rowCount - 1][columnCount - 1];
	}

	// DFS to explore all reachable cells with current cost
	private static void dfs(int[][] grid, int row, int col, int[][] minCost, int cost, Queue<int[]> queue) {
		if (!isEligible(minCost, row, col))
			return;

		queue.offer(new int[] { row, col });
		minCost[row][col] = cost;

		// Follow the arrow direction without cost increase
		int nextDir = grid[row][col];
		dfs(grid, row + DIRECTIONS[nextDir][0], col + DIRECTIONS[nextDir][1], minCost, cost, queue);
	}

	// Check if cell is eligible for traversal
	private static boolean isEligible(int[][] minCost, int row, int col) {
		return (row >= 0 && col >= 0 && row < minCost.length && col < minCost[0].length
				&& minCost[row][col] == Integer.MAX_VALUE);
	}

	/**
	 * This solution uses a priority queue in order to traverse the grid giving
	 * priority to routes with less cost.
	 * 
	 * @param grid
	 * @return
	 */
	public static int minCost2(int[][] grid) {
		if (grid.length == 1 && grid[0].length == 1) {
			return 0;
		}
		Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		int[][] visited = new int[grid.length][grid[0].length];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		int minCost = Integer.MAX_VALUE;
		int[] current = { 0, 0, 0 };
		if (grid[0].length > 1) {
			int cost = grid[0][0] == 1 ? 0 : 1;
			queue.offer(new int[] { 0, 1, cost });
			visited[0][1] = cost;
		}
		if (grid.length > 1) {
			int cost = grid[0][0] == 3 ? 0 : 1;
			queue.offer(new int[] { 1, 0, cost });
			visited[1][0] = cost;
		}
		int lastRowIndex = grid.length - 1;
		int lastColumnIndex = grid[0].length - 1;
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (current[0] == lastRowIndex && current[1] == lastColumnIndex) {
				minCost = Math.min(minCost, current[2]);
				continue;
			}
			if (current[0] > 0) {
				int cost = grid[current[0]][current[1]] == 4 ? 0 : 1;
				int prevI = current[0] - 1;
				int newCost = current[2] + cost;
				if (newCost < visited[prevI][current[1]]) {
					queue.offer(new int[] { prevI, current[1], newCost });
					visited[prevI][current[1]] = newCost;
				}
			}
			if (current[0] < lastRowIndex) {
				int cost = grid[current[0]][current[1]] == 3 ? 0 : 1;
				int nextI = current[0] + 1;
				int newCost = current[2] + cost;
				if (newCost < visited[nextI][current[1]]) {
					queue.offer(new int[] { nextI, current[1], newCost });
					visited[nextI][current[1]] = newCost;
				}
			}
			if (current[1] > 0) {
				int cost = grid[current[0]][current[1]] == 2 ? 0 : 1;
				int prevJ = current[1] - 1;
				int newCost = current[2] + cost;
				if (newCost < visited[current[0]][prevJ]) {
					queue.offer(new int[] { current[0], prevJ, newCost });
					visited[current[0]][prevJ] = newCost;
				}
			}
			if (current[1] < lastColumnIndex) {
				int cost = grid[current[0]][current[1]] == 1 ? 0 : 1;
				int nextJ = current[1] + 1;
				int newCost = current[2] + cost;
				if (newCost < visited[current[0]][nextJ]) {
					queue.offer(new int[] { current[0], nextJ, newCost });
					visited[current[0]][nextJ] = newCost;
				}
			}
		}
		return minCost;
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int[] row : grid) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("expected is: " + expected);
		int minCost = minCost(grid); // Calls your implementation
		System.out.println("minCost is: " + minCost);
	}
}
