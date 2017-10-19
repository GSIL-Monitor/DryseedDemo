package com.dryseed.dryseedapp.LeetCode.tree;

import java.util.Stack;

/**
 * Created by caiminming on 2017/10/19.
 * <p>
 * 题目：输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶结点所经过的所有的结点形成一条路径。
 * 如下图，输入二叉树和整数22，则打印出两条路径，第一条路径包含结点10，12，第二条路径包含的结点为10，5，7.
 */

public class FindTreePath {
    public static void main(String[] args) {
        /**
         *------13
         *---- /  \
         *--- 65    5
         *---/  \    \
         *--97  25   37
         *-/    /\   /
         *22   4 28 32
         */
        int[] tree = {0, 13, 65, 5, 97, 25, 0, 37, 22, 0, 4, 28, 0, 0, 32, 0};
        BinaryTreeTraversal.TreeNode node = BinaryTreeTraversal.makeBinaryTreeByArray(tree, 1);
        FindTreePath.find(new Stack(), node, 107, 0);
    }

    private static void find(Stack<BinaryTreeTraversal.TreeNode> stack, BinaryTreeTraversal.TreeNode node, int expectedSum, int currentSum) {
        if (null == node) return;
        currentSum += node.value;
        stack.push(node);

        if (node.left == null && node.right == null) {
            //叶子节点
            if (currentSum == expectedSum) {
                StringBuffer sb = new StringBuffer();
                for (BinaryTreeTraversal.TreeNode i : stack) {
                    sb.append(i.value + " ");
                }
                System.out.println(sb.toString());
            }
        } else {
            if (null != node.left) {
                find(stack, node.left, expectedSum, currentSum);
            }
            if (null != node.right) {
                find(stack, node.right, expectedSum, currentSum);
            }
        }

        stack.pop();
        currentSum -= node.value;
    }
}
