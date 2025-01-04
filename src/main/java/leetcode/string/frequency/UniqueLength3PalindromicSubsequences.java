package leetcode.string.frequency;

public class UniqueLength3PalindromicSubsequences {

	public static void main(String[] args) {
		check("aabca", 3);
		check("adc", 0);
		check("bbcbaba", 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/unique-length-3-palindromic-subsequences. This
	 * solution keeps the frequencies of characters at the first occurence of each
	 * character and compares them with the frequencies at the last occurence of
	 * each character to find the unique characters between the first and last
	 * occurence and produce the final result. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int countPalindromicSubsequence(String s) {
		byte[] bytes = s.getBytes();
		// keeps the frequencies of characters in the string s
		int[] frequency = new int[26];
		// keeps the index of the first occurence of each character
		int[] firstOccurence = new int[26];
		// keeps a copy of the frequencies at the first occurence of each character
		int[][] firstOccurenceFrequencies = new int[26][];
		for (int i = 0; i < bytes.length; i++) {
			int index = bytes[i] - 'a';
			frequency[index]++;
			if (frequency[index] == 1) {
				// at the first occurence of each character copy the frequency array
				firstOccurence[index] = i;
				int[] firstOccurenceFrequency = new int[26];
				System.arraycopy(frequency, 0, firstOccurenceFrequency, 0, 26);
				firstOccurenceFrequencies[index] = firstOccurenceFrequency;
			}

		}
		// marks the last found occurence for each character
		boolean[] foundLast = new boolean[26];
		int result = 0;
		// traverse the string backwards to find the last occurence first
		for (int i = bytes.length - 1; i >= 2; i--) {
			int index = bytes[i] - 'a';
			frequency[index]--;
			if (frequency[index] >= 1 && !foundLast[index] && i > firstOccurence[index] + 1) {
				// last occurence was found, calculate diff with first occurence
				int diff = 0;
				for (int j = 0; j < 26; j++) {
					diff += ((firstOccurenceFrequencies[index][j] - frequency[j]) >>> 31) & 1;
				}
				result += diff;
				foundLast[index] = true;
			}
		}
		return result;
	}

	/**
	 * Similar solution which does not convert the string to a byte array. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int countPalindromicSubsequence2(String s) {
		int length = s.length();
		// keeps the frequencies of characters in the string s
		int[] frequency = new int[26];
		// keeps the index of the first occurence of each character
		int[] firstOccurence = new int[26];
		// keeps a copy of the frequencies at the first occurence of each character
		int[][] firstOccurenceFrequencies = new int[26][];
		for (int i = 0; i < length; i++) {
			// at the first occurence of each character copy the frequency array
			int index = s.charAt(i) - 'a';
			frequency[index]++;
			if (frequency[index] == 1) {
				firstOccurence[index] = i;
				int[] firstOccurenceFrequency = new int[26];
				System.arraycopy(frequency, 0, firstOccurenceFrequency, 0, 26);
				firstOccurenceFrequencies[index] = firstOccurenceFrequency;
			}

		}
		// marks the last found occurence for each character
		boolean[] foundLast = new boolean[26];
		int result = 0;
		// traverse the string backwards to find the last occurence first
		for (int i = length - 1; i >= 2; i--) {
			int index = s.charAt(i) - 'a';
			frequency[index]--;
			if (frequency[index] >= 1 && !foundLast[index] && i > firstOccurence[index] + 1) {
				// last occurence was found, calculate diff with first occurence
				int diff = 0;
				for (int j = 0; j < 26; j++) {
					diff += ((firstOccurenceFrequencies[index][j] - frequency[j]) >>> 31) & 1;
				}
				result += diff;
				foundLast[index] = true;
			}
		}
		return result;
	}

	/**
	 * Similar solution but performs a single pass and compares frequencies on every
	 * recurring character and not between the first and last occurence. Slightly
	 * less time efficient overall. Time complexity is O(n) where n is the length of
	 * string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int countPalindromicSubsequence3(String s) {
		int length = s.length();
		// keeps the frequencies of characters in the string s
		int[] frequency = new int[26];
		// keeps the index of the first occurence of each character
		int[] firstOccurence = new int[26];
		// keeps a copy of the frequencies at the first occurence of each character
		int[][] firstOccurenceFrequencies = new int[26][];
		int[] counts = new int[26];
		for (int i = 0; i < length; i++) {
			int index = s.charAt(i) - 'a';
			if (frequency[index] >= 1 && i > firstOccurence[index] + 1) {
				// a second occurence was found, calculate and update diff
				int diff = 0;
				for (int j = 0; j < 26; j++) {
					diff += ((firstOccurenceFrequencies[index][j] - frequency[j]) >>> 31) & 1;
				}
				counts[index] = diff;
			}
			frequency[index]++;
			if (frequency[index] == 1) {
				// at the first occurence of each character copy the frequency array
				firstOccurence[index] = i;
				int[] firstOccurenceFrequency = new int[26];
				System.arraycopy(frequency, 0, firstOccurenceFrequency, 0, 26);
				firstOccurenceFrequencies[index] = firstOccurenceFrequency;
			}

		}
		int result = 0;
		for (int i = 0; i < counts.length; i++) {
			result += counts[i];
		}
		return result;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int countPalindromicSubsequence = countPalindromicSubsequence(s); // Calls your implementation
		System.out.println("countPalindromicSubsequence is: " + countPalindromicSubsequence);
	}
}
