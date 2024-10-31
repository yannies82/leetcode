package leetcode.array;

import java.util.Arrays;

public class RemoveElement {

	public static void main(String[] args) {
		check(new int[] { 3, 2, 2, 3 }, 3, new int[] { 2, 2, 2, 3 });
		check(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }, 2, new int[] { 0, 1, 4, 0, 3, 0, 4, 2 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/remove-element. This solution
	 * replaces elements with the specified val with elements from the end of the
	 * array. Time complexity is O(n) where n is the length of the array.
	 * 
	 * @param nums
	 * @param val
	 * @return
	 */
	public static int removeElement(int[] nums, int val) {
		int i = 0;
		int length = nums.length;
		int endIndex = length - 1;
		while (i <= endIndex) {
			if (nums[i] == val) {
				// copy element to the end of the array, increase count of copied elements
				nums[i] = nums[endIndex--];
			} else {
				// element is not val, increase index
				i++;
			}
		}
		return endIndex + 1;
	}

	private static void check(int[] nums, int val, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("val is: " + val);
		System.out.println("expected is: " + Arrays.toString(expected));
		int removeElement = removeElement(nums, val); // Calls your implementation
		System.out.println("removeElement is: " + removeElement);
		System.out.println("final nums is: " + Arrays.toString(nums));
	}
}
