package leetcode.dynamicprogramming;

public class UglyNumber2 {

	public static void main(String[] args) {
		check(10, 12);
		check(1, 1);
		check(213, 20250);
	}

	/**
	 * This solution uses bottom up dynamic programming to calculate the solutions
	 * to all subproblems from 1 to n. Time complexity is O(n).
	 * 
	 * @param n
	 * @return
	 */
	public static int nthUglyNumber(int n) {
		// initialize array which contains solutions to subproblems
		int[] dpArray = new int[n];
		dpArray[0] = 1;
		// keep 3 separate indexes for prime numbers 2, 3, 5
		int index2 = 0;
		int index3 = 0;
		int index5 = 0;
		// initialize multiple values for prime numbers 2, 3, 5
		int multTwo = 2;
		int multThree = 3;
		int multFive = 5;
		// bottom up calculate solutions to all subproblems
		for (int i = 1; i < n; i++) {
			// the next value is the min for the multiples of 2, 3, 5
			dpArray[i] = Math.min(multTwo, Math.min(multThree, multFive));
			if (dpArray[i] == multTwo) {
				// increase index for prime number 2 and recalculate for next comparison
				multTwo = dpArray[++index2] * 2;
			}
			if (dpArray[i] == multThree) {
				// increase index for prime number 3 and recalculate for next comparison
				multThree = dpArray[++index3] * 3;
			}
			if (dpArray[i] == multFive) {
				// increase index for prime number 5 and recalculate for next comparison
				multFive = dpArray[++index5] * 5;
			}
		}
		return dpArray[n - 1];
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int nthUglyNumber = nthUglyNumber(n); // Calls your implementation
		System.out.println("nthUglyNumber is: " + nthUglyNumber);
	}
}
