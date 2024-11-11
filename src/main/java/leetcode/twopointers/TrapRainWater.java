package leetcode.twopointers;

import java.util.Arrays;

public class TrapRainWater {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 2, 1 }, 0);
		check(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }, 6);
		check(new int[] { 4, 2, 0, 3, 2, 5 }, 9);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/trapping-rain-water. This
	 * solution uses two pointers to traverse the array of height, each time using
	 * the pointer from the side with the less height. Time complexity is O(n) where
	 * n is the length of the height array.
	 * 
	 * @param height
	 * @return
	 */
	public static int trap(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int result = 0;
		int leftHeight = height[left];
		int rightHeight = height[right];
		while (left < right) {
			if (leftHeight < rightHeight) {
				left++;
				leftHeight = Math.max(leftHeight, height[left]);
				result += leftHeight - height[left];
			} else {
				right--;
				rightHeight = Math.max(rightHeight, height[right]);
				result += rightHeight - height[right];
			}
		}
		return result;
	}

	/**
	 * This solution iterates all heights and calculates the trapped rain water
	 * assuming that there is a wall of infinite height at the end of the heights
	 * array. It then iterates the heights array again in reverse order up to the
	 * index of the maxHeight in order to calculate the correct trap water for this
	 * section and compensate for the assumption of the infinite wall. Time
	 * complexity is O(n) where n is the length of the height array.
	 * 
	 * @param height
	 * @return
	 */
	public static int trap2(int[] height) {
		int length = height.length;
		int topIndex = -1;
		int topHeight = 0;
		int totalAmount = 0;
		// iterate the array, calculate the trapped water assuming that there is a top
		// height
		// on the left side and an infinite height on the right side
		for (int i = 0; i < length; i++) {
			if (height[i] < topHeight) {
				// height is less than the top height, calculate trapped water
				totalAmount += topHeight - height[i];
			} else {
				// height is less than the top height, update top height and index
				topIndex = i;
				topHeight = height[i];
			}
		}
		// keep the max height and its index
		int maxIndex = topIndex;
		int maxHeight = topHeight;
		topIndex = -1;
		topHeight = 0;
		// iterate the array again in reverse order until the max height index
		// assume top height on the right side and max height on the left side
		// (which will always be greater or equal to the right side)
		for (int i = length - 1; i > maxIndex; i--) {
			if (height[i] < topHeight) {
				// height is less than the top height, calculate trapped water
				totalAmount += topHeight - height[i];
			} else {
				// height is less than the top height, update top height and index
				topIndex = i;
				topHeight = height[i];
			}
			// compensate for the assumption of the infinite wall in the first iteration
			// by subtracting the difference of this height to max height from the total
			// amount
			totalAmount -= maxHeight - height[i];
		}
		return totalAmount;
	}

	private static void check(int[] height, int expectedTrappedAmount) {
		System.out.println("height is: " + Arrays.toString(height));
		System.out.println("expectedTrappedAmount is: " + expectedTrappedAmount);
		int trappedAmount = trap(height); // Calls your implementation
		System.out.println("trappedAmount is: " + trappedAmount);
		trappedAmount = trap2(height); // Calls your implementation
		System.out.println("trappedAmount2 is: " + trappedAmount);
	}
}
