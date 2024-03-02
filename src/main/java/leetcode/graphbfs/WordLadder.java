package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList0 = List.of("hot", "dot", "dog", "lot", "log", "cog");
		check(beginWord, endWord, wordList0, 5);
		beginWord = "hit";
		endWord = "cog";
		List<String> bank1 = List.of("hot", "dot", "dog", "lot", "log");
		check(beginWord, endWord, bank1, 0);
	}

	/**
	 * This solution uses a set to keep the eligible words and performs BFS
	 * traversal. It replaces every word with any possible one that differs by a
	 * letter and puts in the queue the new words that also exist in the set. This
	 * is repeated until the end word is reached.Time complexity is O(N^2 * M) where
	 * N is the number of entries in the wordsList and M is the length of the word.
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (wordList.size() == 0) {
			// early exit in case of empty word list i.e. if no valid transformations exist
			return 0;
		}
		int wordSize = beginWord.length();
		// keep the words in a set
		Set<String> words = new HashSet<>();
		for (int i = 0; i < wordList.size(); i++) {
			words.add(wordList.get(i));
		}
		// exit if the endWord is not contained in the words list
		if (!words.contains(endWord)) {
			return 0;
		}
		// is used for BFS traversal
		Queue<String> levelWords = new ArrayDeque<>();
		levelWords.offer(beginWord);
		int ladderCount = 1;
		while (!levelWords.isEmpty()) {
			// increase ladder count for the nth possible word transformation
			ladderCount++;
			// remove all nth transformations and put in the queue all possible n + 1 level
			// transformations
			int levelLength = levelWords.size();
			for (int i = 0; i < levelLength; i++) {
				// remove possible nth level word transformation from the queue
				// convert it to a chars array so that it can be manipulated
				char[] wordChars = levelWords.poll().toCharArray();
				// try to replace every character of this word, one by one,
				// with every character from 'a' to 'z' and put int he queue
				// the new words which exist in the set
				for (int j = 0; j < wordSize; j++) {
					// keep the original char so that it can be restored
					char originalChar = wordChars[j];
					for (char c = 'a'; c <= 'z'; c++) {
						// replace the char at the specific position
						wordChars[j] = c;
						String newWord = String.valueOf(wordChars);
						if (newWord.equals(endWord)) {
							// we have reached the end word, return result
							return ladderCount;
						}
						// if the new word exists in the set remove it (so that it is ignored by later
						// iterations) and put it in the queue
						if (words.remove(newWord)) {
							levelWords.offer(newWord);
						}
					}
					// restore original char at the specific position
					wordChars[j] = originalChar;
				}
			}
		}
		// we are out of word transformations, the end word cannot be reached
		return 0;
	}

	private static void check(String beginWord, String endWord, List<String> wordList, int expected) {
		System.out.println("beginWord is: " + beginWord);
		System.out.println("endWord is: " + endWord);
		System.out.println("bank is: " + wordList);
		System.out.println("expected is: " + expected);
		int ladderLength = ladderLength(beginWord, endWord, wordList); // Calls your implementation
		System.out.println("ladderLength is: " + ladderLength);
	}
}
