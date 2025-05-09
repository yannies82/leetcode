package leetcode.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LetterTilePossibilities {

	public static void main(String[] args) {
		check("AAB", 8);
		check("AAABBC", 188);
		check("V", 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/letter-tile-possibilities.
	 * This solution uses backtracking to test all possible valid permutations. Time
	 * complexity is O(n^n) where n is the length of the tiles string.
	 * 
	 * @param tiles
	 * @return
	 */
	public static int numTilePossibilities(String tiles) {
		// convert tiles String to an array of chars and sort the array, so that it's
		// easy to detect duplicates
		char[] tilesChars = tiles.toCharArray();
		Arrays.sort(tilesChars);
		// keeps track of used tile chars
		boolean[] usedTilesChars = new boolean[tilesChars.length];
		// result is the count of all permutations minus 1 for the empty string
		return backTrack(tilesChars, usedTilesChars) - 1;
	}

	public static int backTrack(char[] tilesChars, boolean usedTilesChars[]) {
		int result = 1;
		for (int i = 0; i < tilesChars.length; i++) {
			if (usedTilesChars[i] || (i > 0 && tilesChars[i] == tilesChars[i - 1] && !usedTilesChars[i - 1])) {
				// skip if current tile has been used or if it is the same as the previous one
				// and the previous one is not being used (it means that its loop has ended and
				// all possible permutations starting from this tile will end in duplicates)
				continue;
			}
			usedTilesChars[i] = true;
			result += backTrack(tilesChars, usedTilesChars);
			usedTilesChars[i] = false;
		}
		return result;
	}

	/**
	 * Alternative solution which also uses backtracking and actually builds the
	 * strings. Time complexity is O(n^n) where n is the length of the tiles string.
	 * 
	 * @param tiles
	 * @return
	 */
	public static int numTilePossibilities2(String tiles) {
		int[] frequency = new int[26];
		int length = tiles.length();
		for (int i = 0; i < length; i++) {
			frequency[tiles.charAt(i) - 'A']++;
		}
		int[] usedFrequency = new int[26];
		Set<String> distinctTiles = new HashSet<>();
		StringBuilder builder = new StringBuilder();
		return backTrack(frequency, usedFrequency, distinctTiles, builder, 0);
	}

	private static int backTrack(int[] frequency, int[] usedFrequency, Set<String> distinctTiles, StringBuilder builder,
			int i) {
		int result = 0;
		if (i > 0) {
			result += distinctTiles.add(builder.toString()) ? 1 : 0;
		}
		for (int j = 0; j < frequency.length; j++) {
			if (frequency[j] > 0 && usedFrequency[j] < frequency[j]) {
				usedFrequency[j]++;
				builder.append((char) ('A' + j));
				result += backTrack(frequency, usedFrequency, distinctTiles, builder, i + 1);
				usedFrequency[j]--;
				builder.deleteCharAt(builder.length() - 1);
			}
		}
		return result;
	}

	private static void check(String tiles, int expected) {
		System.out.println("tiles is: " + tiles);
		System.out.println("expected is: " + expected);
		int numTilePossibilities = numTilePossibilities(tiles); // Calls your implementation
		System.out.println("numTilePossibilities is: " + numTilePossibilities);
	}
}
