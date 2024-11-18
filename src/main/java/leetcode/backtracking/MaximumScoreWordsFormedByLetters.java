package leetcode.backtracking;

import java.util.Arrays;

public class MaximumScoreWordsFormedByLetters {

	public static void main(String[] args) {
		String[] words = new String[] { "cbc", "bca", "cbb", "bbc" };
		char[] letters = new char[] { 'a', 'b', 'c', 'c', 'c' };
		int[] score = new int[] { 8, 2, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		check(words, letters, score, 18);
		words = new String[] { "azb", "ax", "awb", "ayb", "bpppp" };
		letters = new char[] { 'z', 'a', 'w', 'x', 'y', 'b', 'p', 'p', 'p' };
		score = new int[] { 10, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 2, 3, 3 };
		check(words, letters, score, 14);
		words = new String[] { "dog", "cat", "dad", "good" };
		letters = new char[] { 'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o' };
		score = new int[] { 1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		check(words, letters, score, 23);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-score-words-formed-by-letters. This
	 * solution uses recursion and backtracking. It either includes or excludes each
	 * word from the scoring and backtracks by marking and demarking the count of
	 * used letters. Time complexity is O(2^(n*m)) where n is the length of the
	 * words array and m is the length of each word.
	 * 
	 * @param words
	 * @param letters
	 * @param score
	 * @return
	 */
	public static int maxScoreWords(String[] words, char[] letters, int[] score) {
		// create and populate an array which will keep the number of times
		// that each letter can be used
		int[] lettersFrequency = new int[26];
		for (int i = 0; i < letters.length; i++) {
			lettersFrequency[letters[i] - 'a']++;
		}
		// keeps the max score in a mutable object so that it can be updated
		int[] maxScore = new int[1];
		// recursively calculate max words score
		calculateWordsScore(words, lettersFrequency, score, 0, 0, maxScore);
		return maxScore[0];
	}

	private static void calculateWordsScore(String[] words, int[] lettersFrequency, int[] score, int index,
			int currentScore, int[] scores) {
		if (index == words.length) {
			// there are no more words, compare the currentScore to the max score
			// and update if necessary
			scores[0] = Math.max(currentScore, scores[0]);
			return;
		}

		// skip this word and proceed with the score calculation from the next word
		calculateWordsScore(words, lettersFrequency, score, index + 1, currentScore, scores);

		// check if the score for this word can be calculated according to the available
		// letters
		boolean canSelect = true;
		int wordScore = 0;
		// marks the index of the word letter for restoring when backtracking
		int indexToRestore = 0;
		int length = words[index].length();
		// iterate all letters of this word
		for (int i = 0; i < length; i++) {
			int charIndex = words[index].charAt(i) - 'a';
			lettersFrequency[charIndex]--;
			if (lettersFrequency[charIndex] < 0) {
				// this letter is no longer available, this word should be skipped
				// because we cannot calculate the score for it
				canSelect = false;
				indexToRestore = i;
				break;
			}
			wordScore += score[charIndex];
		}
		if (canSelect) {
			// if the score for this word can be calculated, add it to the current score
			// and proceed with the score calculation from the next word
			// otherwise skip this word
			indexToRestore = length - 1;
			calculateWordsScore(words, lettersFrequency, score, index + 1, wordScore + currentScore, scores);
		}
		// after the calculation of the score backtrack by restoring the frequency
		// of the selected letters to their values before this word was selected
		// restore the frequency values for all word characters up to the indexToRestore
		for (int i = 0; i <= indexToRestore; i++) {
			char c = words[index].charAt(i);
			int charIndex = c - 'a';
			lettersFrequency[charIndex]++;
		}
	}

	private static void check(String[] words, char[] letters, int[] score, int expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("letters is: " + Arrays.toString(letters));
		System.out.println("score is: " + Arrays.toString(score));
		System.out.println("expected is: " + expected);
		int maxScoreWords = maxScoreWords(words, letters, score); // Calls your implementation
		System.out.println("maxScoreWords is: " + maxScoreWords);
	}
}
