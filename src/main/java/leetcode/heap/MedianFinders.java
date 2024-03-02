package leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinders {

	/**
	 * This implementation uses two priority queues, one for the n/2 smaller numbers
	 * and one for the n/2 greater numbers. Numbers are added to the queues in such
	 * a way so that their sizes are either equal or lowerHalf.size() ==
	 * upperHalf.size() + 1 (if the numbers count is odd). When a number is to be
	 * added to a queue, it is compared with the queue's first element. If it is out
	 * of range it is swapped with a number polled from the other queue. This way,
	 * the lowerHalf queue always has the lower median element as first element and
	 * the upperHalf queue always has the higher median element as the first
	 * element. Time complexity of addNum is O(logn) and of findMedian is O(1).
	 * 
	 * @author yanni
	 *
	 */
	public static class MedianFinder {

		// keeps the smaller n/2 numbers in descending order, so that the first element
		// is always the greatest of the n/2 smaller ones (therefore the lower median
		// element)
		private PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Comparator.reverseOrder());
		// keeps the greater n/2 numbers in ascending order, so that the first element
		// is always the smallest of the n/2 greater ones (therefore the higher median
		// element)
		private PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

		public MedianFinder() {

		}

		public void addNum(int num) {
			if (lowerHalf.size() > upperHalf.size()) {
				// a number should be addded to the upper half
				if (num < lowerHalf.peek()) {
					// the number to be added to the upper half is out of range
					// swap it with a number polled from the lower half
					upperHalf.offer(lowerHalf.poll());
					lowerHalf.offer(num);
				} else {
					// the number to be added to the upper half is within range
					// (greater or equal to the first element of the lower half)
					upperHalf.offer(num);
				}
			} else {
				// a number should be addded to the lower half
				if (upperHalf.peek() != null && num > upperHalf.peek()) {
					// the number to be added to the lower half is out of range
					// swap it with a number polled from the upper half
					lowerHalf.offer(upperHalf.poll());
					upperHalf.offer(num);
				} else {
					// the number to be added to the lower half is within range
					// (smaller or equal to the first element of the upper half)
					lowerHalf.offer(num);
				}
			}
		}

		public double findMedian() {
			if (lowerHalf.size() > upperHalf.size()) {
				// number of elements is odd, return first element of the lower half
				return lowerHalf.peek();
			}
			// number of elements is even, return the average of the two median elements
			return (double) (lowerHalf.peek() + upperHalf.peek()) / 2;
		}
	}
}
