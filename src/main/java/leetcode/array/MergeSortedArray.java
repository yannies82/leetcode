package leetcode.array;

import java.util.Arrays;

public class MergeSortedArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 0, 0, 0 }, new int[] { 2, 5, 6 });
		check(new int[] { 1 }, new int[] {});
		check(new int[] { 0 }, new int[] { 1 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/merge-sorted-array. This
	 * solution uses merge sort, starting the merge from the end of the first array
	 * where there are empty positions. Time complexity is O(m+n).
	 * 
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int i1 = m - 1;
		int i2 = n - 1;
		int pos = i1 + i2 + 1;
		// start merge from the end of both arrays and place elements at
		// the end of the first array
		while (i1 >= 0 && i2 >= 0) {
			if (nums1[i1] >= nums2[i2]) {
				nums1[pos--] = nums1[i1--];
			} else {
				nums1[pos--] = nums2[i2--];
			}
		}
		// if i2 >=0 set the remaining elements to the first array
		// if i1 >= 0 there is no need to do anything, the elements are already there
		while (i2 >= 0) {
			nums1[i2] = nums2[i2--];
		}
	}

	private static void check(int[] nums1, int[] nums2) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		merge(nums1, nums1.length - nums2.length, nums2, nums2.length);
		System.out.println("merged is: " + Arrays.toString(nums1));
	}

}
