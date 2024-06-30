package leetcode.graphgeneral;

import java.util.Arrays;

public class RemoveMaxNumberOfEdgesToKeepGraph {

	public static void main(String[] args) {
		check(4, new int[][] { { 3, 1, 2 }, { 3, 2, 3 }, { 1, 1, 3 }, { 1, 2, 4 }, { 1, 1, 2 }, { 2, 3, 4 } }, 2);
		check(4, new int[][] { { 3, 1, 2 }, { 3, 2, 3 }, { 1, 1, 4 }, { 2, 1, 4 } }, 0);
		check(4, new int[][] { { 3, 1, 2 }, { 1, 1, 2 }, { 2, 3, 4 } }, -1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable.
	 * This solution creates specific union structures for Alice and Bob. It first
	 * uses all nodes of type 3, which may connect nodes for both Alice and Bob. It
	 * then uses all remaining nodes of type 1 and 2 which connect nodes that are
	 * not already connected. Time complexity is O(m) where m is the length of the
	 * edges array.
	 * 
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public static int maxNumEdgesToRemove(int n, int[][] edges) {
		// create separate union structures for Alice and Bob
		UnionFind alice = new UnionFind(n);
		UnionFind bob = new UnionFind(n);

		// initialize an edgesRequired counter to keep the number of total required
		// edges
		int edgesRequired = 0;
		// iterate edges array and use all edges of type 3
		for (int[] edge : edges) {
			if (edge[0] == 3) {
				// increase the counter if the edge connects new nodes for either Alice or Bob
				edgesRequired += (alice.performUnion(edge[1], edge[2]) | bob.performUnion(edge[1], edge[2]));
			}
		}

		// iterate edges array and use all edges of type 1 and 2
		for (int[] edge : edges) {
			if (edge[0] == 1) {
				edgesRequired += alice.performUnion(edge[1], edge[2]);
			} else if (edge[0] == 2) {
				edgesRequired += bob.performUnion(edge[1], edge[2]);
			}
		}

		// after traversing all edges check if both graphs are connected and return the diff
		// between all edges and required edges
		if (alice.isConnected() && bob.isConnected()) {
			return edges.length - edgesRequired;
		}
		// one of the 2 graphs is not connected
		return -1;

	}

	private static class UnionFind {

		int components; // the total number of not connected components in the graph
		int[] representative; // keeps mapping of nodes to connected ones with higher componentSize
		int[] componentSize; // keeps the count of connected nodes from each node

		UnionFind(int n) {
			components = n;
			representative = new int[n + 1];
			componentSize = new int[n + 1];

			for (int i = 0; i <= n; i++) {
				// in the beginning every component is mapped to itself
				representative[i] = i;
			}
		}

		/**
		 * Recursively checks if node x is already connected to another node with a bigger component size,
		 * and returns either x or the representative node.
		 * 
		 * @param x
		 * @return
		 */
		int findRepresentative(int x) {
			if (representative[x] == x) {
				return x;
			}

			return representative[x] = findRepresentative(representative[x]);
		}

		/**
		 * Connects nodes x and y if they are not already connected.
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		int performUnion(int x, int y) {
			x = findRepresentative(x);
			y = findRepresentative(y);

			if (x == y) {
				// nodes are already connected
				return 0;
			}

			// the nodes will be connected and the one with the higher component size
			// will act as a representative for the other one
			if (componentSize[x] > componentSize[y]) {
				componentSize[x] += componentSize[y];
				representative[y] = x;
			} else {
				componentSize[y] += componentSize[x];
				representative[x] = y;
			}

			// decrease the number of not connected components
			components--;
			return 1;
		}

		boolean isConnected() {
			// 1 not connected component means that the graph is connected
			return components == 1;
		}
	}

	private static void check(int n, int[][] edges, int expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("expected is: " + expected);
		int maxNumEdgesToRemove = maxNumEdgesToRemove(n, edges); // calls implementation
		System.out.println("maxNumEdgesToRemove is: " + maxNumEdgesToRemove);
	}
}
