package leetcode.graphgeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {

	public static void main(String[] args) {
		int[][] prerequisites0 = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
		check(3, prerequisites0, true);
		int[][] prerequisites1 = { { 1, 4 }, { 2, 4 }, { 3, 1 }, { 3, 2 } };
		check(5, prerequisites1, true);
		int[][] prerequisites2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 2 } };
		check(5, prerequisites2, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/course-schedule. This
	 * solution uses an adjacency list and recursively checks for a cycle for all
	 * courses. Time complexity is O(n + m) where n is the number of courses and m
	 * is the number of prerequisites.
	 * 
	 * @param n
	 * @param prerequisites
	 * @return
	 */
	public static boolean canFinish(int n, int[][] prerequisites) {
		// initialize adjacency lists
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int[] edge : prerequisites) {
			// for each prerequisite record the course depending on the other one is the
			// dest and the other course is the source
			// add an entry with value dest to the adjacency list at index source
			int source = edge[1];
			int dest = edge[0];
			adj.get(source).add(dest);
		}
		// initialize visited array to a value which indicates that the course at index
		// i has not been visited yet
		int[] visited = new int[n];
		for (int i = 0; i < n; i++) {
			visited[i] = -1;
		}
		// iterate all courses
		for (int i = 0; i < n; i++) {
			// for each non visited course check that a cycle does not exist
			if (visited[i] == -1 && cycleExists(i, adj, visited)) {
				return false;
			}
		}
		return true;
	}

	private static boolean cycleExists(int source, List<List<Integer>> adj, int[] visited) {
		if (visited[source] == 0) {
			// the course at index source is currently checked for a cycle and is traversed
			// again, this means that a cycle exists
			return true;
		}
		if (visited[source] == 1) {
			// the course at index source was checked for a cycle and was not found to
			// contain one
			return false;
		}
		// mark the index source as currently checked for a cycle
		visited[source] = 0;
		for (int dest : adj.get(source)) {
			// check all adjacent dest nodes to the source for a cycle
			if (cycleExists(dest, adj, visited)) {
				return true;
			}
		}
		// the source and all adjacent indexes were found not to contain a cycle
		// mark source accordingly
		visited[source] = 1;
		return false;
	}

	/**
	 * This solution uses a map to store the prerequisites per task. It then
	 * traverses all tasks with prerequisites recursively and tries to find if there
	 * is a cycle in the prerequisites path.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public static boolean canFinish2(int numCourses, int[][] prerequisites) {
		// populate the prerequisitesMap with all available prerequisites
		Map<Integer, List<Integer>> prerequisitesMap = new HashMap<>();
		for (int i = 0; i < prerequisites.length; i++) {
			// store all prerequisites per course index
			prerequisitesMap.computeIfAbsent(prerequisites[i][0], k -> new ArrayList<>()).add(prerequisites[i][1]);
		}
		// keep the visited courses in this array by index in order to avoid infinite
		// loops
		boolean[] visited = new boolean[numCourses];
		// keep the current traversed courses in this array in order to detect cycles
		boolean[] traversed = new boolean[numCourses];
		for (Integer courseIndex : prerequisitesMap.keySet()) {
			// traverse all non visited courses, return false if a course cannot be finished
			if (!visited[courseIndex] && !canFinishCourse2(courseIndex, prerequisitesMap, visited, traversed)) {
				return false;
			}
		}
		return true;
	}

	private static boolean canFinishCourse2(Integer courseIndex, Map<Integer, List<Integer>> prerequisitesMap,
			boolean[] visited, boolean[] traversed) {
		// mark course as visited so that it is not traversed again
		visited[courseIndex] = true;
		List<Integer> prerequisites = prerequisitesMap.get(courseIndex);
		if (prerequisites == null) {
			// no prerequisites, no cycle
			return true;
		}
		// mark course as traversed in the current path
		traversed[courseIndex] = true;
		for (Integer prerequisite : prerequisites) {
			// return false if the prerequisite is already traversed in the current path (a
			// cycle exists)
			// or if it is not visited and cannot be finished (recursively)
			if (traversed[prerequisite] || (!visited[prerequisite]
					&& !canFinishCourse2(prerequisite, prerequisitesMap, visited, traversed))) {
				return false;
			}
		}
		// mark course as not traversed, this path has finished
		traversed[courseIndex] = false;
		return true;
	}

	/**
	 * This solution uses dynamic programming.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public static boolean canFinish3(int numCourses, int[][] prerequisites) {
		// populate the prerequisitesMap with all available prerequisites
		Map<Integer, List<Integer>> prerequisitesMap = new HashMap<>();
		for (int i = 0; i < prerequisites.length; i++) {
			// store all prerequisites per course index
			prerequisitesMap.computeIfAbsent(prerequisites[i][0], k -> new ArrayList<>()).add(prerequisites[i][1]);
		}
		// keep the result for each course index
		Boolean[] dpArray = new Boolean[numCourses];
		// keep the current traversed courses in this array in order to detect cycles
		boolean[] traversed = new boolean[numCourses];
		for (Integer courseIndex : prerequisitesMap.keySet()) {
			// traverse all non visited courses, return false if a course cannot be finished
			if (!canFinishCourse3(courseIndex, prerequisitesMap, traversed, dpArray)) {
				return false;
			}
		}
		return true;
	}

	private static boolean canFinishCourse3(Integer courseIndex, Map<Integer, List<Integer>> prerequisitesMap,
			boolean[] traversed, Boolean[] dpArray) {
		if (dpArray[courseIndex] != null) {
			return dpArray[courseIndex];
		}
		List<Integer> prerequisites = prerequisitesMap.get(courseIndex);
		if (prerequisites == null) {
			// no prerequisites, no cycle
			return dpArray[courseIndex] = true;
		}
		// mark course as traversed in the current path
		traversed[courseIndex] = true;
		for (Integer prerequisite : prerequisites) {
			// return false if the prerequisite is already traversed in the current path (a
			// cycle exists)
			// or if it cannot be finished (recursively)
			if (traversed[prerequisite] || !canFinishCourse3(prerequisite, prerequisitesMap, traversed, dpArray)) {
				return dpArray[courseIndex] = false;
			}
		}
		// mark course as not traversed, this path has finished
		traversed[courseIndex] = false;
		return dpArray[courseIndex] = true;
	}

	private static void check(int numCourses, int[][] prerequisites, boolean expected) {
		System.out.println("numCourses is: " + numCourses);
		System.out.println("prerequisites is: ");
		for (int i = 0; i < prerequisites.length; i++) {
			System.out.println(Arrays.toString(prerequisites[i]));
		}
		System.out.println("expected is: " + expected);
		boolean canFinish = canFinish(numCourses, prerequisites); // Calls your implementation
		System.out.println("canFinish is: " + canFinish);
	}
}
