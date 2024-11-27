package leetcode.graphgeneral;

import java.util.Arrays;

public class FindChampion2 {

	public static void main(String[] args) {
		check(3, new int[][] { { 0, 1 }, { 1, 2 } }, 0);
		check(4, new int[][] { { 0, 2 }, { 1, 3 }, { 1, 2 } }, -1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-champion-ii. This
	 * solution calculates how many teams do not have superior teams to them in
	 * order to produce the result. Time complexity is O(n+m) where m is the length
	 * of the edges array.
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public static int findChampion(int n, int[][] edges) {
		boolean[] isInferior = new boolean[n];
		for (int i = 0; i < edges.length; i++) {
			isInferior[edges[i][1]] = true;
		}
		int result = -1;
		for (int i = 0; i < n; i++) {
			if (!isInferior[i]) {
				if (result != -1) {
					return -1;
				}
				result = i;
			}
		}
		return result;
	}

	private static void check(int n, int[][] edges, int expected) {
		System.out.println("n is: " + n);
		System.out.println("edges is: ");
		for (int i = 0; i < edges.length; i++) {
			System.out.println(Arrays.toString(edges[i]));
		}
		System.out.println("expected is: " + expected);
		int findChampion = findChampion(n, edges); // Calls your implementation
		System.out.println("findChampion is: " + findChampion);
	}
}
