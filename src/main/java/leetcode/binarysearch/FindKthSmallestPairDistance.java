package leetcode.binarysearch;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindKthSmallestPairDistance {

	public static void main(String[] args) {
		check(new int[] { 1, 3, 1 }, 1, 0);
		check(new int[] { 1, 1, 1 }, 2, 0);
		check(new int[] { 1, 6, 1 }, 3, 5);
		check(new int[] { 62, 100, 4 }, 2, 58);
		check(new int[] { 38, 33, 57, 65, 13, 2, 86, 75, 4, 56 }, 26, 36);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-k-th-smallest-pair-distance. This solution
	 * uses binary search and the sliding window technique to find the result. Time
	 * complexity is O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int smallestDistancePair(int[] nums, int k) {
		// sort the input array
		Arrays.sort(nums);
		int lastIndex = nums.length - 1;
		// the diff between the numbers can be from 0 up to nums[lastIndex]
		int start = 0;
		int end = nums[lastIndex];
		int candidate = 0;
		// perform binary search to find the result
		while (start <= end) {
			// get the mid of the possible diffs and count how many pairs have a diff
			// that is greater or equal to mid
			int mid = (start + end) / 2;
			int numOfPairsWithDiffUpToMid = countPairsWithDiffUpTo(nums, mid);
			if (numOfPairsWithDiffUpToMid >= k) {
				// since at least k pairs have a diff up to mid, then mid is a candidate
				// result
				candidate = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return candidate;
	}

	/**
	 * Use sliding window technique to determine the number of num pairs with diff
	 * less than or equal to the target.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private static int countPairsWithDiffUpTo(int[] nums, int target) {
		int result = 0;
		int left = 0;
		int right = 0;
		int count = 0;
		while (right < nums.length) {
			// increase window size and add to result until the first and last
			// numbers have a diff greater than target
			while (++right < nums.length && nums[right] - nums[left] <= target) {
				result += ++count;
			}
			if (right < nums.length) {
				// decrease window size until the first and last numbers have a diff
				// less than or equal to target
				while (nums[right] - nums[left] > target) {
					left++;
				}
				count = right - left;
				result += count;
			}
		}
		return result;
	}

	/**
	 * Alternate solution using priority queue. Time complexity is O(n^2).
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int smallestDistancePair2(int[] nums, int k) {
		int n = nums.length;
		Queue<Integer> distanceQueue = new PriorityQueue<>((a, b) -> b - a);
		int threshold = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int distance = Math.abs(nums[i] - nums[j]);
				if (distanceQueue.size() < k) {
					distanceQueue.offer(distance);
					threshold = distanceQueue.peek();
				} else if (distance < threshold) {
					distanceQueue.poll();
					distanceQueue.offer(distance);
					threshold = distanceQueue.peek();
				}
			}
		}
		return distanceQueue.peek();
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int smallestDistancePair = smallestDistancePair(nums, k); // Calls your implementation
		System.out.println("smallestDistancePair is: " + smallestDistancePair);
	}
}
