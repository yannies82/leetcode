package leetcode.trie;

public class ImplementTrie {

	/**
	 * Leetcode problem: https://leetcode.com/problems/implement-trie-prefix-tree.
	 * Implementation of a trie using a Node element with an array representation of
	 * all possible lowercase characters.
	 * 
	 * @author yanni
	 *
	 */
	public static class Trie {

		private Node trieRoot = new Node();

		public Trie() {

		}

		public void insert(String word) {
			// insert one character at each level until the word ends
			Node current = trieRoot;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (current.children[index] == null) {
					current.children[index] = new Node();
				}
				current = current.children[index];
			}
			current.isTerminal = true;
		}

		public boolean search(String word) {
			// traverse the node levels using the word characters
			// if a node is null it means that the word is not present
			Node current = trieRoot;
			for (int i = 0; i < word.length(); i++) {
				current = current.children[word.charAt(i) - 'a'];
				if (current == null) {
					return false;
				}
			}
			// the final node must be terminal for an exact word match
			return current.isTerminal;
		}

		public boolean startsWith(String prefix) {
			// traverse the node levels using the prefix characters
			// if a node is null it means that the prefix is not present
			Node current = trieRoot;
			for (int i = 0; i < prefix.length(); i++) {
				current = current.children[prefix.charAt(i) - 'a'];
				if (current == null) {
					return false;
				}
			}
			return true;
		}

		private static class Node {
			Node[] children = new Node[26];
			boolean isTerminal;
		}
	}

}
