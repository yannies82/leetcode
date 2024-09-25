package leetcode.trie;

import java.util.Arrays;

public class SumOfPrefixScoresOfStrings {

	public static void main(String[] args) {
		check(new String[] { "abc", "ab", "bc", "b" }, new int[] { 5, 4, 3, 2 });
		check(new String[] { "abcd" }, new int[] { 4 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/sum-of-prefix-scores-of-strings. This solution
	 * uses a trie to keep all words with a score for each character. It then
	 * searches for each word adding the score of found characters to produce the
	 * result. Time complexity is O(n*m) where n is the length of the words array
	 * and m is the length of each word.
	 * 
	 * @param words
	 * @return
	 */
	public static int[] sumPrefixScores(String[] words) {
		// create root trie node and add all words
		Node root = new Node();
		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i]);
		}
		int[] result = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			result[i] = search(root, words[i]);
		}
		return result;
	}

	private static void addWord(Node root, String word) {
		// insert one character at each level until the word ends
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (current.children[index] == null) {
				// node for character does not exist, create it
				current.children[index] = new Node();
			} else {
				// node for character already exists increase score
				current.children[index].score++;
			}
			// advance current to the next level node
			current = current.children[index];
		}
	}

	public static int search(Node node, String word) {
		Node current = node;
		int totalScore = 0;
		for (int i = 0; i < word.length(); i++) {
			current = current.children[word.charAt(i) - 'a'];
			if (current == null) {
				// the character was not found in the trie
				break;
			}
			// add node score to total score
			totalScore += current.score;
		}
		return totalScore;
	}

	private static class Node {
		Node[] children = new Node[26];
		int score = 1;
	}

	private static void check(String[] words, int[] expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] sumPrefixScores = sumPrefixScores(words); // Calls your implementation
		System.out.println("sumPrefixScores is: " + Arrays.toString(sumPrefixScores));
	}
}
