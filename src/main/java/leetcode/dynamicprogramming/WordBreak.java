package leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

	public static void main(String[] args) {
		check("leetcode", List.of("leet", "code"));
		check("aaaaaaa", List.of("aaaa", "aaa"));
		check("catsandog", List.of("cats", "dog", "sand", "and", "cat"));
	}

	/**
	 * This solution uses a trie to store the dictionary words and dynamic
	 * programming to solve the subproblems for all substrings of string s. Time
	 * complexity is O(n^2) where n is the length of string s.
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static boolean wordBreak(String s, List<String> wordDict) {
		// generate the trie structure and fill it with the words
		Node root = new Node();
		for (int i = 0; i < wordDict.size(); i++) {
			addWord(root, wordDict.get(i).toCharArray());
		}
		char[] sChars = s.toCharArray();
		// this array caches the intermediate results of the subproblems
		// dpArray[i] is true if string s can be segmented with dictionary words
		// from index i up to its full length
		int[] dpArray = new int[sChars.length];
		Arrays.fill(dpArray, -1);
		// solve problem for i = 0, after recursively solving the subproblems
		// for the other indexes
		dp(sChars, 0, dpArray, root);
		// the first element of the dpArray has the answer for the full string s
		return dpArray[0] == 1;
	}

	private static int dp(char[] sChars, int i, int[] dpArray, Node root) {
		// early exit if index i is greater than sChars length
		if (i >= sChars.length) {
			return 1;
		}
		// early exit if dpArray has already been calculated for index i
		if (dpArray[i] != -1) {
			return dpArray[i];
		}
		Node current = root;
		// iterate all substrings with incrementing start index in order to calculate
		// dpArray[i]
		for (int j = i; j < sChars.length && (current = current.children[sChars[j] - 'a']) != null; j++) {
			// the char was found in the trie
			if (current.isTerminal && dp(sChars, j + 1, dpArray, root) == 1) {
				// if the character at index j is the last of a word and the subproblem can
				// be solved for index j + 1, then set dpArray[i]
				return dpArray[i] = 1;
			}
		}
		return dpArray[i] = 0;
	}

	private static void addWord(Node root, char[] wordChars) {
		// insert one character at each level until the word ends
		Node current = root;
		for (int i = 0; i < wordChars.length; i++) {
			int index = wordChars[i] - 'a';
			if (current.children[index] == null) {
				current.children[index] = new Node();
			}
			// advance current to the next level node
			current = current.children[index];
		}
		current.isTerminal = true;
	}

	private static class Node {
		Node[] children = new Node[26];
		boolean isTerminal;
	}

	/**
	 * This solution uses dynamic programming to solve the subproblems for all
	 * substrings of string s. Time complexity is O(n^2) where n is the length of
	 * string s.
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static boolean wordBreak2(String s, List<String> wordDict) {
		// convert the list dictionary to a set
		Set<String> wordsSet = new HashSet<>();
		for (int i = 0; i < wordDict.size(); i++) {
			wordsSet.add(wordDict.get(i));
		}
		// this array caches the intermediate results of the subproblems
		// dpArray[i] is true if string s can be segmented with dictionary words
		// up to index i (exclusive)
		boolean[] dpArray = new boolean[s.length() + 1];
		// initialize with true for i = 0 which is an empty string
		dpArray[0] = true;
		// traverse the string s
		for (int i = 1; i <= s.length(); i++) {
			// find dpArray value for subproblem i
			for (int j = 0; j < i; j++) {
				// if the string can be segmented up to index j and the
				// rest of the string up to index i is contained in the dictionary
				// then the string can be segmented up to index i
				if (dpArray[j] && wordsSet.contains(s.substring(j, i))) {
					dpArray[i] = true;
					break;
				}
			}
		}
		// the last position of the array has the answer to the problem
		// for the full string s
		return dpArray[s.length()];
	}

	private static void check(String s, List<String> wordDict) {
		System.out.println("s is: " + s);
		System.out.println("wordDict is: " + wordDict);
		boolean wordBreak = wordBreak(s, wordDict); // Calls your implementation
		System.out.println("wordBreak is: " + wordBreak);
	}

}
