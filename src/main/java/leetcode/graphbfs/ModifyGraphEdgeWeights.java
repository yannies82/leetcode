package leetcode.graphbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.ToIntBiFunction;

public class ModifyGraphEdgeWeights {

	public static void main(String[] args) {
		check(4, new int[][] { { 0, 1, -1 }, { 1, 2, -1 }, { 3, 1, -1 }, { 3, 0, 2 }, { 0, 2, 5 } }, 2, 3, 8,
				new int[][] {});
		check(5, new int[][] { { 4, 1, -1 }, { 2, 0, -1 }, { 0, 3, -1 }, { 4, 3, -1 } }, 0, 1, 5,
				new int[][] { { 4, 1, 1 }, { 2, 0, 3 }, { 0, 3, 3 }, { 4, 3, 1 } });
		check(3, new int[][] { { 0, 1, -1 }, { 0, 2, 5 } }, 0, 2, 6, new int[][] {});
		check(4, new int[][] { { 1, 0, 4 }, { 1, 2, 3 }, { 2, 3, 5 }, { 0, 3, -1 } }, 0, 2, 6,
				new int[][] { { 1, 0, 4 }, { 1, 2, 3 }, { 2, 3, 5 }, { 0, 3, 1 } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/modify-graph-edge-weights.
	 * This solution uses two runs of the Djikstra algorithm. The first one
	 * determines if the desired path with target weight can exist. The second run
	 * determines what this path is. Time complexity is O((m + n)logn) where m is
	 * the length of the edges array.
	 * 
	 * @param n
	 * @param edges
	 * @param source
	 * @param destination
	 * @param target
	 * @return
	 */
	public static int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
		// Generate adjacency list, fill with edge info
		List<List<DestInfo>> adjacencyList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		int m = edges.length;
		for (int i = 0; i < m; i++) {
			adjacencyList.get(edges[i][0]).add(new DestInfo(i, edges[i][1]));
			adjacencyList.get(edges[i][1]).add(new DestInfo(i, edges[i][0]));
		}

		// initialize weights array, we will perform 2 runs of the Djikstra algorithm
		int[][] weights = new int[2][n];
		for (int i = 0; i < n; i++) {
			if (i != source) {
				weights[0][i] = weights[1][i] = Integer.MAX_VALUE;
			}
		}
		// first run of Djikstra algorithm to find the minimum weight to every node
		// edges with unspecified weight -1 are assumed to have the minimum weight of 1
		executeDjikstra(adjacencyList, edges, weights[0], source, 0,
				(currentDest, nextDest) -> edges[nextDest.edge][2] == -1 ? 1 : edges[nextDest.edge][2]);
		int difference = target - weights[0][destination];
		// if minimum weight to destination is greater than target then there is no
		// solution
		if (difference < 0) {
			return new int[][] {};
		}
		// second run of Djikstra algorithm which takes into account the difference
		// between the target and the result of the first run when calculating the
		// weight
		executeDjikstra(adjacencyList, edges, weights[1], source, difference, (currentDest, nextDest) -> {
			int weight = edges[nextDest.edge][2];
			if (weight == -1) {
				weight = 1;
				int newWeight = difference + weights[0][nextDest.dest] - weights[1][currentDest.dest];
				if (newWeight > weight) {
					edges[nextDest.edge][2] = weight = newWeight;
				}
			}
			return weight;
		});
		// if the result of the second run is less than target then there is no solution
		if (weights[1][destination] < target) {
			return new int[][] {};
		}

		for (int i = 0; i < edges.length; i++) {
			if (edges[i][2] == -1) {
				edges[i][2] = 1;
			}
		}
		return edges;
	}

	private static void executeDjikstra(List<List<DestInfo>> adjacencyList, int[][] edges, int[] weights, int source,
			int difference, ToIntBiFunction<CurrentDestInfo, DestInfo> weightFunction) {
		// use priority queue in order to favor destinations with less weight
		Queue<CurrentDestInfo> priorityQueue = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		priorityQueue.add(new CurrentDestInfo(source, 0));
		weights[source] = 0;

		while (!priorityQueue.isEmpty()) {
			CurrentDestInfo currentDest = priorityQueue.poll();

			if (currentDest.weight > weights[currentDest.dest]) {
				// do not process this destination if it already exceeds the minimum weight for
				// this node
				continue;
			}

			List<DestInfo> nextDests = adjacencyList.get(currentDest.dest);
			// iterate for all possible adjacent destinations to the current one
			for (int i = 0; i < nextDests.size(); i++) {
				DestInfo nextDest = nextDests.get(i);
				// calculate the weight using the appropriate weight function to add to the
				// current weight
				int weight = weights[currentDest.dest] + weightFunction.applyAsInt(currentDest, nextDest);
				if (weights[nextDest.dest] > weight) {
					// a new minimum weight was found for this destination, add to queue
					weights[nextDest.dest] = weight;
					priorityQueue.add(new CurrentDestInfo(nextDest.dest, weights[nextDest.dest]));
				}
			}
		}
	}

	private static class DestInfo {
		int edge;
		int dest;

		private DestInfo(int edge, int dest) {
			super();
			this.edge = edge;
			this.dest = dest;
		}
	}

	private static class CurrentDestInfo {
		int dest;
		int weight;

		public CurrentDestInfo(int dest, int weight) {
			super();
			this.dest = dest;
			this.weight = weight;
		}
	}

	private static void check(int n, int[][] edges, int source, int destination, int target, int[][] expected) {
		System.out.println("n is: " + n);
		System.out.println("flights is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("source is: " + source);
		System.out.println("destination is: " + destination);
		System.out.println("target is: " + target);
		System.out.println("expected is: ");
		for (int[] result : expected) {
			System.out.println(Arrays.toString(result));
		}
		int[][] modifiedGraphEdges = modifiedGraphEdges(n, edges, source, destination, target); // Calls your
																								// implementation
		System.out.println("modifiedGraphEdges is: ");
		for (int[] result : modifiedGraphEdges) {
			System.out.println(Arrays.toString(result));
		}
	}
}
