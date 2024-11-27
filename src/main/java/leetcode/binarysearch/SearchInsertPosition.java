package leetcode.binarysearch;

import java.util.Arrays;

public class SearchInsertPosition {

	public static void main(String[] args) {
		int[] nums0 = { 1, 3, 5, 6 };
		check(nums0, 5, 2);
		check(nums0, 2, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/search-insert-position.
	 * Performs a binary search to find the target value in the sorted array. Time
	 * complexity is O(logN) where N is the length of the array.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int searchInsert(int[] nums, int target) {
		return searchRecursive(nums, 0, nums.length, target);
	}

	private static int searchRecursive(int[] nums, int start, int end, int target) {
		if (start == end - 1) {
			if (nums[start] >= target) {
				return start;
			}
			return start + 1;
		}
		int mid = (start + end) / 2;
		if (target < nums[mid]) {
			return searchRecursive(nums, start, mid, target);
		}
		return searchRecursive(nums, mid, end, target);
	}

	/**
	 * Iterative solution. Time complexity is O(logN) where N is the length of the
	 * array.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int searchInsert2(int[] nums, int target) {
		int start = 0;
		int end = nums.length;
		while (start < end) {
			int mid = (start + end) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (target < nums[mid]) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}

	private static void check(int[] nums, int target, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		int searchInsert = searchInsert(nums, target); // Calls your implementation
		System.out.println("searchInsert is: " + searchInsert);
	}
}
