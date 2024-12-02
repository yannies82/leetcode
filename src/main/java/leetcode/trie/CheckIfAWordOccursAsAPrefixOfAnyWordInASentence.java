package leetcode.trie;

public class CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {

	public static void main(String[] args) {
		check("i love eating burger", "burg", 4);
		check("this problem is an easy problem", "pro", 2);
		check("hellohello hellohellohello", "ell", -1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence.
	 * This solution uses a trie to insert the words in the sentence and then search
	 * for the searchWord. Time complexity is O(n+m) where n is the length of the
	 * sentence and m is the length of the search word.
	 * 
	 * @param sentence
	 * @param searchWord
	 * @return
	 */
	public static int isPrefixOfWord(String sentence, String searchWord) {
		Node root = new Node();
		int length = sentence.length();
		Node current = root;
		int wordIndex = 1;
		for (int i = 0; i < length; i++) {
			char currentChar = sentence.charAt(i);
			if (currentChar == ' ') {
				current = root;
				wordIndex++;
			} else {
				int index = currentChar - 'a';
				Node next = current.children[index];
				if (next == null) {
					current.children[index] = next = new Node();
					next.wordIndex = wordIndex;
				}
				current = next;
			}
		}
		current = root;
		int wordLength = searchWord.length();
		for (int i = 0; i < wordLength; i++) {
			int index = searchWord.charAt(i) - 'a';
			if (current.children[index] == null) {
				return -1;
			}
			current = current.children[index];
		}
		return current.wordIndex;
	}

	private static class Node {
		Node[] children = new Node[26];
		int wordIndex = 0;
	}

	private static void check(String sentence, String searchWord, int expected) {
		System.out.println("sentence is: " + sentence);
		System.out.println("searchWord is: " + searchWord);
		System.out.println("expected is: " + expected);
		int isPrefixOfWord = isPrefixOfWord(sentence, searchWord); // Calls your implementation
		System.out.println("isPrefixOfWord is: " + isPrefixOfWord);
	}
}
