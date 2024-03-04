package leetcode.arraystring;

import java.util.Arrays;

public class ProductArrayExceptSelf {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4 }, new int[] { 24, 12, 8, 6 });
		check(new int[] { -1, 1, 0, -3, 3 }, new int[] { 0, 0, 9, 0, 0 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/product-of-array-except-self.
	 * This solution traverses the input array to calculate the product of all
	 * elements left of the current one and stores them in the result array. It then
	 * traverses the input array in reverse order to calculate the product of all
	 * elements right of the current one and stores the left * right products for
	 * each position. Time complexity is O(n) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] productExceptSelf(int[] nums) {
		int length = nums.length;
		int[] answer = new int[length];
		int temp = 1;
		// traverse array and store the product of all elements left of the current one
		for (int i = 0; i < length; i++) {
			answer[i] = temp;
			temp *= nums[i];
		}
		temp = 1;
		// traverse array backwards and store the product of all elements right of the
		// current one multiplied with the left product that was already stored from
		// the previous iteration
		for (int i = length - 1; i >= 0; i--) {
			answer[i] *= temp;
			temp *= nums[i];
		}
		return answer;
	}

	/**
	 * This solution calculates the product of all elements in the array and divides
	 * the product with each element to get the result array. Time complexity is
	 * O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] productExceptSelf2(int[] nums) {
		int length = nums.length;
		int[] answer = new int[length];
		int product = 1;
		int zeroCount = 0;
		int zeroIndex = -1;
		for (int i = 0; i < length && zeroCount < 2; i++) {
			if (nums[i] == 0) {
				zeroCount++;
				zeroIndex = i;
			} else {
				product *= nums[i];
			}
		}
		if (zeroCount == 0) {
			for (int i = 0; i < length; i++) {
				answer[i] = product / nums[i];
			}
		} else if (zeroCount == 1) {
			answer[zeroIndex] = product;
		}
		return answer;
	}

	/**
	 * This solution finds the frequency of each number and calculates the result
	 * for each position. It takes advantage of the limited input range [-30...30]
	 * for the array elements. Time complexity is O(n*m) where n is the length of
	 * the nums array and m is the input range (in this case 61).
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] productExceptSelf3(int[] nums) {
		int length = nums.length;
		int[] answer = new int[length];
		int[] frequencies = new int[61];
		int offset = 30;
		int zeroIndex = -1;
		for (int i = 0; i < length; i++) {
			frequencies[nums[i] + offset]++;
			if (nums[i] == 0) {
				zeroIndex = i;
			}
		}
		if (frequencies[offset] == 0) {
			for (int i = 0; i < length; i++) {
				int product = 1;
				for (int j = 0; j < frequencies.length; j++) {
					int frequency = (nums[i] == j - offset) ? frequencies[j] - 1 : frequencies[j];
					if (frequency > 0) {
						product *= (int) Math.pow(j - offset, frequency);
					}
				}
				answer[i] = product;
			}
		} else if (frequencies[offset] == 1) {
			int product = 1;
			for (int j = 0; j < frequencies.length; j++) {
				int frequency = j == offset ? frequencies[j] - 1 : frequencies[j];
				if (frequency > 0) {
					product *= (int) Math.pow(j - offset, frequency);
				}
			}
			answer[zeroIndex] = product;
		}
		return answer;
	}

	private static void check(int[] nums, int[] expectedNums) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedNums is: " + Arrays.toString(expectedNums));
		int[] productExceptSelf = productExceptSelf(nums); // Calls your implementation
		System.out.println("productExceptSelf is: " + Arrays.toString(productExceptSelf));
	}
}
