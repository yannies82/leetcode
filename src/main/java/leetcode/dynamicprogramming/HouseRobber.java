package leetcode.dynamicprogramming;

import java.util.Arrays;

public class HouseRobber {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 1 }, 4);
		check(new int[] { 2, 7, 9, 3, 1 }, 12);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/house-robber. This solution
	 * uses top down dynamic programming to store the values for each array position
	 * and re use them for the calculations that need them. Time complexity is O(n)
	 * where n is the nums array length.
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
		dpArray[1] = Math.max(nums[0], nums[1]);
		// initialize rest of dpArray values so that we know they are not calculated
		for (int i = 2; i < nums.length; i++) {
			dpArray[i] = -1;
		}
		return dp(nums.length - 1, nums, dpArray);
	}

	private static int dp(int target, int[] nums, int[] dpArray) {
		// return 0 if target index is less than 0
		if (target < 0) {
			return 0;
		}
		if (dpArray[target] == -1) {
			// return cached value, calculate and cache it if it is not calculated yet
			// the solution of this problem is the max between the solution for target - 1
			// and
			// the solution for target - 2 plus the value at target index
			dpArray[target] = Math.max(dp(target - 1, nums, dpArray), dp(target - 2, nums, dpArray) + nums[target]);
		}
		return dpArray[target];
	}

	/**
	 * This solution uses bottom up dynamic programming. Time complexity is O(n)
	 * where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int rob2(int[] nums) {
		// early exit if there is only one house
		if (nums.length == 1) {
			return nums[0];
		}
		// this array caches the intermediate results up to index i
		int[] dpArray = new int[nums.length];
		// set the known values for n = 0 and n = 1
		dpArray[0] = nums[0];
		dpArray[1] = Math.max(nums[0], nums[1]);
		// calculate the rest of the dpArray values bottom up
		for (int i = 2; i < nums.length; i++) {
			dpArray[i] = Math.max(dpArray[i - 1], dpArray[i - 2] + nums[i]);
		}
		// return the last value of the dpArray which is the answer to our problem
		return dpArray[nums.length - 1];
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int rob = rob(nums); // Calls your implementation
		System.out.println("rob is: " + rob);
	}

}
