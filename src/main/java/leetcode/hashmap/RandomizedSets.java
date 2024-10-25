package leetcode.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSets {

	/**
	 * Leetcode problem: https://leetcode.com/problems/insert-delete-getrandom-o1.
	 * This solution internally uses a list and a map. Each element is inserted in
	 * the list and is also inserted in the map where the key is the element value
	 * and the value is the list index. When an element is to be removed it is
	 * swapped with the last list element so that the removal can occur in O(1)
	 * time. Insertion, removal and returning a random element all have a time
	 * complexity of O(1).
	 * 
	 * @author yanni
	 *
	 */
	public static class RandomizedSet {
		private Map<Integer, Integer> backingMap;
		private List<Integer> backingList;
		private Random random;

		public RandomizedSet() {
			backingMap = new HashMap<>();
			backingList = new ArrayList<>();
			random = new Random();
		}

		public boolean insert(int val) {
			if (backingMap.putIfAbsent(val, backingList.size()) == null) {
				backingList.add(val);
				return true;
			}
			return false;
		}

		public boolean remove(int val) {
			Integer index = backingMap.remove(val);
			if (index != null) {
				int lastIndex = backingList.size() - 1;
				int lastElement = backingList.get(lastIndex);
				if (index != lastIndex) {
					backingList.set(index, lastElement);
					backingMap.put(lastElement, index);
				}
				backingList.remove(lastIndex);
				return true;
			}
			return false;
		}

		public int getRandom() {
			return backingList.get(random.nextInt(backingList.size()));
		}
	}

}
