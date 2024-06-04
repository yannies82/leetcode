package leetcode.arraystring;

public class LongestPalindrome {

	public static void main(String[] args) {
		check("abccccdd", 7);
		check("a", 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/longest-palindrome. Time
	 * complexity is O(n) where n is the length of the string s.
	 * 
	 * @param s
	 */
	public static int longestPalindrome(String s) {
		char[] sChars = s.toCharArray();
		// count the frequency of each letter
		int[] frequency = new int[64];
		for (int i = 0; i < sChars.length; i++) {
			frequency[sChars[i] - 'A']++;
		}
		// calculate total characters in palindrome string
		int totalChars = 0;
		int oddFrequencyOffset = 0;
		for (int i = 0; i < frequency.length; i++) {
			totalChars += frequency[i];
			// if the character's frequency is odd, then subtract 1 and add
			// to the total chars
			if (frequency[i] % 2 == 1) {
				oddFrequencyOffset = 1;
				totalChars -= 1;
			}
		}
		return totalChars + oddFrequencyOffset;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int longestPalindrome = longestPalindrome(s); // Calls your implementation
		System.out.println("longestPalindrome is: " + longestPalindrome);
	}
}
