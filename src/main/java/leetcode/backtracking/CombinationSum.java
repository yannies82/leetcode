package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

	public static void main(String[] args) {
		int[] candidates0 = { 2, 3, 6, 7 };
		List<List<Integer>> expected = List.of(List.of(2, 2, 3), List.of(7));
		check(candidates0, 7, expected);
		int[] candidates1 = { 2, 3, 5 };
		expected = List.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5));
		check(candidates1, 8, expected);
		int[] candidates2 = { 2 };
		expected = List.of();
		check(candidates2, 1, expected);
	}

	/**
	 * This solution traverses recursively all candidate numbers and keeps the
	 * results in a builder array. Every time the target sum is reached, a list is
	 * built from the builder array and is added to the results. Worst case time
	 * complexity is O(N^(T/M)) where N is the length of the candidates array, T is
	 * the max target sum and M is the min candidate number.
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		// keeps the current sum
		int sum = 0;
		// keeps the current combination, length is equal to max target sum (40) / min
		// candidate number (2)
		int[] builderArray = new int[20];
		// keeps the results to return
		List<List<Integer>> results = new ArrayList<>();
		// recursively traverse the candidates
		// and fill the results list
		for (int i = 0; i < candidates.length; i++) {
			combineRecursive(candidates, i, 0, sum, target, builderArray, results);
		}
		return results;
	}

	private static void combineRecursive(int[] candidates, int i, int level, int sum, int target, int[] builderArray,
			List<List<Integer>> results) {
		// set the candidate number at index i to the builder array using the level as
		// index
		builderArray[level] = candidates[i];
		// add to the current sum
		sum += candidates[i];
		if (sum == target) {
			// the sum is equal to the target sum
			// build a list from the builder array and add to the results
			List<Integer> result = new ArrayList<>();
			for (int l = 0; l <= level; l++) {
				result.add(builderArray[l]);
			}
			results.add(result);
		} else if (sum < target) {
			// the sum is less than the target
			// traverse all eligible candidates from i to n-1
			for (int j = i; j < candidates.length; j++) {
				// advance to the next level using j as index
				// and perform a recursive call
				combineRecursive(candidates, j, level + 1, sum, target, builderArray, results);
			}
		}
		// remove candidate number from the current sum when backtracking so that the
		// sum can be reused
		sum -= candidates[i];
	}

	private static void check(int[] candidates, int target, List<List<Integer>> expected) {
		System.out.println("candidates is: " + Arrays.toString(candidates));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		List<List<Integer>> combinationSum = combinationSum(candidates, target); // Calls your implementation
		System.out.println("combinationSum is: " + combinationSum);
	}
}
