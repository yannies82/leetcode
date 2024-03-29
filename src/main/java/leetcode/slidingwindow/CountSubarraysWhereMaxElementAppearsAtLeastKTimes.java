package leetcode.slidingwindow;

import java.util.Arrays;

public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

	public static void main(String[] args) {
		check(new int[] { 1, 3, 2, 3, 3 }, 2, 6);
		check(new int[] { 1, 4, 2, 1 }, 3, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times.
	 * This solution uses a sliding window. It iterates all numbers and in each
	 * iteration it tries to shrink the window until the max number appears less
	 * than k times in it. Time complexity is O(n) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static long countSubarrays(int[] nums, int k) {
		// calculate the max element
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		long result = 0;
		int start = 0;
		int times = 0;
		// iterate all numbers
		for (int i = 0; i < nums.length; i++) {
			// hack to avoid branching, if nums[i] == max then times += 1
			times += ((nums[i] - max) >> 31) + 1;
			// try to shrink the window until the max number appears less than k
			// times in it
			while (times >= k && start <= i) {
				// hack to avoid branching, if nums[start] == max then times -= 1
				times -= ((nums[start] - max) >> 31) + 1;
				start++;
			}
			// all subarrays starting at (0 to start - 1) and ending at i contain the
			// max number at least k times
			result += start;
		}
		return result;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		long countSubarrays = countSubarrays(nums, k); // Calls your implementation
		System.out.println("countSubarrays is: " + countSubarrays);
	}
}
