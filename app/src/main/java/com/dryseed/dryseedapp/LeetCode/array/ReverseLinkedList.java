package com.dryseed.dryseedapp.LeetCode.array;

/**
 * Created by caiminming on 2017/7/14.
 *
 * 将单链表进行翻转
 */

public class ReverseLinkedList {

    public static void main(String args[]){
        Node node1 = new Node();
        node1.data = 1;
        Node node2 = new Node();
        node2.data = 2;
        Node node3 = new Node();
        node3.data = 3;

        node1.next = node2;
        node2.next = node3;

        Node headNode = reverse(node1);
        while (headNode != null){
            System.out.println(headNode.data + " ");
            headNode = headNode.next;
        }
    }

    private static Node reverse(Node head) {
        if(null == head || head.next == null){
            return head;
        }
        Node headNode = reverse(head.next);

        // head递归到最后是倒数第二个节点
        head.next.next = head;
        head.next = null;

        return headNode;
    }


}

class Node{
    int data;
    Node next;
}
