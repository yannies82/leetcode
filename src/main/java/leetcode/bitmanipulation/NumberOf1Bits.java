package leetcode.bitmanipulation;

public class NumberOf1Bits {

	public static void main(String[] args) {
		check(11, 3);
		check(65, 2);
	}

	/**
	 * Performs the calculation by bit shifting every position in n and adding to
	 * the result.
	 * 
	 * @param n
	 * @return
	 */
	public static int hammingWeight(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			// logically shift the ith most important bit from the left
			// i positions to the left, then 31 positions to the right
			// this will turn all other bits to 0
			// then add to the result
			result += n << i >>> 31;
		}
		return result;
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int hammingWeight = hammingWeight(n); // Calls your implementation
		System.out.println("hammingWeight is: " + hammingWeight);
	}
}
