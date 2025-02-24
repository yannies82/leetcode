package leetcode.graphgeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostProfitablePathInATree {

	public static void main(String[] args) {
		check(new int[][] { { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 2 } }, 1, new int[] { 3958, -9854, -8334, -9388, 3410 },
				7368);
		check(new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 3, 4 } }, 3, new int[] { -2, 4, 2, -4, 6 }, 6);
		check(new int[][] { { 0, 1 } }, 1, new int[] { -7280, 2350 }, -7280);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/most-profitable-path-in-a-tree. This solution
	 * splits the problem into two subproblems. First one is to find Bob's fixed
	 * path, using DFS and backtracking. Second one is to find the max score for
	 * Alice using DFS. Time complexity is O(n) where n is the number of nodes in
	 * the graph.
	 * 
	 * @param edges
	 * @param bob
	 * @param amount
	 * @return
	 */
	public static int mostProfitablePath(int[][] edges, int bob, int[] amount) {
		int n = amount.length;
		// generate adjacency list
		List<Integer>[] adjacencyList = new ArrayList[n];
		for (int i = 0; i < adjacencyList.length; i++) {
			adjacencyList[i] = new ArrayList<>();
		}
		for (int[] edge : edges) {
			adjacencyList[edge[0]].add(edge[1]);
			adjacencyList[edge[1]].add(edge[0]);
		}
		// find Bob's path to node 0
		List<Integer> path = new ArrayList<>();
		fillBobPath(adjacencyList, path, bob, -1);
		// keep the timestamps of intermediate nodes of Bob's path to 0
		int[] bobPath = new int[amount.length];
		for (int i = 0; i < path.size(); i++) {
			bobPath[path.get(i)] = i + 1;
		}
		// perform DFS for Alice to find the max score when reaching a leaf node
		return findAliceMaxScore(0, -1, 0, bobPath, adjacencyList, 1, amount);
	}

	private static boolean fillBobPath(List<Integer>[] adjacencyList, List<Integer> path, int node, int parentNode) {
		if (node == 0) {
			return true;
		}
		for (int neighborNode : adjacencyList[node]) {
			if (neighborNode != parentNode) {
				path.add(node);
				if (fillBobPath(adjacencyList, path, neighborNode, node)) {
					// this was the right path which led to node 0, return true
					return true;
				}
				// this was not the right path, backtrack by removing the node from the path
				path.remove(path.size() - 1);
			}
		}
		return false;
	}

	private static int findAliceMaxScore(int node, int parentNode, int currScore, int[] bobPath,
			List<Integer>[] adjacencyList, int timestamp, int[] amount) {
		if (bobPath[node] == 0 || bobPath[node] > timestamp) {
			// Bob traverses this node after Alice or not at all
			currScore += amount[node];
		} else if (bobPath[node] == timestamp) {
			// Bob traverses this node at the same time as Alice
			currScore += amount[node] / 2;
		}
		if (adjacencyList[node].size() == 1 && node != 0) {
			// we reached a leaf node, return the score
			return currScore;
		}
		int maxScore = Integer.MIN_VALUE;
		for (int neighborNode : adjacencyList[node]) {
			if (neighborNode != parentNode) {
				// update the max score
				maxScore = Math.max(maxScore, findAliceMaxScore(neighborNode, node, currScore, bobPath, adjacencyList,
						timestamp + 1, amount));
			}
		}
		return maxScore;
	}

	private static void check(int[][] edges, int bob, int[] amount, int expected) {
		for (int i = 0; i < edges.length; i++) {
			System.out.println(Arrays.toString(edges[i]));
		}
		System.out.println("bob is: " + bob);
		System.out.println("amount is: " + Arrays.toString(amount));
		System.out.println("expected is: " + expected);
		int mostProfitablePath = mostProfitablePath(edges, bob, amount); // Calls your implementation
		System.out.println("mostProfitablePath is: " + mostProfitablePath);
	}
}
