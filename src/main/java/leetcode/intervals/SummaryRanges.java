package leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SummaryRanges {

	public static void main(String[] args) {
		check(new int[] { 0, 1, 2, 4, 5, 7 }, List.of("0->2", "4->5", "7"));
		check(new int[] { 0, 2, 3, 4, 6, 8, 9 }, List.of("0", "2->4", "6", "8->9"));
	}

	public static List<String> summaryRanges(int[] nums) {
		if (nums.length == 0) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<>();
		int start = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == nums.length - 1 || nums[i] + 1 < nums[i + 1]) {
				if (i == start) {
					result.add(Integer.toString(nums[i]));
				} else {
					result.add(nums[start] + "->" + nums[i]);
				}
				start = i + 1;
			}
		}
		return result;
	}

	public static List<String> summaryRanges2(int[] nums) {
		if (nums.length == 0) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<>();
		int start = 0;
		int limit = nums.length - 1;
		for (int i = 0; i < limit; i++) {
			if (nums[i] + 1 < nums[i + 1]) {
				if (i == start) {
					result.add(Integer.toString(nums[i]));
				} else {
					result.add(nums[start] + "->" + nums[i]);
				}
				start = i + 1;
			}
		}
		if (limit == start) {
			result.add(Integer.toString(nums[start]));
		} else {
			result.add(nums[start] + "->" + nums[limit]);
		}
		return result;
	}

	private static void check(int[] nums, List<String> expectedResult) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedResult is: " + expectedResult);
		List<String> summaryRanges = summaryRanges(nums); // Calls your implementation
		System.out.println("summaryRanges is: " + summaryRanges);
	}
}
