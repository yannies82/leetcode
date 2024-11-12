package leetcode.binarysearch;

import java.util.Arrays;

public class MostBeautifulItemForEachQuery {

	public static void main(String[] args) {
		check(new int[][] { { 193, 732 }, { 781, 962 }, { 864, 954 }, { 749, 627 }, { 136, 746 }, { 478, 548 },
				{ 640, 908 }, { 210, 799 }, { 567, 715 }, { 914, 388 }, { 487, 853 }, { 533, 554 }, { 247, 919 },
				{ 958, 150 }, { 193, 523 }, { 176, 656 }, { 395, 469 }, { 763, 821 }, { 542, 946 }, { 701, 676 } },
				new int[] { 885, 1445, 1580, 1309, 205, 1788, 1214, 1404, 572, 1170, 989, 265, 153, 151, 1479, 1180,
						875, 276, 1584 },
				new int[] { 962, 962, 962, 962, 746, 962, 962, 962, 946, 962, 962, 919, 746, 746, 962, 962, 962, 919,
						962 });
		check(new int[][] { { 1, 2 }, { 3, 2 }, { 2, 4 }, { 5, 6 }, { 3, 5 } }, new int[] { 1, 2, 3, 4, 5, 6 },
				new int[] { 2, 4, 5, 5, 6, 6 });
		check(new int[][] { { 1, 2 }, { 1, 2 }, { 1, 3 }, { 1, 4 } }, new int[] { 1 }, new int[] { 4 });
		check(new int[][] { { 10, 1000 } }, new int[] { 5 }, new int[] { 0 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/most-beautiful-item-for-each-query. This
	 * solution sorts the items array by price, then updates the beauty of each item
	 * and applies binary search to find the result of each query. Time complexity
	 * is O(nlogn+mlogm) where n is the length of the items array and m is the
	 * length of the queries array.
	 * 
	 * @param items
	 * @param queries
	 * @return
	 */
	public static int[] maximumBeauty(int[][] items, int[] queries) {
		Arrays.sort(items, (a, b) -> a[0] - b[0]);
		int max = items[0][1];
		for (int i = 1; i < items.length; i++) {
			max = Math.max(max, items[i][1]);
			items[i][1] = max;
		}
		int[] result = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			result[i] = binarySearch(items, queries[i]);
		}
		return result;
	}

	private static int binarySearch(int[][] items, int target) {
		int low = 0;
		int high = items.length;
		int result = 0;
		while (low < high) {
			int mid = (low + high) / 2;
			if (items[mid][0] <= target) {
				result = items[mid][1];
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return result;
	}

	/**
	 * Alternative solution which sorts the items array by price and iterates the
	 * queries in sorted order to produce the results. Time complexity is
	 * O(nlogn+mlogm) where n is the length of the items array and m is the length
	 * of the queries array.
	 * 
	 * @param items
	 * @param queries
	 * @return
	 */
	public static int[] maximumBeauty2(int[][] items, int[] queries) {
		Arrays.sort(items, (a, b) -> a[0] - b[0]);
		Integer[] queryIndexes = new Integer[queries.length];
		for (int i = 0; i < queryIndexes.length; i++) {
			queryIndexes[i] = i;
		}
		Arrays.sort(queryIndexes, (a, b) -> queries[a] - queries[b]);
		int[] result = new int[queries.length];
		int queryIndex = 0;
		int itemIndex = 0;
		int currentMax = 0;
		while (queryIndex < queries.length) {
			if (itemIndex < items.length && queries[queryIndexes[queryIndex]] >= items[itemIndex][0]) {
				// the query price is greater than the current item price, update the max
				// and proceed to the next item
				currentMax = Math.max(currentMax, items[itemIndex++][1]);
			} else {
				// the query price is less than the item price or there are no more items,
				// assign the currentMax value and proceed to the next query
				result[queryIndexes[queryIndex++]] = currentMax;
			}
		}
		return result;
	}

	/**
	 * Alternative solution which sorts the items array by price and iterates the
	 * queries in sorted order to produce the results. Does not modify the input
	 * arrays. Time complexity is O(nlogn+mlogm) where n is the length of the items
	 * array and m is the length of the queries array.
	 * 
	 * @param items
	 * @param queries
	 * @return
	 */
	public static int[] maximumBeauty3(int[][] items, int[] queries) {
		Integer[] itemIndexes = new Integer[items.length];
		for (int i = 0; i < itemIndexes.length; i++) {
			itemIndexes[i] = i;
		}
		Arrays.sort(itemIndexes, (a, b) -> items[a][0] - items[b][0]);
		Integer[] queryIndexes = new Integer[queries.length];
		for (int i = 0; i < queryIndexes.length; i++) {
			queryIndexes[i] = i;
		}
		Arrays.sort(queryIndexes, (a, b) -> queries[a] - queries[b]);
		int[] result = new int[queries.length];
		int queryIndex = 0;
		int itemIndex = 0;
		int currentMax = 0;
		while (queryIndex < queries.length) {
			if (itemIndex < items.length && queries[queryIndexes[queryIndex]] >= items[itemIndexes[itemIndex]][0]) {
				// the query price is greater than the current item price, update the max
				// and proceed to the next item
				currentMax = Math.max(currentMax, items[itemIndexes[itemIndex]][1]);
				itemIndex++;
			} else {
				// the query price is less than the item price, assign the currentMax value
				result[queryIndexes[queryIndex++]] = currentMax;
			}
		}
		return result;
	}

	private static void check(int[][] items, int[] queries, int[] expected) {
		System.out.println("items is: ");
		for (int[] item : items) {
			System.out.println("item is: " + Arrays.toString(item));
		}
		System.out.println("queries is: " + Arrays.toString(queries));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] maximumBeauty = maximumBeauty2(items, queries); // Calls your implementation
		System.out.println("maximumBeauty is: " + Arrays.toString(maximumBeauty));
	}
}
