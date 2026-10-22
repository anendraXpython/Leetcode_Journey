class Solution {
    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode temp = head;
        
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int middleIndex = count / 2;
        
        temp = head;
        for (int i = 0; i < middleIndex; i++) {
            temp = temp.next;
        }
        
        return temp;
    }
}
