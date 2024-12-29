package leetcode.dynamicprogramming;

import java.util.Arrays;

public class NumberOfWaysToFormATargetStringGivenADictionary {

	public static void main(String[] args) {
		check(new String[] { "acca", "bbbb", "caca" }, "aba", 6);
		check(new String[] { "abba", "baab" }, "bab", 4);
		check(new String[] { "aaaa", "aaaa", "aaaa" }, "aa", 54);
		check(new String[] { "cabbaacaaaccaabbbbaccacbabbbcb", "bbcabcbcccbcacbbbaacacaaabbbac",
				"cbabcaacbcaaabbcbaabaababbacbc", "aacabbbcaaccaabbaccacabccaacca", "bbabbaabcaabccbbabccaaccbabcab",
				"bcaccbbaaccaabcbabbacaccbbcbbb", "cbbcbcaaaacacabbbabacbaabbabaa", "cbbbbbbcccbabbacacacacccbbccca",
				"bcbccbccacccacaababcbcbbacbbbc", "ccacaabaaabbbacacbacbaaacbcaca", "bacaaaabaabccbcbbaacacccabbbcb",
				"bcbcbcabbccabacbcbcaccacbcaaab", "babbbcccbbbbbaabbbacbbaabaabcc", "baaaacaaacbbaacccababbaacccbcb",
				"babbaaabaaccaabacbbbacbcbababa", "cbacacbacaaacbaaaabacbbccccaca", "bcbcaccaabacaacaaaccaabbcacaaa",
				"cccbabccaabbcbccbbabaaacbacaaa", "bbbcabacbbcabcbcaaccbcacacccca", "ccccbbaababacbabcaacabaccbabaa",
				"caaabccbcaaccabbcbcaacccbcacba", "cccbcaacbabaacbaaabbbbcbbbbcbb", "cababbcacbabcbaababcbcabbaabba",
				"aaaacacaaccbacacbbbbccaabcccca", "cbcaaaaabacbacaccbcbcbccaabaac", "bcbbccbabaccabcccacbbaacbbcbba",
				"cccbabbbcbbabccbbabbbbcaaccaab", "acccacccaabbcaccbcaaccbababacc", "bcacabaacccbbcbbacabbbbbcaaaab",
				"cacccaacbcbccbabccabbcbabbcacc", "aacabbabcaacbaaacaabcabcaccaab", "cccacabacbabccbccaaaaabbcacbcc",
				"cabaacacacaaabaacaabababccbaaa", "caabaccaacccbaabcacbcbbabccabc", "bcbbccbbaaacbaacbccbcbababcacb",
				"bbabbcabcbbcababbbbccabaaccbca", "cacbbbccabaaaaccacbcbabaabbcba", "ccbcacbabababbbcbcabbcccaccbca",
				"acccabcacbcbbcbccaccaacbabcaab", "ccacaabcbbaabaaccbabcbacaaabaa", "cbabbbbcabbbbcbccabaabccaccaca",
				"acbbbbbccabacabcbbabcaacbbaacc", "baaababbcabcacbbcbabacbcbaaabc", "cabbcabcbbacaaaaacbcbbcacaccac" },
				"acbaccacbbaaabbbabac", 555996654);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary.
	 * This solution counts the frequency of each character per position in the
	 * words strings, then uses bottom up dynamic programming to get the final
	 * result. Time complexity is O(m*(n+k)) where m is the number of words in the
	 * dictionary, n is the length of each word and k is the length of the target
	 * string.
	 * 
	 * @param words
	 * @param target
	 * @return
	 */
	public static int numWays(String[] words, String target) {
		int wordLength = words[0].length();
		char[] targetChars = target.toCharArray();
		// keeps the frequencies of characters per position
		int[][] frequencies = new int[wordLength][26];
		for (int i = 0; i < wordLength; i++) {
			for (int j = 0; j < words.length; j++) {
				frequencies[i][words[j].charAt(i) - 'a']++;
			}
		}
		// keeps the number of ways to create a target string with i chars
		long[] dpArray = new long[targetChars.length + 1];
		dpArray[0] = 1;
		for (int i = 0; i < wordLength; i++) {
			for (int j = targetChars.length - 1; j >= 0; j--) {
				dpArray[j + 1] = (dpArray[j + 1] + dpArray[j] * frequencies[i][targetChars[j] - 'a']) % 1000000007;
			}
		}
		return (int) dpArray[targetChars.length];
	}

	/**
	 * This solution counts the frequency of each character per position in the
	 * words strings, then uses backtracking to get the final result.
	 * 
	 * @param words
	 * @param target
	 * @return
	 */
	public static int numWays2(String[] words, String target) {
		int wordLength = words[0].length();
		char[] targetChars = target.toCharArray();
		int[][] frequencies = new int[wordLength][26];
		for (int i = 0; i < wordLength; i++) {
			for (int j = 0; j < words.length; j++) {
				frequencies[i][words[j].charAt(i) - 'a']++;
			}
		}
		return (int) (countNumWays(frequencies, targetChars, 0, 0, 0) % 1000000007);
	}

	private static long countNumWays(int[][] frequencies, char[] targetChars, int wordCharIndex, int targetCharIndex,
			long prevNumWays) {
		if (targetCharIndex == targetChars.length) {
			return prevNumWays;
		}
		long doNotUseChar = 0;
		if (frequencies.length - wordCharIndex > targetChars.length - targetCharIndex) {
			doNotUseChar = countNumWays(frequencies, targetChars, wordCharIndex + 1, targetCharIndex, prevNumWays);
		}
		int frequency = frequencies[wordCharIndex][targetChars[targetCharIndex] - 'a'];
		long useChar = 0;
		if (frequency > 0) {
			long newNumWays = prevNumWays == 0 ? frequency : (prevNumWays * frequency) % 1000000007;
			useChar = countNumWays(frequencies, targetChars, wordCharIndex + 1, targetCharIndex + 1, newNumWays);
		}
		return useChar + doNotUseChar;
	}

	private static void check(String[] words, String target, int expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		int numWays = numWays(words, target); // Calls your implementation
		System.out.println("numWays is: " + numWays);
		int numWays2 = numWays2(words, target); // Calls your implementation
		System.out.println("numWays2 is: " + numWays2);
	}
}
