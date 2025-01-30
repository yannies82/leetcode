package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DivideNodesIntoTheMaximumNumberOfGroups {

	public static void main(String[] args) {
		check(6, new int[][] { { 1, 2 }, { 1, 4 }, { 1, 5 }, { 2, 6 }, { 2, 3 }, { 4, 6 } }, 4);
		check(3, new int[][] { { 1, 2 }, { 2, 3 }, { 3, 1 } }, -1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups.
	 * This solution first performs a bipartite check and returns -1 if the graph
	 * isn't bipartite. It then calculates the longest path in each bipartite
	 * component and assigns groups to produce the final answer.
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public static int magnificentSets(int n, int[][] edges) {
		int length = n + 1;
		int[] colors = new int[length];
		List<Integer>[] adjacencyLists = new ArrayList[length];
		for (int i = 1; i < length; i++) {
			adjacencyLists[i] = new ArrayList<>();
		}
		// build adjacency lists
		for (int[] edge : edges) {
			adjacencyLists[edge[0]].add(edge[1]);
			adjacencyLists[edge[1]].add(edge[0]);
		}
		// at most n components may exist
		List<Integer>[] components = new ArrayList[n];
		int componentsCount = 0;
		for (int i = 1; i < length; i++) {
			if (colors[i] == 0) {
				// no color has been assigned to this node, assign color 1
				// and check if it is isBipartite
				List<Integer> component = new ArrayList<>();
				// perform DFS to check for bipartiteness and fill component nodes
				if (!isBipartite(colors, adjacencyLists, i, 1, component)) {
					// a non bipartite component exists
					return -1;
				}
				components[componentsCount++] = component;
			}
		}
		int total = 0;
		for (int i = 0; i < componentsCount; i++) {
			// perform BFS to calculate the max number of groups for this component
			total += maxGroupsInComponent(adjacencyLists, components[i], length);
		}
		return total;
	}

	private static boolean isBipartite(int[] colors, List<Integer>[] adjacencyLists, int node, int color,
			List<Integer> component) {
		colors[node] = color;
		component.add(node);
		for (int neighbor : adjacencyLists[node]) {
			if (colors[neighbor] == color) {
				// color of the neighbor is same as color of this node, there is a cycle
				// with odd number of elements
				return false;
			}
			// if neighbor has not been assigned a color yet, assign a different color from
			// node and check for bipartiteness recursively
			if (colors[neighbor] == 0 && !isBipartite(colors, adjacencyLists, neighbor, 3 - color, component)) {
				return false;
			}
		}
		return true;
	}

	private static int maxGroupsInComponent(List<Integer>[] adjacencyLists, List<Integer> component, int length) {
		int maxDepth = 0;
		for (int start : component) {
			int[] depth = new int[length];
			Arrays.fill(depth, -1);
			Queue<Integer> q = new ArrayDeque<>();
			q.add(start);
			depth[start] = 0;
			while (!q.isEmpty()) {
				int node = q.poll();
				for (int nbr : adjacencyLists[node]) {
					if (depth[nbr] == -1) {
						depth[nbr] = depth[node] + 1;
						maxDepth = Math.max(maxDepth, depth[nbr]);
						q.add(nbr);
					}
				}
			}
		}
		return maxDepth + 1;
	}

	private static void check(int n, int[][] edges, int expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("expected is: " + expected);
		int magnificentSets = magnificentSets(n, edges); // Calls your implementation
		System.out.println("magnificentSets is: " + magnificentSets);
	}
}
