package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

	public static void main(String[] args) {
		int[] nums0 = { 1, 2, 3 };
		List<List<Integer>> expected = List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1),
				List.of(3, 1, 2), List.of(3, 2, 1));
		check(nums0, expected);
		int[] nums1 = { 0, 1 };
		expected = List.of(List.of(0, 1), List.of(1, 0));
		check(nums1, expected);
		int[] nums2 = { 1 };
		expected = List.of(List.of(1));
		check(nums2, expected);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/permutations. This solution
	 * changes the order of the elements in the nums array to create the different
	 * permutations, then backtracks to restore the changes. Time complexity is
	 * O(n*n!) where n is the size of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		permuteRecursive(nums, 0, result);
		return result;
	}

	public static void permuteRecursive(int[] nums, int start, List<List<Integer>> result) {
		if (start == nums.length) {
			List<Integer> list = new ArrayList<>();
			for (int num : nums) {
				list.add(num);
			}
			result.add(list);
			return;
		}

		for (int i = start; i < nums.length; i++) {
			int temp = nums[i];
			nums[i] = nums[start];
			nums[start] = temp;
			permuteRecursive(nums, start + 1, result);
			nums[start] = nums[i];
			nums[i] = temp;
		}
	}

	/**
	 * This solution traverses recursively all eligible numbers and keeps the
	 * results in a builder array. A usedIndexes array is used to mark which indexes
	 * have been used in the current traversal. Every time the last level is
	 * reached, a list is built from the builder array and is added to the results.
	 * Time complexity is O(n*n!) where n is the size of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permute2(int[] nums) {
		// keeps the current permutation
		int[] builderArray = new int[nums.length];
		// keeps used indexes for the current traversal
		boolean[] usedIndexes = new boolean[nums.length];
		// keeps the results to return
		List<List<Integer>> results = new ArrayList<>();
		// recursively traverse the numbers from 0 to n-1
		// and fill the results list
		for (int i = 0; i < nums.length; i++) {
			permuteRecursive2(nums, i, 1, builderArray, usedIndexes, results);
		}
		return results;
	}

	private static void permuteRecursive2(int[] nums, int i, int level, int[] builderArray, boolean[] usedIndexes,
			List<List<Integer>> results) {
		// set the number at index i to the builder array using the level as index
		builderArray[level] = nums[i];
		usedIndexes[i] = true;
		if (level == nums.length) {
			// this is the last level, therefore the last digit of an eligible permutation
			// build a list from the builder array and add to the results
			List<Integer> result = new ArrayList<>();
			for (int l = 1; l <= nums.length; l++) {
				result.add(builderArray[l]);
			}
			results.add(result);
		} else {
			// traverse all eligible numbers from 0 to n-1
			for (int j = 0; j < nums.length; j++) {
				// since this is not the last digit of the combination
				// advance to the next level using j as index
				// and perform a recursive call
				// only for non traversed indexes
				if (!usedIndexes[j]) {
					permuteRecursive2(nums, j, level + 1, builderArray, usedIndexes, results);
				}
			}
		}
		// set used index to false when backtracking so that it can be reused
		usedIndexes[i] = false;
	}

	private static void check(int[] nums, List<List<Integer>> expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		List<List<Integer>> permute = permute(nums); // Calls your implementation
		System.out.println("permute is: " + permute);
		List<List<Integer>> permute2 = permute2(nums); // Calls your implementation
		System.out.println("permute2 is: " + permute2);
	}
}
