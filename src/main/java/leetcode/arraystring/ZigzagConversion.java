package leetcode.arraystring;

public class ZigzagConversion {

	public static void main(String[] args) {
		check("AB", 1, "AB");
		check("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
		check("PAYPALISHIRING", 4, "PINALSIGYAHRPI");
	}

	public static String convert(String s, int numRows) {
		int length = s.length();
		int offset = numRows == 1 ? 1 : 2 * numRows - 2;
		int currentRow = 0;
		int currentIndex = 0;
		StringBuilder result = new StringBuilder();
		while (result.length() < length) {
			result.append(s.charAt(currentIndex));
			if (currentRow > 0 && currentRow < numRows - 1) {
				int zigIndex = currentIndex + offset - 2 * currentRow;
				if (zigIndex < length) {
					result.append(s.charAt(zigIndex));
				}
			}
			currentIndex += offset;
			if (currentIndex >= length) {
				currentRow++;
				currentIndex = currentRow;
			}
		}
		return result.toString();
	}

	private static void check(String s, int numRows, String expectedZigZag) {
		System.out.println("s is: " + s);
		System.out.println("expectedZigZag is: " + expectedZigZag);
		String zigZag = convert(s, numRows); // Calls your implementation
		System.out.println("zigZag is: " + zigZag);
	}
}
