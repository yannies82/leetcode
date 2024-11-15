package leetcode.slidingwindow;

import java.util.Arrays;

public class ShortestSubarrayToRemoveToMakeArraySorted {

	public static void main(String[] args) {
		check(new int[] { 2, 2, 2, 1, 1, 1 }, 3);
		check(new int[] { 1, 2, 3, 10, 4, 2, 3, 5 }, 3);
		check(new int[] { 5, 4, 3, 2, 1 }, 4);
		check(new int[] { 1, 2, 3 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted.
	 * This solution finds the first and last non decreasing subarrays in the array,
	 * since all subarrays which contain decreasing sequences should be removed in
	 * order for the final array to be non decreasing. It then uses a sliding
	 * window, iterating the elements of the first subarray and counting how many
	 * elements from the second subarray should be removed, updating the result.
	 * Time complexity is O(n) where n is the length of the arr array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int findLengthOfShortestSubarray(int[] arr) {
		// find the last index of the first non decreasing subarray starting from the
		// beginning of the array
		int endFirst = 1;
		while (endFirst < arr.length && arr[endFirst] >= arr[endFirst - 1]) {
			endFirst++;
		}
		if (endFirst == arr.length) {
			// all of the array is non decreasing, no items need to be removed
			return 0;
		}
		// find the first index of the last non decreasing subarray ending at the
		// end of the array
		int startLast = arr.length - 2;
		while (startLast >= 0 && arr[startLast] <= arr[startLast + 1]) {
			startLast--;
		}
		startLast++;
		// one possible way to make the array non decreasing is to remove all
		// elements which do not belong to the first non decreasing subarray
		// and another way is to remove all elements which do not belong to the
		// last non decreasing subarray
		// out of these two ways keep the one which removes less elements
		int result = Math.min(startLast, arr.length - endFirst);
		if (result == 1) {
			// do not search further, a better solution is not possible
			return 1;
		}
		int last = startLast;
		// iterate until the elements of one of the two arrays are exhausted
		for (int i = 0; i < endFirst && last < arr.length; i++) {
			// with the first subarray ending after i, count how many elements of the last
			// subarray should be removed
			while (last < arr.length && arr[i] > arr[last]) {
				// all elements of the last subarray which are greater than arr[i] should be
				// removed
				last++;
			}
			// count the number of elements to be removed
			int currentResult = last - i - 1;
			// update result if required
			result = Math.min(result, currentResult);
		}
		return result;
	}

	private static void check(int[] arr, int expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expected is: " + expected);
		int findLengthOfShortestSubarray = findLengthOfShortestSubarray(arr); // Calls your implementation
		System.out.println("findLengthOfShortestSubarray is: " + findLengthOfShortestSubarray);
	}
}
