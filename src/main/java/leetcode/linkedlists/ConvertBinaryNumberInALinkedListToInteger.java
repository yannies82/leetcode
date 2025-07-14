package leetcode.linkedlists;

public class ConvertBinaryNumberInALinkedListToInteger {

    public static void main(String[] args) {
        check(ListNode.createList(1, 0, 1), 5);
        check(ListNode.createList(0), 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer.
     * Time complexity is O(n) where n is the length of the linked list.
     *
     * @param head
     * @return
     */
    public static int getDecimalValue(ListNode head) {
        int[] result = {0, 0, 0};
        calculate(head, result);
        return result[0];
    }

    private static void calculate(ListNode node, int[] result) {
        if (node == null) {
            result[2] = result[1];
            return;
        }
        result[1]++;
        calculate(node.next, result);
        result[0] += node.val << (result[2] - result[1]);
        result[1]--;
    }

    private static void check(ListNode head, int expected) {
        System.out.println("head is: " + head.printAll());
        System.out.println("expected is: " + expected);
        int getDecimalValue = getDecimalValue(head);
        System.out.println("getDecimalValue is: " + getDecimalValue);
    }

}
