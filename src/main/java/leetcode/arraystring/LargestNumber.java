package leetcode.arraystring;

import java.util.Arrays;

public class LargestNumber {

	public static void main(String[] args) {
		check(new int[] { 12341, 123411234 }, "12341123412341");
		check(new int[] { 10, 2 }, "210");
		check(new int[] { 3, 30, 34, 5, 9 }, "9534330");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/largest-number. This solution
	 * calculates an adjusted value of 12 digits for each number by appending its
	 * own digits to the right. It then sorts the adjusted values and iterates the
	 * initial nums array in the sorted order to produce the result string. Time
	 * complexity is O(nlogn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static String largestNumber(int[] nums) {
		// keeps the adjusted nums to be compared
		long[][] adjustedNums = new long[nums.length][2];
		// keeps the digits of the current number
		int[] digits = new int[10];
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			// calculate length and digits of the number
			int length = 0;
			while (num >= 10) {
				digits[length++] = num % 10;
				num = num / 10;
			}
			digits[length++] = num;
			// calculate the adjusted number by appending its own digits to the right
			// until the length is equal to 12
			long adjustedNum = nums[i];
			int lastIndex = length - 1;
			int index = lastIndex;
			while (length < 12) {
				// append digit to the end of the adjusted number
				adjustedNum = adjustedNum * 10 + digits[index];
				if (index == 0) {
					// if we are out of digits, start from the beginning
					index = lastIndex;
				} else {
					// go to the next digit
					index--;
				}
				length++;
			}
			// keep the adjusted number in the array, along with its index in the nums array
			adjustedNums[i][0] = adjustedNum;
			adjustedNums[i][1] = i;
		}
		// sort the adjustedNums array in descending order
		Arrays.sort(adjustedNums, (a, b) -> Long.compare(b[0], a[0]));
		if (adjustedNums[0][0] == 0) {
			// early exit if all numbers are 0
			return "0";
		}
		// build the output string
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < adjustedNums.length; i++) {
			builder.append(nums[(int) adjustedNums[i][1]]);
		}
		return builder.toString();
	}

	private static void check(int[] nums, String expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		String largestNumber = largestNumber(nums); // Calls your implementation
		System.out.println("largestNumber is: " + largestNumber);
	}
}
