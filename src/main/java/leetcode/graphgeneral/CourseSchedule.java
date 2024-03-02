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
	 * This solution uses a map to store the prerequisites per task. It then
	 * traverses all tasks with prerequisites recursively and tries to find if there
	 * is a cycle in the prerequisites path.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
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
			if (!visited[courseIndex] && !canFinishCourse(courseIndex, prerequisitesMap, visited, traversed)) {
				return false;
			}
		}
		return true;
	}

	private static boolean canFinishCourse(Integer courseIndex, Map<Integer, List<Integer>> prerequisitesMap,
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
					&& !canFinishCourse(prerequisite, prerequisitesMap, visited, traversed))) {
				return false;
			}
		}
		// mark course as not traversed, this path has finished
		traversed[courseIndex] = false;
		return true;
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
