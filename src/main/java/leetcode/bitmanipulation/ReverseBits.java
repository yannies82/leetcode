package leetcode.bitmanipulation;

public class ReverseBits {

	public static void main(String[] args) {
		check(43261596, 964176192);
		check(1, -2147483648);
	}

	/**
	 * Performs the calculation by bit shifting every position in n and adding to
	 * the result.
	 * 
	 * @param n
	 * @return
	 */
	public static int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			// logically shift the ith most important bit from the left
			// i positions to the left, then 31 positions to the right
			// this will turn all other bits to 0
			// then shift the bit i positions to the left to reach the target (mirror)
			// position and add to the result
			result += n << i >>> 31 << i;
		}
		return result;
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int reverseBits = reverseBits(n); // Calls your implementation
		System.out.println("reverseBits is: " + reverseBits);
	}
}
