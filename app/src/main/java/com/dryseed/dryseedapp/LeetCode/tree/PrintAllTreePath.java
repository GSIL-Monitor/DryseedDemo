package com.dryseed.dryseedapp.LeetCode.tree;

/**
 * Created by caiminming on 2017/10/19.
 * <p>
 * 生成树中的从根到叶子节点的所有路径
 */

public class PrintAllTreePath {
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
        //System.out.println(node.left.right.left.value);
        PrintAllTreePath.print(node, new int[tree.length - 1], 0);
    }

    private static void print(BinaryTreeTraversal.TreeNode node, int[] list, int length) {
        list[length++] = node.value;
        if (node.left == null && node.right == null) {
            printPath(list);
        } else {
            if (null != node.left) {
                print(node.left, list, length);
            }
            if (null != node.right) {
                print(node.right, list, length);
            }
        }
    }

    private static void printPath(int[] list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.length; i++) {
            sb.append(list[i] + " ");
        }
        System.out.println(sb.toString());
    }

}
