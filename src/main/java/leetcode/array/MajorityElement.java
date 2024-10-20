package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

	public static void main(String[] args) {
		check(new int[] { 3, 2, 3 }, 3);
		check(new int[] { 2, 2, 1, 1, 1, 2, 2 }, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/majority-element. This
	 * solution uses the Moore Voting algorithm to find the majority element. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int majorityElementMooreVoting(int[] nums) {
		int count = 1;
		int candidate = nums[0];
		int n = nums.length;
		for (int i = 1; i < n; i++) {
			if (candidate == nums[i]) {
				count++;
			} else if (count == 1) {
				candidate = nums[i];
			} else {
				count--;
			}
		}
		return candidate;
	}

	/**
	 * This implementation uses a map to store the of occurrences for each number in
	 * the array and terminates when the occurrences of a number exceed n/2. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int majorityElementMap(int[] nums) {
		Map<Integer, Integer> countMap = new HashMap<>();
		int n = nums.length;
		int majorityLimit = n / 2;
		int count = 1;
		for (int i = 0; i < n; i++) {
			Integer countObj = countMap.get(nums[i]);
			if (countObj == null) {
				count = 1;
			} else {
				count = countObj + 1;
			}
			// terminate if the count has already exceeded the majority limit
			if (count > majorityLimit) {
				return nums[i];
			} else {
				countMap.put(nums[i], count);
			}
		}
		return -1;
	}

	private static void check(int[] nums, int expectedMajorityElement) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedMajorityElement is: " + expectedMajorityElement);
		int k = majorityElementMap(nums); // Calls your implementation
		int m = majorityElementMooreVoting(nums); // Calls your implementation
		System.out.println("majorityElementMap is: " + k);
		System.out.println("majorityElementMooreVoting is: " + m);
	}
}
