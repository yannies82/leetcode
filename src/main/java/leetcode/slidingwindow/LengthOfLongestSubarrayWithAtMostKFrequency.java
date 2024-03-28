package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubarrayWithAtMostKFrequency {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 1, 2, 3, 1, 2 }, 2, 6);
		check(new int[] { 1, 2, 1, 2, 1, 2, 1, 2 }, 1, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency.
	 * This solution uses a sliding window which keeps the max subarray for which
	 * the frequency of all numbers is not greater than k. Time complexity is O(n)
	 * where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int maxSubarrayLength(int[] nums, int k) {
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		int start = 0;
		int maxLength = 0;
		// iterate all numbers, each time increasing the window size
		for (int end = 0; end < nums.length; end++) {
			// add element to the map along with its frequency
			frequencyMap.put(nums[end], frequencyMap.getOrDefault(nums[end], 0) + 1);
			// decrease the window size until the frequency of the current element is
			// less than or equal to k
			while (frequencyMap.getOrDefault(nums[end], 0) > k && start <= end) {
				frequencyMap.put(nums[start], frequencyMap.get(nums[start]) - 1);
				start++;
			}
			// update max length if it is less than the length of the current subarray
			int length = end - start + 1;
			if (length > maxLength) {
				maxLength = length;
			}
		}
		return maxLength;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int maxSubarrayLength = maxSubarrayLength(nums, k); // Calls your implementation
		System.out.println("maxSubarrayLength is: " + maxSubarrayLength);
	}
}
