package leetcode.slidingwindow;

public class PermutationInString {

	public static void main(String[] args) {
		check("ab", "eidbaooo", true);
		check("ab", "eidboaoo", false);
		check("adc", "dcda", true);
		check("rokx", "otrxvfszxroxrzdsltg", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/permutation-in-string. This
	 * solution uses a sliding window to count how many letters from s1 exist in s2
	 * for a moving window of size equal to the length of s1. Time complexity is
	 * O(n) where n is the length of s2.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean checkInclusion(String s1, String s2) {
		int s1Length = s1.length();
		int s2Length = s2.length();
		if (s1Length > s2Length) {
			return false;
		}
		// count the frequency of characters in s1
		int[] s1Freq = new int[26];
		for (int i = 0; i < s1Length; i++) {
			s1Freq[s1.charAt(i) - 'a']++;
		}
		// count the frequency of characters in a substring of s2 equal to the length of
		// s1
		// increase count for every matching character with s1
		int count = 0;
		int[] freq = new int[26];
		for (int i = 0; i < s1Length; i++) {
			int index = s2.charAt(i) - 'a';
			if (++freq[index] <= s1Freq[index]) {
				count++;
			}
		}
		// if all characters are matching return true
		if (count == s1Length) {
			return true;
		}
		// use a sliding window of length equal to s1
		for (int i = s1Length; i < s2Length; i++) {
			// remove a character from the start of the window, decrease count if it was
			// included in s1
			int prevIndex = s2.charAt(i - s1Length) - 'a';
			if (--freq[prevIndex] < s1Freq[prevIndex]) {
				count--;
			}
			// add next character, increase count if it is included in s1
			int index = s2.charAt(i) - 'a';
			if (++freq[index] <= s1Freq[index]) {
				count++;
				if (count == s1Length) {
					// all characters in the window are matching
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Alternate solution. Time complexity is O(m*n) where m is the length of s1 and
	 * n is the length of s2.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean checkInclusion2(String s1, String s2) {
		int s1Length = s1.length();
		int s2Length = s2.length();
		if (s1Length > s2Length) {
			return false;
		}
		int[] s1Freq = new int[26];
		for (int i = 0; i < s1Length; i++) {
			s1Freq[s1.charAt(i) - 'a']++;
		}
		int limit = s2Length - s1Length;
		for (int i = 0; i <= limit; i++) {
			int count = 0;
			int[] freq = new int[26];
			for (int j = i; j < s2Length; j++) {
				int index = s2.charAt(j) - 'a';
				if (freq[index] < s1Freq[index]) {
					freq[index]++;
					count++;
					if (count == s1Length) {
						return true;
					}
				} else {
					break;
				}
			}

		}
		return false;
	}

	private static void check(String s1, String s2, boolean expected) {
		System.out.println("s1 is: " + s1);
		System.out.println("s2 is: " + s2);
		System.out.println("expected is: " + expected);
		boolean checkInclusion = checkInclusion(s1, s2); // Calls your implementation
		System.out.println("checkInclusion is: " + checkInclusion);
	}
}
