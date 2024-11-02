package leetcode.string.conversions;

public class RomanToInt {

	public static void main(String[] args) {
		check("III", 3);
		check("LVIII", 58);
		check("MCMXCIV", 1994);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/roman-to-integer. This
	 * solution iterates all characters of the string, calculates their value and
	 * adds to the total value. For I, X, C it are specially treated, according to
	 * the next character. Time complexity is O(n) where n is the length of string
	 * s.
	 * 
	 * @param s
	 * @return
	 */
	public static int romanToInt(String s) {
		int length = s.length();
		int sum = 0;
		char prev = s.charAt(0);
		for (int i = 1; i < length; i++) {
			// calculate character value and add to the total sum
			// for I, X, C also check the next character
			char current = s.charAt(i);
			sum += calculateValue(prev, current);
			prev = current;
		}
		sum += calculateValue(prev, ' ');
		return sum;
	}

	private static int calculateValue(char prev, char current) {
		return switch (prev) {
		case 'I' -> current != 'V' && current != 'X' ? 1 : -1;
		case 'V' -> 5;
		case 'X' -> current != 'L' && current != 'C' ? 10 : -10;
		case 'L' -> 50;
		case 'C' -> current != 'D' && current != 'M' ? 100 : -100;
		case 'D' -> 500;
		case 'M' -> 1000;
		default -> throw new IllegalArgumentException("Unexpected value: " + prev);
		};
	}

	private static void check(String roman, int expectedNumber) {
		System.out.println("roman is: " + roman);
		System.out.println("expectedNumber is: " + expectedNumber);
		int number = romanToInt(roman); // Calls your implementation
		System.out.println("romanToInt is: " + number);
	}
}
