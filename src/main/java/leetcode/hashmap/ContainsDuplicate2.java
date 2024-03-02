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

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> numsMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer prevIndex;
			if ((prevIndex = numsMap.put(nums[i], i)) != null && i - prevIndex <= k) {
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
