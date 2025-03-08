package leetcode.slidingwindow;

public class MinimumRecordsToGetKConsecutiveBlackBlocks {

	public static void main(String[] args) {
		check("WBBWWBBWBW", 7, 3);
		check("WBWBBBW", 2, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks.
	 * This solution uses a sliding window to calculate the max number of
	 * consecutive black characters in a sequence with length k. Time complexity is
	 * O(n) where n is the length of the blocks string.
	 * 
	 * @param blocks
	 * @param k
	 * @return
	 */
	public static int minimumRecolors(String blocks, int k) {
		char[] blockChars = blocks.toCharArray();
		int blackCount = 0;
		for (int i = 0; i < k; i++) {
			blackCount += 1 - (('B' - blockChars[i]) >>> 31);
		}
		int maxBlackCount = blackCount;
		for (int i = k; i < blockChars.length; i++) {
			blackCount += 1 - (('B' - blockChars[i]) >>> 31);
			blackCount -= 1 - (('B' - blockChars[i - k]) >>> 31);
			maxBlackCount = Math.max(maxBlackCount, blackCount);
		}
		return k - maxBlackCount;
	}

	private static void check(String blocks, int k, int expected) {
		System.out.println("blocks is: " + blocks);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int minimumRecolors = minimumRecolors(blocks, k); // Calls your implementation
		System.out.println("minimumRecolors is: " + minimumRecolors);
	}
}
