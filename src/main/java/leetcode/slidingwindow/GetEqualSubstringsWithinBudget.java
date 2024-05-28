package leetcode.slidingwindow;

public class GetEqualSubstringsWithinBudget {

	public static void main(String[] args) {
		check("abcd", "bcdf", 3, 3);
		check("abcd", "cdef", 3, 1);
		check("abcd", "acde", 0, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/get-equal-substrings-within-budget. This
	 * solution calculates the cost for each character and keeps it an an array. It
	 * then uses a sliding window to find the max length for the specified max cost.
	 * Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param t
	 * @param maxCost
	 * @return
	 */
	public static int equalSubstring(String s, String t, int maxCost) {
		int length = s.length();
		// initialize and populate an array to keep the cost for every character
		int[] costs = new int[length];
		for (int i = 0; i < length; i++) {
			costs[i] = Math.abs(s.charAt(i) - t.charAt(i));
		}

		int left = 0;
		int right = 0;
		int maxLength = 0;
		int cost = 0;
		// use a sliding window to find the max length
		while (right < length) {
			// increase window size until the cost exceeds the max cost
			while (right < length && cost <= maxCost) {
				cost += costs[right++];
			}
			// update max length if needed
			maxLength = Math.max(maxLength, right - left - 1);
			// decrease window size until the cost is less than or equal to the max cost
			while (left < right && cost > maxCost) {
				cost -= costs[left++];
			}
			// update max length if needed
			maxLength = Math.max(maxLength, right - left);
		}
		return maxLength;
	}

	private static void check(String s, String t, int maxCost, int expected) {
		System.out.println("s is: " + s);
		System.out.println("t is: " + t);
		System.out.println("expected is: " + expected);
		int equalSubstring = equalSubstring(s, t, maxCost); // Calls your implementation
		System.out.println("equalSubstring is: " + equalSubstring);
	}
}
