package leetcode.array;

import java.util.Arrays;

public class MergeTwo2DArraysBySummingValues {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2 }, { 2, 3 }, { 4, 5 } }, new int[][] { { 1, 4 }, { 3, 2 }, { 4, 1 } },
				new int[][] { { 1, 6 }, { 2, 3 }, { 3, 2 }, { 4, 6 } });
		check(new int[][] { { 2, 4 }, { 3, 6 }, { 5, 5 } }, new int[][] { { 1, 3 }, { 4, 3 } },
				new int[][] { { 1, 3 }, { 2, 4 }, { 3, 6 }, { 4, 3 }, { 5, 5 } });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values. Time
	 * complexity is O(m+n) where m is the length of nums1 and n is the length of
	 * nums2.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[][] mergeArrays(int[][] nums1, int[][] nums2) {
		int[][] result = new int[nums1.length + nums2.length][];
		int index = 0;
		int index1 = 0;
		int index2 = 0;
		while (index1 < nums1.length && index2 < nums2.length) {
			if (nums1[index1][0] == nums2[index2][0]) {
				result[index++] = new int[] { nums1[index1][0], nums1[index1++][1] + nums2[index2++][1] };
			} else if (nums1[index1][0] < nums2[index2][0]) {
				result[index++] = nums1[index1++];
			} else {
				result[index++] = nums2[index2++];
			}
		}
		while (index1 < nums1.length) {
			result[index++] = nums1[index1++];
		}
		while (index2 < nums2.length) {
			result[index++] = nums2[index2++];
		}
		int[][] finalResult = new int[index][];
		System.arraycopy(result, 0, finalResult, 0, index);
		return finalResult;
	}

	private static void check(int[][] nums1, int[][] nums2, int[][] expected) {
		System.out.println("nums1 is: ");
		for (int[] nums : nums1) {
			System.out.println(Arrays.toString(nums));
		}
		System.out.println("nums2 is: ");
		for (int[] nums : nums2) {
			System.out.println(Arrays.toString(nums));
		}
		System.out.println("expected is: ");
		for (int[] nums : expected) {
			System.out.println(Arrays.toString(nums));
		}
		int[][] mergeArrays = mergeArrays(nums1, nums2); // Calls your implementation
		System.out.println("mergeArrays is: ");
		for (int[] nums : mergeArrays) {
			System.out.println(Arrays.toString(nums));
		}
	}
}
