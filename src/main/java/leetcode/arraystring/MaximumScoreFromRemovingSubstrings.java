package leetcode.arraystring;

public class MaximumScoreFromRemovingSubstrings {

	public static void main(String[] args) {
		check("cdbcbbaaabab", 4, 5, 19);
		check("aabbaaxybbaabb", 5, 4, 20);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-score-from-removing-substrings. This
	 * solution iterates the string characters and keeps count of the 'a' and 'b'
	 * occurences to create combinations and add to the total points. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param x
	 * @param y
	 * @return
	 */
	public static int maximumGain(String s, int x, int y) {
		char[] chars = s.toCharArray();
		int totalPoints = 0;
		// put 'a', 'b', x, y in the appropriate variables so that they are treated
		// uniformly
		char firstToMatch, secondToMatch;
		int morePoints, lesspoints;
		if (x > y) {
			firstToMatch = 'a';
			secondToMatch = 'b';
			morePoints = x;
			lesspoints = y;
		} else {
			firstToMatch = 'b';
			secondToMatch = 'a';
			morePoints = y;
			lesspoints = x;
		}
		int firstCount = 0;
		int secondCount = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == firstToMatch) {
				// the first character of the combination which gives more points was
				// encountered, add to the respective counter
				firstCount++;
			} else if (chars[i] == secondToMatch) {
				// the second character of the combination which gives more points was
				// encountered
				if (firstCount > 0) {
					// if the previous character was the first character of the combination
					// reduce count and add the morePoints to the total
					firstCount--;
					totalPoints += morePoints;
				} else {
					// otherwise add to the respective counter
					secondCount++;
				}
			} else {
				// another character apart from 'a' and 'b' was encountered
				// add the remaining lessPoints combinations to totalPoints
				// and reset firstCount, secondCount
				totalPoints += Math.min(firstCount, secondCount) * lesspoints;
				firstCount = 0;
				secondCount = 0;
			}
		}
		// add the remaining lessPoints combinations to totalPoints
		totalPoints += Math.min(firstCount, secondCount) * lesspoints;
		return totalPoints;
	}

	/**
	 * This solution performs successive iterations on the string until no more
	 * points can be extracted. Time complexity is O(n^2) where n is the length of
	 * string s.
	 * 
	 * @param s
	 * @param x
	 * @param y
	 * @return
	 */
	public static int maximumGain2(String s, int x, int y) {
		StringBuilder builder = new StringBuilder(s);
		int totalPoints = 0;
		char firstToMatch, secondToMatch;
		int morePoints, lesspoints;
		if (x > y) {
			firstToMatch = 'a';
			secondToMatch = 'b';
			morePoints = x;
			lesspoints = y;
		} else {
			firstToMatch = 'b';
			secondToMatch = 'a';
			morePoints = y;
			lesspoints = x;
		}
		int currentLength;
		do {
			currentLength = builder.length();
			totalPoints += calculatePoints(builder, firstToMatch, secondToMatch, morePoints);
			currentLength = builder.length();
			totalPoints += calculatePoints(builder, secondToMatch, firstToMatch, lesspoints);
		} while (currentLength > builder.length());
		return totalPoints;
	}

	private static int calculatePoints(StringBuilder builder, char firstToMatch, char secondToMatch, int points) {
		int index = 1;
		int totalPoints = 0;
		while (index < builder.length()) {
			char first = builder.charAt(index - 1);
			char second = builder.charAt(index);
			if (first == firstToMatch && second == secondToMatch) {
				totalPoints += points;
				builder.delete(index - 1, index + 1);
				index = Math.max(index - 1, 1);
			} else {
				index++;
			}
		}
		return totalPoints;
	}

	/**
	 * This solution uses backtracking to test all combinations and is quite slow.
	 * Time complexity is O(2^n) where n is the length of string s.
	 * 
	 * @param s
	 * @param x
	 * @param y
	 * @return
	 */
	public static int maximumGain3(String s, int x, int y) {
		StringBuilder builder = new StringBuilder(s);
		return remove(builder, 0, s.length(), x, y);
	}

	private static int remove(StringBuilder builder, int start, int end, int x, int y) {
		if (start >= end - 1) {
			return 0;
		}
		char first = builder.charAt(start);
		char second = builder.charAt(start + 1);
		int noRemovalPoints = remove(builder, start + 1, end, x, y);
		int removalPoints = 0;
		if (first == 'a' && second == 'b') {
			builder.delete(start, start + 2);
			removalPoints = x + remove(builder, Math.max(start - 1, 0), end - 2, x, y);
			builder.insert(start, "ab");
		} else if (first == 'b' && second == 'a') {
			builder.delete(start, start + 2);
			removalPoints = y + remove(builder, Math.max(start - 1, 0), end - 2, x, y);
			builder.insert(start, "ba");
		}
		return Math.max(noRemovalPoints, removalPoints);
	}

	private static void check(String s, int x, int y, int expected) {
		System.out.println("s is: " + s);
		System.out.println("x is: " + x);
		System.out.println("y is: " + y);
		System.out.println("expected is: " + expected);
		int maximumGain = maximumGain(s, x, y); // Calls your implementation
		System.out.println("maximumGain is: " + maximumGain);
	}
}
