package leetcode.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountNumberOfBadPairs {

	public static void main(String[] args) {
		check(new int[] { 4, 1, 3, 3 }, 5);
		check(new int[] { 1, 2, 3, 4, 5 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-number-of-bad-pairs/description. This
	 * solution calculates the diff nums[i] - i for each index i and stores the
	 * occurrences of each diff in a map to calculate the good pairs. It then
	 * subtracts the good pairs from the number of all possible pairs to find the
	 * result. Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static long countBadPairs(int[] nums) {
		Map<Integer, Integer> diffMap = new HashMap<>();
		long goodPairsCount = 0;
		for (int i = 0; i < nums.length; i++) {
			int diff = nums[i] - i;
			Integer existing = diffMap.get(diff);
			if (existing == null) {
				diffMap.put(diff, 1);
			} else {
				diffMap.put(diff, existing + 1);
				goodPairsCount += existing;
			}
		}
		return ((long) nums.length * (nums.length - 1) / 2) - goodPairsCount;
	}

	/**
	 * Similar solution, a bit more readable with a minor cost to performance. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static long countBadPairs2(int[] nums) {
		Map<Integer, Integer> diffMap = new HashMap<>();
		long goodPairsCount = 0;
		for (int i = 0; i < nums.length; i++) {
			int diff = nums[i] - i;
			int existing = diffMap.getOrDefault(diff, 0);
			diffMap.put(diff, existing + 1);
			goodPairsCount += existing;
		}
		return ((long) nums.length * (nums.length - 1) / 2) - goodPairsCount;
	}

	/**
	 * Simple solution which uses a nested loop. Time complexity is O(n^2) where n
	 * is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static long countBadPairs3(int[] nums) {
		long count = 0;
		int lastIndex = nums.length - 1;
		for (int i = 0; i < lastIndex; i++) {
			int diffI = nums[i] - i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] - j != diffI) {
					count++;
				}
			}
		}
		return count;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		long countBadPairs = countBadPairs(nums); // Calls your implementation
		System.out.println("countBadPairs is: " + countBadPairs);
	}
}
