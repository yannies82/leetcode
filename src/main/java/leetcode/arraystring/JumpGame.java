package leetcode.arraystring;

import java.util.Arrays;

public class JumpGame {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 1, 1, 4 }, true);
		check(new int[] { 3, 2, 1, 0, 4 }, false);
		check(new int[] { 1, 1, 1, 0 }, true);
		check(new int[] { 1, 1, 0, 1 }, false);
	}

	public static boolean canJump(int[] nums) {
		int length = nums.length;
		int maxJump = nums[0];
		int jump;
		for (int i = 1; i < length && maxJump < length - 1; i++) {
			if (i > maxJump) {
				return false;
			} else if ((jump = i + nums[i]) > maxJump) {
				maxJump = jump;
			}
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
