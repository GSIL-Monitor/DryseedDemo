package com.dryseed.dryseedapp.LeetCode.tree;

import java.util.ArrayDeque;

/**
 * Created by User on 2017/10/18.
 *
 * 二叉树 广度、深度优先遍历
 */
public class BinaryTreeTraversal {
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
        TreeNode node = BinaryTreeTraversal.makeBinaryTreeByArray(tree, 1);
        System.out.println(node.left.right.left.value);
        BinaryTreeTraversal.depthOrderTraversal(node);
        BinaryTreeTraversal.levelOrderTraversal(node);
    }

    /**
     * 深度优先遍历，相当于先根遍历
     * 采用非递归实现
     * 需要辅助数据结构：栈
     */
    public static void depthOrderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.push(root);
        while (stack.isEmpty() == false) {
            TreeNode node = stack.pop();
            System.out.print(node.value + "  ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.print("\n"); //13    65    97    22    25    4    28    5    37    32
    }

    /**
     * 广度优先遍历
     * 采用非递归实现
     * 需要辅助数据结构：队列
     */
    public static void levelOrderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while (queue.isEmpty() == false) {
            TreeNode node = queue.remove();
            System.out.print(node.value + "  ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.print("\n");//13    65    5    97    25    37    22    4    28    32
    }


    /**
     * 采用递归的方式创建一颗二叉树
     * 传入的是二叉树的数组表示法
     * 构造后是二叉树的二叉链表表示法
     */
    public static TreeNode makeBinaryTreeByArray(int[] array, int index) {
        if (index < array.length) {
            int value = array[index];
            if (value != 0) {
                TreeNode t = new TreeNode(value);
                array[index] = 0;
                t.left = makeBinaryTreeByArray(array, index * 2);
                t.right = makeBinaryTreeByArray(array, index * 2 + 1);
                return t;
            }
        }
        return null;
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }
}
