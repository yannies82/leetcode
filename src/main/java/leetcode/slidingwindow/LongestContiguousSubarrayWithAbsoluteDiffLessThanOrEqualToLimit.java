package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.TreeMap;

public class LongestContiguousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

	public static void main(String[] args) {
		check(new int[] { 8, 2, 4, 7 }, 4, 2);
		check(new int[] { 10, 1, 2, 4, 7, 2 }, 5, 4);
		check(new int[] { 4, 2, 2, 2, 4, 4, 2, 2 }, 0, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit.
	 * This solution uses a TreeMap to keep the nums as sorted keys along with their
	 * frequency and a sliding window to determine the max length of the contiguous
	 * subarray. Time complexity is O(nlogn) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @param limit
	 * @return
	 */
	public static int longestSubarray(int[] nums, int limit) {
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(nums[0], 1);
		int start = 0;
		int end = 1;
		int maxLength = 1;
		while (end < nums.length) {
			boolean withinLimit;
			// increase size of sliding window as long as the absolute difference of the
			// largest and smallest element is within the limit
			while ((withinLimit = Math.abs(treeMap.firstKey() - treeMap.lastKey()) <= limit) && end < nums.length) {
				treeMap.put(nums[end], treeMap.getOrDefault(nums[end], 0) + 1);
				end++;
			}
			// update the max length
			maxLength = Math.max(end - start - (withinLimit ? 0 : 1), maxLength);
			// decrease size of sliding window until the absolute difference of the largest
			// and smallest element is within the limit
			while (Math.abs(treeMap.firstKey() - treeMap.lastKey()) > limit) {
				Integer count = treeMap.get(nums[start]);
				if (count == 1) {
					treeMap.remove(nums[start]);
				} else {
					treeMap.put(nums[start], count - 1);
				}
				start++;
			}
		}
		return maxLength;
	}

	private static void check(int[] nums, int limit, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("limit is: " + limit);
		System.out.println("expected is: " + expected);
		int longestSubarray = longestSubarray(nums, limit); // Calls your implementation
		System.out.println("longestSubarray is: " + longestSubarray);
	}
}
