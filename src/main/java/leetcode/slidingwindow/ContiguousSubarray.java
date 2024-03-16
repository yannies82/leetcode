package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContiguousSubarray {

	public static void main(String[] args) {
		check(new int[] { 0, 1 }, 2);
		check(new int[] { 0, 1, 0 }, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/contiguous-array. This
	 * solution traverses the nums array a single time and adds 1 for every 1
	 * encountered and -1 for every 0 encountered. When a sum is encountered 2
	 * times, it means that between these 2 indexes there is an equal number of 1s
	 * and 0s. Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * 
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int findMaxLength(int[] nums) {
		int maxLength = 0;
		// keeps the first index where a specific sum was encountered
		// sum is the key, index is the value
		Map<Integer, Integer> sumIndexes = new HashMap<>();
		// initialize for 0 sum
		sumIndexes.put(0, -1);
		int sum = 0;
		// iterate all numbers in the array
		for (int i = 0; i < nums.length; i++) {
			// add 1 for 1s and -1 for 0s
			sum += (nums[i] << 1) - 1;
			Integer prevIndex = sumIndexes.get(sum);
			if (prevIndex == null) {
				// if the sum has never been encountered before, put it in the map
				sumIndexes.put(sum, i);
			} else {
				// the sum has been encountered before, this means that the subarray between
				// this index and the previous index has an equal number of 0s and 1s
				int length = i - prevIndex;
				if (length > maxLength) {
					maxLength = length;
				}
			}
		}
		return maxLength;
	}

	/**
	 * Simple solution which calculates the sum from all starting positions and
	 * returns the length of the max subarray for which sum = length / 2. Time
	 * complexity is O(n^2) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int findMaxLength2(int[] nums) {
		int maxLength = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums.length - i <= maxLength) {
				break;
			}
			int sum = 0;
			for (int j = i; j < nums.length - 1; j += 2) {
				sum += nums[j] + nums[j + 1];
				int length = j - i + 2;
				if (sum << 1 == length && maxLength < length) {
					maxLength = length;
				}
			}
		}
		return maxLength;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int findMaxLength = findMaxLength(nums); // Calls your implementation
		System.out.println("findMaxLength is: " + findMaxLength);
	}
}
