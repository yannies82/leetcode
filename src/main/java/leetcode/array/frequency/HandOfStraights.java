package leetcode.array.frequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HandOfStraights {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 2, 2, 3, 3 }, 2, false);
		check(new int[] { 1, 2, 3, 6, 2, 3, 4, 7, 8 }, 3, true);
		check(new int[] { 1, 2, 3, 4, 5 }, 4, false);
		check(new int[] { 8, 10, 12 }, 3, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/hand-of-straights. This
	 * solution sorts the input array, then keeps the frequency for each number in a
	 * map. It then iterates all numbers and checks if the frequency map contains
	 * the required entries for each group. Time complexity is O(n*groupSize) where
	 * n is the number of elements in the hand array.
	 * 
	 * @param hand
	 * @param groupSize
	 * @return
	 */
	public static boolean isNStraightHand(int[] hand, int groupSize) {
		if (hand.length % groupSize > 0) {
			// early exit in case that the group size is incompatible with the array length
			return false;
		}
		Arrays.sort(hand);
		// populate the map with the frequencies of each number
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		for (int i = 0; i < hand.length; i++) {
			int currentValue = frequencyMap.getOrDefault(hand[i], 0);
			frequencyMap.put(hand[i], currentValue + 1);
		}
		for (int i = 0; i < hand.length; i++) {
			if (frequencyMap.get(hand[i]) == 0) {
				// skip number if its frequency is 0, it cannot be the first of a group
				continue;
			}
			for (int j = 0; j < groupSize; j++) {
				// check if the required numbers for the group exist in the map with frequency >
				// 0
				int next = hand[i] + j;
				int currentValue = frequencyMap.getOrDefault(next, 0);
				if (currentValue == 0) {
					// this number is missing, the group cannot be completed
					return false;
				}
				// decrease the frequency of this number
				frequencyMap.put(next, currentValue - 1);
			}
		}
		return true;
	}

	/**
	 * This solution uses a sorted map and does not alter the input array. Time
	 * complexity is O(nlogn) where n is the length of the hand array.
	 * 
	 * @param hand
	 * @param groupSize
	 * @return
	 */
	public static boolean isNStraightHand2(int[] hand, int groupSize) {
		if (hand.length % groupSize > 0) {
			// early exit in case that the group size is incompatible with the array length
			return false;
		}
		Map<Integer, Integer> sortedMap = new TreeMap<>();
		for (int i = 0; i < hand.length; i++) {
			int currentValue = sortedMap.getOrDefault(hand[i], 0);
			sortedMap.put(hand[i], currentValue + 1);
		}
		while (!sortedMap.isEmpty()) {
			int prev = -1;
			Iterator<Integer> iter = sortedMap.keySet().iterator();
			for (int i = 0; i < groupSize; i++) {
				if (!iter.hasNext()) {
					return false;
				}
				Integer nextKey = iter.next();
				if (prev >= 0 && nextKey != prev + 1) {
					return false;
				}
				int currentValue = sortedMap.get(nextKey);
				if (currentValue == 1) {
					iter.remove();
				} else {
					sortedMap.put(nextKey, currentValue - 1);
				}
				prev = nextKey;
			}
		}
		return true;
	}

	private static void check(int[] hand, int groupSize, boolean expected) {
		System.out.println("hand is: " + Arrays.toString(hand));
		System.out.println("groupSize is: " + groupSize);
		System.out.println("expected is: " + expected);
		boolean isNStraightHand = isNStraightHand(hand, groupSize); // Calls your implementation
		System.out.println("isNStraightHand is: " + isNStraightHand);
	}
}
