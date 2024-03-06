package leetcode.arraystring;

import java.util.Arrays;

public class Candy {

	public static void main(String[] args) {
		check(new int[] { 1, 0, 2 }, 5);
		check(new int[] { 1, 2, 2 }, 4);
		check(new int[] { 1, 3, 2, 2 }, 5);
		check(new int[] { 1, 3, 2, 2, 1 }, 7);
		check(new int[] { 1, 2, 87, 87, 87, 2, 1 }, 13);
		check(new int[] { 1, 2, 3, 1, 0 }, 9);

	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/candy. This solution always
	 * assigns the minimum amount of candies possible to every child. If the amount
	 * to be assigned is 0 then an extra candy is retroactively given to every child
	 * until the last non decreasing index, so that we can assign 1 candy to the
	 * current child. Time complexity is O(n) where n is the length of the ratings
	 * array.
	 * 
	 * @param ratings
	 * @return
	 */
	public static int candy(int[] ratings) {
		int length = ratings.length;
		int totalCandies = 1;
		int previousCandies = 1;
		int lastNonDecrIndex = 0;
		int lastNonDecrCandies = 1;
		int candies;
		// iterate all candies starting from the second one
		for (int i = 1; i < length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				// current kid has higher rating than previous one and should have one more
				// candy
				candies = previousCandies + 1;
				// update last non decreasing index and candies
				lastNonDecrIndex = i;
				lastNonDecrCandies = candies;
			} else if (ratings[i] < ratings[i - 1]) {
				// current kid has lower rating than previous one and should have the minimum
				// number of candies (1)
				candies = 1;
				if (previousCandies == 1) {
					// the previous kid also had one candy but had higher rating, we should
					// compensate give one more candy to all kids until the last non decreasing
					// index
					int candiesOffset = i - lastNonDecrIndex;
					if (lastNonDecrCandies > candiesOffset) {
						// if the child at last non decreasing index already had more candies than the
						// next one after compensating, do not give another candy to it
						totalCandies += candiesOffset - 1;
					} else {
						totalCandies += candiesOffset;
					}
				}
			} else {
				// current kid has the same rating as the previous one and should have the
				// minimum number of candies (1)
				candies = 1;
				// update last non decreasing index and candies
				lastNonDecrIndex = i;
				lastNonDecrCandies = 1;
			}
			// update total number of candies and the previous child candies
			totalCandies += candies;
			previousCandies = candies;
		}
		return totalCandies;
	}

	private static void check(int[] ratings, int expectedCandies) {
		System.out.println("ratings is: " + Arrays.toString(ratings));
		System.out.println("expectedCandies is: " + expectedCandies);
		int candies = candy(ratings); // Calls your implementation
		System.out.println("candies is: " + candies);
	}
}
