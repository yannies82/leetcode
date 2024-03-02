package leetcode.math;

public class SquareRoot {

	public static void main(String[] args) {
		check(4, 2);
		check(3, 1);
		check(26, 5);
		check(1024, 32);
	}

	/**
	 * This solution uses binary search to find the smallest number whose square is
	 * larger than x. Time complexity is O(logx).
	 * 
	 * @param x
	 * @return
	 */
	public static int mySqrt(int x) {
		if (x == 0 || x == 1) {
			return x;
		}
		int start = 0;
		int end = x;
		int mid;
		while (start < (mid = (start + end) / 2)) {
			if ((double) mid * mid > x) {
				end = mid;
			} else {
				start = mid;
			}
		}
		return start;
	}

	/**
	 * This solution uses the approximation of the Newton - Raphson method with 20
	 * iterations.
	 * 
	 * @param x
	 * @return
	 */
	public static int mySqrt2(int x) {
		double xn = 1;
		for (int i = 0; i < 20; i++) {
			xn = 0.5 * (xn + x / xn);
		}
		return (int) xn;
	}

	private static void check(int x, int expected) {
		System.out.println("x is: " + x);
		System.out.println("expected is: " + expected);
		int mySqrt = mySqrt(x); // Calls your implementation
		System.out.println("mySqrt is: " + mySqrt);
	}

}
