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

	public static int[] twoSum(int[] nums, int target) {
		int length = nums.length;
		Map<Integer, Integer> sumMap = new HashMap<>();
		for (int i = 0; i < length; i++) {
			if (sumMap.get(nums[i]) != null) {
				return new int[] { sumMap.get(nums[i]), i };
			}
			sumMap.put(target - nums[i], i);
		}
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
