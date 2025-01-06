package leetcode.string;

import java.util.Arrays;

public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {

	public static void main(String[] args) {
		check("110", new int[] { 1, 1, 3 });
		check("001011", new int[] { 11, 8, 5, 4, 3, 4 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box.
	 * Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param boxes
	 * @return
	 */
	public static int[] minOperations(String boxes) {
		char[] chars = boxes.toCharArray();
		int rightCount = 0;
		int[] answer = new int[chars.length];
		// calculate answer[0] by counting how many 1s exist after position 0
		for (int i = 1; i < chars.length; i++) {
			int value = chars[i] - '0';
			answer[0] += i * (value);
			rightCount += value;
		}
		// calculate total count of 1s
		int totalCount = chars[0] - '0' + rightCount;
		// calculate answer[i] by subtracting rightCount and adding leftCount to
		// answer[i-1]
		// totalCount = leftCount + rightCount therefore leftCount - rightCount =
		// totalCount - 2 * rightCount
		for (int i = 1; i < chars.length; i++) {
			answer[i] = answer[i - 1] + totalCount - (rightCount << 1);
			rightCount -= chars[i] - '0';
		}
		return answer;
	}

	private static void check(String boxes, int[] expected) {
		System.out.println("boxes is: " + boxes);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] minOperations = minOperations(boxes); // Calls your implementation
		System.out.println("minOperations is: " + Arrays.toString(minOperations));
	}
}
