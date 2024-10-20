package leetcode.stack;

public class ParsingABooleanExpression {

	public static void main(String[] args) {
		check("&(|(f))", false);
		check("|(f,f,f,t)", true);
		check("!(&(f,t))", true);
		check("|(f,!(&(t,!(f))))", false);
		check("&(|(f,f,!(t),f),|(!(f),f))", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/parsing-a-boolean-expression.
	 * This solution parses the expression character by character and keeps a stack
	 * with operations, along with their initial values. Time complexity is O(n)
	 * where n is the length of the expression string.
	 * 
	 * @param expression
	 * @return
	 */
	public static boolean parseBoolExpr(String expression) {
		int length = expression.length();
		char[] chars = expression.toCharArray();
		// this is the head of the stack which keeps the nested operations and their
		// results
		Element head = null;
		// keeps the result of the current nested level
		boolean current = true;
		// traverse all chars of the string
		for (int i = 0; i < length; i++) {
			switch (chars[i]) {
			case '!', '&' -> {
				// add a new level to the stack with initial value true
				head = new Element(true, chars[i], head);
				i++;
			}
			case '|' -> {
				// add a new level to the stack with initial value false
				head = new Element(false, chars[i], head);
				i++;
			}
			case ',' -> {
				// apply the current value to the head value using the level operation
				if (head.operation == '&') {
					head.value &= current;
				} else {
					head.value |= current;
				}
			}
			case ')' -> {
				// get the final value of the level according to the operation
				// remove the level and set the value as current
				if (head.operation == '&') {
					current = head.value & current;
				} else if (head.operation == '|') {
					current = head.value | current;
				} else {
					current = !current;
				}
				head = head.next;
			}
			case 'f' -> current = false;
			case 't' -> current = true;
			}
		}
		return current;
	}

	/**
	 * Similar solution to the previous one but uses short circuit logic for & and |
	 * operations with many operands. Time complexity is O(n) where n is the length
	 * of the expression string.
	 * 
	 * @param expression
	 * @return
	 */
	public static boolean parseBoolExpr2(String expression) {
		int length = expression.length();
		char[] chars = expression.toCharArray();
		// this is the head of the stack which keeps the nested operations and their
		// results
		Element head = null;
		// keeps the result of the current nested level
		boolean current = true;
		// traverse all chars of the string
		for (int i = 0; i < length; i++) {
			switch (chars[i]) {
			case '!', '&' -> {
				// add a new level to the stack with initial value true
				head = new Element(true, chars[i], head);
				i++;
			}
			case '|' -> {
				// add a new level to the stack with initial value false
				head = new Element(false, chars[i], head);
				i++;
			}
			case ',' -> {
				// if the operation is '&' and the current value is false then there is no need
				// to calculate the rest of the values of this level
				// same for the case of operation '|' and value true
				// skip all characters up to the closing parenthesis of this level
				boolean shortCircuit = (head.operation == '&') ^ current;
				if (shortCircuit) {
					int openCount = 0;
					while (chars[++i] != ')' || openCount > 0) {
						if (chars[i] == '(') {
							openCount++;
						} else if (chars[i] == ')') {
							openCount--;
						}
					}
					i--;
				}
			}
			case ')' -> {
				// get the final value of the level according to the operation
				// remove the level and set the value as current
				if (head.operation == '&') {
					current = head.value & current;
				} else if (head.operation == '|') {
					current = head.value | current;
				} else {
					current = !current;
				}
				head = head.next;
			}
			case 'f' -> current = false;
			case 't' -> current = true;
			}
		}
		return current;
	}

	private static class Element {
		boolean value;
		char operation;
		Element next;

		public Element(boolean value, char operation, Element next) {
			super();
			this.value = value;
			this.operation = operation;
			this.next = next;
		}
	}

	private static void check(String expression, boolean expected) {
		System.out.println("expression is: " + expression);
		System.out.println("expected is: " + expected);
		boolean parseBoolExpr = parseBoolExpr(expression); // Calls your implementation
		System.out.println("parseBoolExpr is: " + parseBoolExpr);
	}
}
