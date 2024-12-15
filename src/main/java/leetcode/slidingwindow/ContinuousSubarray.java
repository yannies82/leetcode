package leetcode.slidingwindow;

import java.util.Arrays;

public class ContinuousSubarray {

	public static void main(String[] args) {
		check(new int[] { 5, 4, 2, 4 }, 8);
		check(new int[] { 1, 2, 3 }, 6);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/continuous-subarrays. This
	 * solution uses a sliding window to count all valid continuous subarrays. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static long continuousSubarrays(int[] nums) {
		int start = -1;
		int min = nums[0] - 2;
		int max = nums[0] + 2;
		long count = 0;
		for (int end = 0; end < nums.length; end++) {
			if (nums[end] >= min && nums[end] <= max) {
				min = Math.max(min, nums[end] - 2);
				max = Math.min(max, nums[end] + 2);

			} else {
				min = nums[end] - 2;
				max = nums[end] + 2;
				for (start = end - 1; nums[start] >= min && nums[start] <= max; start--) {
					min = Math.max(min, nums[start] - 2);
					max = Math.min(max, nums[start] + 2);

				}
			}
			count += end - start;
		}
		return count;
	}

	/**
	 * Similar solution, slightly different condition logic. Time complexity is O(n)
	 * where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static long continuousSubarrays2(int[] nums) {
		int start = -1;
		int min = nums[0];
		int max = nums[0];
		long count = 0;
		for (int end = 0; end < nums.length; end++) {
			min = Math.min(min, nums[end]);
			max = Math.max(max, nums[end]);
			if (max - min > 2) {
				min = nums[end];
				max = nums[end];
				start = end - 1;
				int newMin, newMax;
				while ((newMax = Math.max(max, nums[start])) - (newMin = Math.min(min, nums[start])) <= 2) {
					min = newMin;
					max = newMax;
					start--;
				}
			}
			count += end - start;
		}
		return count;
	}

	private static void check(int[] nums, long expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		long continuousSubarrays = continuousSubarrays2(nums); // Calls your implementation
		System.out.println("continuousSubarrays is: " + continuousSubarrays);
	}
}
