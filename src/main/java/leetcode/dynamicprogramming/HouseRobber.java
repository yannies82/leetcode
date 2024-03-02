package leetcode.dynamicprogramming;

import java.util.Arrays;

public class HouseRobber {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 1 }, 4);
		check(new int[] { 2, 7, 9, 3, 1 }, 12);
	}

	/**
	 * This solution uses dynamic programming to store the values for each array
	 * position and re use them for the calculations that need them. Time complexity
	 * is O(n) where n is the nums array length.
	 * 
	 * @param nums
	 * @return
	 */
	public static int rob(int[] nums) {
		// early exit if there is only one house
		if (nums.length == 1) {
			return nums[0];
		}
		// this array caches the intermediate results
		int[] dpArray = new int[nums.length];
		// set the known values for n = 0 and n = 1
		dpArray[0] = nums[0];
		dpArray[1] = nums[1];
		// initialize rest of dpArray values so that we know they are not calculated
		for (int i = 2; i < nums.length; i++) {
			dpArray[i] = -1;
		}
		// return the max of the dp value of the last two array positions, one of them
		// will have to be selected
		return Math.max(dp(nums.length - 1, nums, dpArray), dp(nums.length - 2, nums, dpArray));
	}

	private static int dp(int target, int[] nums, int[] dpArray) {
		// return 0 if target index is less than 0
		if (target < 0) {
			return 0;
		}
		if (dpArray[target] == -1) {
			// return cached value, calculate and cache it if it is not calculated yet
			// the previous element cannot be selected, so we should select the max
			// between the target - 2 and the target - 3 element
			dpArray[target] = nums[target] + Math.max(dp(target - 2, nums, dpArray), dp(target - 3, nums, dpArray));
		}
		return dpArray[target];
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int rob = rob(nums); // Calls your implementation
		System.out.println("rob is: " + rob);
	}

}
