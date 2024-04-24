package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class MinimumHeightTrees {

	public static void main(String[] args) {
		check(4, new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } }, List.of(1));
		check(6, new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 5, 4 } }, List.of(3, 4));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-height-trees. This
	 * solution uses topology sort and BFS traversal, starting from vertices with
	 * exactly 1 edge and moving towards the center where the vertices with the most
	 * edges exist. Time complexity is O(n).
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n == 1) {
			// early exit
			return List.of(0);
		}
		// Generate adjacency list, fill with edge info
		// also keep count of the number of edges per vertice
		int[] numOfEdges = new int[n];
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		int m = edges.length;
		for (int i = 0; i < m; i++) {
			adj.get(edges[i][0]).add(edges[i][1]);
			adj.get(edges[i][1]).add(edges[i][0]);
			numOfEdges[edges[i][0]]++;
			numOfEdges[edges[i][1]]++;
		}

		// this queue is used for BFS traversal
		Queue<Integer> queue = new ArrayDeque<>();

		// add to the queue all leaf nodes, i.e. the nodes which
		// belong to exactly 1 edge
		for (int i = 0; i < n; i++) {
			if (numOfEdges[i] == 1)
				queue.add(i);
		}

		// perform BFS traversal until 2 or less elements are left
		// untraversed
		int remaining = n;
		while (remaining > 2) {
			int levelLength = queue.size();
			// subtract vertices to be traversed from the
			// total number of vertices
			remaining -= levelLength;

			for (int i = 0; i < levelLength; i++) {
				int current = queue.poll();

				// decrease the number of edges of each adjacent vertice
				// and add to queue if only 1 edge remains for the vertice
				for (int adjacent : adj.get(current)) {
					if (--numOfEdges[adjacent] == 1)
						queue.add(adjacent);
				}
			}
		}
		// copy the remaining vertices to the result list, they are
		// the MHT roots
		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			result.add(queue.poll());
		}
		return result;
	}

	private static void check(int n, int[][] edges, List<Integer> expected) {
		System.out.println("n is: " + n);
		System.out.println("flights is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("expected is: " + expected);
		List<Integer> findMinHeightTrees = findMinHeightTrees(n, edges); // Calls your implementation
		System.out.println("findMinHeightTrees is: " + findMinHeightTrees);
	}
}
