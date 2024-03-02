package leetcode.arraystring;

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

	public static List<String> fullJustify(String[] words, int maxWidth) {
		int wordsCount = words.length;
		int currentWidth = 0;
		List<String> result = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		int startIndex = 0;
		for (int i = 0; i < wordsCount; i++) {
			currentWidth += words[i].length();
			if (i == wordsCount - 1 || currentWidth + words[i + 1].length() + i + 1 - startIndex > maxWidth) {
				int numOfWords = i + 1 - startIndex;
				int numOfSpaces = maxWidth - currentWidth;
				int spacesPerWord = numOfWords == 1 ? 0 : numOfSpaces / (numOfWords - 1);
				int leftoverSpaces = numOfWords == 1 ? 0 : numOfSpaces % (numOfWords - 1);
				for (int j = startIndex; j <= i; j++) {
					builder.append(words[j]);
					if (i == wordsCount - 1) {
						if (builder.length() < maxWidth) {
							builder.append(" ");
						}
					} else if (j < i) {
						int totalSpaces = spacesPerWord + (j - startIndex < leftoverSpaces ? 1 : 0);
						for (int k = 0; k < totalSpaces; k++) {
							builder.append(" ");
						}
					}
				}
				for (int k = builder.length(); k < maxWidth; k++) {
					builder.append(" ");
				}
				result.add(builder.toString());
				builder.setLength(0);
				currentWidth = 0;
				startIndex = i + 1;
			}
		}
		return result;
	}

	private static void check(String[] words, int maxWidth, List<String> expectedResult) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expectedResult is: " + expectedResult);
		List<String> fullJustify = fullJustify(words, maxWidth); // Calls your implementation
		System.out.println("fullJustify is: " + fullJustify);
	}
}
