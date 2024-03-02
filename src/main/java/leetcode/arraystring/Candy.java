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

	public static int candy(int[] ratings) {
		int length = ratings.length;
		int totalCandies = 1;
		int previousCandies = 1;
		int lastNonDecrIndex = 0;
		int lastNonDecrCandies = 1;
		int candies;
		for (int i = 1; i < length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies = previousCandies + 1;
				lastNonDecrIndex = i;
				lastNonDecrCandies = candies;
			} else if (ratings[i] < ratings[i - 1]) {
				candies = 1;
				if (previousCandies == 1) {
					totalCandies += i - lastNonDecrIndex - (lastNonDecrCandies > i - lastNonDecrIndex ? 1 : 0);
				}
			} else {
				candies = 1;
				lastNonDecrIndex = i;
				lastNonDecrCandies = 1;
			}
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
