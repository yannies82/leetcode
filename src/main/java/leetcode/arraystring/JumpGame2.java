package leetcode.arraystring;

import java.util.Arrays;

public class JumpGame2 {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 1, 1, 4 }, 2);
		check(new int[] { 2, 3, 0, 1, 4 }, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/jump-game-ii. This solution
	 * calculates all available jumps from all positions up to the previous selected
	 * max jump target and greedily chooses the largest jump. Time complexity is
	 * O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int minJumpCount(int[] nums) {
		int length = nums.length;
		if (length == 1) {
			return 0;
		}
		int maxJump = nums[0];
		int candidateJump = 0;
		int count = 0;
		int jump;
		// iterate all positions until maxJump is equal to the last position
		for (int i = 1; i < length && maxJump < length - 1; i++) {
			if ((jump = i + nums[i]) > candidateJump) {
				// current jump is the greatest of the candidate jumps
				candidateJump = jump;
			}
			if (i == maxJump) {
				// we have reached the previous max jump target position
				// increase jump count and update max jump target position
				count++;
				maxJump = candidateJump;
			}
		}
		return ++count;
	}

	private static void check(int[] nums, int expectedMinJumpCount) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedMinJumpCount is: " + expectedMinJumpCount);
		int minJumpCount = minJumpCount(nums); // Calls your implementation
		System.out.println("minJumpCount is: " + minJumpCount);
	}
}
