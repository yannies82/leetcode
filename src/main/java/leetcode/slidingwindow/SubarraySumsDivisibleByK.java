package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {

	public static void main(String[] args) {
		check(new int[] { 4, 5, 0, -2, -3, 1 }, 5, 7);
		check(new int[] { 5 }, 9, 0);
		check(new int[] { -1, 2, 9 }, 2, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/subarray-sums-divisible-by-k.
	 * This solution adds all numbers and keeps the mod by k for each step. If the
	 * same mod occurs more than 1 times then each time there are n - 1 subarrays
	 * divisible by k and they should be added to the total count. Time complexity
	 * is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int subarraysDivByK(int[] nums, int k) {
		int totalCount = 0;
		int sum = 0;
		// keeps the frequency of each mod while adding the numbers
		Map<Integer, Integer> modMap = new HashMap<>();
		modMap.put(0, 1);
		// add all numbers to the sum
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// calculate mod this way in order to take negative sum into account
			// mod should always be positive
			int mod = (sum % k + k) % k;
			Integer existingCount = modMap.get(mod);
			if (existingCount == null) {
				// add the mod to the map for the first time
				modMap.put(mod, 1);
			} else {
				// mod already exists in the map, increment total count
				// and increase count of mod in the map
				totalCount += existingCount;
				modMap.put(mod, existingCount + 1);
			}
		}
		return totalCount;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int subarraysDivByK = subarraysDivByK(nums, k); // Calls your implementation
		System.out.println("subarraysDivByK is: " + subarraysDivByK);
	}
}
