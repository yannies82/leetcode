package leetcode.graphunionfind;

import java.util.Arrays;

public class RedundantConnection {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } }, new int[] { 2, 3 });
		check(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } }, new int[] { 1, 4 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/redundant-connection. This
	 * solution uses DSU (Disjoint Set Union) to find the representatives for each
	 * node and detect which edge will create a cycle, so that it can be removed.
	 * Time complexity is O(n*A(n)) where n is the number of nodes (and edges) and
	 * A(n) is the Ackermann function which is almost O(1).
	 * 
	 * @param edges
	 * @return
	 */
	public static int[] findRedundantConnection(int[][] edges) {
		int n = edges.length;
		int length = n + 1;
		// keeps the representative for each node
		int reps[] = new int[length];
		// by default each node has itself as its representative
		for (int i = 1; i < length; i++) {
			reps[i] = i;
		}
		for (int i = 0; i < n; i++) {
			int rep1 = reps[edges[i][0]];
			int rep2 = reps[edges[i][1]];
			// find the ultimate representative of the first edge node
			while (rep1 != reps[rep1]) {
				rep1 = reps[rep1];
			}
			// find the ultimate representative of the second edge node
			while (rep2 != reps[rep2]) {
				rep2 = reps[rep2];
			}
			// if they are the same then a cycle has been formed, return the edge
			// otherwise make rep1 the representative of rep2
			if (rep1 == rep2) {
				return edges[i];
			} else {
				reps[rep2] = rep1;
			}
		}
		return null;

	}

	/**
	 * Similar solution which uses an auxiliary class and also keeps the sizes of
	 * representatives per node. Time complexity is O(n*A(n)) where n is the number
	 * of nodes (and edges) and A(n) is the Ackermann function which is almost O(1).
	 * 
	 * @param edges
	 * @return
	 */
	public static int[] findRedundantConnection2(int[][] edges) {
		DSU dsu = new DSU(edges.length);
		for (int i = 0; i < edges.length; i++) {
			// If union returns false, we know the nodes are already connected
			// and hence we can return this edge.
			if (!dsu.doUnion(edges[i][0], edges[i][1])) {
				return edges[i];
			}
		}

		return new int[] {}; // This line should theoretically never be reached
	}

	private static class DSU {

		private int[] size;
		private int[] representative;

		// Initialize DSU class, size of each component will be one and each node
		// will be representative of itself
		private DSU(int n) {
			// length is n + 1 since there are n nodes with values from 1 to n
			// index 0 will not be used
			int length = n + 1;
			size = new int[length];
			representative = new int[length];
			for (int node = 1; node < length; node++) {
				size[node] = 1;
				representative[node] = node;
			}
		}

		// Returns the ultimate representative of the node.
		public int find(int node) {
			if (representative[node] == node) {
				return node;
			}

			return representative[node] = find(representative[node]);
		}

		// Returns true if node nodeOne and nodeTwo belong to different component and
		// update the
		// representatives accordingly, otherwise returns false.
		public boolean doUnion(int nodeA, int nodeB) {
			int repA = find(nodeA);
			int repB = find(nodeB);
			if (repA == repB) {
				// the nodes have the same representative, a cycle will be formed by this edge
				return false;
			} else {
				// the nodes don't have the same representative, make the node with the
				// greater size to be the representative of the other node
				if (size[repA] > size[repB]) {
					representative[repB] = repA;
					size[repA] += size[repB];
				} else {
					representative[repA] = repB;
					size[repB] += size[repA];
				}
				return true;
			}
		}
	}

	private static void check(int[][] edges, int[] expected) {
		System.out.println("edges is: ");
		for (int i = 0; i < edges.length; i++) {
			System.out.println(Arrays.toString(edges[i]));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] findRedundantConnection = findRedundantConnection(edges); // Calls your implementation
		System.out.println("findRedundantConnection is: " + Arrays.toString(findRedundantConnection));
	}
}
