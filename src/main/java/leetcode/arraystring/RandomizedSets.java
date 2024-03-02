package leetcode.arraystring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SplittableRandom;

public class RandomizedSets {

	public static class RandomizedSet1 {
		private Set<Integer> set;
		private SplittableRandom random;

		public RandomizedSet1() {
			set = new HashSet<>();
			random = new SplittableRandom();
		}

		public boolean insert(int val) {
			return set.add(val);
		}

		public boolean remove(int val) {
			return set.remove(val);
		}

		public int getRandom() {
			int i = 0;
			int targetIndex = random.nextInt(set.size());
			for (Integer value : set) {
				if (i == targetIndex) {
					return value;
				}
				i++;
			}
			return 0;
		}
	}

	public static class RandomizedSet2 {
		private Set<Integer> backingSet;
		private List<Integer> backingList;
		private Random random;

		public RandomizedSet2() {
			backingSet = new HashSet<>();
			backingList = new ArrayList<>();
			random = new Random();
		}

		public boolean insert(int val) {
			if (backingSet.add(val)) {
				backingList.add(val);
				return true;
			}
			return false;
		}

		public boolean remove(int val) {
			if (backingSet.remove(val)) {
				backingList.remove((Object) val);
				return true;
			}
			return false;
		}

		public int getRandom() {
			return backingList.get(random.nextInt(backingList.size()));
		}
	}

	public static class RandomizedSet3 {
		private Map<Integer, Integer> backingMap;
		private List<Integer> backingList;
		private Random random;

		public RandomizedSet3() {
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
