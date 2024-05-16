package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class FindTheSafestPathInAGrid {

	public static void main(String[] args) {
		List<List<Integer>> grid1 = List.of(List.of(1, 0, 0), List.of(0, 0, 0), List.of(0, 0, 1));
		check(grid1, 0);
		grid1 = List.of(List.of(0, 0, 1), List.of(0, 0, 0), List.of(0, 0, 0));
		check(grid1, 2);
		grid1 = List.of(List.of(0, 0, 0, 1), List.of(0, 0, 0, 0), List.of(0, 0, 0, 0), List.of(1, 0, 0, 0));
		check(grid1, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-safest-path-in-a-grid. This solution
	 * uses BFS from each thief position to calculate the safeness matrix for all
	 * positions. It then uses top down dynamic programming to calculate the
	 * solution. Time complexity is O(n^4) where n is the length of the grid.
	 * 
	 * @param board
	 * @return
	 */
	public static int maximumSafenessFactor(List<List<Integer>> grid) {
		int length = grid.size();
		if (grid.get(0).get(0) == 1 || grid.get(length - 1).get(length - 1) == 1) {
			// early exit
			return 0;
		}
		// initialize safeness matrix
		int[][] safeness = new int[length][length];
		for (int i = 0; i < safeness.length; i++) {
			Arrays.fill(safeness[i], Integer.MAX_VALUE);
		}
		// calculate safeness matrix
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (grid.get(i).get(j) == 1) {
					calculateSafenessBFS(grid, safeness, i, j);
				}
			}
		}
		int[][] dpArray = new int[length][length];
		for (int i = 0; i < dpArray.length; i++) {
			Arrays.fill(dpArray[i], -1);
		}
		dpArray[0][0] = safeness[0][0];
		return dp(safeness, length - 1, length - 1, dpArray);
	}

	private static int dp(int[][] safeness, int i, int j, int[][] dpArray) {
		if (i < 0 || j < 0) {
			return Integer.MIN_VALUE;
		}
		if (dpArray[i][j] != -1) {
			return dpArray[i][j];
		}
		return Math.min(safeness[i][j], Math.max(dp(safeness, i - 1, j, dpArray), dp(safeness, i, j - 1, dpArray)));
	}

	private static void calculateSafenessBFS(List<List<Integer>> grid, int[][] safeness, int startI, int startJ) {
		int lastIndex = safeness.length - 1;
		int safenessCount = 0;
		safeness[startI][startJ] = safenessCount;
		boolean[][] visited = new boolean[safeness.length][safeness.length];
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(startI, startJ));
		while (!queue.isEmpty()) {
			int length = queue.size();
			safenessCount++;
			for (int k = 0; k < length; k++) {
				Node current = queue.poll();
				int i = current.i;
				int j = current.j;
				visited[i][j] = true;
				if (i > 0 && grid.get(i - 1).get(j) == 0 && !visited[i - 1][j]) {
					safeness[i - 1][j] = Math.min(safenessCount, safeness[i - 1][j]);
					queue.add(new Node(i - 1, j));
				}
				if (i < lastIndex && grid.get(i + 1).get(j) == 0 && !visited[i + 1][j]) {
					safeness[i + 1][j] = Math.min(safenessCount, safeness[i + 1][j]);
					queue.add(new Node(i + 1, j));
				}
				if (j > 0 && grid.get(i).get(j - 1) == 0 && !visited[i][j - 1]) {
					safeness[i][j - 1] = Math.min(safenessCount, safeness[i][j - 1]);
					queue.add(new Node(i, j - 1));
				}
				if (j < lastIndex && grid.get(i).get(j + 1) == 0 && !visited[i][j + 1]) {
					safeness[i][j + 1] = Math.min(safenessCount, safeness[i][j + 1]);
					queue.add(new Node(i, j + 1));
				}
			}
		}
	}

	private static record Node(int i, int j) {
	};

	private static void check(List<List<Integer>> grid, int expected) {
		System.out.println("grid is: " + grid);
		System.out.println("expected is: " + expected);
		int maximumSafenessFactor = maximumSafenessFactor(grid); // Calls your implementation
		System.out.println("maximumSafenessFactor is: " + maximumSafenessFactor);
	}
}
