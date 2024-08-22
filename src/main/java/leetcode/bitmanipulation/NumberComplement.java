package leetcode.bitmanipulation;

public class NumberComplement {

	public static void main(String[] args) {
		check(5, 2);
		check(1, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/number-complement. This
	 * solution calculates a mask of bits with as many 1s as the highest value bit
	 * of the num and performs xor with num to get the result. Time complexity is
	 * O(1).
	 * 
	 * @param num
	 * @return
	 */
	public static int findComplement(int num) {
		int mask = 1;
		int current = num;
		while ((current = current >>> 1) > 0) {
			mask = (mask << 1) + 1;
		}
		return num ^ mask;
	}

	private static void check(int num, int expected) {
		System.out.println("num is: " + num);
		System.out.println("expected is: " + expected);
		int findComplement = findComplement(num); // Calls your implementation
		System.out.println("findComplement is: " + findComplement);
	}
}
