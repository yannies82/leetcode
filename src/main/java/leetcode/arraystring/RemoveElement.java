package leetcode.arraystring;

import java.util.Arrays;

public class RemoveElement {

	public static void main(String[] args) {
		check(new int[] { 3, 2, 2, 3 }, 3);
		check(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }, 2);
	}

	public static int removeElement(int[] nums, int val) {
		int i = 0;
		int count = 0;
		int length = nums.length;
		while (i < length - count) {
			if (nums[i] == val) {
				count++;
				nums[i] = nums[length - count];
			} else {
				i++;
			}
		}
		return length - count;
	}

	private static void check(int[] nums, int val) {
		System.out.println("nums is: " + Arrays.toString(nums));
		int[] expectedNums = Arrays.stream(nums).filter(i -> i != val).sorted().toArray();
		System.out.println("expectedNums is: " + Arrays.toString(expectedNums));
		int k = removeElement(nums, val); // Calls your implementation
		System.out.println("k is: " + k);
		System.out.println("final nums is: " + Arrays.toString(nums));
		assert k == expectedNums.length;
		Arrays.sort(nums, 0, k); // Sort the first k elements of nums
		for (int i = 0; i < expectedNums.length; i++) {
			assert nums[i] == expectedNums[i];
		}
	}
}
