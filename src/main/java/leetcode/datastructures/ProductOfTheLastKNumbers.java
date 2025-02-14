package leetcode.datastructures;

import java.util.ArrayList;
import java.util.List;

public class ProductOfTheLastKNumbers {

	public static void main(String[] args) {
		check();
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/product-of-the-last-k-numbers. Both implemented
	 * operations have O(1) time complexity.
	 * 
	 * @author yanni
	 *
	 */
	public static class ProductOfNumbers {

		private List<Integer> productList;
		int lastProduct = 1;
		int lastZeroIndex = -1;

		public ProductOfNumbers() {
			productList = new ArrayList<>();
		}

		public void add(int num) {
			if (num == 0) {
				lastZeroIndex = productList.size();
				productList.add(1);
				lastProduct = 1;
			} else {
				int newProduct = lastProduct * num;
				productList.add(newProduct);
				lastProduct = newProduct;
			}
		}

		public int getProduct(int k) {
			int size = productList.size();
			int startIndex = size - k - 1;
			if (startIndex < lastZeroIndex) {
				return 0;
			} else if (k >= size) {
				return lastProduct;
			}
			return lastProduct / productList.get(startIndex);
		}
	}

	private static void check() {
		ProductOfNumbers productOfNumbers = new ProductOfNumbers();
		productOfNumbers.add(3);
		productOfNumbers.add(0);
		productOfNumbers.add(2);
		productOfNumbers.add(5);
		productOfNumbers.add(4);
		printValue(20, productOfNumbers.getProduct(2));
		printValue(40, productOfNumbers.getProduct(3));
		printValue(0, productOfNumbers.getProduct(4));
		productOfNumbers.add(8);
		printValue(32, productOfNumbers.getProduct(2));
	}

	private static void printValue(int expected, int actual) {
		System.out.println("Expected value is: " + expected + ", actual value is: " + actual);
	}

}
