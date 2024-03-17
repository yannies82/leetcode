package leetcode.hashmap;

public class RansomNote {

	public static void main(String[] args) {
		check("a", "b", false);
		check("aa", "ab", false);
		check("aa", "aab", true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/ransom-note/description. Time
	 * complexity is O(n) where n is the length of magazine.
	 * 
	 * @param ransomNote
	 * @param magazine
	 * @return
	 */
	public static boolean canConstruct(String ransomNote, String magazine) {
		int noteLength = ransomNote.length();
		int magazineLength = magazine.length();
		if (magazineLength < noteLength) {
			// early exit if magazine has less characters than note
			return false;
		}
		// this array keeps the used ransom note chars
		int[] usedChars = new int[26];
		for (int i = 0; i < noteLength; i++) {
			usedChars[ransomNote.charAt(i) - 'a']++;
		}
		int count = 0;
		char next;
		// iterate all magazine chars and keep count of the characters that appear in
		// ransom note
		for (int i = 0; i < magazineLength; i++) {
			next = magazine.charAt(i);
			if (usedChars[next - 'a'] > 0) {
				usedChars[next - 'a']--;
				if (++count == noteLength) {
					// return true if all ransom note characters appear in magazine
					return true;
				}
			}
		}
		return false;
	}

	private static void check(String ransomNote, String magazine, boolean expectedCanConstruct) {
		System.out.println("ransomNote is: " + ransomNote);
		System.out.println("magazine is: " + magazine);
		System.out.println("expectedCanConstruct is: " + expectedCanConstruct);
		boolean canConstruct = canConstruct(ransomNote, magazine); // Calls your implementation
		System.out.println("canConstruct is: " + canConstruct);
	}
}
