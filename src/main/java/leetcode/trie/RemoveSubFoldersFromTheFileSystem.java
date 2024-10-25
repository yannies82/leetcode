package leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveSubFoldersFromTheFileSystem {

	public static void main(String[] args) {
		check(new String[] { "/a", "/a/b", "/c/d", "/c/d/e", "/c/f" }, List.of("/a", "/c/d", "/c/f"));
		check(new String[] { "/a", "/a/b/c", "/a/b/d" }, List.of("/a"));
		check(new String[] { "/a/b/c", "/a/b/ca", "/a/b/d" }, List.of("/a/b/c", "/a/b/ca", "/a/b/d"));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/remove-sub-folders-from-the-filesystem. This
	 * solution sorts the folders by length, then uses a trie structure to store the
	 * folders which don't have a parent. Time complexity is O(n*m) where n is the
	 * folder array length and m is the average length of each word.
	 * 
	 * @param folder
	 * @return
	 */
	public static List<String> removeSubfolders(String[] folder) {
		// sort folder array by string length
		Arrays.sort(folder, (a, b) -> a.length() - b.length());
		Node root = new Node();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < folder.length; i++) {
			// search if a parent exists in the trie for the current folder
			if (!searchParent(root, folder[i])) {
				// if no parent exists, add it
				addFolder(root, folder[i]);
				result.add(folder[i]);
			}
		}
		return result;
	}

	/**
	 * Simple solution which sorts the input array and checks if the previous folder
	 * is the parent of the current one, before adding it to the result list. Time
	 * complexity is O(n*m) where n is the folder array length and m is the average
	 * length of each word.
	 * 
	 * @param folder
	 * @return
	 */
	public List<String> removeSubfolders2(String[] folder) {
		Arrays.sort(folder);
		List<String> result = new ArrayList<>();
		result.add(folder[0]);
		String prev = folder[0];
		int prevLength = prev.length();
		for (int i = 1; i < folder.length; i++) {
			if (folder[i].length() <= prevLength
					|| !(folder[i].startsWith(prev) && folder[i].charAt(prevLength) == '/')) {
				result.add(folder[i]);
				prev = folder[i];
				prevLength = prev.length();
			}
		}
		return result;
	}

	private static void addFolder(Node root, String word) {
		// insert one character at each level until the word ends
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (index < 0) {
				index = 26;
			}
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

	private static boolean searchParent(Node root, String word) {
		// traverse the node levels using the word characters
		Node current = root;
		int length = word.length();
		for (int i = 0; i < length; i++) {
			int index = word.charAt(i) - 'a';
			if (index < 0) {
				index = 26;
				if (current.isTerminal) {
					// the previous char from '/' is a terminal one, this folder has a parent
					return true;
				}
			}
			current = current.children[index];
			if (current == null) {
				// word was not found in the trie
				return false;
			}
		}
		return false;
	}

	private static class Node {
		Node[] children = new Node[27];
		boolean isTerminal;
	}

	private static void check(String[] folder, List<String> expected) {
		System.out.println("customers is: " + Arrays.toString(folder));
		System.out.println("expected is: " + expected);
		List<String> removeSubfolders = removeSubfolders(folder); // Calls your implementation
		System.out.println("removeSubfolders is: " + removeSubfolders);
	}
}
