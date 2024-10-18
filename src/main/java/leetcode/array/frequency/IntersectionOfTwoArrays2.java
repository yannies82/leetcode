package leetcode.array.frequency;

import java.util.Arrays;

public class IntersectionOfTwoArrays2 {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 }, new int[] { 2, 2 });
		check(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 }, new int[] { 9, 4 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/intersection-of-two-arrays-ii. This solution
	 * counts the frequency of elements in the first array, then checks their
	 * existence in the second. Time complexity is O(n+m) where n, m are the lengths
	 * of nums1 and nums2 respectively.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] intersect(int[] nums1, int[] nums2) {
		// count frequency of elements in nums1
		int[] frequency = new int[1001];
		for (int i = 0; i < nums1.length; i++) {
			frequency[nums1[i]]++;
		}
		// create intermediate array to store the results
		// the length cannot be greater than the length of the smallest array
		int[] intermediateResult = new int[Math.min(nums1.length, nums2.length)];
		int length = 0;
		// iterate the second array
		for (int i = 0; i < nums2.length; i++) {
			if (frequency[nums2[i]] > 0) {
				// if the element has appeared in the first array
				// add it to the result
				intermediateResult[length++] = nums2[i];
				frequency[nums2[i]]--;
			}
		}
		// copy the results to an array of the proper length
		int[] result = new int[length];
		System.arraycopy(intermediateResult, 0, result, 0, length);
		return result;
	}

	private static void check(int[] nums1, int[] nums2, int[] expected) {
		System.out.println("nums1 is: " + Arrays.toString(nums1));
		System.out.println("nums2 is: " + Arrays.toString(nums2));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] intersect = intersect(nums1, nums2); // Calls your implementation
		System.out.println("intersect is: " + Arrays.toString(intersect));
	}
}
