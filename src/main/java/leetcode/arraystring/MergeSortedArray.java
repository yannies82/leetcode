package leetcode.arraystring;

import java.util.Arrays;

public class MergeSortedArray {

	public static void main(String[] args) {
		printAndMerge(new int[] { 1, 2, 3, 0, 0, 0 }, new int[] { 2, 5, 6 });
		printAndMerge(new int[] { 1 }, new int[] {});
		printAndMerge(new int[] { 0 }, new int[] { 1 });
	}

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int i1 = m - 1;
		int i2 = n - 1;
		int pos;
		while (i2 >= 0) {
			pos = i1 + i2 + 1;
			if (i1 >= 0 && nums1[i1] >= nums2[i2]) {
				nums1[pos] = nums1[i1];
				i1--;
			} else {
				nums1[pos] = nums2[i2];
				i2--;
			}
		}
	}

	private static void printAndMerge(int[] nums1, int[] nums2) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		merge(nums1, nums1.length - nums2.length, nums2, nums2.length);
		System.out.println("merged is: " + Arrays.toString(nums1));
	}

}
