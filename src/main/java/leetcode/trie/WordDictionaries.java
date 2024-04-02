package leetcode.trie;

public class WordDictionaries {

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/design-add-and-search-words-data-structure.
	 * This implementation inserts new words in a trie structure. For searches it
	 * uses recursion when the search term contains a '.' character, so that all
	 * possible characters are checked.
	 * 
	 * @author yanni
	 *
	 */
	public static class WordDictionary {

		private Node root = new Node();

		public WordDictionary() {

		}

		public void addWord(String word) {
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
			// set the last node (which corresponds to the last letter of the word) as
			// terminal
			current.isTerminal = true;
		}

		public boolean search(String word) {
			// perform search, recurse if a dot is encountered
			return search(root, word);
		}

		public boolean search(Node node, String word) {
			// traverse the node levels using the word characters
			Node current = node;
			for (int i = 0; i < word.length(); i++) {
				char nextChar = word.charAt(i);
				if (nextChar == '.') {
					// next character is a wildcard
					// search recursively using every one of its children as starting nodes
					// and using the word characters after the '.' as the new word
					String newWord = word.substring(i + 1);
					for (char c = 'a'; c <= 'z'; c++) {
						Node next = current.children[c - 'a'];
						if (next != null) {
							// a child node exists for this character
							if (i == word.length() - 1) {
								// if this is the last word character then return true if the node is terminal
								if (next.isTerminal) {
									return true;
								}
							} else if (search(next, newWord)) {
								// if this is not the last word character, search recursively and return true in
								// case of success
								return true;
							}
						}
					}
					// no match was found for any character, return false
					return false;
				} else {
					// next character is a specific one, advance current node
					// if the node is null it means that the word is not present
					current = current.children[word.charAt(i) - 'a'];
					if (current == null) {
						return false;
					}
				}
			}
			// the final node must be terminal for an exact word match
			return current.isTerminal;
		}

		private static class Node {
			Node[] children = new Node[26];
			boolean isTerminal;
		}
	}

}
