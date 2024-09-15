package leetcode.bitmanipulation;

import java.util.Arrays;

public class FindTheLongestSubstringContainingVowelsInEvenCounts {

	public static void main(String[] args) {
		check("eleetminicoworoep", 13);
		check("leetcodeisgreat", 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts.
	 * This solution is based on the fact that a^a = 0. Therefore substrings with an
	 * even count for each individual vowel will have a xor of 0. Or alternatively,
	 * when calculating the xor of all vowels in the string, if we encounter the
	 * same xor value again this means that the substring contains an even count for
	 * each individual vowel. Time complexity is O(n) where n is the length of
	 * string s.
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public static int findTheLongestSubstring(String s) {
		// keeps all vowels
		char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
		// marks all ascii codes of vowels as true
		boolean[] isVowel = new boolean[128];
		for (char vowel : vowels) {
			isVowel[vowel] = true;
		}
		char[] sChars = s.toCharArray();
		// the xor of lowercase vowels can never be greater than 127
		// therefore in this array we keep the first index for every xor value
		int[] vowelXorMap = new int[128];
		// initialize with out of range index -2
		Arrays.fill(vowelXorMap, -2);
		// we start with xor value 0 for index -1 (before index 0)
		vowelXorMap[0] = -1;
		int vowelXor = 0;
		int maxLength = 0;
		for (int i = 0; i < sChars.length; i++) {
			if (isVowel[sChars[i]]) {
				// if the char is a vowel include it in the xor value calculation
				vowelXor ^= sChars[i];
			}
			// get the first encountered index for this xor value
			int firstIndex = vowelXorMap[vowelXor];
			if (firstIndex == -2) {
				// this xor value has not been encountered before, set current index as first
				// encountered index
				vowelXorMap[vowelXor] = i;
			} else {
				// this xor value has already been encountered, this means that the substring
				// from first index up to index i (excluded) has an even count for every
				// individual vowel, compare length to maxLength
				int length = i - firstIndex;
				if (length > maxLength) {
					maxLength = length;
				}
			}
		}
		return maxLength;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int findTheLongestSubstring = findTheLongestSubstring(s); // Calls your implementation
		System.out.println("findTheLongestSubstring is: " + findTheLongestSubstring);
	}
}
