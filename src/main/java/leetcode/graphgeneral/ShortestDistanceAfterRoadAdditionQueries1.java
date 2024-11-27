package leetcode.graphgeneral;

import java.util.Arrays;

public class ShortestDistanceAfterRoadAdditionQueries1 {

	public static void main(String[] args) {
		check(5, new int[][] { { 2, 4 }, { 0, 2 }, { 0, 4 } }, new int[] { 3, 2, 1 });
		check(4, new int[][] { { 0, 3 }, { 0, 2 } }, new int[] { 1, 1 });
		check(5, new int[][] { { 0, 2 }, { 0, 4 } }, new int[] { 3, 1 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i.
	 * This solution uses an adjacency list and, for every query, recalculates the
	 * minimum distance from the start for the target node and adjacent nodes which
	 * are possibly affected. Time complexity is O(m*n), where m is the length of
	 * the queries array.
	 * 
	 * @param n
	 * @param queries
	 * @return
	 */
	public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
		int lastNode = n - 1;
		// keeps next nodes for each node
		int[][] adjacencyArray = new int[lastNode][];
		// keeps the count of next nodes for each node
		int[] adjacencyIndexes = new int[lastNode];
		// keeps the min distance from the start node for each node
		int[] minDistanceFromStart = new int[n];
		// space optimization: precalculate size of adjacent nodes for each node
		// in order to allocate the minimal array sizes
		for (int i = 0; i < queries.length; i++) {
			adjacencyIndexes[queries[i][0]]++;
		}
		for (int i = 0; i < lastNode; i++) {
			adjacencyArray[i] = new int[adjacencyIndexes[i] + 1];
			adjacencyIndexes[i] = 0;
			minDistanceFromStart[i] = i;
			adjacencyArray[i][adjacencyIndexes[i]++] = i + 1;
		}
		minDistanceFromStart[lastNode] = lastNode;
		int[] result = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int startNode = queries[i][0];
			int endNode = queries[i][1];
			// the new connection will possibly alter the min distance from start to endNode
			// recursively recalculate min distance from start node for the endNode and all
			// other of its adjacent nodes which are possibly affected
			calculateMinDistanceFromStart(startNode, endNode, adjacencyArray, adjacencyIndexes, minDistanceFromStart);
			// add new connection to the adjacency array
			adjacencyArray[startNode][adjacencyIndexes[startNode]++] = endNode;
			// update the result for this query index
			result[i] = minDistanceFromStart[lastNode];
		}
		return result;
	}

	private static void calculateMinDistanceFromStart(int node, int nextNode, int[][] adjacencyArray,
			int[] adjacencyIndexes, int[] minDistanceFromStart) {
		int currentDistanceFromStart = minDistanceFromStart[node] + 1;
		if (currentDistanceFromStart < minDistanceFromStart[nextNode]) {
			// new min distance is less than the existing one, set it and
			// recalculate recursively for adjacent nodes
			minDistanceFromStart[nextNode] = currentDistanceFromStart;
			if (nextNode == adjacencyArray.length) {
				// we have reached the last node, no more distances to be calculated
				return;
			}
			for (int i = 0; i < adjacencyIndexes[nextNode]; i++) {
				calculateMinDistanceFromStart(nextNode, adjacencyArray[nextNode][i], adjacencyArray, adjacencyIndexes,
						minDistanceFromStart);
			}
		}
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i.
	 * This solution keeps an adjacency list and performs BFS for each query, after
	 * adding the new connection to the adjacency list. Time complexity is O(m*n),
	 * where m is the length of the queries array.
	 * 
	 * 
	 * @param n
	 * @param queries
	 * @return
	 */
	public static int[] shortestDistanceAfterQueries2(int n, int[][] queries) {
		int[] result = new int[queries.length];
		int[][] adjacencyList = new int[n][n];
		int[] adjacencyIndexes = new int[n];
		int target = n - 1;
		for (int i = 0; i < target; i++) {
			adjacencyList[i][adjacencyIndexes[i]++] = i + 1;
		}
		int[] queue = new int[n];
		int[] visited = new int[n + 1];
		for (int i = 0; i < queries.length; i++) {
			int source = queries[i][0];
			adjacencyList[source][adjacencyIndexes[source]++] = queries[i][1];
			int count = 0;
			int visitedValue = i + 1;
			visited[0] = visitedValue;
			int tail = target;
			int head = target;
			queue[tail] = 0;
			outer: while (tail <= head) {
				int size = head - tail + 1;
				for (int j = 0; j < size; j++) {
					int current = queue[head--];
					if (current == target) {
						result[i] = count;
						break outer;
					}
					for (int k = 0; k < adjacencyIndexes[current]; k++) {
						int next = adjacencyList[current][k];
						if (visited[next] < visitedValue) {
							visited[next] = visitedValue;
							queue[--tail] = next;
						}
					}
				}
				count++;
			}
		}
		return result;
	}

	private static void check(int n, int[][] queries, int[] expected) {
		System.out.println("n is: " + n);
		System.out.println("queries is: ");
		for (int i = 0; i < queries.length; i++) {
			System.out.println(Arrays.toString(queries[i]));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] shortestDistanceAfterQueries = shortestDistanceAfterQueries(n, queries); // Calls your implementation
		System.out.println("queries is: " + Arrays.toString(shortestDistanceAfterQueries));
	}
}
