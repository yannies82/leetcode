package leetcode.bitmanipulation;

import java.util.Arrays;

public class NeighboringBitwiseXor {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 0 }, true);
		check(new int[] { 1, 1 }, true);
		check(new int[] { 1, 0 }, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/neighboring-bitwise-xor. This
	 * solution considers the fact the every 1 in the original array results in two
	 * 1s in the derived array. Therefore, the number of ones in the derived array
	 * should be even. Time complexity is O(n) where n is the length of the derived
	 * array.
	 * 
	 * @param derived
	 * @return
	 */
	public static boolean doesValidArrayExist(int[] derived) {
		int oneCount = 0;
		for (int i = 0; i < derived.length; i++) {
			oneCount += derived[i];
		}
		return (oneCount & 1) == 0;
	}

	private static void check(int[] derived, boolean expected) {
		System.out.println("nums1 is: " + Arrays.toString(derived));
		System.out.println("expected is: " + expected);
		boolean doesValidArrayExist = doesValidArrayExist(derived); // Calls your implementation
		System.out.println("doesValidArrayExist is: " + doesValidArrayExist);
	}

}
