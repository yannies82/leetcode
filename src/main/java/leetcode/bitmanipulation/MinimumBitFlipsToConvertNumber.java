package leetcode.bitmanipulation;

public class MinimumBitFlipsToConvertNumber {

	public static void main(String[] args) {
		check(10, 7, 3);
		check(3, 4, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-bit-flips-to-convert-number. Time
	 * complexity is O(1).
	 * 
	 * @param start
	 * @param goal
	 * @return
	 */
	public static int minBitFlips(int start, int goal) {
		// find the bits which are different between the 2 numbers
		int xor = start ^ goal;
		int count = 0;
		while (xor > 0) {
			// add 1 for every bit that is different
			count += xor % 2;
			xor = xor >>> 1;
		}
		return count;
	}

	private static void check(int start, int goal, int expected) {
		System.out.println("start is: " + start);
		System.out.println("goal is: " + goal);
		System.out.println("expected is: " + expected);
		int minBitFlips = minBitFlips(start, goal); // Calls your implementation
		System.out.println("minBitFlips is: " + minBitFlips);
	}
}
