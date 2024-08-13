package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum2 {

	public static void main(String[] args) {
		int[] candidates0 = { 10, 1, 2, 7, 6, 1, 5 };
		List<List<Integer>> expected = List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6));
		check(candidates0, 8, expected);
		int[] candidates1 = { 2, 5, 2, 1, 2 };
		expected = List.of(List.of(1, 2, 2), List.of(5));
		check(candidates1, 5, expected);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/combination-sum-ii. This
	 * solution uses backtracking to check all possible valid number combinations
	 * and keeps the distinct ones that add up to target. Time complexity is O(2^n)
	 * where n is the length of the candidates array.
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		// sort the input array
		Arrays.sort(candidates);
		// keep the results
		List<List<Integer>> result = new ArrayList<>();
		// keep the current list
		List<Integer> current = new ArrayList<>();
		// check all combinations recursively
		findCombinations(candidates, target, 0, result, current);
		return result;
	}

	private static void findCombinations(int[] candidates, int target, int index, List<List<Integer>> result,
			List<Integer> current) {
		if (target == 0) {
			// the numbers have added up to target, add current list to result
			result.add(new ArrayList<>(current));
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			if (i > index && candidates[i] == candidates[i - 1]) {
				// skip this number if it is the same as the previous one in order to avoid
				// duplicate combinations
				continue;
			}
			if (candidates[i] > target) {
				// no point in adding numbers greater than target to the result
				break;
			}
			current.add(candidates[i]);
			findCombinations(candidates, target - candidates[i], i + 1, result, current);
			current.remove(current.size() - 1);
		}
	}

	/**
	 * Alternate solution which also uses backtracking. Time complexity is O(2^n)
	 * where n is the length of the candidates array.
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
		// keeps the distinct results to return
		Set<List<Integer>> results = new LinkedHashSet<>();
		// keeps the frequency of the numbers added to the current combination
		int[] frequency = new int[101];
		// check all combinations recursively and populate the results set
		combineRecursive(candidates, target, 0, 0, frequency, results);
		// convert the results set to a list and return it
		return new ArrayList<>(results);
	}

	private static void combineRecursive(int[] candidates, int target, int index, int currentSum, int[] frequency,
			Set<List<Integer>> results) {
		if (currentSum == target) {
			// the currentSum is equal to the target, add the combination of numbers to the
			// result
			List<Integer> listToAdd = new ArrayList<>();
			for (int i = 1; i <= target; i++) {
				for (int j = 0; j < frequency[i]; j++) {
					listToAdd.add(i);
				}
			}
			results.add(listToAdd);
			return;
		} else if (index == candidates.length || currentSum > target) {
			// there are no more candidate numbers
			return;
		}

		// do not select the number, proceed to the next one
		combineRecursive(candidates, target, index + 1, currentSum, frequency, results);

		if (candidates[index] > target
				|| (index > 0 && candidates[index] == candidates[index - 1] && frequency[candidates[index]] == 0)) {
			// do not select the number if it is greater than the target or if the previous
			// number is the same one and was not selected
			return;
		}
		// select the number and proceed to the next one
		frequency[candidates[index]]++;
		combineRecursive(candidates, target, index + 1, currentSum + candidates[index], frequency, results);
		// deselect the current number for backtracking by restoring its frequency
		frequency[candidates[index]]--;
	}

	private static void check(int[] candidates, int target, List<List<Integer>> expected) {
		System.out.println("candidates is: " + Arrays.toString(candidates));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		List<List<Integer>> combinationSum = combinationSum2(candidates, target); // Calls your implementation
		System.out.println("combinationSum is: " + combinationSum);
	}
}
