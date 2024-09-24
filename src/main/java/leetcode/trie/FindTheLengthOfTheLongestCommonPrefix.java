package leetcode.trie;

import java.util.Arrays;

public class FindTheLengthOfTheLongestCommonPrefix {

	public static void main(String[] args) {
		check(new int[] { 1, 10, 100 }, new int[] { 1000 }, 3);
		check(new int[] { 1, 2, 3 }, new int[] { 4, 4, 4 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix.
	 * This solution uses a trie to keep all numbers of arr1, then searches all
	 * numbers of arr2 in this trie and returns the max matched length. Time
	 * complexity is O(m+n) where m is the length of arr1 and n is the length of
	 * arr2.
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static int longestCommonPrefix(int[] arr1, int[] arr2) {
		// create a trie and add all numbers from arr1
		Node root = new Node();
		for (int i = 0; i < arr1.length; i++) {
			addNumber(root, arr1[i]);
		}
		// search all numbers from arr2 in the trie, keep the max matching length
		int maxLength = 0;
		// keeps the max matching length of numbers in arr2
		int[] length = { 0 };
		for (int i = 0; i < arr2.length; i++) {
			length[0] = 0;
			// search the number from arr2 in the trie and update the max length
			searchNumber(root, arr2[i], length);
			if (length[0] > maxLength) {
				maxLength = length[0];
			}
		}
		return maxLength;
	}

	private static Node addNumber(Node root, int number) {
		if (number == 0) {
			return null;
		}
		// recursively add digits to the trie so that
		// higher order digits are added first
		Node current = addNumber(root, number / 10);
		// use the node which was returned by the higher order digit
		if (current == null) {
			// if no such node exists, use root
			current = root;
		}
		// add the digit to the trie and return the new (or existing) node
		int digit = number % 10;
		Node result = current.children[digit];
		if (result == null) {
			result = new Node();
			current.children[digit] = result;
		}
		return result;
	}

	public static Node searchNumber(Node root, int number, int[] length) {
		if (number == 0) {
			return null;
		}
		Node current = root;
		int div = number / 10;
		if (div > 0) {
			// recursively search for digits so that
			// higher order digits are searched first
			current = searchNumber(root, div, length);
		}
		// by default assume that there is no match
		Node result = null;
		if (current != null) {
			// if the higher order digit returned a matched node
			// search for the current digit in its children
			int digit = number % 10;
			result = current.children[digit];
			if (result != null) {
				// if a node was found increase the result length
				length[0]++;
			}
		}
		// return the matched node, if there is one
		return result;
	}

	private static class Node {
		Node[] children = new Node[10];
	}

	private static void check(int[] arr1, int[] arr2, int expected) {
		System.out.println("arr1 is: " + Arrays.toString(arr1));
		System.out.println("arr2 is: " + Arrays.toString(arr2));
		System.out.println("expected is: " + expected);
		int longestCommonPrefix = longestCommonPrefix(arr1, arr2); // Calls your implementation
		System.out.println("longestCommonPrefix is: " + longestCommonPrefix);
	}
}
