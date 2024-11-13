package leetcode.twopointers;

import java.util.Arrays;

public class CountTheNumberOfFairPairs {

	public static void main(String[] args) {
		check(new int[] { 6, 9, 4, 2, 7, 5, 10, 3 }, 13, 13, 3);
		check(new int[] { 0, 1, 7, 4, 4, 5 }, 3, 6, 6);
		check(new int[] { 1, 7, 9, 2, 5 }, 11, 11, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-the-number-of-fair-pairs. This solution
	 * uses two pointers to count the number of pairs with sum less than or equal to
	 * upper and subtracts from them the number of pairs with sum less than or equal
	 * to lower - 1. Time complexity is O(n) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @param lower
	 * @param upper
	 * @return
	 */
	public static long countFairPairs(int[] nums, int lower, int upper) {
		Arrays.sort(nums);
		long lessThanUpperCount = findLessThanOrEqualTo(nums, upper);
		long lessThanLowerMinus1Count = findLessThanOrEqualTo(nums, lower - 1);
		return lessThanUpperCount - lessThanLowerMinus1Count;
	}

	private static long findLessThanOrEqualTo(int[] nums, int targetSum) {
		long pairs = 0;
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			if (nums[left] + nums[right] <= targetSum) {
				pairs += right - left;
				left++;
			} else {
				right--;
			}
		}
		return pairs;
	}

	/**
	 * This solution uses binary search to find the eligible pairs. Time complexity
	 * is O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param lower
	 * @param upper
	 * @return
	 */
	public static long countFairPairs2(int[] nums, int lower, int upper) {
		Arrays.sort(nums);
		int lastIndex = nums.length - 1;
		if (nums.length == 1 || nums[0] + nums[1] > upper || nums[lastIndex] + nums[lastIndex - 1] < lower) {
			// early exit if it is not possible for an eligible pair to exist
			return 0;
		}
		long count = 0;
		for (int i = 0; i < lastIndex; i++) {
			if (nums[i] + nums[i + 1] > upper) {
				// it is no longer possible for an eligible pair to exist
				return count;
			}
			// find index which is greater than or equal to the required value for lower
			int indexGreaterThanLower = binarySearchLower(nums, i + 1, lower - nums[i]);
			if (indexGreaterThanLower == -1) {
				continue;
			}
			// find index which is less than the required value for upper
			int indexLessThanUpper = binarySearchUpper(nums, indexGreaterThanLower, upper - nums[i]);
			if (indexLessThanUpper == -1) {
				continue;
			}// increase count by the appropriate amount
			count += indexLessThanUpper - indexGreaterThanLower + 1;
		}
		return count;
	}

	private static int binarySearchLower(int[] nums, int start, int target) {
		int low = start;
		int high = nums.length;
		int result = -1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (nums[mid] >= target) {
				result = mid;
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return result;
	}

	private static int binarySearchUpper(int[] nums, int start, int target) {
		int low = start;
		int high = nums.length;
		int result = -1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (nums[mid] <= target) {
				result = mid;
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return result;
	}

	public static long countFairPairs3(int[] nums, int lower, int upper) {
		int lastIndex = nums.length - 1;
		long count = 0;
		for (int i = 0; i < lastIndex; i++) {
			long lowerLimit = lower - nums[i];
			long upperLimit = upper - nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] >= lowerLimit && nums[j] <= upperLimit) {
					count++;
				}
			}
		}
		return count;
	}

	private static void check(int[] nums, int lower, int upper, long expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("lower is: " + lower);
		System.out.println("upper is: " + upper);
		System.out.println("expected is: " + expected);
		long countFairPairs = countFairPairs(nums, lower, upper); // Calls your implementation
		System.out.println("countFairPairs is: " + countFairPairs);
	}
}
