package leetcode.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		check(new int[] { 0 }, 1);
		check(new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 }, 6);
		check(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }, 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/longest-increasing-subsequence. This solution
	 * iterates the numbers and keeps the longest subsequence in an array. If the
	 * next number is greater than the previous one, it is added to the array. If it
	 * is smaller, binary search is performed in the result array and the number
	 * overwrites an existing element. Time complexity is O(n*logn) where n is the
	 * length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int lengthOfLIS(int[] nums) {
		// this array keeps the longest subsequence
		int[] result = new int[nums.length];
		// index for the result array
		int count = 0;
		// add the first number to the result
		result[count++] = nums[0];
		// iterate all numbers
		for (int i = 1; i < nums.length; i++) {
			int current = nums[i];
			if (current > result[count - 1]) {
				// if current number is greater than the last one of the result
				// add it to the result
				result[count++] = current;
			} else {
				// if current number is less than the last one of the result
				// perform binary search to find the index of the result array
				// where it should be placed
				int left = 0;
				int right = count - 1;
				while (left < right) {
					int mid = (left + right) / 2;
					if (current > result[mid]) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				// overwrite the element at this index of the result array
				result[left] = current;
			}
		}
		// return the size of the result array
		return count;
	}

	/**
	 * This solution uses bottom up dynamic programming to calculate the solutions
	 * for all subproblems for indexes 0 to n-1 and then selects the max value from
	 * the solutions. Time complexity is O(n^2) where n is the length of array nums.
	 * 
	 * @param nums
	 * @return
	 */
	public static int lengthOfLIS2(int[] nums) {
		int[] dpArray = new int[nums.length];
		Arrays.fill(dpArray, 1);
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j] && dpArray[i] < dpArray[j] + 1) {
					dpArray[i] = dpArray[j] + 1;
				}
			}
		}
		int max = dpArray[0];
		for (int i = 1; i < nums.length; i++) {
			if (max < dpArray[i]) {
				max = dpArray[i];
			}
		}
		return max;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int lengthOfLIS = lengthOfLIS(nums); // Calls your implementation
		System.out.println("lengthOfLIS is: " + lengthOfLIS);
	}

}
