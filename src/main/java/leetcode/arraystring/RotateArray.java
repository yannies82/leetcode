package leetcode.arraystring;

import java.util.Arrays;

public class RotateArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, 5, new int[] { 2, 3, 1 });
		check(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3, new int[] { 5, 6, 7, 1, 2, 3, 4 });
	}

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

	public static void rotateSpaceEfficient(int[] nums, int k) {
		int length = nums.length;
		int realK = k % length;
		int temp;
		int offset;
		if (realK == 0) {
			return;
		}
		do {
			int limit = length - realK;
			for (int i = limit - 1; i >= 0; i--) {
				temp = nums[i + realK];
				nums[i + realK] = nums[i];
				nums[i] = temp;
			}
			offset = length % realK;
			length = realK;
			realK = length - offset;
		} while (offset > 0);
	}

	private static void check(int[] nums, int k, int[] expectedNums) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedNums is: " + Arrays.toString(expectedNums));
		// rotateSpaceEfficient(nums, k); // Calls your implementation
		rotateTimeEfficient(nums, k); // Calls your implementation
		System.out.println("k is: " + k);
		System.out.println("rotate is: " + Arrays.toString(nums));
		assert k == expectedNums.length;
		for (int i = 0; i < expectedNums.length; i++) {
			assert nums[i] == expectedNums[i];
		}
	}
}
