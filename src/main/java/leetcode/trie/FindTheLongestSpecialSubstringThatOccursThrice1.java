package leetcode.trie;

public class FindTheLongestSpecialSubstringThatOccursThrice1 {

	public static void main(String[] args) {
		check("aaaa", 2);
		check("abcdef", -1);
		check("abcaba", 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i.
	 * This solution uses a trie to find all possible sequences of characters and
	 * keep count of their occurrences. Time complexity is O(n) where n is the
	 * length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maximumLength(String s) {
		Node root = new Node();
		char[] chars = s.toCharArray();
		int maxLength = -2;
		int index = 0;
		while (index < chars.length) {
			int startIndex = index;
			char current = chars[index++];
			// count consecutive characters which are equal to current
			while (index < chars.length && chars[index] == current) {
				index++;
			}
			int charIndex = current - 'a';
			int length = index - startIndex;
			int currentLength = 0;
			Node currentNode = root;
			// add each subsequence of the consecutive character to the trie, updating
			// counts
			while (currentLength < length) {
				Node child = currentNode.children[charIndex];
				if (child == null) {
					child = new Node();
					child.length = currentLength;
					currentNode.children[charIndex] = child;
				}
				// increase occurences according to the length of the subsequence
				child.occurrences += length - currentLength;
				if (child.occurrences >= 3 && child.length > maxLength) {
					// update maxLength if required
					maxLength = child.length;
				}
				currentNode = currentNode.children[charIndex];
				currentLength++;
			}
		}
		// length was underestimated by 1
		return maxLength + 1;
	}

	private static final class Node {
		Node[] children = new Node[26];
		int occurrences = 0;
		int length = 0;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int maximumLength = maximumLength(s); // Calls your implementation
		System.out.println("maximumLength is: " + maximumLength);
	}
}
