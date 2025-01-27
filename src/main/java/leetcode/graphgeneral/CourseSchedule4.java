package leetcode.graphgeneral;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule4 {

	public static void main(String[] args) {
		check(2, new int[][] { { 1, 0 } }, new int[][] { { 0, 1 }, { 1, 0 } }, List.of(false, true));
		check(2, new int[][] {}, new int[][] { { 1, 0 }, { 0, 1 } }, List.of(false, false));
		check(3, new int[][] { { 1, 2 }, { 1, 0 }, { 2, 0 } }, new int[][] { { 1, 0 }, { 1, 2 } }, List.of(true, true));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/course-schedule-iv. This
	 * solution uses DFS from every node in order to update a 2D array with all
	 * possible answers and use this array to answer the queries. Time complexity is
	 * O(n*m+k) where n is the number of courses, m is the prerequisites array
	 * length and k is the queries array length.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @param queries
	 * @return
	 */
	public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
		if (prerequisites.length == 0) {
			List<Boolean> result = new ArrayList<>(queries.length);
			for (int i = 0; i < queries.length; i++) {
				result.add(false);
			}
			return result;
		}
		List<Integer> adjacencyLists[] = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++) {
			adjacencyLists[i] = new ArrayList<>();
		}
		for (int i = 0; i < prerequisites.length; i++) {
			adjacencyLists[prerequisites[i][0]].add(prerequisites[i][1]);
		}
		boolean isReachable[][] = new boolean[numCourses][numCourses];
		for (int i = 0; i < numCourses; i++) {
			// perform dfs for every node
			if (!isReachable[i][i]) {
				dfs(i, adjacencyLists, isReachable);
			}
		}
		List<Boolean> result = new ArrayList<>(queries.length);
		for (int i = 0; i < queries.length; i++) {
			result.add(isReachable[queries[i][0]][queries[i][1]]);
		}
		return result;
	}

	private static void dfs(int current, List<Integer> adjacencyLists[], boolean isReachable[][]) {
		isReachable[current][current] = true;
		for (int neigbor : adjacencyLists[current]) {
			if (!isReachable[current][neigbor]) {
				isReachable[current][neigbor] = true;
				dfs(neigbor, adjacencyLists, isReachable);
				// update all nodes which are depending on neighbor to also depend on current
				for (int i = 0; i < isReachable.length; i++) {
					isReachable[current][i] |= isReachable[neigbor][i];
				}
			}
		}
	}

	/**
	 * This solution uses an adjacency list and topology sort with indegree array in
	 * order to calculate the dependencies for each node and answer the queries.
	 * Time complexity is O(n*m+k) where n is the number of courses, m is the
	 * prerequisites array length and k is the queries array length.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @param queries
	 * @return
	 */
	public static List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
		List<List<Integer>> adjacencyList = new ArrayList<>();
		int[] indegree = new int[numCourses];
		// initialize adjacency lists for all nodes
		for (int i = 0; i < numCourses; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		// fill adjacency lists and keep count of the number of direct dependencies
		// for each node
		for (int i = 0; i < prerequisites.length; i++) {
			adjacencyList.get(prerequisites[i][0]).add(prerequisites[i][1]);
			indegree[prerequisites[i][1]]++;
		}

		// add to the queue all nodes with no direct dependencies
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		// initialize a dependency list which will contain for each node
		// the nodes it depends on
		List<Set<Integer>> dependencyList = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			dependencyList.add(new HashSet<>());
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();
			List<Integer> adjacent = adjacencyList.get(current);
			int adjacentLength = adjacent.size();
			for (int i = 0; i < adjacentLength; i++) {
				Integer next = adjacent.get(i);
				// the adjacent element depends on the current element and all of its
				// dependencies
				dependencyList.get(next).add(current);
				dependencyList.get(next).addAll(dependencyList.get(current));
				// reduce count of dependencies, if it is 0 add node to queue
				if (--indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}

		List<Boolean> result = new ArrayList<>();
		for (int i = 0; i < queries.length; i++) {
			result.add(dependencyList.get(queries[i][1]).contains(queries[i][0]));
		}
		return result;
	}

	private static void check(int numCourses, int[][] prerequisites, int[][] queries, List<Boolean> expected) {
		System.out.println("numCourses is: " + numCourses);
		System.out.println("prerequisites is: ");
		for (int i = 0; i < prerequisites.length; i++) {
			System.out.println(Arrays.toString(prerequisites[i]));
		}
		System.out.println("queries is: ");
		for (int i = 0; i < queries.length; i++) {
			System.out.println(Arrays.toString(queries[i]));
		}
		System.out.println("expected is: " + expected);
		List<Boolean> checkIfPrerequisite = checkIfPrerequisite(numCourses, prerequisites, queries); // Calls your
																										// implementation
		System.out.println("checkIfPrerequisite is: " + checkIfPrerequisite);
	}
}
