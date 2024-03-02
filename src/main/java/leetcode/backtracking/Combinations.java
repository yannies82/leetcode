package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

	public static void main(String[] args) {
		List<List<Integer>> expected = List.of(List.of(1, 2), List.of(1, 3), List.of(1, 4), List.of(2, 3),
				List.of(2, 4), List.of(3, 4));
		check(4, 2, expected);
		expected = List.of(List.of(1), List.of(2), List.of(3), List.of(4));
		check(4, 1, expected);
	}

	/**
	 * This solution traverses recursively all eligible numbers and keeps the
	 * results in a builder array. Every time the last level is reached, a list is
	 * built from the builder array and is added to the results. Time complexity is
	 * O(n^k).
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> combine(int n, int k) {
		// keeps the current combination
		int[] builderArray = new int[k];
		// keeps the results to return
		List<List<Integer>> results = new ArrayList<>();
		// recursively traverse the numbers from 1 to n
		// and fill the results list
		for (int i = 1; i <= n; i++) {
			combineRecursive(n, k, i, 0, builderArray, results);
		}
		return results;
	}

	private static void combineRecursive(int n, int k, int i, int level, int[] builderArray,
			List<List<Integer>> results) {
		// set each char to the builder array using the level as index
		builderArray[level] = i;
		if (level == k - 1) {
			// this is the last level, therefore the last digit of an eligible combination
			// build a list from the builder array and add to the results
			List<Integer> result = new ArrayList<>();
			for (int l = 0; l < k; l++) {
				result.add(builderArray[l]);
			}
			results.add(result);
		} else {
			// traverse all eligible numbers from i + 1 to n
			for (int j = i + 1; j <= n; j++) {
				// since this is not the last digit of the combination
				// advance to the next level using j as index
				// and perform a recursive call
				combineRecursive(n, k, j, level + 1, builderArray, results);
			}
		}
	}

	private static void check(int n, int k, List<List<Integer>> expected) {
		System.out.println("n is: " + n);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		List<List<Integer>> combine = combine(n, k); // Calls your implementation
		System.out.println("combine is: " + combine);
	}
}
