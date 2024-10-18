package leetcode.array;

import java.util.Arrays;

public class JumpGame2 {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, 2);
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
		int count = 1;
		int lastIndex = length - 1;
		// iterate all positions until maxJump is equal to the last position
		for (int i = 1; i < length && maxJump < lastIndex; i++) {
			int jump = i + nums[i];
			if (jump > candidateJump) {
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
		return count;
	}

	/**
	 * Alternative solution, similar to the first one. Time complexity is O(n) where
	 * n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int minJumpCount2(int[] nums) {
		int length = nums.length;
		if (length == 1) {
			return 0;
		}
		int current = 1;
		int maxJump = nums[0];
		int count = 1;
		int lastIndex = length - 1;
		while (maxJump < lastIndex) {
			// check the positions between current and maxJump to find
			// the one with the greatest range
			int candidateJump = maxJump;
			for (int i = current; i <= maxJump; i++) {
				int jump = i + nums[i];
				if (jump > candidateJump) {
					// current position has the greatest jump range
					candidateJump = jump;
				}
			}
			// increase jump count and update max jump target position
			count++;
			current = maxJump + 1;
			maxJump = candidateJump;
		}
		return count;
	}

	private static void check(int[] nums, int expectedMinJumpCount) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedMinJumpCount is: " + expectedMinJumpCount);
		int minJumpCount = minJumpCount(nums); // Calls your implementation
		System.out.println("minJumpCount is: " + minJumpCount);
		minJumpCount = minJumpCount2(nums); // Calls your implementation
		System.out.println("minJumpCount2 is: " + minJumpCount);
	}
}
