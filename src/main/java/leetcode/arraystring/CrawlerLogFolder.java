package leetcode.arraystring;

import java.util.Arrays;

public class CrawlerLogFolder {

	public static void main(String[] args) {
		check(new String[] { "d1/", "d2/", "../", "d21/", "./" }, 2);
		check(new String[] { "d1/", "d2/", "./", "d3/", "../", "d31/" }, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/crawler-log-folder. Time
	 * complexity is O(n) where n is the length of the logs array.
	 * 
	 * @param logs
	 * @return
	 */
	public static int minOperations(String[] logs) {
		int minActions = 0;
		for (int i = 0; i < logs.length; i++) {
			int action = switch (logs[i]) {
			case "./" -> 0;
			case "../" -> -1;
			default -> 1;
			};
			minActions = Math.max(minActions + action, 0);
		}
		return minActions;
	}

	private static void check(String[] logs, int expected) {
		System.out.println("customers is: " + Arrays.toString(logs));
		System.out.println("expected is: " + expected);
		int minOperations = minOperations(logs); // Calls your implementation
		System.out.println("minOperations is: " + minOperations);
	}
}
