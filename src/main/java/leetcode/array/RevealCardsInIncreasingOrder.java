package leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RevealCardsInIncreasingOrder {

	public static void main(String[] args) {
		check(new int[] { 17, 13, 11, 2, 3, 5, 7 }, new int[] { 2, 13, 3, 11, 5, 17, 7 });
		check(new int[] { 1, 1000 }, new int[] { 1, 1000 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/reveal-cards-in-increasing-order. This solution
	 * uses a custom queue to simulate the described behaviour of the deck. Time
	 * complexity is O(nlogn) where n is the length of the deck array.
	 * 
	 * @param deck
	 * @return
	 */
	public static int[] deckRevealedIncreasing(int[] deck) {
		Arrays.sort(deck);
		// this queue keeps all indexes of the deck
		int[] queue = new int[deck.length * 2];
		int head = 0, tail = -1;
		int lastIndex = deck.length - 1;
		// add all indexes to the queue
		while (tail < lastIndex) {
			queue[++tail] = tail;
		}
		int[] result = new int[deck.length];
		// add each number to the result array at the appropriate index
		// by simulating the deck behavior
		for (int i = 0; i < deck.length; i++) {
			// add the item to the position retrieved from the queue
			result[queue[head++]] = deck[i];
			// move the next position stored in the queue to the end
			queue[++tail] = queue[head++];
		}
		return result;
	}

	/**
	 * Similar solution, this one uses a LinkedList as a queue. Time complexity is
	 * O(nlogn) where n is the length of the deck array.
	 * 
	 * @param deck
	 * @return
	 */
	public static int[] deckRevealedIncreasing2(int[] deck) {
		Arrays.sort(deck);
		// this queue keeps all indexes of the deck
		// we cannot use ArrayDeque because it does not accept
		// null values and it is possible that a null value may be inserted
		// at the last iteration
		Queue<Integer> queue = new LinkedList<>();
		// add all indexes to the queue
		for (int i = 0; i < deck.length; i++) {
			queue.add(i);
		}
		int[] result = new int[deck.length];
		// add each number to the result array at the appropriate index
		// by simulating the deck behavior
		for (int i = 0; i < deck.length; i++) {
			// add the item to the position retrieved from the queue
			result[queue.poll()] = deck[i];
			// move the next position stored in the queue to the end
			queue.add(queue.poll());
		}
		return result;
	}

	private static void check(int[] deck, int[] expected) {
		System.out.println("deck is: " + Arrays.toString(deck));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] deckRevealedIncreasing = deckRevealedIncreasing(deck); // Calls your implementation
		System.out.println("deckRevealedIncreasing is: " + Arrays.toString(deckRevealedIncreasing));
	}
}
