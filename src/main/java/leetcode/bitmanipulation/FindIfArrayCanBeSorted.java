package leetcode.bitmanipulation;

import java.util.Arrays;

public class FindIfArrayCanBeSorted {

	public static void main(String[] args) {
		check(new int[] { 8, 4, 2, 30, 15 }, true);
		check(new int[] { 1, 2, 3, 4, 5 }, true);
		check(new int[] { 3, 16, 8, 4, 2 }, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-if-array-can-be-sorted.
	 * This solution calculates the number of 1s for each num. Every time that the
	 * number of 1s changes, every subsequent number has to be greater than or equal
	 * to the max of the previous numbers for the array to be sortable. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean canSortArray(int[] nums) {
		int prevNumOf1s = findNumOf1s(nums[0]);
		int minAllowedValue = 0;
		int currentMax = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int currentNumOf1s = findNumOf1s(nums[i]);
			if (currentNumOf1s == prevNumOf1s) {
				// calculate the max value of the array segment with same number of 1s
				currentMax = Math.max(currentMax, nums[i]);
			} else {
				// the number of 1s in the current number has changed, this is a new segment
				// no value in the new segment can be less than any value in the previous one
				prevNumOf1s = currentNumOf1s;
				minAllowedValue = currentMax;
				currentMax = nums[i];
			}
			if (nums[i] < minAllowedValue) {
				// the current value is less than the max value of the previous segment, return
				// false
				return false;
			}

		}
		return true;
	}

	private static int findNumOf1s(int num) {
		int count = 0;
		for (int i = 0; i < 31; i++) {
			count += num & 1;
			num = num >> 1;
		}
		return count;
	}

	private static void check(int[] ratings, boolean expected) {
		System.out.println("ratings is: " + Arrays.toString(ratings));
		System.out.println("expected is: " + expected);
		boolean canSortArray = canSortArray(ratings); // Calls your implementation
		System.out.println("canSortArray is: " + canSortArray);
	}
}
