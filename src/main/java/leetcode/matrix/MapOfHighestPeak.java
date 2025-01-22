package leetcode.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class MapOfHighestPeak {

	public static void main(String[] args) {
		check(new int[][] { { 0, 1 }, { 0, 0 } }, new int[][] { { 1, 0 }, { 2, 1 } });
		check(new int[][] { { 0, 0, 1 }, { 1, 0, 0 }, { 0, 0, 0 } },
				new int[][] { { 1, 1, 0 }, { 0, 1, 1 }, { 1, 2, 2 } });
	}

	private static int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	/**
	 * Leetcode problem: https://leetcode.com/problems/map-of-highest-peak. This
	 * solution performs two passes to assign heights to land cells, once taking
	 * into account their left and top neighbors and once taking into account their
	 * right and bottom neighbors. Time complexity is O(m*n) where m is the number
	 * of rows and n is the number of columns in the isWater grid.
	 * 
	 * 
	 * @param isWater
	 * @return
	 */
	public static int[][] highestPeak(int[][] isWater) {
		int rowCount = isWater.length;
		int columnCount = isWater[0].length;
		int maxValue = rowCount * columnCount;

		// initialize heights of water cells to 0, land cells to max
		int[][] result = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (isWater[i][j] == 1) {
					result[i][j] = 0;
				} else {
					result[i][j] = maxValue;
				}
			}
		}

		// perform the first pass, update heights based on top and left neighbors
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				int minNeighborDistance = maxValue;
				// check the top neighbor
				int prevI = i - 1;
				if (isWithinBounds(isWater, prevI, j)) {
					minNeighborDistance = Math.min(minNeighborDistance, result[prevI][j]);
				}
				// check the left neighbor
				int prevJ = j - 1;
				if (isWithinBounds(isWater, i, prevJ)) {
					minNeighborDistance = Math.min(minNeighborDistance, result[i][prevJ]);
				}
				// update the current cell height
				result[i][j] = Math.min(result[i][j], minNeighborDistance + 1);
			}
		}

		// perform the second pass, update heights based on bottom and right neighbors
		for (int i = rowCount - 1; i >= 0; i--) {
			for (int j = columnCount - 1; j >= 0; j--) {
				int minNeighborDistance = maxValue;
				// check the bottom neighbor
				int nextI = i + 1;
				if (isWithinBounds(isWater, nextI, j)) {
					minNeighborDistance = Math.min(minNeighborDistance, result[nextI][j]);
				}
				// check the right neighbor
				int nextJ = j + 1;
				if (isWithinBounds(isWater, i, nextJ)) {
					minNeighborDistance = Math.min(minNeighborDistance, result[i][nextJ]);
				}
				// update the current cell height
				result[i][j] = Math.min(result[i][j], minNeighborDistance + 1);
			}
		}
		return result;
	}

	/**
	 * This solution uses BFS starting from all water cells in order to calculate
	 * the heights of land cells. Time complexity is O(m*n) where m is the number of
	 * rows and n is the number of columns in the isWater grid.
	 * 
	 * @param isWater
	 * @return
	 */
	public static int[][] highestPeak2(int[][] isWater) {
		int rowCount = isWater.length;
		int columnCount = isWater[0].length;
		int[][] result = new int[rowCount][columnCount];
		Queue<int[]> bfsQueue = new ArrayDeque<>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (isWater[i][j] == 0) {
					result[i][j] = Integer.MAX_VALUE;
				} else {
					bfsQueue.offer(new int[] { i, j });
				}
			}
		}
		boolean[][] visited = new boolean[rowCount][columnCount];
		int height = 0;
		while (!bfsQueue.isEmpty()) {
			height++;
			int length = bfsQueue.size();
			for (int k = 0; k < length; k++) {
				int[] current = bfsQueue.poll();
				for (int l = 0; l < DIRECTIONS.length; l++) {
					int nextI = current[0] + DIRECTIONS[l][0];
					int nextJ = current[1] + DIRECTIONS[l][1];
					if (isWithinBounds(isWater, nextI, nextJ) && isWater[nextI][nextJ] == 0 && !visited[nextI][nextJ]) {
						visited[nextI][nextJ] = true;
						result[nextI][nextJ] = Math.min(result[nextI][nextJ], height);
						bfsQueue.offer(new int[] { nextI, nextJ });
					}
				}
			}
		}
		return result;
	}

	private static boolean isWithinBounds(int[][] isWater, int nextI, int nextJ) {
		return nextI >= 0 && nextI < isWater.length && nextJ >= 0 && nextJ < isWater[0].length;
	}

	private static void check(int[][] isWater, int[][] expected) {
		System.out.println("isWater is: ");
		for (int i = 0; i < isWater.length; i++) {
			System.out.println(Arrays.toString(isWater[i]));
		}
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] highestPeak = highestPeak(isWater); // Calls your implementation
		System.out.println("highestPeak is: ");
		for (int i = 0; i < highestPeak.length; i++) {
			System.out.println(Arrays.toString(highestPeak[i]));
		}
	}
}
