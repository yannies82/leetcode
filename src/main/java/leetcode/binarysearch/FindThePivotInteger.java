package leetcode.binarysearch;

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
		int maxSum = n * (n + 1) / 2;
		int sum1, sum2, mid;
		do {
			mid = (start + end) / 2;
			sum1 = mid * (mid + 1) / 2;
			sum2 = maxSum - sum1 + mid;
			if (sum1 > sum2) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		} while (start <= end && sum1 != sum2);
		return sum1 == sum2 ? mid : -1;
	}

	/**
	 * Alternatice solution which linearly test all numbers and keeps a left sum and
	 * right sum at all times. At each step it increases the left sum and decreases
	 * the right sum. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int pivotInteger3(int n) {
		int leftSum = 0;
		int rightSum = n * (n + 1) / 2;
		int candidatePivot = 0;
		while (leftSum < rightSum) {
			candidatePivot++;
			leftSum = leftSum + candidatePivot;
			rightSum = rightSum - candidatePivot + 1;
		}
		return leftSum == rightSum ? candidatePivot : -1;
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int pivotInteger = pivotInteger(n); // Calls your implementation
		System.out.println("pivotInteger is: " + pivotInteger);
	}
}
