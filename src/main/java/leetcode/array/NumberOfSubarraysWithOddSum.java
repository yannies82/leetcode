package leetcode.array;

import java.util.Arrays;

public class NumberOfSubarraysWithOddSum {

	public static void main(String[] args) {
		check(new int[] { 1, 3, 5 }, 4);
		check(new int[] { 2, 4, 6 }, 0);
		check(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 16);
		check(new int[] { 5, 4, 4, 5, 1, 3 }, 12);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum. Time
	 * complexity is O(n) where n is the length of the arr array.
	 * 
	 * @param arr
	 * @return
	 */
	public static int numOfSubarrays(int[] arr) {
		int count = 0;
		int sum = 0;
		// calculate the count of odd sum subarrays starting at index 0
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			count += (sum & 1);
		}
		long result = count;
		int lastIndex = arr.length - 1;
		// iterate all indexes and count the odd sum subarrays starting at index i
		for (int i = 0; i < lastIndex; i++) {
			if ((arr[i] & 1) == 0) {
				// arr[i] is even, therefore odd sum subarrays starting at i are the same
				// as the ones starting at i - 1
				result += count;
			} else {
				// arr[i] is odd, therefore odd sum subarrays starting at i are found by
				// subtracting the ones starting at i - 1 from all possible subarrays starting
				// at i
				count = arr.length - i - count;
				result += count;
			}
			// adjust sum for next iteration
			sum -= arr[i];
		}
		return (int) (result % (1000000000 + 7));
	}

	public int numOfSubarrays2(int[] arr) {
		long result = 0;
		int oddCount = 0;
		int evenCount = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if ((sum & 1) == 0) {
				result += oddCount;
				evenCount++;
			} else {
				result += evenCount + 1;
				oddCount++;
			}
		}
		return (int) (result % (1000000000 + 7));
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int numOfSubarrays = numOfSubarrays(nums); // Calls your implementation
		System.out.println("numOfSubarrays is: " + numOfSubarrays);
	}
}
