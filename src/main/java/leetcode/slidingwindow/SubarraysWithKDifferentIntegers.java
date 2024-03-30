package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDifferentIntegers {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 1, 2, 3 }, 2, 7);
		check(new int[] { 1, 2, 1, 3, 4 }, 3, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/subarrays-with-k-different-integers. This
	 * solution finds all arrays with k + 1 or more distinct numbers and all arrays
	 * with k or more distinct numbers using sliding windows. Their difference is
	 * the count of all arrays with exactly k distinct numbers. Time complexity is
	 * O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int subarraysWithKDistinct(int[] nums, int k) {
		// find the count of all arrays with at least k+1 distinct elements and subtract
		// from count of arrays with at least k distinct elements
		return subarraysWithKOrMoreDistinct(nums, k) - subarraysWithKOrMoreDistinct(nums, k + 1);
	}

	public static int subarraysWithKOrMoreDistinct(int[] nums, int k) {
		// use this array to store the count for each distinct number
		// exploit the fact that no number in nums is greater than nums.length
		int[] frequency = new int[nums.length + 1];
		int start = 0;
		int count = 0;
		int distinctCount = 0;
		// iterate all numbers, each time increasing the window size
		for (int end = 0; end < nums.length; end++) {
			// increase the frequency of the element, increase distinctCount if its previous
			// frequency was 0
			if (frequency[nums[end]]++ == 0) {
				distinctCount++;
			}
			// decrease the window size until the size of distinct elements is less than k
			while (distinctCount >= k && start <= end) {
				// decrease the frequency of the element, decrease distinctCount if its previous
				// frequency was 1
				if (frequency[nums[start]]-- == 1) {
					distinctCount--;
				}
				start++;
			}
			count += start;
		}
		return count;
	}

	/**
	 * Similar solution which uses a hashmap instead of an array for keeping the
	 * count of distinct numbers.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int subarraysWithKDistinct2(int[] nums, int k) {
		// find the count of all arrays with at least k+1 distinct elements and subtract
		// from count of arrays with at least k distinct elements
		return subarraysWithKOrMoreDistinct2(nums, k) - subarraysWithKOrMoreDistinct2(nums, k + 1);
	}

	public static int subarraysWithKOrMoreDistinct2(int[] nums, int k) {
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		int start = 0;
		int count = 0;
		// iterate all numbers, each time increasing the window size
		for (int end = 0; end < nums.length; end++) {
			// add element to the map along with its frequency
			frequencyMap.put(nums[end], frequencyMap.getOrDefault(nums[end], 0) + 1);
			// decrease the window size until the size of distinct elements is less than k
			while (frequencyMap.size() >= k && start <= end) {
				if (frequencyMap.get(nums[start]) == 1) {
					frequencyMap.remove(nums[start]);
				} else {
					frequencyMap.put(nums[start], frequencyMap.get(nums[start]) - 1);
				}
				start++;
			}
			count += start;
		}
		return count;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int subarraysWithKDistinct = subarraysWithKDistinct(nums, k); // Calls your implementation
		System.out.println("subarraysWithKDistinct is: " + subarraysWithKDistinct);
	}
}
