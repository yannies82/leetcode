package leetcode.binarysearch;

import java.util.Arrays;

public class FindMinimumInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums0 = { 4, 5, 6, 7, 0, 1, 2 };
		check(nums0, 0);
		int[] nums1 = { 1, 3 };
		check(nums1, 1);
	}

	/**
	 * This solution uses binary search. Time complexity is O(logN).
	 * 
	 * @param nums
	 * @return
	 */
	public static int findMin(int[] nums) {
		return searchRecursive(nums, 0, nums.length - 1);
	}

	private static int searchRecursive(int[] nums, int start, int end) {
		// end is inclusive, if start == end there is exactly one number left
		if (start == end) {
			return nums[start];
		}
		int mid = (start + end) / 2;
		// condition which determines if we should search the upper or lower half
		if (nums[mid] < nums[start] || nums[mid] < nums[end]) {
			return searchRecursive(nums, start, mid);
		}
		return searchRecursive(nums, mid + 1, end);
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int findMin = findMin(nums); // Calls your implementation
		System.out.println("findMin is: " + findMin);
	}
}
