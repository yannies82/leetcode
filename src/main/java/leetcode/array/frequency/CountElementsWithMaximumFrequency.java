package leetcode.array.frequency;

import java.util.Arrays;

public class CountElementsWithMaximumFrequency {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 2, 3, 1, 4 }, 4);
		check(new int[] { 1, 2, 3, 4, 5 }, 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-elements-with-maximum-frequency. This
	 * solution traverses the array and keeps the frequency of the elements, then
	 * adds all elements with max frequency. Time complexity is O(n) where n is the
	 * length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxFrequencyElements(int[] nums) {
		int[] frequencies = new int[101];
		int maxFrequency = 0;
		for (int i = 0; i < nums.length; i++) {
			frequencies[nums[i]]++;
			if (frequencies[nums[i]] > maxFrequency) {
				maxFrequency = frequencies[nums[i]];
			}
		}
		int result = 0;
		for (int i = 1; i <= 100; i++) {
			if (frequencies[i] == maxFrequency) {
				result += maxFrequency;
			}
		}
		return result;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maxFrequencyElements = maxFrequencyElements(nums); // Calls your implementation
		System.out.println("maxFrequencyElements is: " + maxFrequencyElements);
	}
}
