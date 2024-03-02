package leetcode.arraystring;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray2 {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 1, 2, 2, 3 }, new int[] { 1, 1, 2, 2, 3, 3 });
		check(new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 }, new int[] { 0, 0, 1, 1, 2, 3, 3, 3, 3 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii. This
	 * solution keeps two indexes, a traversal index and an insertion index. Every
	 * time a new item is encountered, it is inserted where the insertion index
	 * points, at most twice. Time complexity is O(n) where n is the length of the
	 * nums array.
	 * 
	 * @param nums
	 * @return
	 */
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
				nums[insertionIndex++] = nums[i];
			}
		}
		return insertionIndex;
	}

	private static void check(int[] nums, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		int removeDuplicates = removeDuplicates(nums); // Calls your implementation
		System.out.println("removeDuplicates is: " + removeDuplicates);
		System.out.println("final nums is: " + Arrays.toString(nums));
	}
}
