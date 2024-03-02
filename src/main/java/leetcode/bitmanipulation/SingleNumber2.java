package leetcode.bitmanipulation;

import java.util.Arrays;

public class SingleNumber2 {

	public static void main(String[] args) {
		check(new int[] { 2, 2, 3, 2 }, 3);
		check(new int[] { 0, 1, 0, 1, 0, 1, 99 }, 99);
	}

	public static int singleNumber(int[] nums) {
		// keeps bits which have appeared 1 or 4 or 7 times etc.
		int firstTime = 0;
		// keeps bits which have appeared 2 or 5 or 8 times etc.
		int secondTime = 0;
		for (int i = 0; i < nums.length; i++) {
			// set bits which appear for the 2nd or 5th or 8th etc. time
			secondTime = secondTime | (firstTime & nums[i]);
			// set bits which appear for the 1st or 4th or 7th etc. time
			firstTime = firstTime ^ nums[i];
			// when a bit appears for the third time it will have been set
			// to both firstTime and secondTime by this point
			// generate mask to reset these bits
			int bothTimesMask = ~(firstTime & secondTime);
			// reset bits which appear for the third time
			firstTime = firstTime & bothTimesMask;
			secondTime = secondTime & bothTimesMask;
		}
		return firstTime;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int singleNumber = singleNumber(nums); // Calls your implementation
		System.out.println("singleNumber is: " + singleNumber);
	}

}
