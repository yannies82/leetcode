package leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class KthDistinctStringInArray {

	public static void main(String[] args) {
		check(new String[] { "d", "b", "c", "b", "c", "a" }, 2, "a");
		check(new String[] { "aaa", "aa", "a" }, 1, "aaa");
		check(new String[] { "a", "b", "a" }, 3, "");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/kth-distinct-string-in-an-array. Time
	 * complexity is O(n) where n is the length of arr.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public static String kthDistinct(String[] arr, int k) {
		Set<String> distinct = new LinkedHashSet<>();
		Set<String> nonDistinct = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (!nonDistinct.contains(arr[i])) {
				if (distinct.contains(arr[i])) {
					distinct.remove(arr[i]);
					nonDistinct.add(arr[i]);
				} else {
					distinct.add(arr[i]);
				}
			}
		}
		if (distinct.size() < k) {
			return "";
		}
		int index = 0;
		Iterator<String> distinctIter = distinct.iterator();
		while (++index < k) {
			distinctIter.next();
		}
		return distinctIter.next();
	}

	/**
	 * Alternative solution. Time complexity is O(n) where n is the length of arr.
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public static String kthDistinct2(String[] arr, int k) {
		Map<String, Boolean> duplicatesMap = new LinkedHashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (duplicatesMap.get(arr[i]) == null) {
				duplicatesMap.put(arr[i], false);
			} else {
				duplicatesMap.put(arr[i], true);
			}
		}
		int index = 0;
		for (Map.Entry<String, Boolean> entry : duplicatesMap.entrySet()) {
			if (!entry.getValue()) {
				index++;
				if (index == k) {
					return entry.getKey();
				}
			}
		}
		return "";
	}

	private static void check(String[] arr, int k, String expected) {
		System.out.println("arr is: " + Arrays.toString(arr));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		String kthDistinct = kthDistinct(arr, k); // Calls your implementation
		System.out.println("kthDistinct is: " + kthDistinct);
	}
}
