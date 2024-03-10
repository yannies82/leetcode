package leetcode.arraystring;

import java.util.Arrays;

public class IntersectionOfTwoArrays {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4 }, new int[] { 3, 4, 5, 6 }, new int[] { 3, 4 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/intersection-of-two-arrays.
	 * This solution traverses both arrays and keeps the elements existing in both.
	 * Time complexity is O(n + m) where n is the length of nums1 and m is the
	 * length of nums2.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] intersection(int[] nums1, int[] nums2) {
		boolean[] existingFirst = new boolean[1001];
		for (int i = 0; i < nums1.length; i++) {
			existingFirst[nums1[i]] = true;
		}
		int count = 0;
		int max = 0;
		boolean[] existingBoth = new boolean[1001];
		for (int i = 0; i < nums2.length; i++) {
			if (existingFirst[nums2[i]] && !existingBoth[nums2[i]]) {
				existingBoth[nums2[i]] = true;
				count++;
				if (nums2[i] > max) {
					max = nums2[i];
				}
			}
		}
		int[] result = new int[count];
		count = 0;
		for (int i = 0; i <= max; i++) {
			if (existingBoth[i]) {
				result[count++] = i;
			}
		}
		return result;
	}

	private static void check(int[] nums1, int[] nums2, int[] expected) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] intersection = intersection(nums1, nums2); // Calls your implementation
		System.out.println("intersection is: " + Arrays.toString(intersection));
	}
}
