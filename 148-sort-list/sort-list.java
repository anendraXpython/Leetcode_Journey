class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null || head.next == null) return head;
        ListNode temp = head;
        int max = head.val;
        int min = head.val;
        int size2=0;
        while(temp!=null)
        {
            if(temp.val>max) max = temp.val;
            else if(temp.val<min) min=temp.val;
            temp=temp.next;
            size2++;
        }
        int size = max-min+1;
        int count[] = new int[size];
        temp = head;
        while(temp!=null) {
            count[temp.val - min]++;
            temp = temp.next;
        }
        for(int i=1;i<size;i++) count[i] += count[i-1];
        int output[] = new int[size2];
        temp = head;
        while(temp!=null) {
            output[--count[temp.val - min]] = temp.val;
            temp = temp.next;
        }

        temp = head;
        int index =0;
        while(temp!=null)
        {
            temp.val = output[index++];
            temp=temp.next;
        }
        return head;
    }
}