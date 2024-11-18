package leetcode.array.prefix;

import java.util.Arrays;

public class DefuseTheBomb {

	public static void main(String[] args) {
		check(new int[] { 5, 7, 1, 4 }, 3, new int[] { 12, 10, 16, 13 });
		check(new int[] { 1, 2, 3, 4 }, 0, new int[] { 0, 0, 0, 0 });
		check(new int[] { 2, 4, 9, 3 }, -2, new int[] { 12, 5, 6, 13 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/defuse-the-bomb. This
	 * solution calculates the prefix or suffix sum for each code digit and sets it
	 * to the result. Time complexity is O(n) where n is the length of the code
	 * array.
	 * 
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] decrypt(int[] code, int k) {
		int[] result = new int[code.length];
		if (k == 0) {
			// early exit
			return result;
		}
		if (k > 0) {
			int sum = 0;
			for (int i = 0; i < k; i++) {
				sum += code[i];
			}
			for (int i = 0; i < code.length; i++) {
				sum += code[(i + k) % code.length] - code[i];
				result[i] = sum;
			}
		} else {
			k = -k;
			int sum = 0;
			for (int i = code.length - 1; i >= code.length - k; i--) {
				sum += code[i];
			}
			for (int i = code.length - 1; i >= 0; i--) {
				sum += code[(((i - k) % code.length) + code.length) % code.length] - code[i];
				result[i] = sum;
			}
		}
		return result;
	}

	private static void check(int[] code, int k, int[] expected) {
		System.out.println("code is: " + Arrays.toString(code));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] decrypt = decrypt(code, k); // Calls your implementation
		System.out.println("decrypt is: " + Arrays.toString(decrypt));
	}
}
