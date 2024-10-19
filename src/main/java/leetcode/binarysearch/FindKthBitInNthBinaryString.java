package leetcode.binarysearch;

public class FindKthBitInNthBinaryString {

	public static void main(String[] args) {
		check(3, 1, '0');
		check(4, 11, '1');
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-kth-bit-in-nth-binary-string. This
	 * solution uses binary search, splitting each string into the left and right
	 * values until the result is returned. Time complexity is O(log2^n) = O(n).
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static char findKthBit(int n, int k) {
		return (char) ('0' + solve(n, k));
	}

	public static int solve(int n, int k) {
		if (k == 1) {
			// first digit is always 0
			return 0;
		}
		// the length of the string is always 2^n - 1
		int length = (1 << n) - 1;
		int mid = length / 2;
		int targetIndex = k - 1;
		if (targetIndex < mid) {
			// the left half of the string contains the n-1 string
			return solve(n - 1, k);
		} else if (targetIndex > mid) {
			// the right half of the string contains the reversed, inverted n-1 string
			return 1 - solve(n - 1, length - targetIndex);
		}
		// the middle character of the string is always 1
		return 1;
	}

	private static final String[] PRECOMPUTED = precomputeValues();
	private static final int CHAR_OFFSET = '0' + '1';

	private static String[] precomputeValues() {
		String[] values = new String[21];
		values[1] = "0";
		for (int i = 2; i < values.length; i++) {
			values[i] = values[i - 1] + "1" + reverseInvert(values[i - 1]);
		}
		return values;
	}

	private static String reverseInvert(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			builder.append(Character.toString(CHAR_OFFSET - s.charAt(i)));
		}
		return builder.toString();
	}

	/**
	 * This solution precomputes all of the 20 values, then just returns the target
	 * character for each requested n. Time complexity excluding the precomputation
	 * is O(1).
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static char findKthBit2(int n, int k) {
		return PRECOMPUTED[n].charAt(k - 1);
	}

	private static void check(int n, int k, char expected) {
		System.out.println("n is: " + n);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		char findKthBit = findKthBit(n, k); // Calls your implementation
		System.out.println("findKthBit is: " + findKthBit);
	}
}
