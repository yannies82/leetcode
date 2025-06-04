package leetcode.string;

public class FindTheLexicographicallyLargestStringFromTheBox1 {

    public static void main(String[] args) {
        check("dbca", 2, "dbc");
        check("gggg", 4, "g");
        check("gh", 1, "gh");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i.
	 * Worst case time complexity is O(n^2) where n is the length of string word.
     *
     * @param word
     * @param numFriends
     * @return
     */
    public static String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int wordLength = word.length();
        char greatestChar = word.charAt(0);
        for (int i = 1; i < wordLength; i++) {
            greatestChar = (char) Math.max(greatestChar, word.charAt(i));
        }
        int length = wordLength - numFriends + 1;
        String result = "0";
        for (int i = 0; i < wordLength; i++) {
            char currentChar = word.charAt(i);
            if (currentChar == greatestChar) {
                String current = word.substring(i, Math.min(wordLength, i + length));
                if (current.compareTo(result) > 0) {
                    result = current;
                }
            }
        }
        return result;
    }

    private static void check(String word, int numFriends, String expected) {
        System.out.println("word is: " + word);
        System.out.println("numFriends is: " + numFriends);
        System.out.println("expected is: " + expected);
        String answerString = answerString(word, numFriends); // Calls your implementation
        System.out.println("answerString is: " + answerString);
    }
}
