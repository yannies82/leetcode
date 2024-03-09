package leetcode.arraystring;

public class IntToRoman {

	public static void main(String[] args) {
		check(3, "III");
		check(58, "LVIII");
		check(1994, "MCMXCIV");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/integer-to-roman. This
	 * solution maps the number digits to the respective combination of roman
	 * letters. Time complexity is O(n) where n is the number of digits of the
	 * number.
	 * 
	 * @param num
	 * @return
	 */
	public static String intToRoman(int num) {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		String unitChar;
		String fiveChar;
		String tenChar;
		do {
			int mod = num % 10;
			switch (index) {
			case 0:
				unitChar = "I";
				fiveChar = "V";
				tenChar = "X";
				break;
			case 1:
				unitChar = "X";
				fiveChar = "L";
				tenChar = "C";
				break;
			case 2:
				unitChar = "C";
				fiveChar = "D";
				tenChar = "M";
				break;
			default:
				unitChar = "M";
				fiveChar = "";
				tenChar = "";
				break;
			}
			switch (mod) {
			case 9:
				builder.insert(0, tenChar).insert(0, unitChar);
				break;
			case 8:
				builder.insert(0, unitChar).insert(0, unitChar).insert(0, unitChar).insert(0, fiveChar);
				break;
			case 7:
				builder.insert(0, unitChar).insert(0, unitChar).insert(0, fiveChar);
				break;
			case 6:
				builder.insert(0, unitChar).insert(0, fiveChar);
				break;
			case 5:
				builder.insert(0, fiveChar);
				break;
			case 4:
				builder.insert(0, fiveChar).insert(0, unitChar);
				break;
			case 3:
				builder.insert(0, unitChar);
			case 2:
				builder.insert(0, unitChar);
			case 1:
				builder.insert(0, unitChar);
				break;
			default:
				break;
			}
			;
			num = num / 10;
			index++;
		} while (num > 0);
		return builder.toString();
	}

	/**
	 * Alternate solution, traverses the digits from greatest order to lesser order.
	 * Time complexity is O(n) where n is the number of digits of the number.
	 * 
	 * @param num
	 * @return
	 */
	public static String intToRoman2(int num) {
		StringBuilder builder = new StringBuilder();
		int factor = (int) Math.pow(10, Integer.toString(num).length() - 1);
		String unitChar;
		String fiveChar;
		String tenChar;
		do {
			int div = num / factor;
			switch (factor) {
			case 1:
				unitChar = "I";
				fiveChar = "V";
				tenChar = "X";
				break;
			case 10:
				unitChar = "X";
				fiveChar = "L";
				tenChar = "C";
				break;
			case 100:
				unitChar = "C";
				fiveChar = "D";
				tenChar = "M";
				break;
			default:
				unitChar = "M";
				fiveChar = "";
				tenChar = "";
				break;
			}
			switch (div) {
			case 9:
				builder.append(unitChar).append(tenChar);
				break;
			case 8:
				builder.append(fiveChar).append(unitChar).append(unitChar).append(unitChar);
				break;
			case 7:
				builder.append(fiveChar).append(unitChar).append(unitChar);
				break;
			case 6:
				builder.append(fiveChar).append(unitChar);
				break;
			case 5:
				builder.append(fiveChar);
				break;
			case 4:
				builder.append(unitChar).append(fiveChar);
				break;
			case 3:
				builder.append(unitChar);
			case 2:
				builder.append(unitChar);
			case 1:
				builder.append(unitChar);
				break;
			default:
				break;
			}
			;
			num = num % factor;
			factor = factor / 10;
		} while (factor > 0);
		return builder.toString();
	}

	private static void check(int num, String expectedRoman) {
		System.out.println("num is: " + num);
		System.out.println("expectedRoman is: " + expectedRoman);
		String roman = intToRoman(num); // Calls your implementation
		System.out.println("intToRoman is: " + roman);
	}
}
