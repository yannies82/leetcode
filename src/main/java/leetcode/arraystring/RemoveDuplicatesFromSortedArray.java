package leetcode.arraystring;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 2 }, 2);
		check(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }, 4);
	}

	/**
	 * This solution keeps two indexes, a traversal index and an insertion index.
	 * Every time a new item is encountered, it is inserted where the insertion
	 * index points. Time complexity is O(n) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates(int[] nums) {
		int length = nums.length;
		int insertionIndex = 1;
		int distinctValue = nums[0];
		for (int i = 1; i < length; i++) {
			if (nums[i] != distinctValue) {
				distinctValue = nums[i];
				nums[insertionIndex++] = nums[i];
			}
		}
		return insertionIndex;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int removeDuplicates = removeDuplicates(nums); // Calls your implementation
		System.out.println("removeDuplicates is: " + removeDuplicates);
		System.out.println("final nums is: " + Arrays.toString(nums));
	}
}
