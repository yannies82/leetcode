package leetcode.graphgeneral;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BuildAMatrixWithConditions {

	public static void main(String[] args) {
		check(3, new int[][] { { 1, 2 }, { 3, 2 } }, new int[][] { { 2, 1 }, { 3, 2 } },
				new int[][] { { 0, 0, 1 }, { 3, 0, 0 }, { 0, 2, 0 } });
		check(3, new int[][] { { 1, 2 }, { 2, 3 }, { 3, 1 }, { 2, 3 } }, new int[][] { { 2, 1 } }, new int[][] {});
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/build-a-matrix-with-conditions. This solution
	 * treats the rowConditions and colConditions as graphs and uses adjacency lists
	 * and topology sort to produce the result. Time complexity is O(k + m + n)
	 * where m is the number of rowConditions and n is the number of colConditions.
	 * 
	 * @param k
	 * @param rowConditions
	 * @param colConditions
	 * @return
	 */
	public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
		// create the vertical adjacency list and the vertical link count array
		List<List<Integer>> verticalAdjacency = new ArrayList<>();
		int[] verticalLinkCount = new int[k + 1];
		for (int i = 0; i <= k; i++) {
			verticalAdjacency.add(new ArrayList<>());
		}
		for (int[] rowCondition : rowConditions) {
			// for each condition add to the adjacency list of the number which is above
			// all numbers which should be below it
			verticalAdjacency.get(rowCondition[0]).add(rowCondition[1]);
			// increase the vertical link count of the number which should be below
			verticalLinkCount[rowCondition[1]]++;
		}
		// create the horizontal adjacency list and the horizontal link count array
		List<List<Integer>> horizontalAdjacency = new ArrayList<>();
		int[] horizontalLinkCount = new int[k + 1];
		for (int i = 0; i <= k; i++) {
			horizontalAdjacency.add(new ArrayList<>());
		}
		for (int[] colCondition : colConditions) {
			// for each condition add to the adjacency list of the number which is left
			// all numbers which should be on its right
			horizontalAdjacency.get(colCondition[0]).add(colCondition[1]);
			// increase the horizontal link count of the number which should be on the right
			horizontalLinkCount[colCondition[1]]++;
		}
		// perform BFS to find the vertical position of every number from 1 to k in the
		// final matrix
		int[] verticalResult = performBFS(verticalLinkCount, verticalAdjacency);
		if (verticalResult.length == 0) {
			// a solution could not be found because there is a cycle, return empty array
			return new int[][] {};
		}
		// perform BFS to find the horizontal position of every number from 1 to k in
		// the final matrix
		int[] horizontalResult = performBFS(horizontalLinkCount, horizontalAdjacency);
		if (horizontalResult.length == 0) {
			// a solution could not be found because there is a cycle, return empty array
			return new int[][] {};
		}
		// create and populate the result array using the horizontal and vertical result
		// arrays which contain the positions of numbers 1 to k in the result array
		int[][] result = new int[k][k];
		for (int i = 1; i <= k; i++) {
			result[verticalResult[i]][horizontalResult[i]] = i;
		}
		return result;
	}

	private static int[] performBFS(int[] linkCount, List<List<Integer>> adjacencyList) {
		Queue<Integer> visited = new ArrayDeque<>();
		// add to the queue all numbers from 1 to k with no dependencies
		// (no elements above them or on their left)
		for (int i = 1; i < linkCount.length; i++) {
			if (linkCount[i] == 0) {
				visited.offer(i);
			}
		}
		// initialize result array and index
		int[] result = new int[linkCount.length];
		int resultIndex = 0;
		while (!visited.isEmpty()) {
			// remove numbers from the queue and add to the result array
			int current = visited.poll();
			// for each number as index add its result position as value
			// (either vertical or horizontal)
			result[current] = resultIndex++;
			// iterate the adjacent numbers of the current one
			// (numbers for which the current one is above or on the left)
			for (int next : adjacencyList.get(current)) {
				// reduce the number of links for each adjacent number and if
				// it reaches zero, add the number to the queue
				linkCount[next]--;
				if (linkCount[next] == 0) {
					visited.offer(next);
				}
			}
		}
		if (resultIndex == linkCount.length - 1) {
			// all of the numbers were traversable and the results array is fully formed
			return result;
		}
		// a cycle exists and it was not possible to traverse all numbers
		// return empty array
		return new int[0];
	}

	private static void check(int k, int[][] rowConditions, int[][] colConditions, int[][] expected) {
		System.out.println("k is: " + k);
		System.out.println("rowConditions is: ");
		for (int i = 0; i < rowConditions.length; i++) {
			System.out.println(Arrays.toString(rowConditions[i]));
		}
		System.out.println("colConditions is: ");
		for (int i = 0; i < colConditions.length; i++) {
			System.out.println(Arrays.toString(colConditions[i]));
		}
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] buildMatrix = buildMatrix(k, rowConditions, colConditions); // Calls your implementation
		System.out.println("buildMatrix is: ");
		for (int i = 0; i < buildMatrix.length; i++) {
			System.out.println(Arrays.toString(buildMatrix[i]));
		}
	}
}
