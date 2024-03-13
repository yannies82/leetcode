package leetcode.arraystring;

public class FindThePivotInteger {

	public static void main(String[] args) {
		check(8, 6);
		check(1, 1);
		check(4, -1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-the-pivot-integer. This
	 * solution uses binary search to find the pivot integer. Time complexity is
	 * O(logn).
	 * 
	 * @param n
	 * @return
	 */
	public static int pivotInteger(int n) {
		int start = 0;
		int end = n;
		int mid = -1, sum1 = 0, sum2 = 0;
		while (start <= end
				&& (sum1 = (mid = (start + end) / 2) * (mid + 1) / 2) != (sum2 = n * (n + 1) / 2 - sum1 + mid)) {
			if (sum1 > sum2) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return sum1 != sum2 ? -1 : mid;
	}

	/**
	 * Alternate solution, similar to the first one. Performs a little worse but is
	 * more readable.
	 * 
	 * @param n
	 * @return
	 */
	public static int pivotInteger2(int n) {
		int start = 0;
		int end = n;
		int maxSum = n * (n + 1) / 2;
		do {
			int mid = (start + end) / 2;
			int sum1 = mid * (mid + 1) / 2;
			int sum2 = maxSum - sum1 + mid;
			if (sum1 > sum2) {
				end = mid - 1;
			} else if (sum1 < sum2) {
				start = mid + 1;
			} else {
				return mid;
			}
		} while (start <= end);
		return -1;
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int pivotInteger = pivotInteger(n); // Calls your implementation
		System.out.println("pivotInteger is: " + pivotInteger);
	}
}
