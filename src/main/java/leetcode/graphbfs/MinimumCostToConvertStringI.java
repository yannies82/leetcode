package leetcode.graphbfs;

import java.util.Arrays;

public class MinimumCostToConvertStringI {

	public static void main(String[] args) {
		check("abcd", "abce", new char[] { 'a' }, new char[] { 'e' }, new int[] { 10000 }, -1);
		check("abcd", "acbe", new char[] { 'a', 'b', 'c', 'c', 'e', 'd' }, new char[] { 'b', 'c', 'b', 'e', 'b', 'e' },
				new int[] { 2, 5, 5, 1, 2, 20 }, 28);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-cost-to-convert-string-i. This solution
	 * uses the Floyd Walsall algorithm to calculate the min cost for all possible
	 * substitutions, then calculates the total cost of the substitution according
	 * to the source and target strings. Time complexity is O(k+l) where k is the
	 * length of the source string and l is the length of the original array.
	 * 
	 * @param source
	 * @param target
	 * @param original
	 * @param changed
	 * @param cost
	 * @return
	 */
	public static long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
		int n = 26;
		char[] sourceChars = source.toCharArray();
		char[] targetChars = target.toCharArray();
		// initialize substitutionCost array
		int[][] substitutionCost = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(substitutionCost[i], 1000000000);
			substitutionCost[i][i] = 0;
		}
		// populate substitutionCost array with info from original, changed and cost
		// arrays
		for (int i = 0; i < original.length; i++) {
			int originalIndex = original[i] - 'a';
			int changedIndex = changed[i] - 'a';
			if (cost[i] < substitutionCost[originalIndex][changedIndex]) {
				substitutionCost[originalIndex][changedIndex] = cost[i];
			}
		}
		// calculate all values of the substitutionCost array
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					int currentCost = substitutionCost[j][i] + substitutionCost[i][k];
					if (currentCost < substitutionCost[j][k]) {
						substitutionCost[j][k] = currentCost;
					}
				}
			}
		}
		// calculate the total minimum cost using the values of the substitutionCost
		// array
		long totalCost = 0;
		for (int i = 0; i < sourceChars.length; i++) {
			int currentCost = substitutionCost[sourceChars[i] - 'a'][targetChars[i] - 'a'];
			if (currentCost == 1000000000) {
				// substitution is not possible
				return -1;
			}
			totalCost += currentCost;
		}
		return totalCost;
	}

	private static void check(String source, String target, char[] original, char[] changed, int[] cost, int expected) {
		System.out.println("source is: " + source);
		System.out.println("target is: " + target);
		System.out.println("original is: " + Arrays.toString(original));
		System.out.println("changed is: " + Arrays.toString(changed));
		System.out.println("cost is: " + Arrays.toString(cost));
		System.out.println("expected is: " + expected);
		long minimumCost = minimumCost(source, target, original, changed, cost); // Calls your implementation
		System.out.println("minimumCost is: " + minimumCost);
	}
}
