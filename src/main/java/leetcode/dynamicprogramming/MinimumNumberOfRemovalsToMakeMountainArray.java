package leetcode.dynamicprogramming;

import java.util.Arrays;

public class MinimumNumberOfRemovalsToMakeMountainArray {

	public static void main(String[] args) {
		check(new int[] { 4, 3, 2, 1, 1, 2, 3, 1 }, 4);
		check(new int[] { 1, 3, 1 }, 0);
		check(new int[] { 2, 1, 1, 5, 6, 2, 3, 1 }, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array.
	 * This solution applies the binary search variant of the longest increasing
	 * subsequence algorithm twice, once from the left and once from the right and
	 * finds the index where the sum of left and right is max. Time complexity is
	 * O(nlogn) where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public static int minimumMountainRemovals(int[] nums) {
		int[] dpArrayLeft = longestIncreasingSubsequence(nums, 0, 1);
		int[] dpArrayRight = longestIncreasingSubsequence(nums, nums.length - 1, -1);
		int mountainLength = 0;
		for (int i = 0; i < nums.length; i++) {
			// if dpArrayLeft[i] is 0 or dpArrayRight[i] is 0 then i is not a mountain
			if (dpArrayLeft[i] != 0 && dpArrayRight[i] != 0) {
				mountainLength = Math.max(mountainLength, dpArrayLeft[i] + dpArrayRight[i]);
			}
		}
		// the rest of the numbers should be removed to form the mountain
		return nums.length - mountainLength - 1;

	}

	private static int[] longestIncreasingSubsequence(int[] nums, int startPosition, int step) {
		// this array keeps the longest subsequence
		int[] result = new int[nums.length];
		int searchOffset = step >>> 31;
		// add the first number to the result
		result[startPosition] = nums[startPosition];
		int index = startPosition;
		// keeps the subproblem solutions for all indexes
		int[] dpArray = new int[nums.length];
		int limit = index + step * nums.length;
		// iterate all numbers
		for (int i = index + step; i != limit; i += step) {
			int current = nums[i];
			if (current > result[index]) {
				// if current number is greater than the last one of the result
				// add it to the result
				result[index += step] = current;
				dpArray[i] = startPosition + step * index;
			} else {
				// if current number is less than the last one of the result
				// perform binary search to find the index of the result array
				// where it should be placed
				int start = startPosition;
				int end = index;
				while (start != end) {
					int mid = (start + end + searchOffset) / 2;
					if (current > result[mid]) {
						start = mid + step;
					} else {
						end = mid;
					}
				}
				// overwrite the element at this index of the result array
				result[start] = current;
				dpArray[i] = startPosition + step * start;
			}
		}
		return dpArray;
	}

	/**
	 * Alternative solution which applies the linear search variant of the longest
	 * increasing subsequence algorithm twice, once from the left and once from the
	 * right and finds the index where the sum of left and right is max. Time
	 * complexity is O(n^2) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int minimumMountainRemovals2(int[] nums) {
		int n = nums.length;
		int[] dpArrayLeft = new int[n];
		int[] dpArrayRight = new int[n];
		// find longest increasing subsequence from left
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dpArrayLeft[i] = Math.max(dpArrayLeft[i], dpArrayLeft[j] + 1);
				}
			}
		}
		// find longest increasing subsequence from right
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n - 1; j > i; j--) {
				if (nums[i] > nums[j]) {
					dpArrayRight[i] = Math.max(dpArrayRight[i], dpArrayRight[j] + 1);
				}
			}
		}
		// find the largest mountain by adding left and right for the same index
		int mountainLength = 0;
		for (int i = 0; i < n; i++) {
			// if dpArrayLeft[i] is 0 or dpArrayRight[i] is 0 then i is not a mountain
			if (dpArrayLeft[i] != 0 && dpArrayRight[i] != 0) {
				mountainLength = Math.max(mountainLength, dpArrayLeft[i] + dpArrayRight[i]);
			}
		}
		// the rest of the numbers should be removed to form the mountain
		return n - mountainLength - 1;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int minimumMountainRemovals = minimumMountainRemovals(nums); // Calls your implementation
		System.out.println("minimumMountainRemovals is: " + minimumMountainRemovals);
	}
}
