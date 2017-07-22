package com.dryseed.dryseedapp.LeetCode.swordToOffer;

/**
 * Created by User on 2017/7/15.
 */
public class P16 {
    /**
     * P113
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     * 【书本上的方法，不使用逻辑头结点】
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode reverseHead = null;
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = null;

        while (curNode != null){
            nextNode = curNode.next;

            if(nextNode == null){
                reverseHead = curNode;
            }

            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }

        return reverseHead;
    }

    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     * 输出链表的元素值
     *
     * @param head 链表的头结点
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 1;

        head.next = new ListNode();
        head.next.value = 2;

        head.next.next = new ListNode();
        head.next.next.value = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        printList(head);
        head = reverseList2(head);
        printList(head);

    }

}



