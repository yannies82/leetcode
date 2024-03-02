package leetcode.arraystring;

public class RomanToInt {

	public static void main(String[] args) {
		check("III", 3);
		check("LVIII", 58);
		check("MCMXCIV", 1994);
	}

	public static int romanToInt(String s) {
		int length = s.length();
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += switch (s.charAt(i)) {
			case 'I' -> (i == length - 1 || (s.charAt(i + 1) != 'V' && s.charAt(i + 1) != 'X')) ? 1 : -1;
			case 'V' -> 5;
			case 'X' -> (i == length - 1 || (s.charAt(i + 1) != 'L' && s.charAt(i + 1) != 'C')) ? 10 : -10;
			case 'L' -> 50;
			case 'C' -> (i == length - 1 || (s.charAt(i + 1) != 'D' && s.charAt(i + 1) != 'M')) ? 100 : -100;
			case 'D' -> 500;
			case 'M' -> 1000;
			default -> throw new IllegalArgumentException("Unexpected value: " + s.charAt(i));
			};
		}
		return sum;
	}

	public static int romanToInt2(String s) {
		int length = s.length();
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += switch (s.charAt(i)) {
			case 'I' -> (i == length - 1 || (s.charAt(i + 1) != 'V' && s.charAt(i + 1) != 'X')) ? 1 : 0;
			case 'V' -> i == 0 || s.charAt(i - 1) != 'I' ? 5 : 4;
			case 'X' -> (i == 0 || s.charAt(i - 1) != 'I')
					? ((i == length - 1 || (s.charAt(i + 1) != 'L' && s.charAt(i + 1) != 'C')) ? 10 : 0)
					: 9;
			case 'L' -> i == 0 || s.charAt(i - 1) != 'X' ? 50 : 40;
			case 'C' -> (i == 0 || s.charAt(i - 1) != 'X')
					? ((i == length - 1 || (s.charAt(i + 1) != 'D' && s.charAt(i + 1) != 'M')) ? 100 : 0)
					: 90;
			case 'D' -> i == 0 || s.charAt(i - 1) != 'C' ? 500 : 400;
			case 'M' -> i == 0 || s.charAt(i - 1) != 'C' ? 1000 : 900;
			default -> throw new IllegalArgumentException("Unexpected value: " + s.charAt(i));
			};
		}
		return sum;
	}

	private static void check(String roman, int expectedNumber) {
		System.out.println("roman is: " + roman);
		System.out.println("expectedNumber is: " + expectedNumber);
		int number = romanToInt(roman); // Calls your implementation
		System.out.println("romanToInt is: " + number);
		number = romanToInt2(roman); // Calls your implementation
		System.out.println("romanToInt2 is: " + number);
	}
}
