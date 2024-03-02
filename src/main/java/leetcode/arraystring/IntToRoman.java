package leetcode.arraystring;

public class IntToRoman {

	public static void main(String[] args) {
		check(3, "III");
		check(58, "LVIII");
		check(1994, "MCMXCIV");
	}

	public static String intToRoman(int num) {
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
