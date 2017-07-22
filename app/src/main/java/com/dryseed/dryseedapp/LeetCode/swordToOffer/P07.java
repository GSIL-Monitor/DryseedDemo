package com.dryseed.dryseedapp.LeetCode.swordToOffer;

import java.util.Stack;

/**
 * Created by User on 2017/7/15.
 */
public class P07 {
    /**
     * 用两个栈模拟的队列
     * 用两个核实现一个队列。队列的声明如下，诸实现它的两个函数appendTail和deleteHead，
     * 分别完成在队列尾部插入结点和在队列头部删除结点的功能。
     */

    public static void main(String args[]){
        MList<String> mList = new MList<>();
        mList.appendTail("1");
        mList.appendTail("2");
        mList.appendTail("3");
        System.out.println(mList.deleteHead());
        System.out.println(mList.deleteHead());
    }

    public static class MList<T> {
        // 插入栈，只用于插入的数据
        private Stack<T> stack1 = new Stack<>();
        // 弹出栈，只用于弹出数据
        private Stack<T> stack2 = new Stack<>();

        public MList() {
        }

        // 添加操作，成在队列尾部插入结点
        public void appendTail(T t) {
            if (null != stack1) {
                stack1.push(t);
            }
        }

        // 删除操作，在队列头部删除结点
        public T deleteHead() {
            if(null == stack1 || null == stack2){
                throw new RuntimeException("error");
            }

            if(null != stack2 && stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }

            if(stack2.isEmpty()){
               throw new RuntimeException("error");
            }

            return stack2.pop();
        }
    }
}
