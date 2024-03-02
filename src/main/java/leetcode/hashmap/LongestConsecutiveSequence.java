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

	public static int longestConsecutive(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		Set<Integer> numsSet = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			numsSet.add(nums[i]);
		}
		int maxSize = 1;
		for (int i = 0; i < nums.length; i++) {
			if (!numsSet.contains(nums[i] - 1)) {
				int j = nums[i];
				while (numsSet.remove(j++));
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
