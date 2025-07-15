package leetcode.string;

public class ValidWord {

    public static void main(String[] args) {
        check("234Adas", true);
        check("b3", false);
        check("a3$e", false);
    }

    private static final boolean[] VOWELS = initializeVowels();

    private static boolean[] initializeVowels() {
        boolean[] result = new boolean[128];
        result['a'] = true;
        result['e'] = true;
        result['i'] = true;
        result['o'] = true;
        result['u'] = true;
        result['A'] = true;
        result['E'] = true;
        result['I'] = true;
        result['O'] = true;
        result['U'] = true;
        return result;
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/valid-word.
     * Time complexity is O(n) where n is the length of string word.
     *
     * @param word
     * @return
     */
    public static boolean isValid(String word) {
        int wordLength = word.length();
        if (wordLength < 3) {
            return false;
        }
        boolean hasVowel = false;
        boolean hasConsonant = false;
        for (int i = 0; i < wordLength; i++) {
            char current = word.charAt(i);
            if (!isValidChar(current)) {
                return false;
            }
            hasVowel = hasVowel || VOWELS[current];
            hasConsonant = hasConsonant || isConsonant(current);
        }
        return hasVowel && hasConsonant;
    }

    private static boolean isValidChar(char current) {
        return (current >= 'a' && current <= 'z')
                || (current >= 'A' && current <= 'Z')
                || (current >= '0' && current <= '9');
    }

    private static boolean isConsonant(char current) {
        boolean isLowerCaseConsonant = current >= 'a' && current <= 'z' && !VOWELS[current];
        boolean isUpperCaseConsonant = current >= 'A' && current <= 'Z' && !VOWELS[current];
        return isLowerCaseConsonant || isUpperCaseConsonant;
    }

    private static void check(String word, boolean expected) {
        System.out.println("word is: " + word);
        System.out.println("expected is: " + expected);
        boolean isValid = isValid(word); // Calls your implementation
        System.out.println("isValid is: " + isValid);
    }
}
