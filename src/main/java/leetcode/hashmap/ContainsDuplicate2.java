package leetcode.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate2 {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 1 }, 3, true);
		check(new int[] { 1, 0, 1, 1 }, 1, true);
		check(new int[] { 1, 2, 3, 1, 2, 3 }, 2, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/contains-duplicate-ii. This
	 * solution stores each number in a map along with its index. If a number is
	 * encountered again then the new index is compared to the old index to check if
	 * the difference is greater than or equal to k. Time complexity is O(n) where n
	 * is the nums array length.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> numsMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer prevIndex = numsMap.put(nums[i], i);
			if (prevIndex != null && i - prevIndex <= k) {
				// the number has been encountered again after less than k positions
				return true;
			}
		}
		return false;
	}

	private static void check(int[] nums, int k, boolean expectedResult) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expectedResult is: " + expectedResult);
		boolean containsNearbyDuplicate = containsNearbyDuplicate(nums, k); // Calls your implementation
		System.out.println("containsNearbyDuplicate is: " + containsNearbyDuplicate);
	}
}
