package leetcode.twopointers;

import java.util.Arrays;

public class TwoSum2 {

	public static void main(String[] args) {
		check(new int[] { -10, -8, -2, 1, 2, 5, 6 }, 0, new int[] { 3, 5 });
		check(new int[] { 3, 24, 50, 79, 88, 150, 345 }, 200, new int[] { 3, 6 });
		int[] arr = new int[30000];
		for (int i = 0; i < 29998; i++) {
			arr[i] = -1;
		}
		arr[29998] = 1;
		arr[29999] = 1;
		check(arr, 2, new int[] { 29999, 30000 });

		check(new int[] { -1, -1, 1, 1 }, 2, new int[] { 3, 4 });
		check(new int[] { 1, 3, 4, 4 }, 8, new int[] { 3, 4 });
		check(new int[] { 2, 7, 11, 15 }, 9, new int[] { 1, 2 });
		check(new int[] { 2, 3, 4 }, 6, new int[] { 1, 3 });
		check(new int[] { -1, 0 }, -1, new int[] { 1, 2 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted. This solution
	 * uses two pointers, one at the start and one at the end of the array. As an
	 * optimization pointers converge in logarithmic fashion. Time complexity is
	 * O(n) where n is the length of the numbers array.
	 * 
	 * 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] numbers, int target) {
		int length = numbers.length;
		int left = 0;
		int right = length - 1;
		int adjustedLeft;
		// converge left pointer in logarithmic steps instead of linear, while the sum is less than target
		while ((adjustedLeft = left + (right - left) / 2) > left && numbers[adjustedLeft] + numbers[right] < target) {
			left = adjustedLeft;
		}
		int adjustedRight;
		// converge right pointer in logarithmic steps instead of linear, while the sum is greater than target
		while ((adjustedRight = right - (right - left) / 2) < right && adjustedRight > left
				&& numbers[adjustedRight] + numbers[left] > target) {
			right = adjustedRight;
		}
		int sum;
		while (left < right) {
			if ((sum = numbers[left] + numbers[right]) == target) {
				return new int[] { left + 1, right + 1 };
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return null;
	}

	public static int[] twoSum2(int[] numbers, int target) {
		int start = 0;
		int end = numbers.length - 1;
		int sum;
		while (start < end && (sum = numbers[start] + numbers[end]) != target) {
			if (sum < target) {
				start++;
			} else {
				end--;
			}
		}
		return start < end ? new int[] { start + 1, end + 1 } : null;
	}

	public static int[] twoSum3(int[] numbers, int target) {
		int length = numbers.length;
		int index1 = 0;
		int index2 = 1;
		int sum;
		int[] result = null;
		while (result == null) {
			sum = numbers[index1] + numbers[index2++];
			if (sum == target) {
				return new int[] { index1 + 1, index2 };
			} else if (sum > target || index2 == length) {
				index1++;
				index2 = index1 + 1;
			}
		}
		return new int[] { 0, 0 };
	}

	public static int[] twoSum4(int[] numbers, int target) {
		int length = numbers.length;
		int index1 = 0;
		int low = 0;
		int high = length - 1;
		int index2 = (low + high) / 2;
		int sum;
		while ((sum = numbers[index1] + numbers[index2]) != target) {
			if (low > high) {
				index1++;
				low = index1;
				high = length - 1;
				index2 = (low + high) / 2;
			} else {
				if (sum < target) {
					low = index2 + 1;

				} else {
					high = index2 - 1;
				}
				index2 = (low + high) / 2;
			}
			if (index1 == index2) {
				index2++;
			}
		}
		return new int[] { index1 + 1, index2 + 1 };
	}

	private static void check(int[] numbers, int target, int[] expectedIndexes) {
		System.out.println("numbers is: " + Arrays.toString(numbers));
		System.out.println("target is: " + target);
		System.out.println("expectedIndexes is: " + Arrays.toString(expectedIndexes));
		int[] twoSum = twoSum(numbers, target); // Calls your implementation
		System.out.println("twoSum is: " + Arrays.toString(twoSum));
	}
}
