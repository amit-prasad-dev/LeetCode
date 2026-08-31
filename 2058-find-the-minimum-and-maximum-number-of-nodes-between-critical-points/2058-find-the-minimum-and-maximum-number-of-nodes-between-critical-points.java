/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode curr = head;
        int index = 0;
        int first = -1, 
            prev = -1,
            last = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr != null &&
                curr.next != null && 
                curr.next.next != null) {

            // Check whether curr is a critical point
            if ((curr.val < curr.next.val && curr.next.val > curr.next.next.val) || 
                (curr.val > curr.next.val && curr.next.val < curr.next.next.val)) {
                
                int currentIndex = index + 1;

                // First Critical point
                if (first == -1) {
                    first = currentIndex;
                }
                // Distance from previous critical point
                else {
                    minDistance = Math.min(minDistance, currentIndex-prev);
                }
                prev = currentIndex;
                last = currentIndex;
            }
            curr = curr.next;
            index++;
        }
        // Less than two critical point 
        if (first == last) return new int[]{-1, -1};

        return new int[]{minDistance, last - first};
    }
}