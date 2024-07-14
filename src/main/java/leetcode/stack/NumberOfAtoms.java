package leetcode.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class NumberOfAtoms {

	public static void main(String[] args) {
		check("H2O", "H2O");
		check("Mg(OH)2", "H2MgO2");
		check("K4(ON(SO3)2)2", "K4N2O14S4");
	}

	private static final Comparator<Element> COMPARATOR = (a, b) -> a.name.compareTo(b.name);

	/**
	 * Leetcode problem: https://leetcode.com/problems/number-of-atoms. This
	 * solution uses a stack to keep the nested list of elements for each level and
	 * multiply their occurences with the appropriate multiplier. Time complexity is
	 * O(max(n,mlogm)) where n is the length of the formula string and m is the
	 * number of elements in the formula.
	 * 
	 * @param formula
	 * @return
	 */
	public static String countOfAtoms(String formula) {
		char[] chars = formula.toCharArray();
		int length = chars.length;
		// stack which keeps the list of elements for the current level
		Deque<List<Element>> stack = new ArrayDeque<>();
		// add list for the first level, this will also be the final list
		// containing all elements
		stack.offer(new ArrayList<>());
		// this builder is used for building element names
		StringBuilder nameBuilder = new StringBuilder();
		// keeps the current element
		Element currentElement = null;
		// keeps the current list if parentheses were encountered
		List<Element> currentList = null;
		int i = 0;
		while (i < length) {
			if (chars[i] == '(') {
				// add a new list to the stack to keep the elements of the new level
				stack.offerFirst(new ArrayList<>());
				i++;
			} else if (chars[i] == ')') {
				// remove the last level list from the stack and add its elements
				// to the previous level list
				List<Element> nestedList = stack.poll();
				stack.peek().addAll(nestedList);
				if (i != length - 1 && isNumber(chars[i + 1])) {
					// if a number follows the closing parenthesis keep the nestedList
					// so that we can multiply the count of its elements with the number
					currentList = nestedList;
				}
				i++;
			} else if (isNumber(chars[i])) {
				// calculate the number
				int currentCount = 0;
				while (i < length && isNumber(chars[i])) {
					currentCount = currentCount * 10 + chars[i++] - '0';
				}
				if (currentList != null) {
					// the previous element was a nested list, therefore multiply
					// all of its elements with this number
					for (int j = 0; j < currentList.size(); j++) {
						currentList.get(j).count *= currentCount;
					}
				} else if (currentElement != null) {
					// the previous element was a single element, multiply its count with
					// this number
					currentElement.count *= currentCount;
				}
				// reset previous element values and count
				currentList = null;
				currentElement = null;
				currentCount = 0;
			} else if (isUpperLetter(chars[i])) {
				// a new element has been encountered, build its name
				nameBuilder.append(chars[i++]);
				while (i < length && isLowerLetter(chars[i])) {
					nameBuilder.append(chars[i++]);
				}
				// create the element and add it to the current level list
				currentElement = new Element(nameBuilder.toString());
				stack.peek().add(currentElement);
				// reset the builder so that it can be used again
				nameBuilder.setLength(0);
			}
		}
		// at this point the stack should only contain a single list
		List<Element> finalList = stack.peek();
		// sort it by element name
		finalList.sort(COMPARATOR);
		// build the final output
		StringBuilder resultBuilder = new StringBuilder(finalList.get(0).name);
		String previousName = finalList.get(0).name;
		int totalCount = finalList.get(0).count;
		for (int j = 1; j < finalList.size(); j++) {
			Element current = finalList.get(j);
			if (current.name.equals(previousName)) {
				// if the current list has the same element name as the previous one,
				// just add to the total count
				totalCount += current.count;
			} else {
				// a new element has been encountered
				// append the totalCount of the previous element to the output
				// then append the name of the current element
				if (totalCount > 1) {
					resultBuilder.append(totalCount);
				}
				resultBuilder.append(current.name);
				previousName = current.name;
				totalCount = current.count;
			}
		} // append the totalCount of the last element
		if (totalCount > 1) {
			resultBuilder.append(totalCount);
		}
		return resultBuilder.toString();
	}

	private static boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	private static boolean isUpperLetter(char c) {
		return c >= 'A' && c <= 'Z';
	}

	private static boolean isLowerLetter(char c) {
		return c >= 'a' && c <= 'z';
	}

	private static class Element {
		private String name;
		private int count = 1;

		public Element(String name) {
			super();
			this.name = name;
		}
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String countOfAtoms = countOfAtoms(s); // Calls your implementation
		System.out.println("countOfAtoms is: " + countOfAtoms);
	}
}
