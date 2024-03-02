package leetcode.twopointers;

import java.util.Arrays;

public class ContainerMostWater {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 4, 5, 18, 17, 6 }, 17);
		check(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }, 49);
		check(new int[] { 1, 1 }, 1);
	}

	public static int maxArea(int[] height) {
		int length = height.length;
		int left = 0;
		int right = length - 1;
		int maxArea = 0;
		int area;
		while (left < right) {
			if ((area = Math.min(height[right], height[left]) * (right - left)) > maxArea) {
				maxArea = area;
			}
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return maxArea;
	}

	public static int maxArea2(int[] height) {
		int length = height.length;
		int left = 0;
		int selectedLeft = 0;
		int right = length - 1;
		int selectedRight = length - 1;
		int maxArea = Math.min(height[right], height[left]) * (right - left);
		int currentArea;
		boolean shouldCheck = false;
		while (left < right) {
			if (height[left] <= height[right]) {
				left++;
				shouldCheck = height[left] > height[left - 1];
				if (shouldCheck && (currentArea = (Math.min(height[selectedRight], height[left])
						* (selectedRight - left))) > maxArea) {
					selectedLeft = left;
					maxArea = currentArea;
				}
			} else {
				right--;
				shouldCheck = height[right] > height[right + 1];
				if (shouldCheck && (currentArea = (Math.min(height[right], height[selectedLeft])
						* (right - selectedLeft))) > maxArea) {
					selectedRight = right;
					maxArea = currentArea;
				}
			}
			if (shouldCheck && (currentArea = (Math.min(height[right], height[left]) * (right - left))) > maxArea) {
				selectedRight = right;
				selectedLeft = left;
				maxArea = currentArea;
			}
		}
		return maxArea;
	}

	public static int maxArea3(int[] height) {
		int length = height.length;
		int left = 0;
		int selectedLeft = 0;
		int right = length - 1;
		int selectedRight = length - 1;
		int maxArea = 0;
		int currentArea;
		while (left < right) {
			if ((currentArea = (Math.min(height[selectedRight], height[left]) * (selectedRight - left))) > maxArea) {
				selectedLeft = left;
				maxArea = currentArea;
			} else if ((currentArea = (Math.min(height[right], height[selectedLeft])
					* (right - selectedLeft))) > maxArea) {
				selectedRight = right;
				maxArea = currentArea;
			} else if ((currentArea = (Math.min(height[right], height[left]) * (right - left))) > maxArea) {
				selectedRight = right;
				selectedLeft = left;
				maxArea = currentArea;
			}
			if (height[left] <= height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return maxArea;
	}

	private static void check(int[] height, int expectedMaxArea) {
		System.out.println("height is: " + Arrays.toString(height));
		System.out.println("expectedMaxArea is: " + expectedMaxArea);
		int maxArea = maxArea(height); // Calls your implementation
		System.out.println("maxArea is: " + maxArea);
	}
}
