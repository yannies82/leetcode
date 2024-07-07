package leetcode.math;

public class WaterBottles {

	public static void main(String[] args) {
		check(9, 3, 13);
		check(15, 4, 19);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/water-bottles. Time
	 * complexity is O(logn) by m where n is the numBottles and m is the
	 * numExchange.
	 * 
	 * @param numBottles
	 * @param numExchange
	 * @return
	 */
	public static int numWaterBottles(int numBottles, int numExchange) {
		int result = numBottles;
		int emptyBottles = numBottles;
		int div;
		while ((div = emptyBottles / numExchange) > 0) {
			result += div;
			emptyBottles = div + emptyBottles % numExchange;
		}
		return result;
	}

	private static void check(int numBottles, int numExchange, int expected) {
		System.out.println("numBottles is:" + numBottles);
		System.out.println("numExchange is: " + numExchange);
		System.out.println("expected is: " + expected);
		int numWaterBottles = numWaterBottles(numBottles, numExchange); // Calls your implementation
		System.out.println("numWaterBottles is: " + numWaterBottles);
	}
}
