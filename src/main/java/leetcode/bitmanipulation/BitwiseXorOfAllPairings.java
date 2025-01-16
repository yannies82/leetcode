package leetcode.bitmanipulation;

import java.util.Arrays;

public class BitwiseXorOfAllPairings {

	public static void main(String[] args) {
		check(new int[] { 2, 1, 3 }, new int[] { 10, 2, 5, 0 }, 13);
		check(new int[] { 1, 2 }, new int[] { 3, 4 }, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/bitwise-xor-of-all-pairings.
	 * This solution is based on the fact that each number will be applied in the
	 * XOR operation as many times as the elements of the other array. If the number
	 * of elements in the other array is even, the elements of the first array will
	 * be cancelled out. Time complexity is O(m+n) where m is the length of the
	 * nums1 array and n is the length of the nums2 array.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int xorAllNums(int[] nums1, int[] nums2) {
		int result = 0;
		if ((nums1.length & 1) == 1) {
			for (int i = 0; i < nums2.length; i++) {
				result ^= nums2[i];
			}
		}
		if ((nums2.length & 1) == 1) {
			for (int i = 0; i < nums1.length; i++) {
				result ^= nums1[i];
			}
		}
		return result;
	}

	private static void check(int[] nums1, int[] nums2, int expected) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		System.out.println("expected is: " + expected);
		int xorAllNums = xorAllNums(nums1, nums2); // Calls your implementation
		System.out.println("xorAllNums is: " + xorAllNums);
	}

}
