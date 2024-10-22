package leetcode.array;

import java.util.Arrays;

public class MinimumCommonValue {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, new int[] { 2, 4 }, 2);
		check(new int[] { 1, 2, 3, 6 }, new int[] { 2, 3, 4, 5 }, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-common-value. Time
	 * complexity is O(m+n) where m is the length of nums1 and n is the length of
	 * nums2.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int getCommon(int[] nums1, int[] nums2) {
		if (nums1[nums1.length - 1] < nums2[0] || nums2[nums2.length - 1] < nums1[0]) {
			// early exit if the arrays have non overlapping ranges
			return -1;
		}
		int index1 = 1;
		int index2 = 1;
		int current1 = nums1[0];
		int current2 = nums2[0];
		while (index1 < nums1.length || index2 < nums2.length) {
			while (index1 < nums1.length && current1 < current2) {
				current1 = nums1[index1++];
			}
			while (index2 < nums2.length && current2 < current1) {
				current2 = nums2[index2++];
			}
			if (current1 == current2) {
				return current1;
			}
		}
		return -1;
	}

	/**
	 * Alternative solution. Time complexity is O(m+n) where m is the length of
	 * nums1 and n is the length of nums2.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int getCommon2(int[] nums1, int[] nums2) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < nums1.length && index2 < nums2.length) {
			if (nums1[index1] > nums2[index2]) {
				index2++;
			} else if (nums1[index1] < nums2[index2]) {
				index1++;
			} else {
				return nums1[index1];
			}
		}
		return -1;
	}

	private static void check(int[] nums1, int[] nums2, int expected) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		System.out.println("expected is: " + expected);
		int getCommon = getCommon(nums1, nums2); // Calls your implementation
		System.out.println("getCommon is: " + getCommon);
	}
}
