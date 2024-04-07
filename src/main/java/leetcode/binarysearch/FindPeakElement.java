package leetcode.binarysearch;

import java.util.Arrays;

public class FindPeakElement {

	public static void main(String[] args) {
		int[] nums0 = { 1, 2, 3, 1 };
		check(nums0, 2);
		int[] nums1 = { 1, 2, 1, 3, 5, 6, 4 };
		check(nums1, 5);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-peak-element. This
	 * solution uses binary search and has O(logN) time complexity.
	 * 
	 * @param nums
	 * @return
	 */
	public static int findPeakElement(int[] nums) {
		// early exit if the array has a single element
		if (nums.length == 1) {
			return 0;
		}
		return searchRecursive(nums, 0, nums.length);
	}

	private static int searchRecursive(int[] nums, int start, int end) {
		// find middle element
		int mid = (start + end) / 2;
		boolean greaterThanPrev = mid == 0 || nums[mid] > nums[mid - 1];
		boolean greaterThanNext = (mid == nums.length - 1) || nums[mid] > nums[mid + 1];
		if (greaterThanPrev && greaterThanNext) {
			// return middle element if it is greater than the prev and the next one (peak)
			return mid;
		} else if (greaterThanPrev) {
			// if the mid element is greater than the previous one it should be
			// less than the next one, therefore a peak should exist in the range
			// [mid, end]
			return searchRecursive(nums, mid, end);
		}
		// the mid element is greater than the next one and it should be
		// less than the previous one, therefore a peak should exist in the range
		// [start, mid]
		return searchRecursive(nums, start, mid);
	}

	/**
	 * This is a simple solution with O(N) time complexity.
	 * 
	 * @param nums
	 * @return
	 */
	public static int findPeakElement2(int[] nums) {
		// early exit if the array has a single element
		if (nums.length == 1) {
			return 0;
		}
		// check first element
		if (nums[0] > nums[1]) {
			return 0;
		}
		// check last element
		if (nums[nums.length - 1] > nums[nums.length - 2]) {
			return nums.length - 1;
		}
		// check all intermediate elements
		for (int i = 1; i < nums.length - 1; i++) {
			if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
				return i;
			}
		}
		// no peak found
		return -1;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int findPeakElement = findPeakElement(nums); // Calls your implementation
		System.out.println("findPeakElement is: " + findPeakElement);
	}
}
