package com.dryseed.dryseedapp.LeetCode.list;

/**
 * Created by caiminming on 2017/10/19.
 *
 * 单链表反转
 * http://blog.csdn.net/guyuealian/article/details/51119499
 *
 */

public class ReverseLinkedList {
    static class Node {
        Node next;
        int value;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String args[]) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        print(head);
        Node head2 = reverseLinkedList(head);
        print(head2);
    }

    private static void print(Node head){
        StringBuffer sb = new StringBuffer();
        while (head != null){
            sb.append(head.value + " ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    private static Node reverseLinkedList(Node head) {
        if (null == head) return head;

        Node pre = head;
        Node cur = head.next;
        Node tmp;

        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        head.next = null;
        return pre;
    }


}













