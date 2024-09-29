package leetcode.linkedlists;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AllOOneDataStructure {

	public static void main(String[] args) {
		AllOne allOne = new AllOne();
		allOne.inc("hello");
		allOne.inc("hello");
		System.out.println("getMaxKey result is: " + allOne.getMaxKey() + ", expected is: " + "hello");
		System.out.println("getMinKey result is: " + allOne.getMinKey() + ", expected is: " + "hello");
		allOne.inc("leet");
		System.out.println("getMaxKey result is: " + allOne.getMaxKey() + ", expected is: " + "hello");
		System.out.println("getMinKey result is: " + allOne.getMinKey() + ", expected is: " + "leet");
		allOne.dec("leet");
		System.out.println("getMaxKey result is: " + allOne.getMaxKey() + ", expected is: " + "hello");
		System.out.println("getMinKey result is: " + allOne.getMinKey() + ", expected is: " + "hello");
	}

	/**
	 * Leetcode problem: This solution uses a map to keep every word with its count
	 * and a treemap to keep the existing counts of words with the respective words.
	 * Time complexity of inc and dec operations is O(logn) and time complexity of
	 * getMaxKey, getMinKey operations is O(1).
	 * 
	 * @author yanni
	 *
	 */
	private static class AllOne {

		private Map<String, Integer> keyMap = new HashMap<>();
		private TreeMap<Integer, Set<String>> countMap = new TreeMap<>();

		public void inc(String key) {
			Integer currentCount = keyMap.getOrDefault(key, 0);
			if (currentCount != 0) {
				Set<String> currentSet = countMap.get(currentCount);
				if (currentSet.size() == 1) {
					countMap.remove(currentCount);
				} else {
					currentSet.remove(key);
				}
			}
			int newCount = currentCount + 1;
			keyMap.put(key, newCount);
			countMap.computeIfAbsent(newCount, k -> new HashSet<>()).add(key);
		}

		public void dec(String key) {
			Integer currentCount = keyMap.get(key);
			if (currentCount == 1) {
				keyMap.remove(key);
			} else {
				int newCount = currentCount - 1;
				keyMap.put(key, newCount);
				countMap.computeIfAbsent(newCount, k -> new HashSet<>()).add(key);
			}
			Set<String> currentSet = countMap.get(currentCount);
			if (currentSet.size() == 1) {
				countMap.remove(currentCount);
			} else {
				currentSet.remove(key);
			}
		}

		public String getMaxKey() {
			if (countMap.isEmpty()) {
				return "";
			}
			return countMap.get(countMap.lastKey()).iterator().next();
		}

		public String getMinKey() {
			if (countMap.isEmpty()) {
				return "";
			}
			return countMap.get(countMap.firstKey()).iterator().next();
		}
	}

}
