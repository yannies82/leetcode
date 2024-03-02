package leetcode.arraystring;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray2 {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 1, 2, 2, 3 }, new int[] { 1, 1, 2, 2, 3 });
		check(new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 }, new int[] { 0, 0, 1, 1, 2, 3, 3 });
	}

	public static int removeDuplicates(int[] nums) {
		int length = nums.length;
		int insertionIndex = 1;
		int currentValue = nums[0];
		int occurences = 1;
		for (int i = 1; i < length; i++) {
			if (nums[i] == currentValue) {
				occurences++;
			} else {
				currentValue = nums[i];
				occurences = 1;
			}
			if (occurences <= 2) {
				nums[insertionIndex] = nums[i];
				insertionIndex++;
			}
		}
		return insertionIndex;
	}

	private static void check(int[] nums, int[] expectedNums) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedNums is: " + Arrays.toString(expectedNums));
		int k = removeDuplicates(nums); // Calls your implementation
		System.out.println("k is: " + k);
		System.out.println("final nums is: " + Arrays.toString(nums));
		assert k == expectedNums.length;
		for (int i = 0; i < expectedNums.length; i++) {
			assert nums[i] == expectedNums[i];
		}
	}
}
