package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllDuplicatesInArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 5, 6, 7 }, List.of());
		check(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }, List.of(2, 3));
		check(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }, List.of(2, 3));
		check(new int[] { 1, 1, 2 }, List.of(1));
		check(new int[] { 1 }, List.of());
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-all-duplicates-in-an-array. This solution
	 * iterates the array and treats its values as indexes. For each value it
	 * negates the value in index abs(value) - 1. If the value is already negated
	 * then it means that it is duplicate. Time complexity is O(n) where n is the
	 * length of the array and space complexity is O(1) but it modifies the input
	 * array.
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public static List<Integer> findDuplicates(int[] nums) {
		int n = nums.length;
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			// calculate the absolute value for this index
			// because it may already have been negated
			int absNumber = Math.abs(nums[i]);
			int targetIndex = absNumber - 1;
			if (nums[targetIndex] < 0) {
				// the number at target index is already negated, add to result
				result.add(absNumber);
			} else {
				// negate the number at target index
				nums[targetIndex] = -nums[targetIndex];
			}
		}
		return result;
	}

	/**
	 * Simple approach using hashset. Time complexity is O(n) where n is the length
	 * of the array and space complexity is O(n).
	 * 
	 * @param nums
	 * @return
	 */
	public static List<Integer> findDuplicates2(int[] nums) {
		int n = nums.length;
		Set<Integer> numSet = new HashSet<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (!numSet.add(nums[i])) {
				result.add(nums[i]);
			}
		}
		return result;
	}

	private static void check(int[] nums, List<Integer> expected) {
		System.out.println("gas is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		List<Integer> findDuplicates = findDuplicates(nums); // Calls your implementation
		System.out.println("findDuplicates is: " + findDuplicates);
	}
}
