package leetcode.trie;

import java.util.List;

public class ReplaceWords {

	public static void main(String[] args) {
		check(List.of("cat", "bat", "rat"), "the cattle was rattled by the battery", "the cat was rat by the bat");
		check(List.of("a", "b", "c"), "aadsfasf absbs bbab cadsfafs", "a a b c");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/replace-words. This solution
	 * uses a trie to insert all dictionary words, then searches every sentence word
	 * in the trie and replaces it with the first root match. Time complexity is
	 * O(max(n, m*k)) where n is the length of sentence, m is the number of words in
	 * the dictionary and k is the length of each word in the dictionary.
	 * 
	 * @param dictionary
	 * @param sentence
	 * @return
	 */
	public static String replaceWords(List<String> dictionary, String sentence) {
		// create root trie node and add all words
		Node root = new Node();
		int dictionarySize = dictionary.size();
		for (int i = 0; i < dictionarySize; i++) {
			addWord(root, dictionary.get(i));
		}
		// initialize string builder
		StringBuilder builder = new StringBuilder();
		int length = sentence.length();
		int startIndex = 0;
		// iterate all sentence characters
		for (int i = 0; i < length; i++) {
			if (sentence.charAt(i) == ' ') {
				// whenever a word ends, search it in the trie and append the result to the
				// builder
				builder.append(" ");
				builder.append(searchRoot(root, sentence.substring(startIndex, i)));
				// set the start of the next word
				startIndex = i + 1;
			}
		}
		if (startIndex < length) {
			// special case for the last word
			builder.append(" ");
			builder.append(searchRoot(root, sentence.substring(startIndex, length)));
		}
		return builder.substring(1).toString();
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

	private static String searchRoot(Node root, String word) {
		// traverse the node levels using the word characters
		// if a node is null it means that the word is not present
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			current = current.children[word.charAt(i) - 'a'];
			if (current == null) {
				// the word was not found in the trie, return the word
				return word;
			}
			if (current.fullWord != null) {
				// a root was found for this word, return the word
				return current.fullWord;
			}
		}
		// no root was found for this word, return
		return word;
	}

	private static class Node {
		Node[] children = new Node[26];
		String fullWord;
	}

	private static void check(List<String> dictionary, String sentence, String expected) {
		System.out.println("dictionary is: " + dictionary);
		System.out.println("sentence is: " + sentence);
		System.out.println("expected is: " + expected);
		String replaceWords = replaceWords(dictionary, sentence); // Calls your implementation
		System.out.println("replaceWords is: " + replaceWords);
	}
}
