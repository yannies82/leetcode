package leetcode.arraystring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 0 }, 3);
		check(new int[] { 3, 4, -1, 1 }, 2);
		check(new int[] { 7, 8, 9, 11, 12 }, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/first-missing-positive. This
	 * solution checks if number 1 exists in the array and exits early if it does
	 * not. If it exists, it iterates all numbers and converts all zero, less than
	 * zero or greater than n to 1. It then iterates the array again and uses the
	 * numbers as indexes to add n to the positions. Finally it checks which
	 * position is less than n to get the final answer. Time complexity is O(n) and
	 * space complexity is O(1) but the initial array is modified.
	 * 
	 * @param nums
	 * @return
	 */
	
	public static int firstMissingPositive(int[] nums) {
		int n = nums.length;
		// check if number 1 exists
		boolean oneExists = false;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 1) {
				oneExists = true;
				break;
			}
		}
		if (!oneExists) {
			// early exit if number 1 does not exist
			return 1;
		}
		// convert all negative or zero or greater than n numbers to 1
		for (int i = 0; i < n; i++) {
			if (nums[i] <= 0 || nums[i] > n) {
				nums[i] = 1;
			}
		}
		// use nums[i] as index and add n to nums[nums[i] - 1]
		for (int i = 0; i < n; i++) {
			nums[(nums[i] - 1) % n] += n;
		}
		// iterate all numbers to find the one that is less than n
		// if index i is less than n this means that no number existed
		// in the nums array with value index + 1, therefore index + 1 is
		// the smallest positive number not to exist in the array
		for (int i = 0; i < n; i++) {
			if (nums[i] <= n) {
				return i + 1;
			}
		}
		// all numbers were greater than n, this means that the array
		// contained all numbers from 1 to n, therefore the smallest positive
		// number not to exist in the array is n + 1
		return n + 1;
	}

	/**
	 * Simple approach using hashset. Time complexity is O(n) where n is the length
	 * of the array and space complexity is O(n).
	 * 
	 * @param nums
	 * @return
	 */
	public static int firstMissingPositive2(int[] nums) {
		Set<Integer> existing = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				existing.add(nums[i]);
			}
		}
		int missing = 1;
		while (true) {
			if (!existing.contains(missing)) {
				return missing;
			}
			missing++;
		}
	}

	private static void check(int[] nums, int expected) {
		System.out.println("gas is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int firstMissingPositive = firstMissingPositive(nums); // Calls your implementation
		System.out.println("firstMissingPositive is: " + firstMissingPositive);
	}
}
