package com.dryseed.dryseedapp.LeetCode.swordToOffer;

/**
 * Created by User on 2017/7/15.
 */
public class P09 {
    /**
     * 写一个函数，输入n，求斐波那契（Fibonacci) 数列的第n项
     *
     * @param n Fibonacci数的项数
     * @return 第n项的结果
     */
    public static long fibonacci(int n) {
        if (n < 0 || n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int fibMinusTwo = 0;
        int fibMinusOne = 1;
        int fib = 0;

        for (int i = 2; i <= n; i++) {
            fib = fibMinusOne + fibMinusTwo;
            fibMinusTwo = fibMinusOne;
            fibMinusOne = fib;
        }

        return fib;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(40));
    }
}
