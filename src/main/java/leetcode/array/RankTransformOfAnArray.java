package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RankTransformOfAnArray {

	public static void main(String[] args) {
		check(new int[] { 40, 10, 20, 30 }, new int[] { 4, 1, 2, 3 });
		check(new int[] { 100, 100, 100 }, new int[] { 1, 1, 1 });
		check(new int[] { 37, 12, 28, 9, 100, 56, 80, 5, 12 }, new int[] { 5, 3, 4, 2, 8, 6, 7, 1, 3 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/rank-transform-of-an-array.
	 * This approach uses 2 different solution according to the range of values in
	 * arr. If the range is small, it instantiates intermediate arrays of size range
	 * and uses a non sorting solution of time complexity O(n). If the range is
	 * large, it uses an intermediate array of size n to copy and sort the initial
	 * array with a time complexity of O(nlogn).
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] arrayRankTransform(int[] arr) {
		if (arr.length == 0) {
			return arr;
		}
		// find min and max to calculate range
		int min = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			} else if (arr[i] > max) {
				max = arr[i];
			}
		}
		int range = max - min + 1;
		if (range > 10000000) {
			// range is large
			return arrayRankTransformSorting(arr);
		}
		// range is small
		return arrayRankTransformNoSorting(arr, min, max, range);
	}

	private static int[] arrayRankTransformSorting(int[] arr) {
		// copy the input array and sort the copy
		int[] sorted = new int[arr.length];
		System.arraycopy(arr, 0, sorted, 0, arr.length);
		Arrays.sort(sorted);
		// intstantiate a map and assign a rank to each of the arr values
		Map<Integer, Integer> rankMap = new HashMap<>();
		int rank = 1;
		for (int i = 0; i < sorted.length; i++) {
			if (!rankMap.containsKey(sorted[i])) {
				rankMap.put(sorted[i], rank++);
			}
		}
		// replace the initial arr values with their rank
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rankMap.get(arr[i]);
		}
		return arr;
	}

	private static int[] arrayRankTransformNoSorting(int[] arr, int min, int max, int range) {
		// instantiate an array which marks existing offseted values
		boolean[] exist = new boolean[range];
		for (int i = 0; i < arr.length; i++) {
			exist[arr[i] - min] = true;
		}
		int rank = 1;
		// instantiate an array to assign a rank to every existing offseted value
		int[] ranks = new int[range];
		for (int i = 0; i < range; i++) {
			if (exist[i]) {
				ranks[i] = rank++;
			}
		}
		// replace arr values with their ranks
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ranks[arr[i] - min];
		}
		return arr;
	}

	/**
	 * Alternate solution. Time complexity is O(nlogn) where n is the length of arr.
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] arrayRankTransform2(int[] arr) {
		if (arr.length == 0) {
			return arr;
		}
		int[][] arrWithIndex = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			arrWithIndex[i] = new int[] { arr[i], i };
		}
		Arrays.sort(arrWithIndex, (a, b) -> Integer.compare(a[0], b[0]));
		int rank = 1;
		arr[arrWithIndex[0][1]] = rank;
		for (int i = 1; i < arr.length; i++) {
			if (arrWithIndex[i][0] > arrWithIndex[i - 1][0]) {
				rank++;
			}
			arr[arrWithIndex[i][1]] = rank;
		}
		return arr;
	}

	private static void check(int[] arr, int[] expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] arrayRankTransform = arrayRankTransform(arr); // Calls your implementation
		System.out.println("arrayRankTransform is: " + Arrays.toString(arrayRankTransform));
	}
}
