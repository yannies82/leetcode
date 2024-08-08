package leetcode.arraystring;

import java.util.Arrays;

public class RangeSumOfSortedSubarraySums {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4 }, 4, 1, 5, 13);
		check(new int[] { 1, 2, 3, 4 }, 4, 3, 4, 6);
		check(new int[] { 1, 2, 3, 4 }, 4, 1, 10, 50);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/range-sum-of-sorted-subarray-sums. This
	 * solution calculates all sums, then returns the sum of the requestes ones.
	 * Time complexity is O(n^2) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param n
	 * @param left
	 * @param right
	 * @return
	 */
	public static int rangeSum(int[] nums, int n, int left, int right) {
		// create the sums array
		int sumsLength = n * (n + 1) / 2;
		int[] sums = new int[sumsLength];
		// populate the sums array with all sums
		int index = 0;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += nums[j];
				sums[index++] = sum;
			}
		}
		Arrays.sort(sums);
		// calculate and return the requested sum
		long result = 0;
		for (int i = left - 1; i < right; i++) {
			result += sums[i];
		}
		return (int) (result % (1000000000 + 7));
	}

	private static void check(int[] nums, int n, int left, int right, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("n is: " + n);
		System.out.println("left is: " + left);
		System.out.println("right is: " + right);
		System.out.println("expected is: " + expected);
		int rangeSum = rangeSum(nums, n, left, right); // Calls your implementation
		System.out.println("rangeSum is: " + rangeSum);
	}
}
