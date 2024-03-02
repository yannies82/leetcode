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

	public static String simplifyPath(String path) {
		int length = path.length();
		Deque<String> pathParts = new ArrayDeque<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char ch = path.charAt(i);
			if (ch != '/') {
				builder.append(ch);
			}
			if (!builder.isEmpty() && (ch == '/' || i == length - 1)) {
				String part = builder.toString();
				if (part.equals("..")) {
					pathParts.poll();
				} else if (!part.equals(".")) {
					pathParts.offerFirst(part);
				}
				builder.setLength(0);
			}
		}
		String part;
		while ((part = pathParts.pollLast()) != null) {
			builder.append("/").append(part);
		}
		if (builder.isEmpty()) {
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
