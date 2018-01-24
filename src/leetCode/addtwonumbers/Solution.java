package leetCode.addtwonumbers;

/**
 * 
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * 
 * 
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode start = new ListNode(0);
        ListNode head = start;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int tmp = 0;
            if (l1 != null) tmp += l1.val;
            if (l2 != null) tmp += l2.val;
            tmp += carry;
            carry = tmp / 10;
            cur.val = tmp % 10;
            head.next = cur;
            head = cur;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return start.next;
    }
}