package leetcode.string;

public class SentenceSimilarity3 {

	public static void main(String[] args) {
		check("My name is Haley", "My Haley", true);
		check("of", "A lot of words", false);
		check("Eating right now", "Eating", true);
		check("Luky", "Lucccky", false);
		check("C", "CB B C", true);
		check("B", "Best practice", false);
		check("Barry", "Better yourself to carry", false);
		check("Aa AAaAaaAaaaAaAAaAA aaaAaAaAAaAaaAA", "Aa AA", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sentence-similarity-iii. This
	 * solution counts the similar characters at the start and end of the two
	 * sentences. Time complexity is O(n) where n is the length of the smaller
	 * sentence.
	 * 
	 * @param sentence1
	 * @param sentence2
	 * @return
	 */
	public static boolean areSentencesSimilar(String sentence1, String sentence2) {
		int s1Length = sentence1.length();
		int s1LastIndex = s1Length - 1;
		int s2Length = sentence2.length();
		int s2LastIndex = s2Length - 1;
		int limit = Math.min(s1Length, s2Length);
		int indexLeft = 0;
		// find how many characters at the beginning of the two strings are the same
		while (indexLeft < limit && sentence1.charAt(indexLeft) == sentence2.charAt(indexLeft)) {
			indexLeft++;
		}
		int indexRight = 0;
		// find how many characters at the end of the two strings are the same
		while (indexRight < limit
				&& sentence1.charAt(s1LastIndex - indexRight) == sentence2.charAt(s2LastIndex - indexRight)) {
			indexRight++;
		}
		String larger = s1Length > s2Length ? sentence1 : sentence2;
		int largerLength = larger.length();
		// the sentences are similar if they are the same or if one of the three
		// conditions are valid:
		// 1) the larger sentence starts with the full smaller sentence and its next
		// character is a space
		// 2) the larger sentence ends with the full smaller sentence and its previous
		// character is a space
		// 3) the larger sentence starts with a part of the smaller sentence and ends
		// with the rest of the smaller sentence, separated by a space
		return (indexLeft == limit && (largerLength == limit || larger.charAt(limit) == ' '))
				|| (indexRight == limit && (largerLength == limit || larger.charAt(largerLength - limit - 1) == ' '))
				|| (indexLeft < limit && indexRight < limit && indexLeft + indexRight - 1 >= limit);
	}

	private static void check(String sentence1, String sentence2, boolean expected) {
		System.out.println("sentence1 is: " + sentence1);
		System.out.println("sentence2 is: " + sentence2);
		System.out.println("expected is: " + expected);
		boolean areSentencesSimilar = areSentencesSimilar(sentence1, sentence2); // Calls your implementation
		System.out.println("areSentencesSimilar is: " + areSentencesSimilar);
	}
}
