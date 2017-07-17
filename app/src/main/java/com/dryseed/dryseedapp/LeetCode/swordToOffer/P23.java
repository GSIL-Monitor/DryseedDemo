package com.dryseed.dryseedapp.LeetCode.swordToOffer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by caiminming on 2017/7/17.
 */

public class P23 {
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /**
     * 从上往下打印出二叉树的每个结点，向一层的结点按照从左往右的顺序打印。
     * 例如下的二叉树，
     * 8
     * /    \
     * 6     10
     * /  \   / \
     * 5   7  9  11
     * 则依次打印出8、6、10、5、7 、9、11.
     *
     * @param root 树的结点
     */
    public static void printFromToBottom(BinaryTreeNode root) {
        if(root == null) return;

        Queue<BinaryTreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            BinaryTreeNode node = nodes.remove();
            System.out.print(node.value + " ");

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    public static void main(String[] args) {

        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 8;
        root.left = new BinaryTreeNode();
        root.left.value = 6;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 5;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 10;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 9;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 11;
        printFromToBottom(root);

        //         1
        //        /
        //       3
        //      /
        //     5
        //    /
        //   7
        //  /
        // 9
        BinaryTreeNode root2 = new BinaryTreeNode();
        root2.value = 1;
        root2.left = new BinaryTreeNode();
        root2.left.value = 3;
        root2.left.left = new BinaryTreeNode();
        root2.left.left.value = 5;
        root2.left.left.left = new BinaryTreeNode();
        root2.left.left.left.value = 7;
        root2.left.left.left.left = new BinaryTreeNode();
        root2.left.left.left.left.value = 9;
        System.out.println("\n");
        printFromToBottom(root2);

        // 0
        //  \
        //   2
        //    \
        //     4
        //      \
        //       6
        //        \
        //         8
        BinaryTreeNode root3 = new BinaryTreeNode();
        root3.value = 0;
        root3.right = new BinaryTreeNode();
        root3.right.value = 2;
        root3.right.right = new BinaryTreeNode();
        root3.right.right.value = 4;
        root3.right.right.right = new BinaryTreeNode();
        root3.right.right.right.value = 6;
        root3.right.right.right.right = new BinaryTreeNode();
        root3.right.right.right.right.value = 8;
        System.out.println("\n");
        printFromToBottom(root3);

        // 1
        BinaryTreeNode root4 = new BinaryTreeNode();
        root4.value = 1;
        System.out.println("\n");
        printFromToBottom(root4);

        // null
        System.out.println("\n");
        printFromToBottom(null);
    }
}
