package leetcode.hashmap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		check(new int[] { 100, 4, 200, 1, 3, 2 }, 4);
		check(new int[] { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 }, 9);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/longest-consecutive-sequence.
	 * This solution traverses all numbers and puts them in a set. It then traverses
	 * all numbers again and checks for numbers where nums[i] - 1 is not in the set.
	 * It adds 1 to nums[i] until nums[i] + k is not in the set. Finally it keeps
	 * the max length of the consecutive numbers. Time complexity is O(n) where n is
	 * the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int longestConsecutive(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		// add all numbers to a set
		Set<Integer> numsSet = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			numsSet.add(nums[i]);
		}
		int maxSize = 1;
		// iterate all numbers again
		for (int i = 0; i < nums.length; i++) {
			if (!numsSet.contains(nums[i] - 1)) {
				// if the set does not contain nums[i] - 1 check how many
				// consecutive numbers it contains
				int j = nums[i];
				// remove found numbers for efficiency, they will be searched exactly 1 time
				while (numsSet.remove(j++))
					;
				// update max length if needed
				if (j - nums[i] - 1 > maxSize) {
					maxSize = j - nums[i] - 1;
				}
			}
		}
		return maxSize;
	}

	public static int longestConsecutive2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		Arrays.sort(nums);
		int maxSize = 1;
		int size = 1;
		int prevNum = nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == prevNum + 1) {
				size++;
				if (size > maxSize) {
					maxSize = size;
				}
			} else if (nums[i] != prevNum) {
				size = 1;
			}
			prevNum = nums[i];
		}
		return maxSize;
	}

	public static int longestConsecutive3(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		SortedSet<Integer> numsSet = new TreeSet<>();
		int maxSize = 1;
		int size = 1;
		for (int i = 0; i < nums.length; i++) {
			numsSet.add(nums[i]);
		}
		Integer prevNum = numsSet.first();
		for (Integer num : numsSet) {
			if (num == prevNum + 1) {
				size++;
				if (size > maxSize) {
					maxSize = size;
				}
			} else {
				size = 1;
			}
			prevNum = num;
		}
		return maxSize;
	}

	private static void check(int[] nums, int expectedResult) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedResult is: " + expectedResult);
		int longestConsecutive = longestConsecutive(nums); // Calls your implementation
		System.out.println("longestConsecutive is: " + longestConsecutive);
	}
}
