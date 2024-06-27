package leetcode.graphgeneral;

import java.util.Arrays;

public class FindCenterOfStarGraph {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2 }, { 2, 3 }, { 4, 2 } }, 2);
		check(new int[][] { { 1, 2 }, { 5, 1 }, { 1, 3 }, { 1, 4 } }, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-center-of-star-graph.
	 * This solution just checks the first two edges to determine the center. Time
	 * complexity is O(1).
	 * 
	 * @param edges
	 * @return
	 */
	public static int findCenter(int[][] edges) {
		if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
			return edges[0][0];
		}
		return edges[0][1];
	}

	private static void check(int[][] edges, int expected) {
		System.out.println("edges is: ");
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
		System.out.println("expected is: " + expected);
		int findCenter = findCenter(edges); // calls implementation
		System.out.println("findCenter is: " + findCenter);
	}
}
