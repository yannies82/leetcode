package leetcode.arraystring;

import java.util.Arrays;

public class JumpGame {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 1, 1, 4 }, true);
		check(new int[] { 3, 2, 1, 0, 4 }, false);
		check(new int[] { 1, 1, 1, 0 }, true);
		check(new int[] { 1, 1, 0, 1 }, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/jump-game. This solution
	 * checks that every position is within the max jump range until the max jump
	 * reaches the last index. Time complexity is O(n) where n is the nums array
	 * length.
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean canJump(int[] nums) {
		int length = nums.length;
		int lastIndex = length - 1;
		int maxJump = nums[0];
		for (int i = 1; i < length; i++) {
			if (i > maxJump) {
				// this index is not reachable by jumping from previous ones
				return false;
			}
			int jump = i + nums[i];
			if (jump <= maxJump) {
				continue;
			}
			if (jump >= lastIndex) {
				// return true if we have reached the last index
				return true;
			}
			maxJump = jump;
		}
		return true;
	}

	private static void check(int[] nums, boolean expectedCanJump) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedCanJump is: " + expectedCanJump);
		boolean canJump = canJump(nums); // Calls your implementation
		System.out.println("canJump is: " + canJump);
	}
}
