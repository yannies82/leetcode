package leetcode.math;

public class PassThePillow {

	public static void main(String[] args) {
		check(4, 5, 2);
		check(3, 2, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/pass-the-pillow. Time
	 * complexity is O(1).
	 * 
	 * @param n
	 * @param time
	 * @return
	 */
	public static int passThePillow(int n, int time) {
		int numOfPasses = n - 1;
		int div = time / numOfPasses;
		int mod = time % numOfPasses;
		if (div % 2 == 0) {
			return mod + 1;
		}
		return n - mod;
	}

	private static void check(int n, int time, int expected) {
		System.out.println("n is: " + n);
		System.out.println("time is: " + time);
		System.out.println("expected is: " + expected);
		int passThePillow = passThePillow(n, time); // Calls your implementation
		System.out.println("passThePillow is: " + passThePillow);
	}
}
