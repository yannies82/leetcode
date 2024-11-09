package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextJustification {

	public static void main(String[] args) {
		check(new String[] { "This", "is", "an", "example", "of", "text", "justification." }, 16,
				List.of("This    is    an", "example  of text", "justification.  "));
		check(new String[] { "What", "must", "be", "acknowledgment", "shall", "be" }, 16,
				List.of("What   must   be", "acknowledgment  ", "shall be        "));
		check(new String[] { "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a",
				"computer.", "Art", "is", "everything", "else", "we", "do" }, 20,
				List.of("Science  is  what we", "understand      well", "enough to explain to", "a  computer.  Art is",
						"everything  else  we", "do                  "));
		check(new String[] { "ask", "not", "what", "your", "country", "can", "do", "for", "you", "ask", "what", "you",
				"can", "do", "for", "your", "country" }, 16,
				List.of("ask   not   what", "your country can", "do  for  you ask", "what  you can do",
						"for your country"));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/text-justification. This
	 * solution traverses all words and builds a new row when maxWidth is reached,
	 * taking spaces into consideration. Time complexity is O(n^2) where n is the
	 * length of the words array.
	 * 
	 * @param words
	 * @param maxWidth
	 * @return
	 */
	public static List<String> fullJustify(String[] words, int maxWidth) {
		int wordsCount = words.length;
		int lastWordIndex = wordsCount - 1;
		int currentWidth = 0;
		List<String> result = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		int startIndex = 0;
		// traverse all words
		for (int i = 0; i < lastWordIndex; i++) {
			currentWidth += words[i].length();
			int nextIndex = i + 1;
			if (currentWidth + words[nextIndex].length() + nextIndex - startIndex > maxWidth) {
				// adding the next word will exceed the maxWidth for this line
				// (taking into consideration one space between words as minimum)
				// therefore add a new line to the result now
				int numOfWords = nextIndex - startIndex;
				int numOfSpaces = maxWidth - currentWidth;
				int spacesPerWord = numOfWords == 1 ? 0 : numOfSpaces / (numOfWords - 1);
				int leftoverSpaces = numOfWords == 1 ? 0 : numOfSpaces % (numOfWords - 1);
				int extraSpaceLimitIndex = startIndex + leftoverSpaces;
				for (int j = startIndex; j < i; j++) {
					// append all words and spaces between
					builder.append(words[j]);
					// count an extra space for the first words until leftoverSpaces are exhausted
					int totalSpaces = spacesPerWord + (((j - extraSpaceLimitIndex) >>> 31) & 1);
					for (int k = 0; k < totalSpaces; k++) {
						builder.append(" ");
					}
				}
				// append last word of the line
				builder.append(words[i]);
				// fill with spaces on the right, if needed
				for (int k = builder.length(); k < maxWidth; k++) {
					builder.append(" ");
				}
				result.add(builder.toString());
				builder.setLength(0);
				currentWidth = 0;
				startIndex = nextIndex;
			}
		}
		// this is the last line, just add a single space between words
		// and pad with spaces on the right
		for (int j = startIndex; j < lastWordIndex; j++) {
			// append all words and spaces between
			builder.append(words[j]).append(" ");
		}
		// append last word of the line
		builder.append(words[lastWordIndex]);
		// fill with spaces on the right, if needed
		for (int k = builder.length(); k < maxWidth; k++) {
			builder.append(" ");
		}
		result.add(builder.toString());
		return result;
	}

	private static void check(String[] words, int maxWidth, List<String> expectedResult) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expectedResult is: " + expectedResult);
		List<String> fullJustify = fullJustify(words, maxWidth); // Calls your implementation
		System.out.println("fullJustify is: " + fullJustify);
	}
}
