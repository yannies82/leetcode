package leetcode.intervals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidArrangementOfPairs {

	public static void main(String[] args) {
		check(new int[][] { { 5, 1 }, { 4, 5 }, { 11, 9 }, { 9, 4 } },
				new int[][] { { 11, 9 }, { 9, 4 }, { 4, 5 }, { 5, 1 } });
		check(new int[][] { { 1, 3 }, { 3, 2 }, { 2, 1 } }, new int[][] { { 1, 3 }, { 3, 2 }, { 2, 1 } });
		check(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 1 } }, new int[][] { { 1, 2 }, { 2, 1 }, { 1, 3 } });
		check(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 1 }, { 4, 1 }, { 1, 4 } },
				new int[][] { { 1, 4 }, { 4, 1 }, { 1, 2 }, { 2, 1 }, { 1, 3 } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/valid-arrangement-of-pairs.
	 * This solution generates an adjacency list and uses an optimized calculation
	 * for the start node to construct the Eulerian path for the graph. Time
	 * complexity is O(m+n) where m is the length of the pairs array (edges) and n
	 * is the number of distinct values (vertices) in the pairs array.
	 * 
	 * @param pairs
	 * @return
	 */
	public static int[][] validArrangement(int[][] pairs) {
		Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
		int inOutDegreeDiff = 0;
		int inOutDegreeXor = 0;

		// generate adjacency list and calculate the diff and xor for all pairs
		for (int i = 0; i < pairs.length; i++) {
			List<Integer> list;
			if ((list = adjacencyList.get(pairs[i][0])) == null) {
				list = new ArrayList<>();
				adjacencyList.put(pairs[i][0], list);
			}
			list.add(pairs[i][1]);
			inOutDegreeDiff += pairs[i][0];
			inOutDegreeDiff -= pairs[i][1];
			inOutDegreeXor ^= pairs[i][0] ^ pairs[i][1];
		}

		// find starting node if inOutDegreeDiff > 0 (a node has one more start than an
		// end)
		// else select the first start
		int startNode = pairs[0][0];
		if (inOutDegreeDiff != 0) {
			startNode = findStartNode(pairs, inOutDegreeXor, inOutDegreeDiff);
		}

		int[] path = new int[pairs.length + 1];
		int pathSize = 0;
		int[] nodeStack = new int[pairs.length + 1];
		nodeStack[0] = startNode;
		int stackIndex = 0;

		// construct Eulerian path using a stack
		List<Integer> emptyList = Collections.emptyList();
		while (stackIndex >= 0) {
			List<Integer> neighbors = adjacencyList.getOrDefault(nodeStack[stackIndex], emptyList);
			if (neighbors.isEmpty()) {
				path[pathSize++] = nodeStack[stackIndex--];
			} else {
				nodeStack[++stackIndex] = neighbors.remove(neighbors.size() - 1);
			}
		}

		int pathEdgesCount = pathSize - 1;
		int[][] arrangement = new int[pathEdgesCount][2];
		// iterate path from end to start and construct result
		for (int i = pathEdgesCount; i > 0; --i) {
			arrangement[pathEdgesCount - i] = new int[] { path[i], path[i - 1] };
		}

		return arrangement;
	}

	// finds start node using the in out degree xor and diff
	private static int findStartNode(int[][] pairs, int inOutDegreeXor, int inOutDegreeDiff) {

		// find last set bit
		int lastSetBit = inOutDegreeXor & (-inOutDegreeXor);

		int[] nums = new int[2];
		for (int i = 0; i < pairs.length; i++) {
			if ((pairs[i][0] & lastSetBit) == 0) { // the bit is not set
				nums[0] ^= pairs[i][0];
			} else { // the bit is set
				nums[1] ^= pairs[i][0];
			}
			if ((pairs[i][1] & lastSetBit) == 0) { // the bit is not set
				nums[0] ^= pairs[i][1];
			} else { // the bit is set
				nums[1] ^= pairs[i][1];
			}
		}

		if (inOutDegreeDiff >= 0) {
			return Math.max(nums[0], nums[1]);
		}

		return Math.min(nums[0], nums[1]);
	}

	/**
	 * This solution generates an adjacency list and keeps the in/out degrees for
	 * each node to construct the Eulerian path for the graph. Time complexity is
	 * O(m+n) where m is the length of the pairs array (edges) and n is the number
	 * of distinct values (vertices) in the pairs array.
	 * 
	 * @param pairs
	 * @return
	 */
	public static int[][] validArrangement2(int[][] pairs) {
		Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
		Map<Integer, Integer> inOutDegree = new HashMap<>();

		// Build graph and count in/out degrees
		for (int i = 0; i < pairs.length; i++) {
			List<Integer> list;
			if ((list = adjacencyList.get(pairs[i][0])) == null) {
				list = new ArrayList<>();
				adjacencyList.put(pairs[i][0], list);
			}
			list.add(pairs[i][1]);
			Integer outDegree;
			if ((outDegree = inOutDegree.get(pairs[i][0])) == null) {
				outDegree = 1;
			} else {
				outDegree += 1;
			}
			inOutDegree.put(pairs[i][0], outDegree);
			Integer inDegree;
			if ((inDegree = inOutDegree.get(pairs[i][1])) == null) {
				inDegree = -1;
			} else {
				inDegree += -1;
			}
			inOutDegree.put(pairs[i][1], inDegree);
//			adjacencyList.computeIfAbsent(pairs[i][0], k -> new ArrayList<>()).add(pairs[i][1]);
//			inOutDegree.merge(pairs[i][0], 1, Integer::sum); // out-degree
//			inOutDegree.merge(pairs[i][1], -1, Integer::sum); // in-degree
		}

		// Find starting node
		int startNode = pairs[0][0];
		for (Map.Entry<Integer, Integer> entry : inOutDegree.entrySet()) {
			if (entry.getValue() == 1) {
				startNode = entry.getKey();
				break;
			}
		}

		List<Integer> path = new ArrayList<>();
		Deque<Integer> nodeStack = new ArrayDeque<>();
		nodeStack.push(startNode);

		List<Integer> emptyList = Collections.emptyList();
		while (!nodeStack.isEmpty()) {
			List<Integer> neighbors = adjacencyList.getOrDefault(nodeStack.peek(), emptyList);
			if (neighbors.isEmpty()) {
				path.add(nodeStack.pop());
			} else {
				nodeStack.push(neighbors.remove(neighbors.size() - 1));
			}
		}

		int pathSize = path.size();
		int pathEdgesCount = pathSize - 1;
		int[][] arrangement = new int[pathEdgesCount][2];

		for (int i = pathEdgesCount; i > 0; --i) {
			arrangement[pathEdgesCount - i] = new int[] { path.get(i), path.get(i - 1) };
		}

		return arrangement;
	}

	private static void check(int[][] intervals, int[][] expectedIntervals) {
		System.out.println("intervals is: ");
		for (int[] interval : intervals) {
			System.out.println(Arrays.toString(interval));
		}
		System.out.println("expectedIntervals is: ");
		for (int[] interval : expectedIntervals) {
			System.out.println(Arrays.toString(interval));
		}
		int[][] validArrangement = validArrangement(intervals); // Calls your implementation
		System.out.println("validArrangement");
		for (int[] interval : validArrangement) {
			System.out.println(Arrays.toString(interval));
		}
	}
}
