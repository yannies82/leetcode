package leetcode.string;

public class MinimumDeletionsToMakeStringBalanced {

	public static void main(String[] args) {
		check("aababbab", 2);
		check("bbaaaaabb", 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-deletions-to-make-string-balanced. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minimumDeletions(String s) {
		char[] chars = s.toCharArray();
		int minDeletions = 0;
		int bCount = 0;
		for (int i = 0; i < chars.length; i++) {
			// we have to perform a deletion for encountered 'a's if matching 'b's exist
			if (minDeletions < bCount) {
				// there are more 'b's than 'a's, add to minDeletions in case of 'a'
				minDeletions += 'b' - chars[i];
			}
			bCount += chars[i] - 'a';
		}
		return minDeletions;
	}

	/**
	 * This solution counts the number of 'b's before and 'a's after each index and
	 * returns the min sum as the result. Time complexity is O(n) where n is the
	 * length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minimumDeletions2(String s) {
		char[] chars = s.toCharArray();
		int[] bsBefore = new int[chars.length];
		int[] asAfter = new int[chars.length];
		for (int i = 1; i < chars.length; i++) {
			int prevIndex = i - 1;
			int mirrorNextIndex = chars.length - i;
			int mirrorI = mirrorNextIndex - 1;
			bsBefore[i] = bsBefore[prevIndex];
			asAfter[mirrorI] = asAfter[mirrorNextIndex];
			if (chars[prevIndex] == 'b') {
				bsBefore[i]++;
			}
			if (chars[mirrorNextIndex] == 'a') {
				asAfter[mirrorI]++;
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < chars.length; i++) {
			int operations = bsBefore[i] + asAfter[i];
			if (operations < result) {
				result = operations;
			}
		}
		return result;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minimumDeletions = minimumDeletions(s); // Calls your implementation
		System.out.println("minimumDeletions is: " + minimumDeletions);
	}
}
