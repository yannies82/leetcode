package leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class DesignANumberContainerSystem {

	public static void main(String[] args) {
		check();
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/design-a-number-container-system. This solution
	 * uses two maps, one for mapping idexes to numbers and one for mapping numbers
	 * to all of their indexes. Time complexity of both operations is O(logn) where
	 * n is the number of elements in the container.
	 * 
	 * @author yanni
	 *
	 */
	public static class NumberContainers {

		private Map<Integer, Integer> numbersByIndex;
		private Map<Integer, SortedSet<Integer>> indexesByNumber;

		public NumberContainers() {
			numbersByIndex = new HashMap<>();
			indexesByNumber = new HashMap<>();
		}

		public void change(int index, int number) {
			Integer currentNumber = numbersByIndex.get(index);
			if (currentNumber == null) {
				numbersByIndex.put(index, number);
				indexesByNumber.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
			} else if (currentNumber != number) {
				numbersByIndex.put(index, number);
				indexesByNumber.get(currentNumber).remove(index);
				indexesByNumber.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
			}
		}

		public int find(int number) {
			SortedSet<Integer> indexes = indexesByNumber.get(number);
			return indexes == null || indexes.isEmpty() ? -1 : indexes.first();
		}
	}

	/**
	 * Similar solution, does not check if the number inserted at an index already
	 * exists there.
	 * 
	 * @author yanni
	 *
	 */
	public static class NumberContainers2 {

		private Map<Integer, Integer> numbersByIndex;
		private Map<Integer, SortedSet<Integer>> indexesByNumber;

		public NumberContainers2() {
			numbersByIndex = new HashMap<>();
			indexesByNumber = new HashMap<>();
		}

		public void change(int index, int number) {
			Integer currentNumber = numbersByIndex.get(index);
			if (currentNumber != null) {
				indexesByNumber.get(currentNumber).remove(index);
			}
			numbersByIndex.put(index, number);
			indexesByNumber.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
		}

		public int find(int number) {
			SortedSet<Integer> indexes = indexesByNumber.get(number);
			return indexes == null || indexes.isEmpty() ? -1 : indexes.first();
		}
	}

	private static void check() {
		NumberContainers containers = new NumberContainers();
		printValue(-1, containers.find(10));
		containers.change(2, 10);
		containers.change(1, 10);
		containers.change(3, 10);
		containers.change(5, 10);
		printValue(1, containers.find(10));
		containers.change(1, 20);
		printValue(2, containers.find(10));
	}

	private static void printValue(int expected, int actual) {
		System.out.println("Expected value is: " + expected + ", actual value is: " + actual);
	}

}
