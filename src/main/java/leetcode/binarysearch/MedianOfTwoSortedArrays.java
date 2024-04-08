package leetcode.binarysearch;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4 }, new int[] { 5, 6, 7, 8 }, 4.5d);
		check(new int[] { 1, 2 }, new int[] { 3, 4 }, 2.5d);
		check(new int[] { 1, 3 }, new int[] { 2 }, 2d);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/median-of-two-sorted-arrays.
	 * This solution uses binary search to find the indexes of the lower and higher
	 * medians in the final array. Time complexity is O(log(min(m, n))) where m, n
	 * the lengths of arrays nums1 and nums2 respectively.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		if (m > n) {
			// nums1 should always be shorter, therefore switch the arrays if it is not the
			// case
			return findMedianSortedArrays(nums2, nums1);
		}
		int start = 0;
		int end = m;
		// the mid of the merged array
		int totalMid = (m + n + 1) / 2;
		// perform binary search
		// select the lower and higher median elements from the first array
		// and the adjusted second array
		while (start <= end) {
			int mid1 = (start + end) / 2;
			int lowerMedianIndex1 = mid1;
			int lowerMedianIndex2 = totalMid - mid1;
			int lowerMedian1 = (lowerMedianIndex1 > 0) ? nums1[lowerMedianIndex1 - 1] : Integer.MIN_VALUE;
			int lowerMedian2 = (lowerMedianIndex2 > 0) ? nums2[lowerMedianIndex2 - 1] : Integer.MIN_VALUE;
			int higherMedian1 = (lowerMedianIndex1 < m) ? nums1[lowerMedianIndex1] : Integer.MAX_VALUE;
			int higherMedian2 = (lowerMedianIndex2 < n) ? nums2[lowerMedianIndex2] : Integer.MAX_VALUE;

			// we are trying to find median values where the lower ones are
			// both less than both higher ones of both arrays
			// if this is the case we know that the final array will have a subset
			// of these values as medians
			if (lowerMedian1 <= higherMedian2 && lowerMedian2 <= higherMedian1) {
				if ((n + m) % 2 == 0) {
					// if final array size is even then the median is calculated as the mean of the
					// two middle elements
					// the middle elements are the max lower and the min higher from both arrays at
					// the respective median positions
					return (Math.max(lowerMedian1, lowerMedian2) + Math.min(higherMedian1, higherMedian2)) / 2.0;
				}
				// if the final array size is odd, the median is a single element,
				// the max from both arrays at the lower median position
				return Math.max(lowerMedian1, lowerMedian2);
			} else if (lowerMedian1 > higherMedian2) {
				// first array median values are greater than second array respective values
				// search for median values in the lower half of the first array
				// and the adjusted upper half of the second array
				end = mid1 - 1;
			} else {
				// first array median values are less than second array respective values
				// search for median values in the upper half of the first array
				// and the adjusted lower half of the second array
				start = mid1 + 1;
			}
		}
		return 0;
	}

	/**
	 * This solution iterates the two arrays until it reaches the indexes required
	 * for the median calculation, then returns the result. Time complexity is
	 * O((m+n)/2) where m is the length of nums1 and n is the length of nums2.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		int totalLength = nums1.length + nums2.length;
		// initialize the lower median number to an out of range value
		int lowerMedianNumber = -10000000;
		// indexes required for the calculation of the median
		int higherMedianIndex = (totalLength / 2);
		int lowerMedianIndex = totalLength % 2 == 1 ? higherMedianIndex : higherMedianIndex - 1;
		// iteration indexes for both arrays
		int index1 = 0;
		int index2 = 0;
		// iterate both arrays until we can calculate the median
		while (index1 < nums1.length && index2 < nums2.length) {
			int currentNumber;
			if (nums1[index1] < nums2[index2]) {
				// current number is from the nums1 array
				currentNumber = nums1[index1];
				index1++;
			} else {
				// current number is from the nums2 array
				currentNumber = nums2[index2];
				index2++;
			}
			int currentIndex = index1 + index2 - 1;
			if (currentIndex == higherMedianIndex) {
				// we have reached the higher median index, now we can return the result
				if (lowerMedianIndex == higherMedianIndex) {
					return currentNumber;
				} else {
					return (double) (currentNumber + lowerMedianNumber) / 2;
				}
			} else if (currentIndex == lowerMedianIndex) {
				// set lower median number
				lowerMedianNumber = currentNumber;
			}
		}
		if (index1 < nums1.length) {
			// the nums2 array has been exhausted before we reached the higher median index
			// if the lower median index has also not been reached, set the lower median
			// number now
			if (lowerMedianNumber == -10000000) {
				lowerMedianNumber = nums1[lowerMedianIndex - index2];
			}
			// return the result
			return (double) (lowerMedianNumber + nums1[higherMedianIndex - index2]) / 2;
		}
		if (index2 < nums2.length) {
			// the nums1 array has been exhausted before we reached the higher median index
			// if the lower median index has also not been reached, set the lower median
			// number now
			if (lowerMedianNumber == -10000000) {
				lowerMedianNumber = nums2[lowerMedianIndex - index1];
			}
			// return the result
			return (double) (lowerMedianNumber + nums2[higherMedianIndex - index1]) / 2;
		}
		// non case
		return 0;
	}

	private static void check(int[] nums1, int[] nums2, double expected) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		System.out.println("expected is: " + expected);
		double findMin = findMedianSortedArrays(nums1, nums2); // Calls your implementation
		System.out.println("findMin is: " + findMin);
	}
}
