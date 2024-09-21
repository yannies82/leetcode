package leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers {

	public static void main(String[] args) {
		check(13, List.of(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9));
		check(2, List.of(1, 2));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/lexicographical-numbers. This
	 * solution traverses recursively all numbers starting with digits from 1 to 9
	 * and adds them to the result list. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> lexicalOrder(int n) {
		List<Integer> result = new ArrayList<>();
		int limit = Math.min(n, 9);
		for (int i = 1; i <= limit; i++) {
			// recursively add to the list all numbers starting with i
			add(i, n, result);
		}
		return result;
	}

	private static void add(int i, int n, List<Integer> result) {
		if (i > n) {
			// early exit if i is greater than n
			return;
		}
		// add the number to the list
		result.add(i);
		// multiply number * 10 and recursively add numbers
		// starting from newI to newI + 9 while they are not greater than n
		i = i * 10;
		int limit = Math.min(n, i + 9);
		for (int j = i; j <= limit; j++) {
			add(j, n, result);
		}
	}

	private static void check(int n, List<Integer> expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		List<Integer> lexicalOrder = lexicalOrder(n); // Calls your implementation
		System.out.println("lexicalOrder is: " + lexicalOrder);
	}
}
