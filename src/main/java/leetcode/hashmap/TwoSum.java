package leetcode.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	public static void main(String[] args) {
		check(new int[] { 2, 7, 11, 15 }, 9, new int[] { 0, 1 });
		check(new int[] { 3, 2, 4 }, 6, new int[] { 1, 2 });
		check(new int[] { 3, 3 }, 6, new int[] { 0, 1 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/two-sum. This solution
	 * iterates all numbers and stores in a map the target - nums[i] as key and the
	 * index i as value. If an index i is found where the map contains nums[i] this
	 * means that this is a solution to the problem because nums[i] == target -
	 * nums[j] or nums[i] + nums[j] == target. Time complexity is O(n) where n is
	 * the length of the nums array.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		int length = nums.length;
		// for each number this map keeps the target - nums[i] as key and the index i as
		// value
		Map<Integer, Integer> sumMap = new HashMap<>();
		for (int i = 0; i < length; i++) {
			if (sumMap.get(nums[i]) != null) {
				// if nums[i] exists in the map return the solution indexes
				return new int[] { sumMap.get(nums[i]), i };
			}
			sumMap.put(target - nums[i], i);
		}
		// no solution was found
		return null;
	}

	private static void check(int[] nums, int target, int[] expectedIndexes) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("target is: " + target);
		System.out.println("expectedIndexes is: " + Arrays.toString(expectedIndexes));
		int[] twoSum = twoSum(nums, target); // Calls your implementation
		System.out.println("twoSum is: " + Arrays.toString(twoSum));
	}
}
