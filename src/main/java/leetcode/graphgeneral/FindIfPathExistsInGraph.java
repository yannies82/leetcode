package leetcode.graphgeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindIfPathExistsInGraph {

	public static void main(String[] args) {
		int[][] edges1 = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
		check(3, edges1, 0, 2, true);
		int[][] edges2 = { { 0, 1 }, { 0, 2 }, { 3, 5 }, { 5, 4 }, { 4, 3 } };
		check(6, edges2, 0, 5, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-if-path-exists-in-graph.
	 * This solution uses DFS traversal to find if there is a path from the source
	 * node to the destination node. Time complexity is O(n).
	 * 
	 * @param n
	 * @param edges
	 * @param source
	 * @param destination
	 * @return
	 */
	public static boolean validPath(int n, int[][] edges, int source, int destination) {
		if (edges.length == 0) {
			// early exit
			return true;
		}
		// generate adjacency list
		List<List<Integer>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		for (int i = 0; i < edges.length; i++) {
			adjacencyList.get(edges[i][0]).add(edges[i][1]);
			adjacencyList.get(edges[i][1]).add(edges[i][0]);
		}
		// tracks visited vertices
		boolean[] visited = new boolean[n];
		return performDfs(adjacencyList, source, destination, visited);
	}

	private static boolean performDfs(List<List<Integer>> adjacencyList, int source, int destination,
			boolean[] visited) {
		visited[source] = true;
		for (int dest : adjacencyList.get(source)) {
			if (dest == destination) {
				return true;
			} else if (!visited[dest]) {
				if (performDfs(adjacencyList, dest, destination, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	private static void check(int n, int[][] edges, int source, int destination, boolean expected) {
		System.out.println("n is: " + n);
		System.out.println("grid is: ");
		for (int i = 0; i < edges.length; i++) {
			System.out.println(Arrays.toString(edges[i]));
		}
		System.out.println("source is: " + source);
		System.out.println("destination is: " + destination);
		System.out.println("expected is: " + expected);
		boolean validPath = validPath(n, edges, source, destination); // Calls your implementation
		System.out.println("validPath is: " + validPath);
	}
}
