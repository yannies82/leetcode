package leetcode.graphgeneral;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule2 {

	public static void main(String[] args) {
		int[][] prerequisites0 = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
		int[] expected0 = { 2, 1, 0 };
		check(3, prerequisites0, expected0);
		int[][] prerequisites1 = { { 1, 4 }, { 2, 4 }, { 3, 1 }, { 3, 2 } };
		int[] expected1 = { 0, 4, 1, 2, 3 };
		check(5, prerequisites1, expected1);
		int[][] prerequisites2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 2 } };
		int[] expected2 = {};
		check(5, prerequisites2, expected2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/course-schedule-ii. This
	 * solution uses topological sort and BFS. Time complexity is O(n+m) where n is
	 * the number of courses and m is the length of the prerequisites array.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] numOfLinks = new int[numCourses];
		// initialize the adjacency map and the numOfLinks array
		// for each course the adjacency map keeps all courses which have the course as
		// a prerequisite
		// the numOfLinks array keeps for each course the number of courses which it has
		// as prerequisites
		Map<Integer, List<Integer>> adjacentMap = new HashMap<>();
		for (int[] prerequisite : prerequisites) {
			numOfLinks[prerequisite[0]]++;
			adjacentMap.computeIfAbsent(prerequisite[1], k -> new ArrayList<>()).add(prerequisite[0]);
		}
		// perform BFS and return the result
		return performBFS(numOfLinks, adjacentMap);
	}

	private static int[] performBFS(int[] numOfLinks, Map<Integer, List<Integer>> adjacentMap) {
		Queue<Integer> visited = new ArrayDeque<>();
		// add to the queue all elements with no prerequisites (no links)
		for (int i = 0; i < numOfLinks.length; i++) {
			if (numOfLinks[i] == 0) {
				visited.offer(i);
			}
		}
		// initialize result array and index
		int[] result = new int[numOfLinks.length];
		int resultIndex = 0;
		while (!visited.isEmpty()) {
			// remove courses from the queue and add to the result array
			int current = visited.poll();
			result[resultIndex++] = current;
			// iterate the adjacent courses of the current one
			// (courses for which the current one is a prerequisite)
			if (adjacentMap.containsKey(current)) {
				for (int next : adjacentMap.get(current)) {
					// reduce the number of links for each adjacent course and if
					// it reaches zero, add the course to the queue
					numOfLinks[next]--;
					if (numOfLinks[next] == 0) {
						visited.offer(next);
					}
				}
			}
		}
		if (resultIndex == numOfLinks.length) {
			// all of the courses were traversable and the results array is fully formed
			return result;
		}
		// a cycle exists and it was not possible to traverse all courses
		// return empty array
		return new int[0];
	}

	/**
	 * This solution uses a map to store the prerequisites per course. It then
	 * traverses all tasks with prerequisites recursively and tries to find if there
	 * is a cycle in the prerequisites path. Duplicate paths are removed in the
	 * prerequisites per course and a stack is used to store the longest path per
	 * course.
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public static int[] findOrder2(int numCourses, int[][] prerequisites) {
		// populate the prerequisitesMap with all available prerequisites
		Map<Integer, Set<Integer>> prerequisitesMap = new HashMap<>();
		for (int i = 0; i < prerequisites.length; i++) {
			// store all prerequisites per course index
			prerequisitesMap.computeIfAbsent(prerequisites[i][0], k -> new HashSet<>()).add(prerequisites[i][1]);
		}
		// remove entries from the sets that are prerequisites to elements from the same
		// set, so that no duplicate paths exist
		for (Integer prerequisite : prerequisitesMap.keySet()) {
			Set<Integer> existing = prerequisitesMap.get(prerequisite);
			for (Iterator<Integer> iter = existing.iterator(); iter.hasNext();) {
				Integer next = iter.next();
				boolean shouldRemove = false;
				for (Integer i : existing) {
					if (prerequisitesMap.get(i) != null && prerequisitesMap.get(i).contains(next)) {
						shouldRemove = true;
						break;
					}
				}
				if (shouldRemove) {
					iter.remove();
				}
			}
		}
		// keep the visited courses in this array by index in order to avoid infinite
		// loops
		boolean[] visited = new boolean[numCourses];
		// keep the current traversed courses in this array in order to detect cycles
		boolean[] traversed = new boolean[numCourses];
		// keeps the visited courses in a stack
		Deque<Integer> visitStack = new ArrayDeque<>();
		int[] result = new int[numCourses];
		int i = 0;
		for (Integer courseIndex : prerequisitesMap.keySet()) {
			// traverse all non visited courses, return false if a course cannot be finished
			if (!visited[courseIndex]
					&& !canFinishCourse2(courseIndex, prerequisitesMap, visited, traversed, visitStack)) {
				return new int[0];
			}
			// empty the stack and fill the results array
			while (!visitStack.isEmpty()) {
				result[i++] = visitStack.poll();
			}
		}
		// add to the results array all unvisited courses
		for (int j = 0; i < numCourses; j++) {
			if (!visited[j]) {
				result[i++] = j;
			}
		}
		return result;
	}

	private static boolean canFinishCourse2(Integer courseIndex, Map<Integer, Set<Integer>> prerequisitesMap,
			boolean[] visited, boolean[] traversed, Deque<Integer> visitStack) {
		// mark course as visited so that it is not traversed again
		visited[courseIndex] = true;
		visitStack.offerFirst(courseIndex);
		Set<Integer> prerequisites = prerequisitesMap.get(courseIndex);
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
					&& !canFinishCourse2(prerequisite, prerequisitesMap, visited, traversed, visitStack))) {
				return false;
			}
		}
		// mark course as not traversed, this path has finished
		traversed[courseIndex] = false;
		return true;
	}

	private static void check(int numCourses, int[][] prerequisites, int[] expected) {
		System.out.println("numCourses is: " + numCourses);
		System.out.println("prerequisites is: ");
		for (int i = 0; i < prerequisites.length; i++) {
			System.out.println(Arrays.toString(prerequisites[i]));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] findOrder = findOrder(numCourses, prerequisites); // Calls your implementation
		System.out.println("findOrder is: " + Arrays.toString(findOrder));
	}
}
