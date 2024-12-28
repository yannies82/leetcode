package leetcode.slidingwindow;

import java.util.Arrays;

public class MaximumSumOf3NonOverlappingSubarrays {

	public static void main(String[] args) {
		check(new int[] { 7, 13, 20, 19, 19, 2, 10, 1, 1, 19 }, 3, new int[] { 1, 4, 7 });
		check(new int[] { 1, 2, 1, 2, 6, 7, 5, 1 }, 2, new int[] { 0, 3, 5 });
		check(new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1 }, 2, new int[] { 0, 2, 4 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays.
	 * This solution initializes three subarrays of size k and uses a sliding window
	 * for each one to calculate their updated max sums. Time complexity is O(n)
	 * where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		// calculate the initial sums of the 3 arrays
		int firstArraySum = 0;
		int secondArrayStart = k;
		for (int i = 0; i < secondArrayStart; i++) {
			firstArraySum += nums[i];
		}
		int secondArraySum = 0;
		int thirdArrayStart = 2 * k;
		for (int i = secondArrayStart; i < thirdArrayStart; i++) {
			secondArraySum += nums[i];
		}
		int thirdArraySum = 0;
		int thirdArrayEnd = 3 * k;
		for (int i = thirdArrayStart; i < thirdArrayEnd; i++) {
			thirdArraySum += nums[i];
		}

		// calculate the initial best incremental sums for 1, 2 and 3 arrays
		int bestSingleSum = firstArraySum;
		int bestDoubleSum = firstArraySum + secondArraySum;
		int bestTripleSum = firstArraySum + secondArraySum + thirdArraySum;

		// keep the indexes for max sums when using 1,2 and 3 arrays
		int bestIndexForOneArray = 0;
		int[] bestIndexesForTwoArrays = { 0, secondArrayStart };
		int[] bestIndexesForThreeArrays = { 0, secondArrayStart, thirdArrayStart };

		int limit = nums.length - thirdArrayEnd;
		for (int firstArrayStart = 1; firstArrayStart <= limit; firstArrayStart++) {
			secondArrayStart++;
			thirdArrayStart++;
			// update the sums of all arrays by moving the sliding window for each array
			firstArraySum = firstArraySum - nums[firstArrayStart - 1] + nums[firstArrayStart + k - 1];
			secondArraySum = secondArraySum - nums[secondArrayStart - 1] + nums[secondArrayStart + k - 1];
			thirdArraySum = thirdArraySum - nums[thirdArrayStart - 1] + nums[thirdArrayStart + k - 1];

			// check if a better max sum is found for the first array and update the sum and
			// the first start index
			if (firstArraySum > bestSingleSum) {
				bestIndexForOneArray = firstArrayStart;
				bestSingleSum = firstArraySum;
			}

			// check if a better max sum is found for the first two arrays and update the
			// sum and the start indexes
			int newDoubleSum = secondArraySum + bestSingleSum;
			if (newDoubleSum > bestDoubleSum) {
				bestIndexesForTwoArrays[0] = bestIndexForOneArray;
				bestIndexesForTwoArrays[1] = secondArrayStart;
				bestDoubleSum = newDoubleSum;
			}

			// check if a better max sum is found for the three arrays and update the sum
			// and the start indexes
			int newTripleSum = thirdArraySum + bestDoubleSum;
			if (newTripleSum > bestTripleSum) {
				bestIndexesForThreeArrays[0] = bestIndexesForTwoArrays[0];
				bestIndexesForThreeArrays[1] = bestIndexesForTwoArrays[1];
				bestIndexesForThreeArrays[2] = thirdArrayStart;
				bestTripleSum = newTripleSum;
			}
		}
		return bestIndexesForThreeArrays;
	}

	/**
	 * This solution uses prefix and suffix sums and a sliding window to calculate
	 * the result. Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
		int lastSumIndex = nums.length - k;
		int sumSize = lastSumIndex + 1;
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += nums[i];
		}
		int[] sums = new int[sumSize];
		sums[0] = sum;
		int[][] maxPrefixSum = new int[sumSize][2];
		maxPrefixSum[0][0] = sum;
		maxPrefixSum[0][1] = 0;
		int[] currentMaxSum = new int[2];
		currentMaxSum[0] = sum;
		currentMaxSum[1] = 0;
		for (int i = 0; i < lastSumIndex; i++) {
			sum += nums[i + k] - nums[i];
			int nextI = i + 1;
			sums[nextI] = sum;
			if (sums[nextI] > currentMaxSum[0]) {
				currentMaxSum[0] = sums[nextI];
				currentMaxSum[1] = nextI;
			}
			maxPrefixSum[nextI][0] = currentMaxSum[0];
			maxPrefixSum[nextI][1] = currentMaxSum[1];
		}
		int[][] maxSuffixSum = new int[sumSize][2];
		maxSuffixSum[lastSumIndex][0] = sums[lastSumIndex];
		maxSuffixSum[lastSumIndex][1] = lastSumIndex;
		currentMaxSum[0] = sums[lastSumIndex];
		currentMaxSum[1] = lastSumIndex;
		for (int i = lastSumIndex - 1; i >= 0; i--) {
			if (sums[i] >= currentMaxSum[0]) {
				currentMaxSum[0] = sums[i];
				currentMaxSum[1] = i;
			}
			maxSuffixSum[i][0] = currentMaxSum[0];
			maxSuffixSum[i][1] = currentMaxSum[1];
		}
		int maxSum = 0;
		int[] result = new int[3];
		for (int i = k; i < sumSize - k; i++) {
			int newSum = maxPrefixSum[i - k][0] + sums[i] + maxSuffixSum[i + k][0];
			if (newSum > maxSum) {
				maxSum = newSum;
				result[0] = maxPrefixSum[i - k][1];
				result[1] = i;
				result[2] = maxSuffixSum[i + k][1];
			}
		}
		return result;
	}

	private static void check(int[] nums, int k, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] maxSumOfThreeSubarrays = maxSumOfThreeSubarrays(nums, k); // Calls your implementation
		System.out.println("maxSumOfThreeSubarrays is: " + Arrays.toString(maxSumOfThreeSubarrays));
	}
}
