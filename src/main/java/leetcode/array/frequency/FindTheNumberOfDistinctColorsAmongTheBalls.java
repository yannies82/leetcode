package leetcode.array.frequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindTheNumberOfDistinctColorsAmongTheBalls {

	public static void main(String[] args) {
		check(4, new int[][] { { 1, 4 }, { 2, 5 }, { 1, 3 }, { 3, 4 } }, new int[] { 1, 2, 2, 3 });
		check(4, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 2 }, { 3, 4 }, { 4, 5 } }, new int[] { 1, 2, 2, 3, 4 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls.
	 * Time complexity is O(n) where n is the length of the queries array.
	 * 
	 * @param limit
	 * @param queries
	 * @return
	 */
	public static int[] queryResults(int limit, int[][] queries) {
		Map<Integer, Integer> colorCounts = new HashMap<>();
		Map<Integer, Integer> ballColors = new HashMap<>();
		int[] result = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			Integer currentColor = ballColors.get(queries[i][0]);
			if (currentColor != null) {
				int count = colorCounts.get(currentColor);
				if (count == 1) {
					colorCounts.remove(currentColor);
				} else {
					colorCounts.put(currentColor, count - 1);
				}
			}
			int newColor = queries[i][1];
			ballColors.put(queries[i][0], newColor);
			colorCounts.put(newColor, colorCounts.getOrDefault(newColor, 0) + 1);
			result[i] = colorCounts.size();
		}
		return result;
	}

	private static void check(int limit, int[][] queries, int[] expected) {
		System.out.println("limit is: " + limit);
		System.out.println("queries is: ");
		for (int[] query : queries) {
			System.out.println(Arrays.toString(query));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] queryResults = queryResults(limit, queries); // Calls your implementation
		System.out.println("queryResults is: " + Arrays.toString(queryResults));
	}
}
