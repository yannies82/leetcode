package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TrapRainWater2 {

	public static void main(String[] args) {
		check(new int[][] { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } }, 4);
		check(new int[][] { { 3, 3, 3, 3, 3 }, { 3, 2, 2, 2, 3 }, { 3, 2, 1, 2, 3 }, { 3, 2, 2, 2, 3 },
				{ 3, 3, 3, 3, 3 } }, 10);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/trapping-rain-water-ii. This
	 * solution uses a priority queue as min heap in order to keep the boundary
	 * cells in order of increasing height. It then compares each cell in the heap
	 * with its neighbors, increasing the trapped water and putting the nighbors in
	 * the queue with adjusted height. Time complexity is O(m*n*log(m*n)) where m is
	 * the number of rows and n is the number of columns in the heightMap.
	 * 
	 * @param heightMap
	 * @return
	 */
	public static int trapRainWater(int[][] heightMap) {
		int rowCount = heightMap.length;
		int colCount = heightMap[0].length;
		boolean[][] visited = new boolean[rowCount][colCount];
		// min heap is ordered by increasing height
		Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

		// add all boundary cells to the min heap
		int lastCol = colCount - 1;
		for (int i = 0; i < rowCount; ++i) {
			minHeap.offer(new int[] { i, 0, heightMap[i][0] });
			visited[i][0] = true;
			minHeap.offer(new int[] { i, lastCol, heightMap[i][lastCol] });
			visited[i][lastCol] = true;
		}
		int lastRow = rowCount - 1;
		for (int j = 0; j < colCount; ++j) {
			minHeap.offer(new int[] { 0, j, heightMap[0][j] });
			visited[0][j] = true;
			minHeap.offer(new int[] { lastRow, j, heightMap[lastRow][j] });
			visited[lastRow][j] = true;
		}

		int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int result = 0;
		while (!minHeap.isEmpty()) {
			int[] current = minHeap.poll();
			int x = current[0];
			int y = current[1];
			int height = current[2];
			for (int i = 0; i < directions.length; i++) {
				int newX = x + directions[i][0];
				int newY = y + directions[i][1];
				if (newX >= 0 && newX < rowCount && newY >= 0 && newY < colCount && !visited[newX][newY]) {
					result += Math.max(0, height - heightMap[newX][newY]);
					minHeap.offer(new int[] { newX, newY, Math.max(height, heightMap[newX][newY]) });
					visited[newX][newY] = true;
				}
			}
		}
		return result;
	}

	private static void check(int[][] heightMap, int expected) {
		System.out.println("height is: ");
		for (int[] height : heightMap) {
			System.out.println(Arrays.toString(height));
		}
		System.out.println("expected is: " + expected);
		int trapRainWater = trapRainWater(heightMap); // Calls your implementation
		System.out.println("trapRainWater is: " + trapRainWater);
	}
}
