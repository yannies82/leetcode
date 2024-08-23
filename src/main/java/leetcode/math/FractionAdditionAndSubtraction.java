package leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class FractionAdditionAndSubtraction {

	public static void main(String[] args) {
		check("5/2+2/9-5/6", "17/9");
		check("-5/2+10/3+7/9", "29/18");
		check("-1/2+1/2", "0/1");
		check("-1/2+1/2+1/3", "1/3");
		check("1/3-1/2", "-1/6");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/fraction-addition-and-subtraction. This
	 * solution parses the input expression into fractions, then calculates the
	 * minimum common product of the denominators and then normalizes and adds the
	 * numerators to producr the final result. Time complexity is O(n) where n is
	 * the length of the expression string.
	 * 
	 * @param expression
	 * @return
	 */
	public static String fractionAddition(String expression) {
		char[] chars = expression.toCharArray();
		List<Fraction> fractions = new ArrayList<>();
		int currentSign = 1;
		int currentNumerator = 0;
		int currentDenominator = 1;
		// since the denominators are from 1-10, these are all possible denominator
		// factors
		int[] factors = { 2, 3, 5, 7 };
		// keeps the count of each denominator factor
		int[] factorCount = new int[8];
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
			case ('+') -> {
				currentSign = 1;
				currentNumerator = 0;
			}
			case ('-') -> {
				currentSign = -1;
				currentNumerator = 0;
			}
			case ('/') -> {
				// next 1 or two chars are the denominator
				currentDenominator = chars[++i] - '0';
				if (i < chars.length - 1 && chars[i + 1] == '0') {
					currentDenominator = 10;
					i++;
				}
				// calculate denominator prime factors and update factorCount
				switch (currentDenominator) {
				case 4 -> factorCount[2] = Math.max(2, factorCount[2]);
				case 6 -> {
					factorCount[2] = Math.max(1, factorCount[2]);
					factorCount[3] = Math.max(1, factorCount[3]);
				}
				case 8 -> factorCount[2] = 3;
				case 9 -> factorCount[3] = 2;
				case 10 -> {
					factorCount[2] = Math.max(1, factorCount[2]);
					factorCount[5] = 1;
				}
				default -> factorCount[currentDenominator] = Math.max(1, factorCount[currentDenominator]);
				}
				fractions.add(new Fraction(currentSign, currentNumerator, currentDenominator));
			}
			default -> currentNumerator = currentNumerator * 10 + chars[i] - '0';
			}
		}
		// calculate finalDenominator according to the factorCount array
		int finalDenominator = 1;
		for (int i = 0; i < factors.length; i++) {
			for (int j = 0; j < factorCount[factors[i]]; j++) {
				finalDenominator *= factors[i];
			}
		}
		// normalize and add numerators
		int numeratorSum = 0;
		for (int i = 0; i < fractions.size(); i++) {
			Fraction current = fractions.get(i);
			numeratorSum += current.sign * current.numerator * (finalDenominator / current.denominator);
		}
		// check if the final fraction is reducible and calculate common divisor of
		// numerator and denominator
		int numeratorAbsolute = Math.abs(numeratorSum);
		int commonDivisor = 1;
		for (int i = 0; i < factors.length; i++) {
			while (--factorCount[factors[i]] >= 0 && numeratorAbsolute % factors[i] == 0) {
				numeratorAbsolute /= factors[i];
				commonDivisor *= factors[i];
			}
		}
		// build output result string
		StringBuilder builder = new StringBuilder();
		builder.append(numeratorSum / commonDivisor);
		builder.append('/');
		builder.append(finalDenominator / commonDivisor);
		return builder.toString();
	}

	private static class Fraction {
		int sign;
		int numerator;
		int denominator;

		private Fraction(int sign, int numerator, int denominator) {
			super();
			this.sign = sign;
			this.numerator = numerator;
			this.denominator = denominator;
		}

	}

	private static void check(String expression, String expected) {
		System.out.println("expression is: " + expression);
		System.out.println("expected is: " + expected);
		String fractionAddition = fractionAddition(expression); // Calls your implementation
		System.out.println("fractionAddition is: " + fractionAddition);
	}

}
