package leetcode.binarysearch;

import java.util.Arrays;

public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums0 = { 4, 5, 6, 7, 0, 1, 2 };
		check(nums0, 0, 4);
		check(nums0, 2, 6);
		int[] nums1 = { 1, 3 };
		check(nums1, 1, 0);
		check(nums1, 3, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/search-in-rotated-sorted-array. Performs a
	 * binary search to find the target value in the rotated sorted array. Time
	 * complexity is O(logN) where N is the length of the array.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int search(int[] nums, int target) {
		return searchRecursive(nums, 0, nums.length, target);
	}

	private static int searchRecursive(int[] nums, int start, int end, int target) {
		if (start == end - 1) {
			if (nums[start] == target) {
				return start;
			}
			return -1;
		}
		int mid = (start + end) / 2;
		// condition is more complex because the nums array is rotated
		if ((nums[mid] > nums[start] && target < nums[mid] && target >= nums[start])
				|| (nums[mid] < nums[start] && (target < nums[mid] || target >= nums[start]))) {
			return searchRecursive(nums, start, mid, target);
		}
		return searchRecursive(nums, mid, end, target);
	}

	private static void check(int[] nums, int target, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		int searchInsert = search(nums, target); // Calls your implementation
		System.out.println("searchInsert is: " + searchInsert);
	}
}
