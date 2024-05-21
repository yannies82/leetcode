package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, List.of(List.of(), List.of(1), List.of(2), List.of(1, 2), List.of(3),
				List.of(1, 3), List.of(2, 3), List.of(1, 2, 3)));
		check(new int[] { 0 }, List.of(List.of(), List.of(0)));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/subsets. This solution uses
	 * recursion and backtracking to produce all possible subsets. Time complexity
	 * is O(2^n).
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		subsetsRecursive(nums, result, new ArrayList<Integer>(), 0);
		return result;
	}

	private static void subsetsRecursive(int[] nums, List<List<Integer>> result, List<Integer> current, int index) {
		if (index == nums.length) {
			// return the temp array if there are no more elements
			result.add(new ArrayList<>(current));
			return;
		}
		// skip the element and proceed to the next
		subsetsRecursive(nums, result, current, index + 1);
		// add the element and proceed to the next
		current.add(nums[index]);
		subsetsRecursive(nums, result, current, index + 1);
		// backtrack by removing the last element
		current.remove(current.size() - 1);
	}

	/**
	 * Iterative solution. Considering the length of the nums array as n, this
	 * solution uses all numbers from 0 to 2^n as bit masks in order to generate all
	 * possible combinations. Time complexity is O(n*2^n).
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> subsets2(int[] nums) {
		int n = nums.length;
		int limit = 1 << n; // 2^n

		List<List<Integer>> result = new ArrayList<>();
		// iterate all numbers from 1 to 2^n
		for (int i = 0; i < limit; i++) {
			// traverse all numbers from 0 to n - 1
			// pick num at index j if the jth bit of i is 1
			List<Integer> list = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					// pick num at index j because the jth bit of i is 1
					list.add(nums[j]);
				}
			}
			// add to the total sum
			result.add(list);
		}
		return result;
	}

	private static void check(int[] nums, List<List<Integer>> expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		List<List<Integer>> subsets = subsets(nums); // Calls your implementation
		System.out.println("subsets is: " + subsets);
	}
}
