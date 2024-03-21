package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {

	public static void main(String[] args) {
		check("/a//b////c/d//././/..", "/a/b/c");
		check("/a/../../b/../c//.//", "/c");
		check("/a/./b/../../c/", "/c");
		check("/home/", "/home");
		check("/../", "/");
		check("/home//foo/", "/home/foo");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/simplify-path. This solution
	 * traverses all characters of the input path and uses a deque as a stack to
	 * push every encountered path part. The path parts are evaluated and parts are
	 * discarded from the stack if two dots are encountered. The final path is built
	 * by traversing the deque as a queue and by appending the parts. Time
	 * complexity is O(n) where n is the length of the string path.
	 * 
	 * @param path
	 * @return
	 */
	public static String simplifyPath(String path) {
		int length = path.length();
		// this stack keeps the path parts as we traverse the path
		Deque<String> pathParts = new ArrayDeque<>();
		// this builder is used for building the current path part
		// and also for building the final path
		StringBuilder builder = new StringBuilder();
		// iterate all characters of the path
		for (int i = 0; i < length; i++) {
			char ch = path.charAt(i);
			if (ch != '/') {
				// if character is not '/' then append it to the current path part
				builder.append(ch);
			}
			if (!builder.isEmpty() && (ch == '/' || i == length - 1)) {
				// if the character is '/' or this is the last character of the path
				// build the current path part to evaluate it
				String part = builder.toString();
				if (part.equals("..")) {
					// two dots mean that the last path part will be discarded from the stack
					pathParts.poll();
				} else if (!part.equals(".")) {
					// one dot means that the part will be ignored, otherwise it will be
					// pushed to the stack of path parts
					pathParts.offerFirst(part);
				}
				// reset the builder to be ready for the next path part
				builder.setLength(0);
			}
		}
		String part;
		// iterate the deque from the other end (as a queue) and append path parts
		// to the final path builder
		while ((part = pathParts.pollLast()) != null) {
			builder.append("/").append(part);
		}
		if (builder.isEmpty()) {
			// do not return empty string, return / if there are no parts
			builder.append("/");
		}
		return builder.toString();
	}

	private static void check(String path, String expectedValidPath) {
		System.out.println("path is: " + path);
		System.out.println("expectedValidPath is: " + expectedValidPath);
		String simplifyPath = simplifyPath(path); // Calls your implementation
		System.out.println("simplifyPath is: " + simplifyPath);
	}
}
