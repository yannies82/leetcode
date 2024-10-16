package leetcode.array.frequency;

import java.util.Arrays;

public class HIndex {

	public static void main(String[] args) {
		check(new int[] { 3, 0, 6, 1, 5 }, 3);
		check(new int[] { 1, 3, 1 }, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/h-index. This solution keeps
	 * the count per citations in an array, then traverses the array backwards to
	 * find the position where the citations count is greater than the index. Time
	 * complexity is O(n+m) where n is the length of the citations array and m is
	 * the max number of citations.
	 * 
	 * @param citations
	 * @return
	 */
	public static int hIndex(int[] citations) {
		int length = citations.length;
		short[] frequency = new short[1001];
		short maxCitations = 0;
		for (short i = 0; i < length; i++) {
			short currentCitations = (short) citations[i];
			if (currentCitations > maxCitations) {
				maxCitations = currentCitations;
			}
			frequency[currentCitations]++;
		}
		short paperCount = 0;
		for (short i = maxCitations; i >= 0; i--) {
			paperCount += frequency[i];
			// the researcher has published paperCount papers with at least i citations
			if (paperCount >= i) {
				// since paperCount >= i, by definition hIndex = i
				return i;
			}
		}
		return 0;
	}

	/**
	 * This solution sorts the citations array and then traverses the array
	 * backwards to find the position where the citations count is greater than the
	 * index. Time complexity is O(nlogn) where n is the length of the citations
	 * array.
	 * 
	 * @param citations
	 * @return
	 */
	public static int hIndex2(int[] citations) {
		Arrays.sort(citations);
		int length = citations.length;
		for (int i = length - 1; i >= 0; i--) {
			if (citations[i] < length - i) {
				return length - i - 1;
			}
		}
		return citations.length;
	}

	private static void check(int[] citations, int expectedHIndex) {
		System.out.println("citations is: " + Arrays.toString(citations));
		System.out.println("expectedHIndex is: " + expectedHIndex);
		int hIndex = hIndex(citations); // Calls your implementation
		System.out.println("hIndex is: " + hIndex);
		hIndex = hIndex2(citations); // Calls your implementation
		System.out.println("hIndex2 is: " + hIndex);
	}
}
