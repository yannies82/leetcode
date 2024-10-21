package leetcode.backtracking;

import java.util.HashSet;
import java.util.Set;

public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {

	public static void main(String[] args) {
		check("ababccc", 5);
		check("aba", 2);
		check("aa", 1);
		check("wwwzfvedwfvhsww", 11);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings.
	 * This solution uses backtracking to try out all different ways to split string
	 * s. It uses a trie structure to keep substrings. Time complexity is O(2^n)
	 * where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maxUniqueSplit(String s) {
		Node root = new Node();
		int[] max = { 0 };
		char[] chars = s.toCharArray();
		split(chars, 0, 1, root, max);
		return max[0];
	}

	private static void split(char[] chars, int start, int i, Node root, int[] max) {
		if (i > chars.length) {
			// we have reached the end of the string
			if (root.count > max[0]) {
				max[0] = root.count;
			}
			return;
		}
		if (root.count + chars.length - i < max[0]) {
			// early exit if it is not possible to exceed the max count on this path
			return;
		}
		// insert the substring starting at start and ending at i in the trie
		// and return the last character node, if the word did not exist in the trie
		Node endNode = insert(root, chars, start, i);
		if (endNode != null) {
			// the word is new, keep it in the trie and continue with the next characters
			split(chars, i, i + 1, root, max);
			// backtrack by removing the word from the trie and decreasing word count
			endNode.isTerminal = false;
			root.count--;
		}
		// do not insert a substring, continue with the next characters
		split(chars, start, i + 1, root, max);
	}

	public static Node insert(Node root, char[] chars, int start, int end) {
		// insert one character at each level until the word ends
		Node current = root;
		for (int i = start; i < end; i++) {
			int index = chars[i] - 'a';
			if (current.children[index] == null) {
				current.children[index] = new Node();
			}
			current = current.children[index];
		}
		if (current.isTerminal) {
			// the word already exists
			return null;
		}
		// new word, mark it and return the last node
		current.isTerminal = true;
		root.count++;
		return current;
	}

	private static class Node {
		Node[] children = new Node[26];
		boolean isTerminal;
		int count = 0;
	}

	/**
	 * Similar solution to the first one but does not use a wrapper array for the
	 * max and does not have an early exit clause. Time complexity is O(2^n) where n
	 * is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maxUniqueSplit2(String s) {
		Node root = new Node();
		char[] chars = s.toCharArray();
		return split2(chars, 0, 1, root);
	}

	private static int split2(char[] chars, int start, int i, Node root) {
		if (i > chars.length) {
			// we have reached the end of the string
			return root.count;
		}
		// insert the substring starting at start and ending at i in the trie
		// and return the last character node, if the word did not exist in the trie
		Node endNode = insert(root, chars, start, i);
		int result1 = 0;
		if (endNode != null) {
			// the word is new, keep it in the trie and continue with the next characters
			result1 = split2(chars, i, i + 1, root);
			// backtrack by removing the word from the trie and decreasing word count
			endNode.isTerminal = false;
			root.count--;
		}
		// do not insert a substring, continue with the next characters
		int result2 = split2(chars, start, i + 1, root);
		return Math.max(result1, result2);
	}

	/**
	 * Alternative solution which uses backtracking and a set to store the
	 * substrings. Time complexity is O(2^n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maxUniqueSplit3(String s) {
		Set<String> currentSet = new HashSet<>();
		return split3(s, 0, 1, s.length(), currentSet);
	}

	private static int split3(String s, int start, int i, int length, Set<String> currentSet) {
		if (i > length) {
			// we have reached the end of the string
			return currentSet.size();
		}
		String substring = s.substring(start, i);
		int result1 = 0;
		if (!currentSet.contains(substring)) {
			currentSet.add(substring);
			result1 = split3(s, i, i + 1, length, currentSet);
			currentSet.remove(substring);
		}
		int result2 = split3(s, start, i + 1, length, currentSet);
		return Math.max(result1, result2);
	}

	/**
	 * Alternative solution which uses backtracking and a set to store the
	 * substrings. It uses a wrapper array to store the max number of words. Time
	 * complexity is O(2^n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maxUniqueSplit4(String s) {
		Set<String> currentSet = new HashSet<>();
		int[] max = { 0 };
		split4(s, 0, 1, s.length(), currentSet, max);
		return max[0];
	}

	private static void split4(String s, int start, int i, int length, Set<String> currentSet, int[] max) {
		if (i > length) {
			// we have reached the end of the string
			int currentSize = currentSet.size();
			if (currentSize > max[0]) {
				max[0] = currentSize;
			}
			return;
		}
		String substring = s.substring(start, i);
		if (!currentSet.contains(substring)) {
			currentSet.add(substring);
			split4(s, i, i + 1, length, currentSet, max);
			currentSet.remove(substring);
		}
		split4(s, start, i + 1, length, currentSet, max);
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int maxUniqueSplit = maxUniqueSplit(s); // Calls your implementation
		System.out.println("maxUniqueSplit is: " + maxUniqueSplit);
	}
}
