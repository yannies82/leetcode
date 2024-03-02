package leetcode.arraystring;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 2 });
		check(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 });
	}

	public static int removeDuplicates(int[] nums) {
		int length = nums.length;
		int insertionIndex = 0;
		int distinctValue = nums[0];
		for (int i = 1; i < length; i++) {
			if (nums[i] != distinctValue) {
				insertionIndex++;
				distinctValue = nums[i];
				nums[insertionIndex] = nums[i];
			}
		}
		return insertionIndex + 1;
	}

	private static void check(int[] nums) {
		System.out.println("nums is: " + Arrays.toString(nums));
		int[] expectedNums = Arrays.stream(nums).distinct().sorted().toArray();
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
