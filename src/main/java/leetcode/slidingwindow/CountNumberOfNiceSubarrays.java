package leetcode.slidingwindow;

import java.util.Arrays;

public class CountNumberOfNiceSubarrays {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 2, 1, 1 }, 3, 2);
		check(new int[] { 2, 4, 6 }, 1, 0);
		check(new int[] { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, 2, 16);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-number-of-nice-subarrays. This solution
	 * uses the sliding window technique and counts the number of subarrays with at
	 * least k + 1 elements and subtracts it from the number of subarrays with at
	 * least k elements to find the result. Time complexity is O(n) where n is the
	 * length of nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int numberOfSubarrays(int[] nums, int k) {
		// find the count of all arrays with at least k+1 odd numbers and subtract
		// from count of arrays with at least k odd numbers
		return numberOfSubarraysWithKOrMoreOddNumbers(nums, k) - numberOfSubarraysWithKOrMoreOddNumbers(nums, k + 1);
	}

	public static int numberOfSubarraysWithKOrMoreOddNumbers(int[] nums, int k) {
		int start = 0;
		int count = 0;
		int oddCount = 0;
		// iterate all numbers, each time increasing the window size
		for (int end = 0; end < nums.length; end++) {
			if (nums[end] % 2 == 1) {
				oddCount++;
			}
			// decrease the window size until the count of odd numbers is less than k
			while (oddCount >= k && start <= end) {
				// if the number at start is odd, decrease oddCount since it will be
				// out of the sliding window
				if (nums[start] % 2 == 1) {
					oddCount--;
				}
				start++;
			}
			// add start to the count of total subarrays
			count += start;
		}
		return count;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int numberOfSubarrays = numberOfSubarrays(nums, k); // Calls your implementation
		System.out.println("numberOfSubarrays is: " + numberOfSubarrays);
	}
}
