package leetcode.dynamicprogramming;

import java.util.Arrays;

public class ExtraCharactersInAString {

	public static void main(String[] args) {
		check("leetscode", new String[] { "leet", "code", "leetcode" }, 1);
		check("sayhelloworld", new String[] { "hello", "world" }, 3);
		check("kevlplxozaizdhxoimmraiakbak",
				new String[] { "yv", "bmab", "hv", "bnsll", "mra", "jjqf", "g", "aiyzi", "ip", "pfctr", "flr", "ybbcl",
						"biu", "ke", "lpl", "iak", "pirua", "ilhqd", "zdhx", "fux", "xaw", "pdfvt", "xf", "t", "wq",
						"r", "cgmud", "aokas", "xv", "jf", "cyys", "wcaz", "rvegf", "ysg", "xo", "uwb", "lw", "okgk",
						"vbmi", "v", "mvo", "fxyx", "ad", "e" },
				9);
		check("ecolloycollotkvzqpdaumuqgs",
				new String[] { "flbri", "uaaz", "numy", "laper", "ioqyt", "tkvz", "ndjb", "gmg", "gdpbo", "x", "collo",
						"vuh", "qhozp", "iwk", "paqgn", "m", "mhx", "jgren", "qqshd", "qr", "qpdau", "oeeuq", "c",
						"qkot", "uxqvx", "lhgid", "vchsk", "drqx", "keaua", "yaru", "mla", "shz", "lby", "vdxlv",
						"xyai", "lxtgl", "inz", "brhi", "iukt", "f", "lbjou", "vb", "sz", "ilkra", "izwk", "muqgs",
						"gom", "je" },
				2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/extra-characters-in-a-string.
	 * This solution uses a trie structure and bottom up dynamic programming to
	 * calculate the solutions for substrings of all length up to the length of
	 * string s. Time complexity is O(n*m) where n is the length of string s and m
	 * is the average word length in dictionary.
	 * 
	 * @param s
	 * @param dictionary
	 * @return
	 */
	public static int minExtraChar(String s, String[] dictionary) {
		char[] chars = s.toCharArray();
		// create root trie node and add all words
		Node root = new Node();
		for (int i = 0; i < dictionary.length; i++) {
			addWord(root, dictionary[i]);
		}
		// keeps the length of every found word on each step, is reused for space
		// efficiency
		int[] wordLengths = new int[dictionary.length];
		// dpArray[i] keeps the subproblem solution for substrings starting at index i
		// end ending at chars.length
		int[] dpArray = new int[chars.length + 1];
		// iterate all characters starting from the end of the string
		// and try to find the lengths of all words starting at index i
		for (int i = chars.length - 1; i >= 0; i--) {
			// by default assume that no word starts at index i, therefore character at i
			// is included in the subproblem solution
			dpArray[i] = dpArray[i + 1] + 1;
			// find the lengths of all words starting at i using the trie
			int wordCount = search(chars, root, i, wordLengths, 0);
			// for every matched word length keep the minimum solution between
			// the current one and dpArray[i + wordLength]
			for (int j = 0; j < wordCount; j++) {
				dpArray[i] = Math.min(dpArray[i], dpArray[i + wordLengths[j]]);
			}
			// reset wordLengths array
			for (int j = 0; j < wordCount; j++) {
				wordLengths[j] = 0;
			}
		}
		// the solution to subproblem for i = 0 is the solution for string s
		return dpArray[0];
	}

	/**
	 * Alternate solution which uses top down dynamic programming. Time complexity
	 * is O(n*m) where n is the length of string s and m is the average word length
	 * in dictionary.
	 * 
	 * @param s
	 * @param dictionary
	 * @return
	 */
	public static int minExtraChar2(String s, String[] dictionary) {
		char[] chars = s.toCharArray();
		// create root trie node and add all words
		Node root = new Node();
		for (int i = 0; i < dictionary.length; i++) {
			addWord(root, dictionary[i]);
		}
		// dpArray[i] keeps the subproblem solution for substrings starting at index i
		// end ending at chars.length
		int[] dpArray = new int[chars.length + 1];
		Arrays.fill(dpArray, -1);
		dpArray[chars.length] = 0;
		dp(chars, root, dpArray, dictionary.length, 0);
		return dpArray[0];
	}

	private static int dp(char[] chars, Node root, int[] dpArray, int dictionaryLength, int i) {
		if (i == chars.length || dpArray[i] >= 0) {
			// return cached result if the solution to the subproblem for index i
			// has already been calculated
			return dpArray[i];
		}
		// by default assume that no word starts at index i, therefore character at i
		// is included in the subproblem solution
		dpArray[i] = dp(chars, root, dpArray, dictionaryLength, i + 1) + 1;
		// keeps the length of every found word
		int[] wordLengths = new int[dictionaryLength];
		// find the lengths of all words starting at i using the trie
		int wordCount = search(chars, root, i, wordLengths, 0);
		// for every matched word length keep the minimum solution between
		// the current one and dpArray[i + wordLength]
		for (int j = 0; j < wordCount; j++) {
			dpArray[i] = Math.min(dpArray[i], dp(chars, root, dpArray, dictionaryLength, i + wordLengths[j]));
		}
		return dpArray[i];
	}

	private static void addWord(Node root, String word) {
		// insert one character at each level until the word ends
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (current.children[index] == null) {
				current.children[index] = new Node();
			}
			// advance current to the next level node
			current = current.children[index];
		}
		// set the full word to the last node, indicating that the word is complete
		current.wordLength = word.length();
	}

	public static int search(char[] chars, Node node, int i, int[] wordLengths, int wordIndex) {
		// traverse the node levels using the word characters
		Node current;
		if (i == chars.length || (current = node.children[chars[i] - 'a']) == null) {
			// this character leads to a null node or we are out of characters, return 0
			return wordIndex;
		}
		if (current.wordLength > 0) {
			wordLengths[wordIndex++] = current.wordLength;
		}
		return search(chars, current, i + 1, wordLengths, wordIndex);
	}

	private static class Node {
		Node[] children = new Node[26];
		int wordLength;
	}

	private static void check(String s, String[] dictionary, int expected) {
		System.out.println("s is: " + s);
		System.out.println("words is: " + Arrays.toString(dictionary));
		System.out.println("expected is: " + expected);
		int minExtraChar = minExtraChar2(s, dictionary); // Calls your implementation
		System.out.println("minExtraChar is: " + minExtraChar);
	}
}
