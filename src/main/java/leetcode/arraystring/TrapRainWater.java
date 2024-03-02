package leetcode.arraystring;

import java.util.Arrays;

public class TrapRainWater {

	public static void main(String[] args) {
		check(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }, 6);
		check(new int[] { 4, 2, 0, 3, 2, 5 }, 9);
	}

	public static int trap(int[] height) {
		int length = height.length;
		int topIndex = -1;
		int topHeight = 0;
		int totalAmount = 0;
		for (int i = 0; i < length; i++) {
			if (height[i] < topHeight) {
				totalAmount += topHeight - height[i];
			} else {
				topIndex = i;
				topHeight = height[i];
			}
		}
		int maxIndex = topIndex;
		int maxHeight = topHeight;
		topIndex = -1;
		topHeight = 0;
		for (int i = length - 1; i > maxIndex; i--) {
			if (height[i] < topHeight) {
				totalAmount += topHeight - height[i];
			} else {
				topIndex = i;
				topHeight = height[i];
			}
			totalAmount -= maxHeight - height[i];
		}
		return totalAmount;
	}

	private static void check(int[] height, int expectedTrappedAmount) {
		System.out.println("height is: " + Arrays.toString(height));
		System.out.println("expectedTrappedAmount is: " + expectedTrappedAmount);
		int trappedAmount = trap(height); // Calls your implementation
		System.out.println("trappedAmount is: " + trappedAmount);
	}
}
