package com.dryseed.dryseedapp.test.stack;

/**
 * Created by caiminming on 2017/6/9.
 * <p>
 * 用java数组实现stack 的pop和push接口 要求：
 * 1.用基本的数组实现
 * 2.考虑范型 : 数组不支持泛型
 * 3.考虑下同步问题
 * 4.考虑扩容问题
 */

public class Stack {

    Object[] data;

    int maxSize;
    //栈顶位置
    int top;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        data = new Object[maxSize];
        top = -1;
    }

    /**
     * 获取堆栈长度
     *
     * @return 堆栈长度
     */
    public int getSize() {
        return maxSize;
    }

    /**
     * 返回栈中元素的个数
     *
     * @return 栈中元素的个数
     */
    public int getElementCount() {
        return top;
    }

    /**
     * 判断栈空
     *
     * @return 栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈满
     *
     * @return 栈满
     */
    public boolean isFull() {
        return top + 1 == maxSize;
    }

    /**
     * 依次加入数据
     *
     * @param data 要加入的数据通信
     * @return 添加是否成功
     */
    public boolean push(Object data) {
        if (isFull()) {
            this.data = arrycopy();
            System.out.println("arrycopy ： " + this.data.length);
        }
        synchronized (this.data) {
            this.data[++top] = data;
        }
        return true;
    }

    /**
     * 从栈中取出数据
     *
     * @return 取出的数据
     */
    public Object pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("栈已空!");
        }
        synchronized (this.data) {
            return this.data[top--];
        }
    }

    /**
     * 返回栈顶元素
     *
     * @return
     */
    public Object peek() {
        return this.data[getElementCount()];
    }

    /**
     * 数组扩容
     *
     * @return
     */
    public Object[] arrycopy() {
        Object[] ii = new Object[maxSize * 2];
        System.arraycopy(data, 0, ii, 0, maxSize);
        return ii;
    }


    public static void main(String[] args) throws Exception {
        Stack stack = new Stack(4);
        stack.push(new String("1"));
        stack.push(new String("2"));
        stack.push(new String("3"));
        stack.push(new String("4"));
        stack.push(new String("5"));
        System.out.println(stack.peek());

        while (stack.top >= 0) {
            System.out.println(stack.pop());
        }
    }
}