package leetcode.math;

public class MaximumSwap {

	public static void main(String[] args) {
		check(1993, 9913);
		check(115, 511);
		check(120, 210);
		check(2736, 7236);
		check(9973, 9973);
		check(98368, 98863);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-swap. This solution
	 * searches for the highest order digit which is smaller than the max of its
	 * lower order digits and performs the swap. Time complexity is O(1).
	 * 
	 * @param num
	 * @return
	 */
	public static int maximumSwap(int num) {
		int n = num;
		int prevMaxIndex = 0;
		int maxIndex = 0;
		int swapIndex = -1;
		int index = 0;
		// keeps the digits of the number
		int[] nums = new int[10];
		// iterate all digits and add them to the array
		do {
			// put the next digit in the array
			nums[index] = n % 10;
			if (nums[index] < nums[prevMaxIndex]) {
				// if it is smaller than any previous digit, they should be swapped
				swapIndex = index;
				maxIndex = prevMaxIndex;
			} else if (nums[index] > nums[prevMaxIndex]) {
				// update the prevMaxIndex, this will be used for swapping only if
				// a swapIndex with higher order is found
				prevMaxIndex = index;
			}
			index++;
		} while ((n = n / 10) > 0);
		if (swapIndex <= 0) {
			// no digit found for swapping, or its already at the lowest order
			return num;
		}
		// perform the swap in the array
		int temp = nums[maxIndex];
		nums[maxIndex] = nums[swapIndex];
		nums[swapIndex] = temp;
		// calculate and return the result
		int result = 0;
		for (int i = index - 1; i >= 0; i--) {
			result = result * 10 + nums[i];
		}
		return result;
	}

	private static void check(int num, int expected) {
		System.out.println("num is: " + num);
		System.out.println("expected is: " + expected);
		int maximumSwap = maximumSwap(num); // Calls your implementation
		System.out.println("maximumSwap is: " + maximumSwap);
	}

}
