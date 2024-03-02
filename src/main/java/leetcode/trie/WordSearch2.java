package leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {

	public static void main(String[] args) {
		char[][] board0 = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
				{ 'i', 'f', 'l', 'v' } };
		String[] words0 = { "oath", "pea", "eat", "rain" };
		List<String> expected = List.of("eat", "oath");
		check(board0, words0, expected);
	}

	/**
	 * This solution puts the target words in a trie structure. Then for all
	 * starting positions on the board it traverses the trie using the adjacent
	 * positions as next characters and returns the results, if it finds any.
	 * 
	 * @param board
	 * @param words
	 * @return
	 */
	public static List<String> findWords(char[][] board, String[] words) {
		// create root trie node and add all words
		Node root = new Node();
		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i]);
		}
		// keeps results to return
		List<String> results = new ArrayList<>();
		// iterate all board positions as starting points and perform search
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				search(board, root, i, j, results);
			}
		}
		return results;
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
		current.fullWord = word;
	}

	public static void search(char[][] board, Node node, int i, int j, List<String> results) {
		// traverse the node levels using the word characters
		Node current = node.children[board[i][j] - 'a'];
		if (current == null) {
			// this character leads to a null node, return
			return;
		}
		if (current.fullWord != null) {
			// the node is terminal and contains a full word, add it to the list
			// and set the node property to null in order to avoid duplicates
			results.add(current.fullWord);
			current.fullWord = null;
		}
		// replace the character on the board with a special placeholder char to denote
		// that it is visited
		char originalChar = board[i][j];
		board[i][j] = '*';
		// continue search with the left position
		if (j > 0 && board[i][j - 1] != '*') {
			search(board, current, i, j - 1, results);
		}
		// continue search with the right position
		if (j < board[0].length - 1 && board[i][j + 1] != '*') {
			search(board, current, i, j + 1, results);
		}
		// continue search with the above position
		if (i > 0 && board[i - 1][j] != '*') {
			search(board, current, i - 1, j, results);
		}
		// continue search with the below position
		if (i < board.length - 1 && board[i + 1][j] != '*') {
			search(board, current, i + 1, j, results);
		}
		// restore the character on the board to be used in searches from different
		// positions
		board[i][j] = originalChar;
	}

	private static class Node {
		Node[] children = new Node[26];
		String fullWord;
	}

	/**
	 * This solution puts the target words in a trie structure. Then for all
	 * starting positions on the board it traverses the trie using the adjacent
	 * positions as next characters and returns the results, if it finds any.
	 * 
	 * @param board
	 * @param words
	 * @return
	 */
	public static List<String> findWords2(char[][] board, String[] words) {
		// create root trie node and add all words
		Node2 root = new Node2();
		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i]);
		}
		// builds the strings from the traversed characters
		StringBuilder builder = new StringBuilder();
		// keeps results in a set in order to prevent duplicates
		Set<String> resultsSet = new HashSet<>();
		// keeps results to return
		List<String> results = new ArrayList<>();
		// keeps visited positions for each traversal
		boolean[][] visited = new boolean[board.length][board[0].length];
		// iterate all board positions as starting points and perform search
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				search(board, root, i, j, visited, builder, results, resultsSet);
			}
		}
		return results;
	}

	private static void addWord(Node2 root, String word) {
		// insert one character at each level until the word ends
		Node2 current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (current.children[index] == null) {
				current.children[index] = new Node2();
			}
			// advance current to the next level node
			current = current.children[index];
		}
		// set the last node (which corresponds to the last letter of the word) as
		// terminal
		current.isTerminal = true;
	}

	public static void search(char[][] board, Node2 node, int i, int j, boolean[][] visited, StringBuilder builder,
			List<String> results, Set<String> resultsSet) {
		// traverse the node levels using the word characters
		Node2 current = node.children[board[i][j] - 'a'];
		if (current == null) {
			// this character leads to a null node, return
			return;
		}
		// there is a node for this character, add the character to the builder
		builder.append(board[i][j]);
		if (current.isTerminal) {
			// the node is terminal, add the built string to the results list if it is not
			// duplicate
			String result = builder.toString();
			if (resultsSet.add(result)) {
				results.add(result);
			}
		}
		// mark position as visited
		visited[i][j] = true;
		// continue search with the left position
		if (j > 0 && !visited[i][j - 1]) {
			search(board, current, i, j - 1, visited, builder, results, resultsSet);
		}
		// continue search with the right position
		if (j < board[0].length - 1 && !visited[i][j + 1]) {
			search(board, current, i, j + 1, visited, builder, results, resultsSet);
		}
		// continue search with the above position
		if (i > 0 && !visited[i - 1][j]) {
			search(board, current, i - 1, j, visited, builder, results, resultsSet);
		}
		// continue search with the below position
		if (i < board.length - 1 && !visited[i + 1][j]) {
			search(board, current, i + 1, j, visited, builder, results, resultsSet);
		}
		// remove char from builder, search has ended for this node
		builder.deleteCharAt(builder.length() - 1);
		// mark position as not visited so that it can be visited again from another
		// position
		visited[i][j] = false;
	}

	private static class Node2 {
		Node2[] children = new Node2[26];
		boolean isTerminal;
	}

	private static void check(char[][] board, String[] words, List<String> expected) {
		System.out.println("board is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expected is: " + expected);
		List<String> findWords = findWords(board, words); // Calls your implementation
		System.out.println("findWords is: " + findWords);
	}
}
