package leetcode.binarysearch;

import java.util.Arrays;

public class FindFirstLastPosition {

	public static void main(String[] args) {
		int[] nums2 = { 1, 4 };
		check(nums2, 4, new int[] { 1, 1 });
		int[] nums0 = { 5, 7, 7, 8, 8, 10 };
		check(nums0, 8, new int[] { 3, 4 });
		int[] nums1 = { 2, 2 };
		check(nums1, 2, new int[] { 0, 1 });
	}

	/**
	 * This solution performs two binary searches. The first finds the min index of
	 * the target element and the second finds the max index of the target element.
	 * The result is produced by combining the results of both searches. Time
	 * complexity is O(logN).
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] searchRange(int[] nums, int target) {
		// early exit in case that the array has no elements
		if (nums.length == 0) {
			return new int[] { -1, -1 };
		}
		// perform two binary searches, one for the min index and one for the max index
		// and return the result
		return new int[] { searchMin(nums, 0, nums.length - 1, target), searchMax(nums, 0, nums.length, target) };
	}

	private static int searchMin(int[] nums, int start, int end, int target) {
		// end index is inclusive
		if (start == end) {
			// if start index is equal to end index return result
			if (nums[start] == target) {
				return start;
			}
			return -1;
		}
		int mid = (start + end) / 2;
		if (target > nums[mid]) {
			return searchMin(nums, mid + 1, end, target);
		}
		return searchMin(nums, start, mid, target);
	}

	private static int searchMax(int[] nums, int start, int end, int target) {
		// end index is exclusive
		int mid = (start + end) / 2;
		if (mid == start) {
			// if mid index is equal to start index return result
			if (nums[start] == target) {
				return start;
			}
			return -1;
		}
		if (target < nums[mid]) {
			return searchMax(nums, start, mid, target);
		}
		return searchMax(nums, mid, end, target);
	}

	/**
	 * This solution performs a single binary search to find the target element,
	 * then performs two linear searches for the first and last index. Best case of
	 * time complexity is O(logN) but worst case is O(N).
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] searchRange2(int[] nums, int target) {
		// early exist in case of no elements
		if (nums.length == 0) {
			return new int[] { -1, -1 };
		}
		int[] result = new int[2];
		// recursively perform binary search and fill the result array
		searchRecursive2(nums, 0, nums.length, target, result);
		return result;
	}

	private static void searchRecursive2(int[] nums, int start, int end, int target, int[] result) {
		if (start == end - 1) {
			// only a single element is left
			if (nums[start] == target) {
				// if the element is equal to the target return the index
				result[0] = start;
				result[1] = start;
				return;
			}
			// no match, return -1
			result[0] = -1;
			result[1] = -1;
			return;
		}
		int mid = (start + end) / 2;
		if (target == nums[mid]) {
			// the mid element matches
			// perform linear searches to find first and last index
			int j = mid - 1;
			while (j >= 0 && target == nums[j]) {
				j--;
			}
			result[0] = j + 1;
			j = mid + 1;
			while (j < nums.length && target == nums[j]) {
				j++;
			}
			result[1] = j - 1;
			return;
		} else if (target < nums[mid]) {
			searchRecursive2(nums, start, mid, target, result);
		} else {
			searchRecursive2(nums, mid, end, target, result);
		}
	}

	private static void check(int[] nums, int target, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] searchRange = searchRange(nums, target); // Calls your implementation
		System.out.println("searchRange is: " + Arrays.toString(searchRange));
	}
}
