package leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class WordBreak2 {

	public static void main(String[] args) {
		check("catsanddog", List.of("cat", "cats", "and", "sand", "dog"), List.of("cats and dog", "cat sand dog"));
		check("pineapplepenapple", List.of("apple", "pen", "applepen", "pine", "pineapple"),
				List.of("pine apple pen apple", "pineapple pen apple", "pine applepen apple"));
		check("catsandog", List.of("cats", "dog", "sand", "and", "cat"), List.of());
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/word-break-ii. This solution
	 * uses a trie and structure for storing the dictionary of words for fast
	 * searching and recursion with backtracking to test out all different
	 * combinations of words and produce the result. Worst case time complexity is
	 * O(2^n) where n is the length of string s.
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static List<String> wordBreak(String s, List<String> wordDict) {
		// create and populate the trie structure from the dictionary of words
		Node root = new Node();
		for (int i = 0; i < wordDict.size(); i++) {
			addWord(root, wordDict.get(i));
		}
		char[] sChars = s.toCharArray();
		List<String> result = new ArrayList<>();
		// recursively traverse all s characters starting from index 0 and fill out
		// the results list
		wordBreakRecursive(sChars, root, root, result, new StringBuilder(), 0);
		return result;
	}

	private static void wordBreakRecursive(char[] sChars, Node root, Node current, List<String> result,
			StringBuilder builder, int index) {
		if (index == sChars.length) {
			// we have reached the end of string s, we have a valid solution
			// add it to the results list after skipping the first character (which is
			// blank)
			result.add(builder.substring(1));
			return;
		}

		// proceed to the next node of the trie
		Node next = current.children[sChars[index] - 'a'];
		if (next == null) {
			// there is no word in the trie which has this sequence of characters,
			// therefore there can be no solution for this path
			return;
		}

		if (index < sChars.length - 1) {
			// if this is not the last character of the word proceed to the next one without
			// checking if a dictionary word exists in the trie for the next node,
			// in order to test out all combinations
			wordBreakRecursive(sChars, root, next, result, builder, index + 1);
		}

		if (next.word != null) {
			// if a word exists for the next character add the word to the string builder,
			// prefixed with an empty space and proceed to the next character of the word,
			// starting the search again from the root node of the trie
			builder.append(" ").append(next.word);
			wordBreakRecursive(sChars, root, root, result, builder, index + 1);
			// backtrack by removing the added word from the builder, along with the
			// prefixed empty space, so that the builder can be used again for the next
			// combination
			builder.delete(builder.length() - next.word.length() - 1, builder.length());
		}
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
		// add full word to the node of the last word character
		current.word = word;
	}

	private static class Node {
		Node[] children = new Node[26];
		String word;
	}

	private static void check(String s, List<String> wordDict, List<String> expected) {
		System.out.println("s is: " + s);
		System.out.println("wordDict is: " + wordDict);
		System.out.println("expected is: " + expected);
		List<String> wordBreak = wordBreak(s, wordDict); // Calls your implementation
		System.out.println("wordBreak is: " + wordBreak);
	}

}
