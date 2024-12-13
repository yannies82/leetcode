package leetcode.array;

import java.util.Arrays;

public class FindScoreOfAnArrayAfterMarkingAllElements {

	public static void main(String[] args) {
		check(new int[] { 2, 1, 3, 4, 5, 2 }, 7);
		check(new int[] { 2, 3, 5, 1, 3, 2 }, 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-score-of-an-array-after-marking-all-elements.
	 * This solution tries to find local minima in the array. Once it finds a local
	 * minimum, it adds its value to the sum as well as the value of every second
	 * element until the previous local minimum. Time complexity is O(n) where n is
	 * the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static long findScore(int[] nums) {
		long result = 0;
		int index = 0;
		while (index < nums.length) {
			int start = index;
			// find the first local minimum after start
			int nextIndex;
			while ((nextIndex = index + 1) < nums.length && nums[nextIndex] < nums[index]) {
				index++;
			}
			// add to the result every second element from the local minimum up to start,
			// since the others will have been marked
			for (int i = index; i >= start; i -= 2) {
				result += nums[i];
			}
			// increase index by 2 since next element will be marked
			// and search again for the next local minimum
			index += 2;
		}
		return result;
	}

	/**
	 * This solution keeps an array with the sorted indexes and another array which
	 * marks the visited indexes and traverses all numbers. Time complexity is
	 * O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static long findScore2(int[] nums) {
		Integer[] sortedIndexes = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++) {
			sortedIndexes[i] = i;
		}
		Arrays.sort(sortedIndexes, (a, b) -> nums[a] - nums[b]);
		boolean[] visited = new boolean[nums.length];
		long sum = 0;
		int lastIndex = nums.length - 1;
		for (int i = 0; i < nums.length; i++) {
			int currentIndex = sortedIndexes[i];
			if (!visited[currentIndex]) {
				sum += nums[currentIndex];
				visited[currentIndex] = true;
				if (currentIndex > 0) {
					visited[currentIndex - 1] = true;
				}
				if (currentIndex < lastIndex) {
					visited[currentIndex + 1] = true;
				}
			}
		}
		return sum;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		long findScore = findScore(nums); // Calls your implementation
		System.out.println("findScore is: " + findScore);
	}
}
