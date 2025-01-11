package leetcode.string.frequency;

public class ConstructKPalindromeStrings {

	public static void main(String[] args) {
		check("annabelle", 2, true);
		check("leetcode", 3, false);
		check("true", 4, true);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/construct-k-palindrome-strings. This solution
	 * counts the frequency of each character and compares the number of odd
	 * frequencies to k. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public static boolean canConstruct(String s, int k) {
		int[] frequency = new int[26];
		char[] chars = s.toCharArray();
		if (chars.length < k) {
			return false;
		}
		if (chars.length == k) {
			return true;
		}
		for (int i = 0; i < chars.length; i++) {
			frequency[chars[i] - 'a']++;
		}
		int oddCount = 0;
		for (int i = 0; i < frequency.length; i++) {
			oddCount += frequency[i] & 1;
		}
		return oddCount <= k;
	}

	/**
	 * Similar solution, uses a bit mask instead of a counter array. Time complexity
	 * is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public static boolean canConstruct2(String s, int k) {
		char[] chars = s.toCharArray();
		if (chars.length < k) {
			return false;
		}
		if (chars.length == k) {
			return true;
		}
		int oddCount = 0;
		for (int i = 0; i < chars.length; i++) {
			// perform xor with the bit position corresponding to the char
			oddCount ^= 1 << (chars[i] - 'a');
		}
		// the number of bits in oddCount is equal to the characters with odd frequency
		return Integer.bitCount(oddCount) <= k;
	}

	private static void check(String s, int k, boolean expected) {
		System.out.println("s is: " + s);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		boolean canConstruct = canConstruct(s, k); // Calls your implementation
		System.out.println("canConstruct is: " + canConstruct);
	}
}
