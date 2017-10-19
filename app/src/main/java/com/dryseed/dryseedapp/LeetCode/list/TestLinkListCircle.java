package com.dryseed.dryseedapp.LeetCode.list;

/**
 * Created by caiminming on 2017/10/19.
 * <p>
 * http://blog.csdn.net/lavor_zl/article/details/42784247
 * 另外可参考：http://blog.jobbole.com/106227/
 */

public class TestLinkListCircle {
    static class Node {
        Node next;
        int value;
    }

    public static void main(String[] args) {
        TestLinkListCircle linkListCircle = new TestLinkListCircle();
        Node headNode = new Node();
        headNode.value = 0;
        linkListCircle.createLinkList(headNode);
        System.out.println(headNode.next.next.next.next.value);
        System.out.println("searchEntranceNode : " + TestLinkListCircle.searchEntranceNode(headNode).value);
        System.out.println("circleLength : " + TestLinkListCircle.circleLength(headNode));
    }

    // 0 1 2 3 1
    public void createLinkList(Node headNode) {
        Node lastNode = headNode;
        for (int i = 1; i < 4; i++) {
            Node node = new Node();
            node.value = i;
            lastNode.next = node;
            lastNode = node;
        }
        lastNode.next = headNode.next;
    }

    //当单链表中没有环时返回null，有环时返回环的入口结点  
    public static Node searchEntranceNode(Node headNode) {
        if (headNode == null) return null;//单链表为空时，单链表没有环
        if (headNode.next == null) return null;//单链表中只有头结点，而且头结点的next为空，单链表没有环
        Node slow = headNode.next;//p表示从头结点开始每次往后走一步的指针
        Node fast = headNode.next.next;//q表示从头结点开始每次往后走两步的指针
        while (fast != null) //q不为空执行while循环
        {
            if (slow == fast) break;//p与q相等，单链表有环
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) return null;

        //这里之所以没有向上面一样，先让p,q走一步再进入循环判断，是因为头结点可能就是环的入口结点  
        fast = headNode;
        while (fast != null) {
            if (slow == fast) return slow;//返回环中入口结点
            slow = slow.next;
            fast = fast.next;
        }
        return null;
    }

    //求单链表环的长度  
    public static int circleLength(Node L) {
        Node p = searchEntranceNode(L);//找到环的入口结点
        if (p == null) return 0;//不存在环时，返回0
        Node fast = p.next;
        int length = 1;
        while (p != fast) {
            length++;
            fast = fast.next;
        }
        return length;//返回环的长度  
    }
}
