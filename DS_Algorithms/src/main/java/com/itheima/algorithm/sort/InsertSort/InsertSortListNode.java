package com.itheima.algorithm.sort.InsertSort;
/**
 * 插入排序(链表)
 */
public class InsertSortListNode {


    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        System.out.println("hello world");
        ListNode head= new ListNode(2);
        head.next= new ListNode(5);
        head.next.next= new ListNode(3);
        sort(head);
        print(head);
    }
    private static void print(ListNode head){
        while(head!=null){
            System.out.print(head.val + " ");
            head=head.next;
        }

    }
    public static ListNode sort(ListNode head){
        ListNode cur = head.next;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head.next = null;
        while(cur!=null){
            ListNode nodeToInsert = cur;
            cur = cur.next;
            nodeToInsert.next = null;
            charu(dummy,nodeToInsert);

        }
        return dummy.next;
    }
    public static ListNode charu(ListNode dummy,ListNode yaopaixu){
        ListNode cur = dummy;
        while(cur.next!=null&&cur.next.val<yaopaixu.val){
            cur = cur.next;
        }
        yaopaixu.next = cur.next;
        cur.next = yaopaixu;
        return dummy;
    }
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
}
