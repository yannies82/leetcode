package leetcode.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		check(new int[] { -1, 0, 1, 2, -1, -4 }, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
		check(new int[] { -2, 0, 1, 1, 2 }, List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)));
		check(new int[] { 3, 0, -2, -1, 1, 2 }, List.of(List.of(-2, -1, 3), List.of(-2, 0, 2), List.of(-1, 0, 1)));
		check(new int[] { 0, 0, 0, 0 }, List.of(List.of(0, 0, 0)));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/3sum. This solution sorts the
	 * input array, then takes each element at position i and tries to calculate all
	 * elements at positions j and k where i < j < k and nums[j] + nums[k] ==
	 * -nums[i]. Time complexity is O(n^2) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum(int[] nums) {
		int length = nums.length;
		int lastIndex = length - 1;
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < length - 2; i++) {
			if (nums[i] > 0) {
				break;
			}
			if (i == 0 || nums[i] > nums[i - 1]) {
				int left = i + 1;
				int right = lastIndex;
				int target = -nums[i];
				int sum;
				while (left < right && nums[left] <= target) {
					if (left > i + 1 && nums[left] == nums[left - 1]) {
						left++;
					} else if (right < length - 1 && nums[right] == nums[right + 1]) {
						right--;
					} else if ((sum = nums[left] + nums[right]) == target) {
						result.add(List.of(nums[i], nums[left], nums[right]));
						left++;
						right--;
					} else if (sum < target) {
						left++;
					} else {
						right--;
					}
				}
			}
		}
		return result;
	}

	public static List<List<Integer>> threeSum2(int[] nums) {
		int length = nums.length;
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		int left;
		int right;
		int sum;
		for (int i = 0; i < length - 2; i++) {
			if (i == 0 || nums[i] > nums[i - 1]) {
				left = i + 1;
				right = length - 1;
				while (left < right) {
					if (left > i + 1 && nums[left] == nums[left - 1]) {
						left++;
					} else if (right < length - 1 && nums[right] == nums[right + 1]) {
						right--;
					} else if ((sum = nums[i] + nums[left] + nums[right]) == 0) {
						result.add(List.of(nums[i], nums[left], nums[right]));
						left++;
						right--;
					} else if (sum < 0) {
						left++;
					} else {
						right--;
					}
				}
			}
		}
		return result;
	}

	private static void check(int[] numbers, List<List<Integer>> expectedResult) {
		System.out.println("numbers is: " + Arrays.toString(numbers));
		System.out.println("expectedResult is: " + expectedResult);
		List<List<Integer>> threeSum = threeSum(numbers); // Calls your implementation
		System.out.println("threeSum is: " + threeSum);
		threeSum = threeSum2(numbers); // Calls your implementation
		System.out.println("threeSum2 is: " + threeSum);
	}
}
