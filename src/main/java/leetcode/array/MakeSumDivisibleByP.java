package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MakeSumDivisibleByP {

	public static void main(String[] args) {
		check(new int[] { 3, 1, 4, 2 }, 6, 1);
		check(new int[] { 6, 3, 5, 2 }, 9, 2);
		check(new int[] { 1, 2, 3 }, 3, 0);
		check(new int[] { 4, 4, 2 }, 7, -1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/make-sum-divisible-by-p. This
	 * solution calculates the prefix sum for each index and stores its modulo with
	 * p in a map with the value being the current index. This way it is possible
	 * for each index to find the previous index with the target modulo value for
	 * the rest of the array to be divisible by p. The result is the length of the
	 * minimum subarray out of these. Time complexity is O(n) where n is the length
	 * of the nums array.
	 * 
	 * @param nums
	 * @param p
	 * @return
	 */
	public static int minSubarray(int[] nums, int p) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum < p) {
			return -1;
		}
		int remainderMod = (int) (sum % p);
		if (remainderMod == 0) {
			// the sum of the array elements is already divisible by p
			return 0;
		}
		Map<Integer, Integer> lastIndexPerPrefixMod = new HashMap<>();
		lastIndexPerPrefixMod.put(0, -1);
		long prefixSum = 0;
		int minLength = nums.length;
		for (int i = 0; i < nums.length; ++i) {
			prefixSum += nums[i];
			int currentMod = (int) (prefixSum % p);
			int targetMod = (currentMod - remainderMod + p) % p;

			if (lastIndexPerPrefixMod.containsKey(targetMod)) {
				// there is an index with the targetMod value as key, this means that
				// excluding the subarray from that index up to i will result in the sum
				// of the rest of the nums array to be divisible by p
				// update the minLength if necessary
				minLength = Math.min(minLength, i - lastIndexPerPrefixMod.get(targetMod));
			}
			// insert or update the map with the current index as value for this mod key
			lastIndexPerPrefixMod.put(currentMod, i);
		}

		return minLength == nums.length ? -1 : minLength;
	}

	/**
	 * Simple solution, time complexity is O(n^2) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @param p
	 * @return
	 */
	public static int minSubarray2(int[] nums, int p) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum < p) {
			return -1;
		}
		int mod = (int) (sum % p);
		if (mod == 0) {
			return 0;
		}
		int minLength = nums.length;
		for (int i = 0; i < nums.length && minLength > 1; i++) {
			long currentSum = 0;
			int limit = Math.min(nums.length, i + minLength);
			for (int j = i; j < limit; j++) {
				currentSum += nums[j];
				if (currentSum % p == mod) {
					minLength = j - i + 1;
					break;
				}
			}
		}
		return minLength == nums.length ? -1 : minLength;
	}

	private static void check(int[] nums, int p, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int minSubarray = minSubarray(nums, p); // Calls your implementation
		System.out.println("minSubarray is: " + minSubarray);
	}
}
