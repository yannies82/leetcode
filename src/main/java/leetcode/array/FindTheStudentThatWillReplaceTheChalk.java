package leetcode.array;

import java.util.Arrays;

public class FindTheStudentThatWillReplaceTheChalk {

	public static void main(String[] args) {
		check(new int[] { 5, 1, 5 }, 22, 0);
		check(new int[] { 3, 4, 1, 2 }, 25, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk.
	 * Time complexity is O(n) where n is the length of the chalk array.
	 * 
	 * @param chalk
	 * @param k
	 * @return
	 */
	public static int chalkReplacer(int[] chalk, int k) {
		long total = 0;
		for (int i = 0; i < chalk.length; i++) {
			total += chalk[i];
		}
		long realK = k % total;
		int i = 0;
		do {
			realK -= chalk[i++];
		} while (realK >= 0);
		return i - 1;
	}

	private static void check(int[] chalk, int k, int expected) {
		System.out.println("chalk is: " + Arrays.toString(chalk));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int chalkReplacer = chalkReplacer(chalk, k); // Calls your implementation
		System.out.println("chalkReplacer is: " + chalkReplacer);
	}
}
