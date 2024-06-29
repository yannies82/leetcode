package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class AllAncestorsOfANodeInADirectedAcyclicGraph {

	public static void main(String[] args) {
		check(8, new int[][] { { 0, 3 }, { 0, 4 }, { 1, 3 }, { 2, 4 }, { 2, 7 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
				{ 4, 6 } },
				List.of(List.of(), List.of(), List.of(), List.of(0, 1), List.of(0, 2), List.of(0, 1, 3),
						List.of(0, 1, 2, 3, 4), List.of(0, 1, 2, 3)));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph.
	 * This solution uses an adjacency list and BFS traversal to determine the
	 * ancestors of each node. Time complexity is O(n * m) where m is the length of
	 * the edges array.
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public static List<List<Integer>> getAncestors(int n, int[][] edges) {
		// create and populate adjacency lists
		List<List<Integer>> adjacency = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjacency.add(new ArrayList<>());
		}
		for (int i = 0; i < edges.length; i++) {
			// keep the direct ancestors of each node
			adjacency.get(edges[i][1]).add(edges[i][0]);
		}
		List<List<Integer>> result = new ArrayList<>();
		// perform BFS for each node
		for (int i = 0; i < n; i++) {
			// keep the visited nodes for this traversal
			boolean[] visited = new boolean[n];
			List<Integer> ancestors = new ArrayList<>();
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(i);
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int j = 0; j < size; j++) {
					Integer current = queue.poll();
					List<Integer> currentAncestors = adjacency.get(current);
					// iterate ancestors of this node
					for (int k = 0; k < currentAncestors.size(); k++) {
						Integer ancestor = currentAncestors.get(k);
						// ignore ancestors that are already visited
						if (!visited[ancestor]) {
							// mark ancestor as visited, put in the queue and in the result list
							visited[ancestor] = true;
							ancestors.add(ancestor);
							queue.offer(ancestor);
						}
					}
				}
			}
			// sort result list
			Collections.sort(ancestors);
			result.add(ancestors);
		}
		return result;
	}

	private static void check(int n, int[][] edges, List<List<Integer>> expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("expected is: " + expected);
		List<List<Integer>> getAncestors = getAncestors(n, edges); // Calls your implementation
		System.out.println("getAncestors is: " + getAncestors);
	}
}
