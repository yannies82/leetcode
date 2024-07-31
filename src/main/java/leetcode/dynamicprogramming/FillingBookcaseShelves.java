package leetcode.dynamicprogramming;

import java.util.Arrays;

public class FillingBookcaseShelves {

	public static void main(String[] args) {
		check(new int[][] { { 1, 1 }, { 2, 3 }, { 2, 3 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 2 } }, 4, 6);
		check(new int[][] { { 1, 3 }, { 2, 4 }, { 3, 2 } }, 6, 4);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/filling-bookcase-shelves.
	 * This solution uses bottom up dynamic programming to find the solutions to all
	 * subproblems from 0 to n. Time complexity is O(n^2) where n is the length of
	 * the books array.
	 * 
	 * @param books
	 * @param shelfWidth
	 * @return
	 */
	public static int minHeightShelves(int[][] books, int shelfWidth) {
		int n = books.length;
		int[] dpArray = new int[n + 1];
		Arrays.fill(dpArray, Integer.MAX_VALUE);
		dpArray[0] = 0;
		for (int i = 1; i <= n; ++i) {
			int currentWidth = 0;
			int currentHeight = 0;
			// initiate a new shelf starting with book i - 1 and keep adding books
			// as long as they fit into the shelf (currentWidth <= shelfWidth)
			for (int j = i - 1; j >= 0; j--) {
				// add previous book to the shelf if it fits
				currentWidth += books[j][0];
				if (currentWidth > shelfWidth) {
					// shelf width has been exceeded, no more calculations for this book
					break;
				}
				// check if the new book will increase the current shelf height
				currentHeight = Math.max(currentHeight, books[j][1]);
				// update the result for the current book
				dpArray[i] = Math.min(dpArray[i], dpArray[j] + currentHeight);
			}
		}

		return dpArray[n];
	}

	private static void check(int[][] books, int shelfWidth, int expected) {
		System.out.println("books is: ");
		for (int[] book : books) {
			System.out.println(Arrays.toString(book));
		}
		System.out.println("shelfWidth is: " + shelfWidth);
		System.out.println("expected is: " + expected);
		int minHeightShelves = minHeightShelves(books, shelfWidth); // Calls your implementation
		System.out.println("minHeightShelves is: " + minHeightShelves);
	}

}
