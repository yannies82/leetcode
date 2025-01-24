package leetcode.graphgeneral;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FindEventualSafeStates {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} }, List.of(2, 4, 5, 6));
		check(new int[][] { { 1, 2, 3, 4 }, { 1, 2 }, { 3, 4 }, { 0, 4 }, {} }, List.of(4));
		check(new int[][] { { 1, 3, 4, 5 }, {}, {}, {}, {}, { 2, 4 } }, List.of(0, 1, 2, 3, 4, 5));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-eventual-safe-states.
	 * This solution performs DFS to mark which nodes will be visited by other
	 * nodes. Time complexity is O(m+n) where m is the length of the graph array and
	 * n is the number of edges.
	 * 
	 * @param graph
	 * @return
	 */
	public static List<Integer> eventualSafeNodes(int[][] graph) {
		int m = graph.length;
		int[] visited = new int[m];
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			if (visited[i] == 0) {
				dfs(i, graph, visited);
			}
			if (visited[i] == 1) {
				// if visited[i] == 1 it means that this node always leads to terminal nodes
				// if visited[i] == 2 it means that the node also leads to non terminal nodes
				// and the traversal was stopped before visited[i] was set to 1
				result.add(i);
			}
		}
		return result;
	}

	private static void dfs(int node, int[][] graph, int[] visited) {
		// mark as visited during this traversal
		visited[node] = 2;
		for (int child : graph[node]) {
			if (visited[child] == 0) {
				// perform dfs only on non visited children
				dfs(child, graph, visited);
			}
			if (visited[child] == 2) {
				// stop the traversal because the child has already been visited
				return;
			}
		}
		// mark as visited for next traversals
		visited[node] = 1;
	}

	/**
	 * This solution uses topology sort and inDegree/outDegree arrays to find the
	 * result.
	 * 
	 * @param graph
	 * @return
	 */
	public static List<Integer> eventualSafeNodes2(int[][] graph) {
		int m = graph.length;
		List<Integer> result = new ArrayList<>();
		List<Set<Integer>> inDegreeSets = new ArrayList<>();
		Queue<int[]> zeroOutdegree = new ArrayDeque<>();
		int[][] outDegree = new int[m][2];
		for (int i = 0; i < m; i++) {
			inDegreeSets.add(new HashSet<>());
			outDegree[i][0] = graph[i].length;
			outDegree[i][1] = i;
			if (outDegree[i][0] == 0) {
				zeroOutdegree.offer(outDegree[i]);
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				inDegreeSets.get(graph[i][j]).add(i);
			}
		}
		do {
			int length = zeroOutdegree.size();
			for (int i = 0; i < length; i++) {
				int[] current = zeroOutdegree.poll();
				if (current[0] > 0) {
					break;
				}
				result.add(current[1]);
				for (Integer index : inDegreeSets.get(current[1])) {
					outDegree[index][0]--;
					if (outDegree[index][0] == 0) {
						zeroOutdegree.offer(outDegree[index]);
					}
				}
			}
		} while (!zeroOutdegree.isEmpty());
		Collections.sort(result);
		return result;
	}

	private static void check(int[][] graph, List<Integer> expected) {
		System.out.println("graph is: ");
		for (int[] edge : graph) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("expected is: " + expected);
		List<Integer> eventualSafeNodes = eventualSafeNodes(graph); // Calls your implementation
		System.out.println("eventualSafeNodes is: " + eventualSafeNodes);
	}
}
