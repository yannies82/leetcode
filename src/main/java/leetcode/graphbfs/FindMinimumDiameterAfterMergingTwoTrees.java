package leetcode.graphbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMinimumDiameterAfterMergingTwoTrees {

	public static void main(String[] args) {
		check(new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 } }, new int[][] { { 0, 1 } }, 3);
		check(new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 2, 4 }, { 2, 5 }, { 3, 6 }, { 2, 7 } },
				new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 2, 4 }, { 2, 5 }, { 3, 6 }, { 2, 7 } }, 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees.
	 * 
	 * 
	 * @param edges1
	 * @param edges2
	 * @return
	 */
	public static int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
		int n = edges1.length + 1;
		int m = edges2.length + 1;
		List<List<Integer>> adjacencyList1 = new ArrayList<>();
		List<List<Integer>> adjacencyList2 = new ArrayList<>();
		// initialize the graph adjacency lists
		for (int i = 0; i < n; i++) {
			adjacencyList1.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			adjacencyList2.add(new ArrayList<>());
		}
		// fill the adjacency list for graph1
		for (int i = 0; i < edges1.length; i++) {
			adjacencyList1.get(edges1[i][0]).add(edges1[i][1]);
			adjacencyList1.get(edges1[i][1]).add(edges1[i][0]);
		}
		// fill the adjacency list for graph2
		for (int i = 0; i < edges2.length; i++) {
			adjacencyList2.get(edges2[i][0]).add(edges2[i][1]);
			adjacencyList2.get(edges2[i][1]).add(edges2[i][0]);
		}
		// find diameters of both graphs
		boolean[] visited1 = new boolean[n];
		int[] maxDiameter1Wrapper = new int[] { 0 };
		findHeight(adjacencyList1, visited1, 0, maxDiameter1Wrapper);
		int maxDiameter1 = maxDiameter1Wrapper[0];
		boolean[] visited2 = new boolean[m];
		int[] maxDiameter2Wrapper = new int[] { 0 };
		findHeight(adjacencyList2, visited2, 0, maxDiameter2Wrapper);
		int maxDiameter2 = maxDiameter2Wrapper[0];

		int mergedDiameter = Math.max(maxDiameter1, maxDiameter2);
		// minimum diameter after merging will be either the max diameter of graph1 and
		// graph2, or the one that will be created by connecting the middle of the two
		// graphs' diameters
		mergedDiameter = Math.max(mergedDiameter, (maxDiameter1 + 1) / 2 + (maxDiameter2 + 1) / 2 + 1);
		return mergedDiameter;
	}

	/**
	 * Calculates the height of the non visited part of the graph starting at node.
	 * Updates the maxDiameterWrapper if needed.
	 * 
	 * @param adjacencyList
	 * @param visited
	 * @param node
	 * @param diameterWrapper
	 * @return
	 */
	private static int findHeight(List<List<Integer>> adjacencyList, boolean[] visited, int node,
			int[] maxDiameterWrapper) {
		visited[node] = true;
		int maxHeight = -1;
		int secondMaxHeight = -1;
		List<Integer> neighbors = adjacencyList.get(node);
		int neighborsSize = neighbors.size();
		for (int i = 0; i < neighborsSize; i++) {
			int neighbor = neighbors.get(i);
			if (!visited[neighbor]) {
				int height = findHeight(adjacencyList, visited, neighbor, maxDiameterWrapper);
				if (height > maxHeight) {
					secondMaxHeight = maxHeight;
					maxHeight = height;
				} else if (height > secondMaxHeight) {
					secondMaxHeight = height;
				}
			}
		}
		// update max diameter if it is less than the sum of the two biggest heights
		// starting from this node
		if (maxHeight + secondMaxHeight + 2 > maxDiameterWrapper[0]) {
			maxDiameterWrapper[0] = maxHeight + secondMaxHeight + 2;
		}
		return maxHeight + 1;
	}

	private static void check(int[][] edges1, int[][] edges2, int expected) {
		System.out.println("edges1 is: ");
		for (int[] edge1 : edges1) {
			System.out.println(Arrays.toString(edge1));
		}
		System.out.println("edges2 is: ");
		for (int[] edge2 : edges2) {
			System.out.println(Arrays.toString(edge2));
		}
		System.out.println("expected is: " + expected);
		int minimumDiameterAfterMerge = minimumDiameterAfterMerge(edges1, edges2); // Calls your implementation
		System.out.println("minimumDiameterAfterMerge is: " + minimumDiameterAfterMerge);
	}
}
