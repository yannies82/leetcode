package leetcode.math;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindTheWinnerOfTheCircularGame {

	public static void main(String[] args) {
		check(5, 4, 1);
		check(5, 2, 3);
		check(6, 5, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-the-winner-of-the-circular-game.
	 * This is the solution to the Josephus problem. Time complexity is O(n).
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int findTheWinner(int n, int k) {
		return josephusHelper(n, k) + 1;
	}

	private static int josephusHelper(int n, int k) {
		if (n == 1) {			
			return 0;
		}
		return (josephusHelper(n - 1, k) + k) % n;
	}

	/**
	 * Alternative solution.
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int findTheWinner2(int n, int k) {
		if (n == 1) {
			return 1;
		}
		Set<Integer> numSet = new LinkedHashSet<>();
		for (int i = 1; i <= n; i++) {
			numSet.add(i);
		}
		Iterator<Integer> iter = numSet.iterator();
		while (numSet.size() > 1) {
			int realK = ((k - 1) % numSet.size()) + 1;
			int count = 1;
			do {
				if (!iter.hasNext()) {
					iter = numSet.iterator();
				}
				iter.next();
			} while (count++ < realK);
			iter.remove();
		}
		return numSet.iterator().next();
	}

	private static void check(int n, int k, int expected) {
		System.out.println("n is:" + n);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int findTheWinner = findTheWinner(n, k); // Calls your implementation
		System.out.println("findTheWinner is: " + findTheWinner);
	}
}
