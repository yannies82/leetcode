package leetcode.arraystring;

import java.util.Arrays;

public class RotateArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, 5, new int[] { 2, 3, 1 });
		check(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3, new int[] { 5, 6, 7, 1, 2, 3, 4 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/rotate-array. This solution
	 * reverses the array, then reverses the first k elements and the rest of the
	 * elements separately. Time complexity is O(n) where n is the length of the
	 * nums array.
	 * 
	 * @param nums
	 * @param k
	 */
	public static void rotate(int[] nums, int k) {
		int length = nums.length;
		int realK = k % length;
		// reverse the full array
		reverse(nums, 0, length);
		// reverse the first realK elements of the array
		reverse(nums, 0, realK);
		// reverse the rest of the array
		reverse(nums, realK, length);
	}

	private static void reverse(int[] nums, int start, int end) {
		int lastIndex = end - 1;
		// swap element from start with element from end at every iteration
		while (start < lastIndex) {
			int temp = nums[lastIndex];
			nums[lastIndex--] = nums[start];
			nums[start++] = temp;
		}
	}

	/**
	 * This solution uses a buffer array of length k to store the rotated elements,
	 * then inserts them at the beginning of the nums array. Time complexity is O(n)
	 * where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @param k
	 */
	public static void rotateTimeEfficient(int[] nums, int k) {
		int length = nums.length;
		int realK = k % length;
		int[] buffer = new int[realK];
		int limit = length - realK;
		for (int i = limit; i < length; i++) {
			buffer[i - limit] = nums[i];
		}
		for (int i = limit - 1; i >= 0; i--) {
			nums[i + realK] = nums[i];
		}
		for (int i = 0; i < realK; i++) {
			nums[i] = buffer[i];
		}
	}

	/**
	 * This solution applies rotation by swapping elements and bringing the last k
	 * ones to the first k array positions. Then if required it applies this
	 * operation iteratively for the first k elements. Time complexity is O(n) where
	 * n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 */
	public static void rotateSpaceEfficient(int[] nums, int k) {
		int length = nums.length;
		int realK = k % length;
		while (realK > 0) {
			int limit = length - realK;
			for (int i = limit - 1; i >= 0; i--) {
				int temp = nums[i + realK];
				nums[i + realK] = nums[i];
				nums[i] = temp;
			}
			int mod = length % realK;
			length = realK;
			realK = (realK - mod) % realK;
		}
	}

	private static void check(int[] nums, int k, int[] expectedNums) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedNums is: " + Arrays.toString(expectedNums));
		// rotateSpaceEfficient(nums, k); // Calls your implementation
		rotate(nums, k); // Calls your implementation
		System.out.println("k is: " + k);
		System.out.println("rotate is: " + Arrays.toString(nums));
		assert k == expectedNums.length;
		for (int i = 0; i < expectedNums.length; i++) {
			assert nums[i] == expectedNums[i];
		}
	}
}
