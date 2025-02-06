package leetcode.array.frequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TupleWithSameProduct {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 4, 6 }, 8);
		check(new int[] { 1, 2, 4, 5, 10 }, 16);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/tuple-with-same-product. This
	 * solution calculates all products and keeps same product occurences in a map.
	 * It then iterates the map and calculates the resulting number of permutations.
	 * Time complexity is O(n^2) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int tupleSameProduct(int[] nums) {
		if (nums.length < 4) {
			return 0;
		}
		// keeps count of the occurrences of each product
		Map<Integer, Integer> productMap = new HashMap<>();
		int lastIndex = nums.length - 1;
		// calculate all products, keep count of each product
		for (int i = 0; i < lastIndex; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int product = nums[i] * nums[j];
				Integer productCount = productMap.get(product);
				if (productCount == null) {
					productMap.put(product, 1);
				} else {
					productMap.put(product, productCount + 1);
				}
			}
		}
		// calculate permutations for each product occurence
		// formula is 8 * count * (count - 1) / 2 == 4 * count * (count - 1)
		// where count is the number of occurrences for each product
		int result = 0;
		for (int count : productMap.values()) {
			result += (count * (count - 1)) << 2;
		}
		return result;
	}

	/**
	 * Similar solution which calculates the result in the same loop as the product
	 * calculations. Time complexity is O(n^2) where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int tupleSameProduct2(int[] nums) {
		if (nums.length < 4) {
			return 0;
		}
		Map<Integer, Integer> productMap = new HashMap<>();
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int product = nums[i] * nums[j];
				result += (productMap.getOrDefault(product, 0)) << 3;
				productMap.merge(product, 1, Integer::sum);
			}
		}
		return result;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int tupleSameProduct = tupleSameProduct(nums); // Calls your implementation
		System.out.println("tupleSameProduct is: " + tupleSameProduct);
	}
}
