package leetcode.array;

import java.util.Arrays;

public class LongestSquareStreakInAnArray {

	public static void main(String[] args) {
		check(new int[] { 4, 3, 6, 16, 8, 2 }, 3);
		check(new int[] { 2, 3, 5, 6, 7 }, -1);
	}

	private static int LIMIT = 317;

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/longest-square-streak-in-an-array. This
	 * solution checks all the numbers in the range 2 to sqrt(100000) to find
	 * sequences which exist in nums. Time complexity is O(n) where n is the length
	 * of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int longestSquareStreak(int[] nums) {
		int result = -1;
		int range = 100001;
		// marks numbers which exist in nums as true
		boolean[] exists = new boolean[range];
		for (int i = 0; i < nums.length; i++) {
			exists[nums[i]] = true;
		}
		// keeps visited square numbers
		boolean[] visited = new boolean[range];
		for (int i = 2; i < LIMIT; i++) {
			if (!exists[i] || visited[i]) {
				// skip number if it does not exist in nums array or if it has already been
				// visited as a square
				continue;
			}
			visited[i] = true;
			int length = 1;
			int j = i;
			do {
				j = j * j;
				if (!exists[j]) {
					// as long as the square exists in nums execute the loop
					break;
				}
				visited[j] = true;
				length++;
			} while (j < LIMIT);
			if (length > 1) {
				result = Math.max(result, length);
			}
		}
		return result;
	}
	
	/**
	 * This solution sorts the input array, then iterates the sorted number and
	 * calculates their squares while keeping a streak array with the max length.
	 * Time complexity is O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int longestSquareStreak2(int[] nums) {
		Arrays.sort(nums);
		int[] streak = new int[100001];
		for (int i = 0; i < nums.length; i++) {
			streak[nums[i]] = 1;
		}
		int maxLength = -1;
		int prev = -1;
		for (int i = 0; i < nums.length && nums[i] < LIMIT; i++) {
			if (nums[i] == prev) {
				// skip number if it is the same as the previous one
				continue;
			}
			int currentSquare = nums[i] * nums[i];
			if (streak[currentSquare] > 0) {
				// the square of the number exists in nums, add to its streak
				// and update maxLength if necessary
				streak[currentSquare] += streak[nums[i]];
				if (streak[currentSquare] > maxLength) {
					maxLength = streak[currentSquare];
				}
			}
			prev = nums[i];
		}
		return maxLength;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("ratings is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int longestSquareStreak = longestSquareStreak(nums); // Calls your implementation
		System.out.println("longestSquareStreak is: " + longestSquareStreak);
	}
}
