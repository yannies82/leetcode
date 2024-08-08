package leetcode.arraystring;

public class IntegerToEnglishWords {

	public static void main(String[] args) {
		check(123, "One Hundred Twenty Three");
		check(12345, "Twelve Thousand Three Hundred Forty Five");
		check(1234567, "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven");
		check(1000000, "One Million");

	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/integer-to-english-words.
	 * This solution uses recursion and takes all corner cases into account to
	 * produce the final result. Time complexity is O(n) where n is the number of
	 * digits in number num.
	 * 
	 * @param num
	 * @return
	 */
	public static String numberToWords(int num) {
		if (num == 0) {
			// early exit in case of 0 input
			return "Zero";
		}
		// initialize the builder to be populated with the result
		StringBuilder builder = new StringBuilder();
		// populate the builder with the result
		numberToWordsRecursive(num, 0, builder);
		// return result after skipping the first character which is always a space
		return builder.substring(1).toString();
	}

	public static void numberToWordsRecursive(int num, int range, StringBuilder builder) {
		// divide the number by 1000, populate the builder with the string of the more
		// important digits first
		int div = num / 1000;
		if (div > 0) {
			numberToWordsRecursive(div, range + 1, builder);
		}
		// populate the builder with the string of the less important digits
		int mod = num % 1000;
		if (mod > 0) {
			threeDigitNumToWord(num % 1000, builder);
			builder.append(rangeToWord(range));
		}
	}

	private static void threeDigitNumToWord(int num, StringBuilder builder) {
		int div = num / 100;
		if (div > 0) {
			// populate with hundreds string
			builder.append(singleDigitNumToWord(div)).append(" Hundred");
		}
		// then populate with the string of the two less important characters
		twoDigitNumToWord(num % 100, builder);
	}

	private static void twoDigitNumToWord(int num, StringBuilder builder) {
		if (num < 10) {
			builder.append(singleDigitNumToWord(num));
		} else if (num < 20) {
			builder.append(tenToNineteenDigitNumToWord(num));
		} else {
			builder.append(tensDigitToWord(num / 10)).append(singleDigitNumToWord(num % 10));
		}
	}

	private static String tensDigitToWord(int num) {
		return switch (num) {
		case 2 -> " Twenty";
		case 3 -> " Thirty";
		case 4 -> " Forty";
		case 5 -> " Fifty";
		case 6 -> " Sixty";
		case 7 -> " Seventy";
		case 8 -> " Eighty";
		case 9 -> " Ninety";
		default -> "";
		};
	}

	private static Object tenToNineteenDigitNumToWord(int num) {
		return switch (num) {
		case 10 -> " Ten";
		case 11 -> " Eleven";
		case 12 -> " Twelve";
		case 13 -> " Thirteen";
		case 14 -> " Fourteen";
		case 15 -> " Fifteen";
		case 16 -> " Sixteen";
		case 17 -> " Seventeen";
		case 18 -> " Eighteen";
		case 19 -> " Nineteen";
		default -> "";
		};
	}

	private static String singleDigitNumToWord(int num) {
		return switch (num) {
		case 1 -> " One";
		case 2 -> " Two";
		case 3 -> " Three";
		case 4 -> " Four";
		case 5 -> " Five";
		case 6 -> " Six";
		case 7 -> " Seven";
		case 8 -> " Eight";
		case 9 -> " Nine";
		default -> "";
		};
	}

	private static String rangeToWord(int range) {
		return switch (range) {
		case 1 -> " Thousand";
		case 2 -> " Million";
		case 3 -> " Billion";
		default -> "";
		};
	}

	private static void check(int num, String expected) {
		System.out.println("num is: " + num);
		System.out.println("expected is: " + expected);
		String numberToWords = numberToWords(num); // Calls your implementation
		System.out.println("numberToWords is: " + numberToWords);
	}
}
