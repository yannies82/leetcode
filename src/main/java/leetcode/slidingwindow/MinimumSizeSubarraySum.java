package leetcode.slidingwindow;

import java.util.Arrays;

public class MinimumSizeSubarraySum {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 5 }, 15, 5);
		check(new int[] { 2, 3, 1, 2, 4, 3 }, 7, 2);
		check(new int[] { 1, 4, 4 }, 4, 1);
		check(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 }, 11, 0);
	}

	public static int minSubArrayLen(int[] nums, int target) {
		int length = nums.length;
		int start = 0;
		int end = 0;
		int minSize = length + 1;
		int sum = 0;
		while (end < length) {
			while (sum < target && end < length) {
				sum += nums[end++];
			}
			while (sum >= target && start < length) {
				if (end - start < minSize) {
					minSize = end - start;
				}
				sum -= nums[start++];
			}
		}
		return minSize < length + 1 ? minSize : 0;
	}

	public static int minSubArrayLen2(int[] nums, int target) {
		int length = nums.length;
		int start = 0;
		int end = 0;
		int minSize = length + 1;
		int sum = nums[0];
		while (start <= end && end < length) {
			if (sum < target) {
				end++;
				if (end < length) {
					sum += nums[end];
					if (nums[end] >= target) {
						minSize = 1;
						start = end;
					}
				}
			} else {
				if (end - start < minSize) {
					minSize = end - start + 1;
				}
				sum -= nums[start];
				start++;
			}
		}
		return minSize < length + 1 ? minSize : 0;
	}

	private static void check(int[] nums, int target, int expectedSize) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedSize is: " + expectedSize);
		int minSubArrayLen = minSubArrayLen(nums, target); // Calls your implementation
		System.out.println("minSubArrayLen is: " + minSubArrayLen);
	}
}
