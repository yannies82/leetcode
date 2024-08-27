package leetcode.graphbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMaximumProbability {

	public static void main(String[] args) {
		check(3, new int[][] { { 0, 1 }, { 1, 2 }, { 0, 2 } }, new double[] { 0.5d, 0.5d, 0.2d }, 0, 2, 0.2500d);
		check(3, new int[][] { { 0, 1 }, { 1, 2 }, { 0, 2 } }, new double[] { 0.5d, 0.5d, 0.3d }, 0, 2, 0.3000d);
		check(3, new int[][] { { 0, 1 } }, new double[] { 0.5d }, 0, 2, 0.0000d);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/path-with-maximum-probability. This solution
	 * uses an adjacency list and a priority queue to traverse the graph from
	 * start_node to end_node, favoring routes with the highest probability. Time
	 * complexity is O(nlogn + m) where m is the length of the edges array.
	 * 
	 * @param n
	 * @param edges
	 * @param succProb
	 * @param start_node
	 * @param end_node
	 * @return
	 */
	public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
		// Generate adjacency list, fill with node info
		List<List<Node>> adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		int m = edges.length;
		// populate adjacency list in both directions
		for (int i = 0; i < m; i++) {
			adj.get(edges[i][0]).add(new Node(edges[i][1], succProb[i]));
			adj.get(edges[i][1]).add(new Node(edges[i][0], succProb[i]));
		}
		// start from start_node 1 and iterate nodes until end_node is reached
		// use priority queue to favor routes with greater probability
		Queue<Node> queue = new PriorityQueue<>((a, b) -> Double.compare(b.probability, a.probability));
		queue.add(new Node(start_node, 1));
		// keeps max probability to reach each node
		double[] maxProbability = new double[n];
		// iterate the queue as long as it has elements
		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			if (currentNode.probability <= maxProbability[currentNode.node]) {
				// do not process this node if it is reached again with lower probability than
				// before
				continue;
			}
			maxProbability[currentNode.node] = currentNode.probability;
			if (currentNode.node == end_node) {
				// we reached end node, return result
				return currentNode.probability;
			}
			List<Node> nextDests = adj.get(currentNode.node);
			// iterate for all possible adjacent nodes to the current one
			for (int j = 0; j < nextDests.size(); j++) {
				Node nextDest = nextDests.get(j);
				double newProbability = currentNode.probability * nextDest.probability;
				if (newProbability > maxProbability[nextDest.node]) {
					queue.add(new Node(nextDest.node, newProbability));
				}
			}
		}
		return 0;
	}

	private static class Node {

		private int node;
		private double probability;

		public Node(int node, double probability) {
			super();
			this.node = node;
			this.probability = probability;
		}
	}

	private static void check(int n, int[][] edges, double[] succProb, int start_node, int end_node, double expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("succProb is: " + Arrays.toString(succProb));
		System.out.println("start_node is: " + start_node);
		System.out.println("end_node is: " + end_node);
		System.out.println("expected is: " + expected);
		double maxProbability = maxProbability(n, edges, succProb, start_node, end_node); // Calls your implementation
		System.out.println("maxProbability is: " + maxProbability);
	}
}
