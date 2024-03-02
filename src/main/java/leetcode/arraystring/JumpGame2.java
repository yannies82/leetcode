package leetcode.arraystring;

import java.util.Arrays;

public class JumpGame2 {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 1, 1, 4 }, 2);
		check(new int[] { 2, 3, 0, 1, 4 }, 2);
	}

	public static int minJumpCount(int[] nums) {
		int length = nums.length;
		if (length == 1) {
			return 0;
		}
		int maxJump = nums[0];
		int candidateJump = 0;
		int count = 0;
		int jump;
		for (int i = 1; i < length && maxJump < length - 1; i++) {
			if ((jump = i + nums[i]) > candidateJump) {
				candidateJump = jump;
			}
			if (i == maxJump) {
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
