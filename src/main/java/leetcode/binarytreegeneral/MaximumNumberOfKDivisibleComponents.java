package leetcode.binarytreegeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumNumberOfKDivisibleComponents {

	public static void main(String[] args) {
		check(5, new int[][] { { 0, 2 }, { 1, 2 }, { 1, 3 }, { 2, 4 } }, new int[] { 1, 8, 1, 4, 4 }, 6, 2);
		check(7, new int[][] { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 } },
				new int[] { 3, 0, 6, 1, 5, 2, 1 }, 3, 3);

	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-number-of-k-divisible-components. This
	 * solution uses an adjacency list and DFS to find nodes which have a sum
	 * divisible by k and create components. This solution is optimized by using a
	 * composite array instead of a generic list for the adjacency list. Time
	 * complexity is O(m+n) where m is the length of the edges array.
	 * 
	 * @param root
	 * @param expected
	 */
	public static int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
		// create and populate the array which will be used as adjacency list
		int[][] adjacencyList = new int[n][];
		int[] indexes = new int[n];
		// calculate the size of each element's adjacency array
		for (int i = 0; i < edges.length; i++) {
			indexes[edges[i][0]]++;
			indexes[edges[i][1]]++;
		}
		// allocate the array of appropriate size for each element and reset the index
		for (int i = 0; i < n; i++) {
			adjacencyList[i] = new int[indexes[i]];
			indexes[i] = 0;
		}
		// populate the adjacency arrays
		for (int i = 0; i < edges.length; i++) {
			adjacencyList[edges[i][0]][indexes[edges[i][0]]++] = edges[i][1];
			adjacencyList[edges[i][1]][indexes[edges[i][1]]++] = edges[i][0];
		}
		boolean[] visited = new boolean[n];
		// traverse the tree-graph starting from node 0 using DFS
		int[] result = { 0 };
		traverse(0, adjacencyList, indexes, values, k, visited, result);
		return result[0];
	}

	private static long traverse(int node, int[][] adjacencyList, int[] indexes, int[] values, int k, boolean[] visited,
			int[] result) {
		visited[node] = true;
		long value = values[node];
		for (int i = 0; i < indexes[node]; i++) {
			int adjacent = adjacencyList[node][i];
			if (!visited[adjacent]) {
				value += traverse(adjacent, adjacencyList, indexes, values, k, visited, result);
			}
		}
		if (value % k == 0) {
			// this is a valid component, increase count and do not add to the sum
			// of its parent
			result[0]++;
			return 0;
		}
		return value;
	}

	/**
	 * This solution uses an adjacency list and DFS to find nodes which have a sum
	 * divisible by k and create components. Time complexity is O(m+n) where m is
	 * the length of the edges array.
	 * 
	 * @param root
	 * @param expected
	 */
	public static int maxKDivisibleComponents2(int n, int[][] edges, int[] values, int k) {
		// create and populate the adjacency list
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		for (int i = 0; i < edges.length; i++) {
			adjacencyList.get(edges[i][0]).add(edges[i][1]);
			adjacencyList.get(edges[i][1]).add(edges[i][0]);
		}
		boolean[] visited = new boolean[n];
		// traverse the tree-graph starting from node 0 using DFS
		int[] result = { 0 };
		traverse(0, adjacencyList, values, k, visited, result);
		return result[0];
	}

	private static long traverse(int node, List<List<Integer>> adjacencyList, int[] values, int k, boolean[] visited,
			int[] result) {
		visited[node] = true;
		long value = values[node];
		for (int neighbor : adjacencyList.get(node)) {
			if (!visited[neighbor]) {
				value += traverse(neighbor, adjacencyList, values, k, visited, result);
			}
		}
		if (value % k == 0) {
			// this is a valid component, increase count and do not add to the sum
			// of its parent
			result[0]++;
			return 0;
		}
		return value;
	}

	private static void check(int n, int[][] edges, int[] values, int k, int expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("values is: " + Arrays.toString(values));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int distributeCoins = maxKDivisibleComponents(n, edges, values, k);
		System.out.println("distributeCoins is: " + distributeCoins);
	}

}
