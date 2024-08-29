package leetcode.graphunionfind;

import java.util.Arrays;

public class MostStonesRemovedWithSameRowOrColumn {

	public static void main(String[] args) {
		check(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } }, 5);
		check(new int[][] { { 0, 0 }, { 0, 2 }, { 1, 1 }, { 2, 0 }, { 2, 2 } }, 3);
		check(new int[][] { { 0, 0 } }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column.
	 * This solution treats rows and columns as distinct sets and uses union find
	 * algorithm to find all the distinct connected groups. One element will remain
	 * for each connected group, therefore the total number of removed elements will
	 * be stones.length - connectedGroupCount. Time complexity is O(n*a(n)) where n
	 * is the length of the stones array.
	 * 
	 * @param stones
	 * @return
	 */
	public static int removeStones(int[][] stones) {
		// element 0 is used as a default value
		// next 10001 elements for row sets and next 10001 elements for column sets
		int[] parents = new int[20003];
		// keeps the count of connected groups
		int[] connectedGroupCount = { 0 };
		for (int i = 0; i < stones.length; i++) {
			mergeComponents(stones[i][0] + 1, stones[i][1] + 10002, parents, connectedGroupCount);
		}
		return stones.length - connectedGroupCount[0];
	}

	private static void mergeComponents(int i, int j, int[] parents, int[] connectedGroupCount) {
		int parentI = findParent(i, parents, connectedGroupCount);
		int parentJ = findParent(j, parents, connectedGroupCount);
		if (parentI != parentJ) {
			parents[parentJ] = parentI;
			connectedGroupCount[0]--;
		}
	}

	private static int findParent(int i, int[] parents, int[] connectedGroupCount) {
		if (parents[i] == 0) {
			parents[i] = i;
			connectedGroupCount[0]++;
		} else if (parents[i] != i) {
			parents[i] = findParent(parents[i], parents, connectedGroupCount);
		}
		return parents[i];
	}

	private static void check(int[][] stones, int expected) {
		System.out.println("stones is: ");
		for (int i = 0; i < stones.length; i++) {
			System.out.println(Arrays.toString(stones[i]));
		}
		System.out.println("expected is: " + expected);
		int removeStones = removeStones(stones); // Calls your implementation
		System.out.println("removeStones is: " + removeStones);
	}
}
