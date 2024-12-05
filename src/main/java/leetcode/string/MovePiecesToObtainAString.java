package leetcode.string;

public class MovePiecesToObtainAString {

	public static void main(String[] args) {
		check("_L__R__R_", "L______RR", true);
		check("R_L_", "__LR", false);
		check("_R", "R_", false);
		check("L_L", "_LL", false);
		check("R_R", "RR_", false);
		check("RR____R__RL__R___R__R__R__LL__RL_R_LR______L_______L_RRRL_R_RR____RL_RL_",
				"R_R___R__RL___R___R___RRLL____RL_RL_RL___________L___RRRL__RR___R_RL_RL_", true);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/move-pieces-to-obtain-a-string. This solution
	 * checks if the 'L', 'R' characters of the strings are in the same order and if
	 * their positions make it possible to get from string start to string target.
	 * Time complexity is O(n) where n is the length of the start string.
	 * 
	 * @param start
	 * @param target
	 * @return
	 */
	public static boolean canChange(String start, String target) {
		char[] sourceChars = start.toCharArray();
		char[] targetChars = target.toCharArray();
		if (sourceChars.length != targetChars.length) {
			return false;
		}
		int indexSource = 0;
		int indexTarget = 0;
		// skip all underscores at the beginning of both strings to find the first
		// char which is either 'L' or 'R'
		while (indexSource < sourceChars.length && sourceChars[indexSource] == '_') {
			indexSource++;
		}
		while (indexTarget < targetChars.length && targetChars[indexTarget] == '_') {
			indexTarget++;
		}
		if (indexSource == sourceChars.length ^ indexTarget == targetChars.length) {
			// one of the two arrays contains only '_' characters while the other does not
			return false;
		} else if (indexSource == sourceChars.length && indexTarget == targetChars.length) {
			// both arrays contain only '_' characters
			return true;
		}
		int firstIndexOfSourceChar = indexSource;
		int firstIndexOfTargetChar = indexTarget;
		if (sourceChars[indexSource] != targetChars[indexTarget]) {
			// first non underscore character is not the same
			return false;
		}
		while (indexSource < sourceChars.length && indexTarget < targetChars.length) {
			char sourceChar = sourceChars[indexSource++];
			char targetChar = targetChars[indexTarget++];
			int sourceCount = 1;
			int targetCount = 1;
			int lastIndexOfSourceChar = firstIndexOfSourceChar;
			// iterate until we reach the opposite char from sourceChar
			while (indexSource < sourceChars.length) {
				if (sourceChars[indexSource] == sourceChar) {
					// update last index of source char and increase count
					lastIndexOfSourceChar = indexSource;
					sourceCount++;
				} else if (sourceChars[indexSource] != '_') {
					// the opposite char from sourceChar was encountered
					break;
				}
				indexSource++;
			}
			int lastIndexOfTargetChar = firstIndexOfTargetChar;
			// iterate until we reach the opposite char from targetChar
			while (indexTarget < targetChars.length
					&& (targetChars[indexTarget] == '_' || targetChars[indexTarget] == targetChar)) {
				if (targetChars[indexTarget] == targetChar) {
					// update last index of target char and increase count
					lastIndexOfTargetChar = indexTarget;
					targetCount++;
				} else if (targetChars[indexTarget] != '_') {
					// the opposite char from targetChar was encountered
					break;
				}
				indexTarget++;
			}
			if (sourceCount != targetCount
					|| (sourceChar == 'L' && (firstIndexOfSourceChar < firstIndexOfTargetChar
							|| lastIndexOfSourceChar < lastIndexOfTargetChar))
					|| (sourceChar == 'R' && (firstIndexOfSourceChar > firstIndexOfTargetChar
							|| lastIndexOfSourceChar > lastIndexOfTargetChar))) {
				// if one of these conditions is true it is not possible to get to the target
				// string by moving pieces of the start string
				return false;
			}
			firstIndexOfSourceChar = indexSource;
			firstIndexOfTargetChar = indexTarget;
		}
		return indexSource == indexTarget;
	}

	private static void check(String start, String target, boolean expected) {
		System.out.println("start is: " + start);
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		boolean canChange = canChange(start, target); // Calls your implementation
		System.out.println("canChange is: " + canChange);
	}
}
