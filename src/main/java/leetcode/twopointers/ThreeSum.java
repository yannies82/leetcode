package leetcode.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

	public static void main(String[] args) {
		check(new int[] { -2, 0, 1, 1, 2 }, List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)));
		check(new int[] { 3, 0, -2, -1, 1, 2 }, List.of(List.of(-2, -1, 3), List.of(-2, 0, 2), List.of(-1, 0, 1)));
		check(new int[] { 0, 0, 0, 0 }, List.of(List.of(0, 0, 0)));
		check(new int[] { -1, 0, 1, 2, -1, -4 }, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
	}

	public static List<List<Integer>> threeSum(int[] nums) {
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

	public static List<List<Integer>> threeSum2(int[] nums) {
		int length = nums.length;
		List<List<Integer>> result = new ArrayList<>();
		Set<Integer> hash = new HashSet<>();
		int absSum;
		for (int i = 0; i < length - 2; i++) {
			for (int j = i + 1; j < length - 1; j++) {
				for (int k = j + 1; k < length; k++) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						absSum = nums[i] * nums[i] * nums[i] + nums[j] * nums[j] * nums[j]
								+ nums[k] * nums[k] * nums[k];
						if (hash.add(absSum)) {
							result.add(List.of(nums[i], nums[j], nums[k]));
						}

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
