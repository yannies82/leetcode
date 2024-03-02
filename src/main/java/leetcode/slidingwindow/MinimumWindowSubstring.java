package leetcode.slidingwindow;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MinimumWindowSubstring {

	public static void main(String[] args) {
		check("ADOBECODEBANC", "ABC", "BANC");
		check("a", "a", "a");
		check("a", "aa", "");
	}

	public static String minWindow(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		if (sLength < tLength)
			return "";
		int[] chars = new int[128];
		int count = 0;
		for (int i = 0; i < tLength; i++) {
			chars[t.charAt(i)]++;
		}
		int minLength = sLength + 1;
		char nextChar;
		int length;
		int start = 0;
		int resultStart = -1;
		int[] tempChars = new int[128];
		for (int i = 0; i < sLength; i++) {
			nextChar = s.charAt(i);
			if (chars[nextChar] > 0) {
				if (++tempChars[nextChar] <= chars[nextChar]) {
					count++;
				}
				if (count == tLength) {
					while (chars[(nextChar = s.charAt(start))] == 0 || tempChars[nextChar] > chars[nextChar]) {
						if (tempChars[nextChar] > chars[nextChar]) {
							tempChars[nextChar]--;
						}
						start++;
					}
					length = i - start + 1;
					if (length < minLength) {
						minLength = length;
						resultStart = start;
					}
				}
			}
		}
		return resultStart == -1 ? "" : s.substring(resultStart, resultStart + minLength);
	}

	public static String minWindow2(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		if (sLength < tLength)
			return "";
		int[] chars = new int[128];
		int count = 0;
		for (int i = 0; i < tLength; i++) {
			chars[t.charAt(i)]++;
		}
		Queue<Integer> positions = new ArrayDeque<>();
		int minLength = sLength + 1;
		char nextChar;
		int length;
		int start;
		int resultStart = -1;
		int[] tempChars = new int[128];
		for (int i = 0; i < sLength; i++) {
			nextChar = s.charAt(i);
			if (chars[nextChar] > 0) {
				positions.add(i);
				if (++tempChars[nextChar] <= chars[nextChar]) {
					count++;
				}
				while (count == tLength) {
					start = positions.remove();
					length = i - start + 1;
					if (length < minLength) {
						minLength = length;
						resultStart = start;
					}
					nextChar = s.charAt(start);
					if (--tempChars[nextChar] < chars[nextChar]) {
						count--;
					}
				}
			}
		}
		return resultStart == -1 ? "" : s.substring(resultStart, resultStart + minLength);
	}

	public static String minWindow3(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		if (sLength < tLength)
			return "";
		int[] chars = new int[128];
		for (int i = 0; i < tLength; i++) {
			chars[t.charAt(i)]++;
		}
		Queue<Integer> positions = new ArrayDeque<>();
		int minLength = sLength + 1;
		char nextChar;
		int length;
		int start = -1;
		int count = 0;
		int[] tempChars = new int[128];
		for (int i = 0; i < sLength; i++) {
			if (chars[s.charAt(i)] > 0) {
				if (tLength == 1) {
					return String.valueOf(s.charAt(i));
				}
				start = i;
				count = 1;
				tempChars[s.charAt(i)]++;
				break;
			}
		}
		if (start == -1) {
			return "";
		}
		int resultStart = -1;
		for (int i = start + 1; i < sLength; i++) {
			nextChar = s.charAt(i);
			if (chars[nextChar] > 0) {
				positions.add(i);
				if (++tempChars[nextChar] <= chars[nextChar]) {
					count++;
				}
				if (count == tLength) {
					while (tempChars[(nextChar = s.charAt(start))] > chars[nextChar]) {
						tempChars[nextChar]--;
						start = positions.remove();
					}
					length = i - start + 1;
					if (length < minLength) {
						minLength = length;
						resultStart = start;
					}
				}
			}
		}
		return resultStart == -1 ? "" : s.substring(resultStart, resultStart + minLength);
	}

	public static String minWindow4(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		if (sLength < tLength)
			return "";
		int[] chars = new int[128];
		int count = 0;
		for (int i = 0; i < tLength; i++) {
			if (++chars[t.charAt(i)] == 1) {
				count++;
			}
		}
		Queue<Integer> positions = new ArrayDeque<>();
		String result = "";
		int minLength = sLength + 1;
		char nextChar;
		int length;
		int start;
		int[] tempChars = new int[128];
		for (int i = 0; i < sLength; i++) {
			nextChar = s.charAt(i);
			if (chars[nextChar] > 0) {
				positions.add(i);
				tempChars[nextChar] = tempChars[nextChar] + 1;
				if (tempChars[nextChar] == chars[nextChar]) {
					count--;
					while (count == 0) {
						start = positions.remove();
						length = i - start + 1;
						if (length < minLength) {
							minLength = length;
							result = s.substring(start, i + 1);
						}
						nextChar = s.charAt(start);
						tempChars[nextChar] = tempChars[nextChar] - 1;
						if (tempChars[nextChar] < chars[nextChar]) {
							count++;
						}
					}
				}
			}
		}
		return result;
	}

	public static String minWindow5(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		if (sLength < tLength)
			return "";
		Map<Character, Integer> charsMap = new HashMap<>();
		for (int i = 0; i < tLength; i++) {
			charsMap.put(t.charAt(i), charsMap.getOrDefault(t.charAt(i), 0) + 1);
		}
		Queue<Integer> positions = new ArrayDeque<>();
		int minLength = sLength + 1;
		int count = charsMap.keySet().size();
		int charCount;
		int length;
		int start;
		int resultStart = -1;
		int resultEnd = -1;
		Map<Character, Integer> tempMap = new HashMap<>();
		for (int i = 0; i < sLength; i++) {
			if (charsMap.containsKey(s.charAt(i))) {
				positions.add(i);
				charCount = tempMap.getOrDefault(s.charAt(i), 0) + 1;
				tempMap.put(s.charAt(i), charCount);
				if (charCount == charsMap.get(s.charAt(i))) {
					count--;
					while (count == 0) {
						start = positions.remove();
						length = i - start + 1;
						if (length < minLength) {
							minLength = length;
							resultStart = start;
							resultEnd = i + 1;
						}
						charCount = tempMap.get(s.charAt(start)) - 1;
						tempMap.put(s.charAt(start), charCount);
						if (charCount < charsMap.get(s.charAt(start))) {
							count++;
						}
					}
				}
			}
		}
		return resultStart == -1 ? "" : s.substring(resultStart, resultEnd);
	}

	private static void check(String s, String t, String expectedResult) {
		System.out.println("s is: " + s);
		System.out.println("t is: " + t);
		System.out.println("expectedResult is: " + expectedResult);
		String result = minWindow(s, t); // Calls your implementation
		System.out.println("positions is: " + result);
	}
}
