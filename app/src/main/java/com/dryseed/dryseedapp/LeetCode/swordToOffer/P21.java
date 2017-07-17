package com.dryseed.dryseedapp.LeetCode.swordToOffer;

import java.util.Stack;

/**
 * Created by caiminming on 2017/7/17.
 */

public class P21 {
    /**
     * P133
     * <p>
     * 定义栈的数据结构，请在该类型中实现一个能够得到校的最小元素的min函数。
     * 在该栈中，调用pop、push 及min的时间复杂度都是0(1)
     * <p>
     * 解题思路：
     * 把每次的最小元素（之前的最小元素和新压入战的元素两者的较小值）都保存起来放到另外一个辅助栈里
     * 如果每次都把最小元素压入辅助栈， 那么就能保证辅助栈的栈顶一直都是最小元素．
     * 当最小元素从数据栈内被弹出之后，同时弹出辅助栈的栈顶元素，此时辅助栈的新栈顶元素就是下一个最小值。
     *
     * @param <T> 泛型参数
     */
    public static class StackWithMin<T extends Comparable<T>> {
        // 数据栈，用于存放插入的数据
        private Stack<T> dataStack;
        // 最小数位置栈，存放数据栈中最小的数的位置
        private Stack<T> minStack;

        // 构造函数
        public StackWithMin() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        /**
         * 出栈方法
         *
         * @return 栈顶元素
         */
        public T pop() {
            if (null == minStack || minStack.isEmpty() || null == dataStack || dataStack.isEmpty()) {
                throw new RuntimeException("error");
            }

            minStack.pop();
            return dataStack.pop();
        }

        /**
         * 元素入栈
         *
         * @param t 入栈的元素
         */
        public void push(T t) {
            if (null == minStack || null == dataStack) {
                throw new RuntimeException("error");
            }

            if (dataStack.isEmpty()) {
                minStack.push(t);
            } else {
                if (minStack.peek().compareTo(t) < 0) {
                    minStack.push(minStack.peek());
                } else {
                    minStack.push(t);
                }
            }
            dataStack.push(t);
        }

        /**
         * 获取栈中的最小元素
         *
         * @return 栈中的最小元素
         */
        public T min() {
            if(null == minStack || minStack.isEmpty()){
                throw new RuntimeException("error");
            }

            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        StackWithMin<Integer> stack = new StackWithMin<>();
        stack.push(3);
        System.out.println(stack.min() == 3);
        stack.push(4);
        System.out.println(stack.min() == 3);
        stack.push(2);
        System.out.println(stack.min() == 2);
        stack.push(3);
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 3);
        stack.push(0);
        System.out.println(stack.min() == 0);
    }
}
