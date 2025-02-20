package leetcode.trie;

import java.util.Arrays;

public class FindUniqueBinaryString {

	public static void main(String[] args) {
		check(new String[] { "01", "10" }, "11");
		check(new String[] { "00", "01" }, "10");
		check(new String[] { "111", "011", "001" }, "000");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-unique-binary-string.
	 * Since the array contains n strings of length n, then we can invert one
	 * character from each string in order to create a binary string which will be
	 * different from every existing one. Time complexity is O(n) where n is the
	 * length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static String findDifferentBinaryString(String[] nums) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			// 97 is '0' + '1'
			result.append((char) (97 - nums[i].charAt(i)));
		}
		return result.toString();
	}

	/**
	 * This solution creates a trie to keep all words, then uses backtracking to
	 * find a word that does not exist in the trie. Time complexity is O(n^2) where
	 * n is the length of the nums array (and the length of each word).
	 * 
	 * @param nums
	 * @return
	 */
	public static String findDifferentBinaryString2(String[] nums) {
		// create root trie node and add all words
		Node root = new Node();
		for (int i = 0; i < nums.length; i++) {
			addWord(root, nums[i]);
		}
		char[] result = new char[nums[0].length()];
		return backTrack(root, result, 0);
	}

	private static String backTrack(Node current, char[] result, int i) {
		if (i == result.length) {
			return null;
		}
		if (current.children[0] == null) {
			for (int j = i; j < result.length; j++) {
				result[j] = '0';
			}
			return new String(result);
		}
		if (current.children[1] == null) {
			for (int j = i; j < result.length; j++) {
				result[j] = '1';
			}
			return new String(result);
		}
		result[i] = '0';
		String newWord = backTrack(current.children[0], result, i + 1);
		if (newWord != null) {
			return newWord;
		}
		result[i] = '1';
		newWord = backTrack(current.children[1], result, i + 1);
		return newWord;
	}

	private static void addWord(Node root, String word) {
		// insert one character at each level until the word ends
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - '0';
			if (current.children[index] == null) {
				current.children[index] = new Node();
			}
			// advance current to the next level node
			current = current.children[index];
		}
	}

	private static class Node {
		Node[] children = new Node[2];
	}

	private static void check(String[] nums, String expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		String findDifferentBinaryString = findDifferentBinaryString(nums); // Calls your implementation
		System.out.println("findDifferentBinaryString is: " + findDifferentBinaryString);
	}
}
