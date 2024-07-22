package leetcode.arraystring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortThePeople {

	public static void main(String[] args) {
		check(new String[] { "Mary", "John", "Emma" }, new int[] { 180, 165, 170 },
				new String[] { "Mary", "Emma", "John" });
		check(new String[] { "Alice", "Bob", "Bob" }, new int[] { 155, 185, 150 },
				new String[] { "Bob", "Alice", "Bob" });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sort-the-people. Time
	 * complexity is O(nlogn) where n is the length of the names array.
	 * 
	 * @param names
	 * @param heights
	 * @return
	 */
	public static String[] sortPeople(String[] names, int[] heights) {
		// keeps the names by height
		Map<Integer, String> namesMap = new HashMap<>();
		for (int i = 0; i < names.length; i++) {
			namesMap.put(heights[i], names[i]);
		}
		int lastIndex = names.length - 1;
		Arrays.sort(heights);
		String[] result = new String[names.length];
		for (int i = 0; i <= lastIndex; i++) {
			result[i] = namesMap.get(heights[lastIndex - i]);
		}
		return result;
	}

	/**
	 * Alternate solution which uses an array instead of a map. Time complexity is
	 * O(nlogn) where n is the length of the names array.
	 * 
	 * @param names
	 * @param heights
	 * @return
	 */
	public static String[] sortPeople2(String[] names, int[] heights) {
		int[] indexByHeight = new int[100001];
		int minHeight = Integer.MAX_VALUE;
		int maxHeight = 0;
		for (int i = 0; i < names.length; i++) {
			if (heights[i] < minHeight) {
				minHeight = heights[i];
			}
			if (heights[i] > maxHeight) {
				maxHeight = heights[i];
			}
			indexByHeight[heights[i]] = i + 1;
		}
		int index = 0;
		String[] result = new String[names.length];
		for (int i = maxHeight; i >= minHeight; i--) {
			if (indexByHeight[i] > 0) {
				result[index++] = names[indexByHeight[i] - 1];
			}
		}
		return result;
	}

	private static void check(String[] names, int[] heights, String[] expected) {
		System.out.println("names is: " + Arrays.toString(names));
		System.out.println("heights is: " + Arrays.toString(heights));
		System.out.println("expected is: " + Arrays.toString(expected));
		String[] sortPeople = sortPeople(names, heights); // Calls your implementation
		System.out.println("sortPeople is: " + Arrays.toString(sortPeople));
	}
}
