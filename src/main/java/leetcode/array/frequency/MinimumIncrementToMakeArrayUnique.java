package leetcode.array.frequency;

import java.util.Arrays;

public class MinimumIncrementToMakeArrayUnique {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 2 }, 1);
		check(new int[] { 3, 2, 1, 2, 1, 7 }, 6);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-increment-to-make-array-unique. This
	 * solution counts the frequency of every number in nums, then iterates the
	 * frequency array and keeps the sum of how many increments should take place
	 * until every number is unique. Time complexity is O(Math.max(n, 100000)) where
	 * n is the length of the nums array and 100000 is the upper bound for nums[i].
	 * 
	 * @param nums
	 * @return
	 */
	public static int minIncrementForUnique(int[] nums) {
		int[] frequency = new int[100001];
		int max = -1;
		// count frequency of all numbers, as well as max number
		for (int i = 0; i < nums.length; i++) {
			frequency[nums[i]]++;
			max = Math.max(nums[i], max);
		}
		int sum = 0;
		// iterate frequency array up to max and calculate increments
		for (int i = 0; i < max; i++) {
			if (frequency[i] > 1) {
				// number i exists k times in the nums array
				// therefore k - 1 numbers i should be incremented by 1
				// and added to the sum and the next number frequency
				int diff = frequency[i] - 1;
				sum += diff;
				frequency[i + 1] += diff;
			}
		}
		// given the frequency of max as k, then the number of remaining
		// increments is k * (k - 1) / 2
		sum += frequency[max] * (frequency[max] - 1) / 2;
		return sum;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int minIncrementForUnique = minIncrementForUnique(nums); // Calls your implementation
		System.out.println("minIncrementForUnique is: " + minIncrementForUnique);
	}
}
