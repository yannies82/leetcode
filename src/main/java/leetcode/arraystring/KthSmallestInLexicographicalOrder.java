package leetcode.arraystring;

public class KthSmallestInLexicographicalOrder {

	public static void main(String[] args) {
		check(957747794, 424238336, 481814499);
		check(1, 1, 1);
		check(13, 2, 10);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order. This
	 * solution considers every starting digit as a tree and counts how many numbers
	 * exist on each tree level that are less than or equal to n, adjusting the
	 * current index and the result as necessary. Time complexity is O(logn).
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int findKthNumber(int n, int k) {
		// assume that the result starts with 1
		int result = 1;
		// start with index 1
		int index = 1;
		while (index < k) {
			// count how many numbers exist that start with result and
			// are less than or equal to n
			int count = countStartingWithUpTo(result, n);
			if (index + count <= k) {
				// the numbers already counted plus count are less than or equal to k
				// this means that the kth number does not start with result
				// therefore continue searching after adding count to the index
				// and 1 to result
				index += count;
				result++;
			} else {
				// the numbers already counted plus count are more than k
				// this means that the kth number starts with result but is not result
				// therefore continue searching after adding 1 to the index
				// and multiplying result by 10
				index++;
				result *= 10;
			}
		}
		return result;
	}

	/**
	 * Counts the numbers that start with startWith as first digits and are less
	 * than or equal to limit.
	 * 
	 * @param startWith
	 * @param limit
	 * @return
	 */
	private static int countStartingWithUpTo(long startWith, int limit) {
		long startWithNext = startWith + 1;
		int diff = 0;
		while (startWith <= limit) {
			diff += Math.min(limit + 1, startWithNext) - startWith;
			startWith *= 10;
			startWithNext *= 10;
		}
		return diff;
	}

	private static void check(int n, int k, int expected) {
		System.out.println("n is: " + n);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int lexicalOrder = findKthNumber(n, k); // Calls your implementation
		System.out.println("lexicalOrder is: " + lexicalOrder);
	}
}
