package com.dryseed.dryseedapp.LeetCode.swordToOffer;

import java.util.Stack;

/**
 * Created by caiminming on 2017/7/17.
 */

public class P22 {
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。
     * 【按书本上的思路进行求解，两者相差不大】
     * <p>
     * 解题思路：
     * 解决这个问题很直观的想法就是建立一个辅助栈，把输入的第一个序列中的数字依次压入该辅助栈，并按照第二个序列的顺序依次从该栈中弹出数字。
     * 判断一个序列是不是栈的弹出序列的规律：如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。
     * 如果下一个弹出的数字不在栈顶，我们把压栈序列中还没有入栈的数字压入辅助栈，直到把下一个需要弹出的数字压入栈顶为止。
     * 如果所有的数字都压入栈了仍然没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。
     *
     * @param pushInts 入栈序列
     * @param popInts  出栈序列
     * @return true：出栈序列是入栈序列的一个弹出顺序
     */
    public static boolean isPopOrder2(int[] pushInts, int[] popInts) {

        if (pushInts == null || popInts == null || pushInts.length != popInts.length)
            return false;

        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        int popIndex = 0;

        while (popIndex < popInts.length) {
            //如果下一个弹出的数字不在栈顶，我们把压栈序列中还没有入栈的数字压入辅助栈，直到把下一个需要弹出的数字压入栈顶为止。
            while (pushIndex < pushInts.length && (stack.isEmpty() || stack.peek() != popInts[popIndex])) {
                stack.push(pushInts[pushIndex]);
                pushIndex++;
            }

            if (stack.peek() == popInts[popIndex]) {
                //如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。
                stack.pop();
                popIndex++;
            } else {
                //如果所有的数字都压入栈了仍然没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {3, 5, 4, 2, 1};
        int[] pop3 = {4, 3, 5, 1, 2};
        int[] pop4 = {3, 5, 4, 1, 2};
        int[] push5 = {1};
        int[] pop5 = {2};
        int[] push6 = {1};
        int[] pop6 = {1};

        System.out.println("true: " + isPopOrder2(push, pop1));
        System.out.println("true: " + isPopOrder2(push, pop2));
        System.out.println("false: " + isPopOrder2(push, pop3));
        System.out.println("false: " + isPopOrder2(push, pop4));
        System.out.println("false: " + isPopOrder2(push5, pop5));
        System.out.println("true: " + isPopOrder2(push6, pop6));
        System.out.println("false: " + isPopOrder2(null, null));
    }
}
